/*
 * component:   "openEHR Reference Implementation"
 * description: "Class Archetype"
 * keywords:    "archetype"
 *
 * author:      "Rong Chen <rong@acode.se>"
 * support:     "Acode HB <support@acode.se>"
 * copyright:   "Copyright (c) 2004 Acode HB, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL$"
 * revision:    "$LastChangedRevision$"
 * last_change: "$LastChangedDate$"
 */
package org.openehr.am.archetype;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.log4j.Logger;
import org.openehr.am.archetype.constraintmodel.*;
import org.openehr.rm.common.resource.ArchetypeDescription;
import org.openehr.am.archetype.ontology.ArchetypeOntology;
import org.openehr.rm.common.archetyped.Archetyped;
import org.openehr.rm.support.identification.ArchetypeID;
import org.openehr.rm.support.identification.ObjectID;
import org.openehr.rm.util.RMObjectBuilder;
import org.openehr.rm.util.SystemValue;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Archetype equivalent to ARCHETYPED class in Common reference model. Defines
 * semantics of identfication, lifecycle, versioning, composition and
 * specialisation. Instances of this class are immutable.
 * <p/>
 * Also has logic for construction and validation of reference model instance
 * constrained by this archetype
 *
 * @author Rong Chen
 * @version 1.0
 */
public final class Archetype {

	/**
	 * Constructs an Archetype
	 *
	 * @param adlVersion null if unspecified
	 * @param id
	 * @param parentId
	 * @param concept
	 * @param description
	 * @param definition
	 * @param ontology
	 * @throws IllegalArgumentException if description null or ontology null
	 */
	public Archetype(String adlVersion, String id, String parentId,
			String conceptCode, ArchetypeDescription description,
			CComplexObject definition, ArchetypeOntology ontology) {
		if (id != null && StringUtils.isEmpty(id)) {
			throw new IllegalArgumentException("empty id");
		}
		if (ontology == null) {
			throw new IllegalArgumentException("ontology null");
		}
		if (definition == null) {
			throw new IllegalArgumentException("definition null");
		}
		this.adlVersion = adlVersion;
		this.archetypeId = new ArchetypeID(id);
		this.concept = conceptCode;
		this.parentArchetypeId = (parentId == null ? null : new ArchetypeID(
				parentId));
		this.description = description;
		this.definition = definition;
		this.ontology = ontology;
		this.pathNodeMap = new HashMap<String, CObject>();
		this.pathInputMap = new HashMap<String, String>();
		this.inputPathMap = new HashMap<String, String>();
		this.requiredInput = new HashSet<String>();
		inputCounter = 0;
		loadMaps(definition, true);
		loadInternalRefs(definition, true, null);

		logger.debug("inputPathMap: " + inputPathMap);
		logger.debug("requiredInput: " + requiredInput);
	}

	/**
	 * Constructs an Archetype
	 *
	 * @param id
	 * @param parentId
	 * @param concept
	 * @param description
	 * @param definition
	 * @param ontology
	 * @throws IllegalArgumentException if description null or ontology null
	 */
	public Archetype(String id, String parentId, String conceptCode,
			ArchetypeDescription description, CComplexObject definition,
			ArchetypeOntology ontology) {
		this(null, id, parentId, conceptCode, description, definition, ontology);
	}

	private void loadMaps(CObject node, boolean required) {
		pathNodeMap.put(node.path(), node);

		if (node instanceof LeafConstraint) {
			LeafConstraint leaf = (LeafConstraint) node;
			if (!leaf.hasAssignedValue()) {
				inputCounter++;
				String input = INPUT + inputCounter;
				pathInputMap.put(node.path(), input);
				inputPathMap.put(input, node.path());
				if (required && node.isRequired()) {
					requiredInput.add(node.path());
				}
			}
			return;
		}

		if (!(node instanceof CComplexObject)) {
			return; // other types of cobject
		}
		CComplexObject parent = (CComplexObject) node;

		if (parent.getAttributes() == null) {
			return; // no attribute
		}
		for (CAttribute attribute : parent.getAttributes()) {
			if (attribute.getExistence().equals(
					CAttribute.Existence.NOT_ALLOWED)) {
				continue;
			}
			if (attribute.getChildren() == null) {
				continue; // no child
			}
			for (CObject child : attribute.getChildren()) {
				loadMaps(child, required && node.isRequired()
						&& attribute.isRequired());
			}
		}
	}

	private void loadInternalRefs(CObject node, boolean required, String refPath) {

		if ((node instanceof LeafConstraint) && refPath != null) {
			LeafConstraint leaf = (LeafConstraint) node;
			if (!leaf.hasAssignedValue()) {
				inputCounter++;
				String input = INPUT + inputCounter;
				String path = refPath + ArchetypeInternalRef.USE_NODE
						+ node.path();
				pathInputMap.put(path, input);
				inputPathMap.put(input, path);
				if (required && node.isRequired()) {
					requiredInput.add(path);
				}
			}
			return;
		}

		if (node instanceof ArchetypeInternalRef) {
			ArchetypeInternalRef ref = (ArchetypeInternalRef) node;
			loadInternalRefs(node(ref.getTargetPath()), required
					&& node.isRequired(), ref.path());
		}

		if (!(node instanceof CComplexObject)) {
			return; // other types of cobject
		}
		CComplexObject parent = (CComplexObject) node;

		if (parent.getAttributes() == null) {
			return; // no attribute
		}
		for (CAttribute attribute : parent.getAttributes()) {
			if (attribute.getExistence().equals(
					CAttribute.Existence.NOT_ALLOWED)) {
				continue;
			}
			if (attribute.getChildren() == null) {
				continue; // no child
			}
			for (CObject child : attribute.getChildren()) {
				loadInternalRefs(child, required && node.isRequired()
						&& attribute.isRequired(), refPath);
			}
		}
	}

	/**
	 * ADL version if archteype was read in from an ADL sharable 
	 * archetype.
	 * 
	 * @return null if unspecified
	 */
	public String getAdlVersion() {
		return adlVersion;
	}

	/**
	 * Multi-axial identifier of this archetype in archetype space.
	 *
	 * @return archetypeId
	 */
	public ArchetypeID getArchetypeId() {
		return archetypeId;
	}

	/**
	 * OID identifier of this archetype.
	 *
	 * @return uid
	 */
	public ObjectID getUid() {
		return uid;
	}

	/**
	 * The normative meaning of the archetype as a whole.
	 *
	 * @return concept code
	 */
	public String getConcept() {
		return concept;
	}

	/**
	 * Identifier of the specialisation parent of this archetype.
	 *
	 * @return parent id
	 */
	public ArchetypeID getParentArchetypeId() {
		return parentArchetypeId;
	}

	/**
	 * Description and lifecycle information of the archetype - all archetype
	 * information which is not required at runtime.
	 *
	 * @return description
	 */
	public ArchetypeDescription getDescription() {
		return description;
	}

	/**
	 * Root node of this archetype
	 *
	 * @return definition
	 */
	public CComplexObject getDefinition() {
		return definition;
	}

	/**
	 * Ontology definition of this archetype
	 *
	 * @return ontology
	 */
	public ArchetypeOntology getOntology() {
		return ontology;
	}

	/**
	 * Version of this archetype, extracted from id.
	 *
	 * @return version
	 */
	public String version() {
		return archetypeId.versionID();
	}

	/**
	 * Version of predecessor archetype of this archetype, if any.
	 *
	 * @return previous version
	 */
	public String previousVersion() {
		// todo: how to find the previous version?
		return null;
	}

	/**
	 * Retrun a node at given path
	 *
	 * @param path
	 * @return null if node not found
	 */
	public CObject node(String path) {
		return pathNodeMap.get(path);
	}

	/**
	 * String representation of this object
	 *
	 * @return string form
	 */
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.MULTI_LINE_STYLE);
	}

	/**
	 * Create a reference model instance by given input and constraints
	 * within this archetype
	 *
	 * @param valueMap
	 * @param errorMap
	 * @param systemValues
	 * @return locatable null if creation fails
	 */
	public Object buildRMObject(Map<String, String> valueMap,
			Map<String, ErrorType> errorMap,
			Map<SystemValue, Object> systemValues) {

		return buildRMObject(valueMap, new HashMap<String, Object>(), errorMap,
				systemValues);
	}

	private Object buildRMObject(Map<String, String> valueMap,
			Map<String, Object> objectMap, Map<String, ErrorType> errorMap,
			Map<SystemValue, Object> systemValues) {

		logger.debug("PHASE ONE -> Creating all leaf nodes objects..");

		Set<String> inputPaths = new HashSet<String>();

		for (String input : inputPathMap.keySet()) {
			String path = inputPathMap.get(input);
			String value = valueMap.get(input);
			int index = path.indexOf(ArchetypeInternalRef.USE_NODE);
			String refpath = path;
			if (index > 0) {
				refpath = path.substring(index
						+ ArchetypeInternalRef.USE_NODE.length());
			}
			CObject cobj = pathNodeMap.get(refpath);
			LeafConstraint leafc = (LeafConstraint) cobj;

			// empty strings are skipped
			if (StringUtils.isBlank(value)) {

				logger.debug("empty value of " + input + " skipped..");

				valueMap.remove(inputByPath(path));
				continue;
			}
			inputPaths.add(path);

			Object datavalue = null;
			try {
				datavalue = leafc.createObject(value, systemValues, ontology);
				objectMap.put(path, datavalue);

				logger.debug("object for node " + input
						+ " successfully created..");

			} catch (DVObjectCreationException e) {

				logger.warn("failed to create object for node " + input
						+ " from value: " + value, e);

				errorMap.put(path, e.getErrorType());
			}
		}

		logger.debug("checking if all required leaf nodes has been created..");

		for (String path : requiredInput) {
			if (!valueMap.containsKey(inputByPath(path))) {

				logger.warn("missing value for required leaf node " + path);

				errorMap.put(path, ErrorType.MISSING);
			}
		}

		logger.debug("getting assigned values for leaf nodes..");

		for (String path : pathNodeMap.keySet()) {
			CObject node = pathNodeMap.get(path);
			if (node instanceof LeafConstraint) {
				LeafConstraint leafc = (LeafConstraint) node;
				if (leafc.hasAssignedValue()) {
					objectMap.put(path, leafc.assignedValue(systemValues,
							ontology));
				}
			}
		}

		// only continue if all leaf nodes are constructed successfully
		if (!errorMap.isEmpty()) {

			logger.warn(errorMap.size()
					+ "error(s) occurred during phase one, terminating..");

			return null; // terminate construction
		}

		// create archetypeDetails for the root object
		// todo: hardcoded RM version
		Archetyped archetypeDetails = new Archetyped(archetypeId, null, "1.0");
		RMObjectBuilder builder = new RMObjectBuilder(systemValues);

		logger.debug("PHASE TWO -> Building whole object tree..");

		// build whole object tree
		return definition.createObject(objectMap, inputPaths, errorMap, this,
				builder, archetypeDetails);
	}

	/**
	 * Find input name by node path
	 *
	 * @param path
	 * @return null if path unknown
	 */
	public String inputByPath(String path) {
		return pathInputMap.get(path);
	}

	/**
	 * Find node path by input name
	 *
	 * @param input
	 * @return null if input unknown
	 */
	public String pathByInput(String input) {
		return inputPathMap.get(input);
	}

	/* fields */
	private final String adlVersion;

	private final ArchetypeID archetypeId;

	private final ObjectID uid = null; // todo: not yet from adl

	private final String concept;

	private final ArchetypeID parentArchetypeId;

	private final ArchetypeDescription description;

	private final CComplexObject definition;

	private final ArchetypeOntology ontology;

	private int inputCounter;

	/* calculated fields */
	private final Map<String, CObject> pathNodeMap;

	private final Map<String, String> pathInputMap;

	private final Map<String, String> inputPathMap;

	private final Set<String> requiredInput;

	/**
	 * prefix for all input names
	 */
	public static final String INPUT = "input";

	/* static field */
	private Logger logger = Logger.getLogger(Archetype.class);
}

/*
 *  ***** BEGIN LICENSE BLOCK *****
 *  Version: MPL 1.1/GPL 2.0/LGPL 2.1
 *
 *  The contents of this file are subject to the Mozilla Public License Version
 *  1.1 (the 'License'); you may not use this file except in compliance with
 *  the License. You may obtain a copy of the License at
 *  http://www.mozilla.org/MPL/
 *
 *  Software distributed under the License is distributed on an 'AS IS' basis,
 *  WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
 *  for the specific language governing rights and limitations under the
 *  License.
 *
 *  The Original Code is Archetype.java
 *
 *  The Initial Developer of the Original Code is Rong Chen.
 *  Portions created by the Initial Developer are Copyright (C) 2003-2004
 *  the Initial Developer. All Rights Reserved.
 *
 *  Contributor(s):
 *
 * Software distributed under the License is distributed on an 'AS IS' basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
 * for the specific language governing rights and limitations under the
 * License.
 *
 *  ***** END LICENSE BLOCK *****
 */