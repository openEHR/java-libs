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
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/TRUNK/libraries/src/java/org/openehr/am/archetype/Archetype.java $"
 * revision:    "$LastChangedRevision: 54 $"
 * last_change: "$LastChangedDate: 2006-08-11 16:37:33 +0200 (Fri, 11 Aug 2006) $"
 */
package org.openehr.am.archetype;

import java.util.*;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.openehr.am.archetype.assertion.Assertion;
import org.openehr.am.archetype.constraintmodel.ArchetypeInternalRef;
import org.openehr.am.archetype.constraintmodel.CAttribute;
import org.openehr.am.archetype.constraintmodel.CComplexObject;
import org.openehr.am.archetype.constraintmodel.CObject;
import org.openehr.am.archetype.ontology.ArchetypeOntology;
import org.openehr.rm.common.resource.AuthoredResource;
import org.openehr.rm.support.identification.ArchetypeID;
import org.openehr.rm.support.identification.ObjectID;

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

// TODO: extends AuthoredResource
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
			String conceptCode, AuthoredResource description,
			CComplexObject definition, ArchetypeOntology ontology, 
			Set<Assertion> invariants) {	
		
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
		this.invariants = invariants;
		this.pathNodeMap = new HashMap<String, CObject>();
		this.pathInputMap = new HashMap<String, String>();
		this.inputPathMap = new HashMap<String, String>();
		loadMaps(definition, true);
		loadInternalRefs(definition, true, null);
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
			AuthoredResource description, CComplexObject definition,
			ArchetypeOntology ontology) {
		
		this(null, id, parentId, conceptCode, description, definition, 
				ontology, null);
	}

	private void loadMaps(CObject node, boolean required) {
		pathNodeMap.put(node.path(), node);

		// TODO
		/*if (node instanceof LeafConstraint) {
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
		}*/

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

		// TODO
	/*	if ((node instanceof LeafConstraint) && refPath != null) {
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
		}*/

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
	 * Root node of this archetype
	 *
	 * @return definition
	 */
	public CComplexObject getDefinition() {
		return definition;
	}
	
	/**
     * Invariant statements about this object. Statements are expressed in
     * first order predicate logic, and usually refer to at least two
     * attributes.
     *
     * @return invariants
     */
    public Set<Assertion> getInvariants() {
        return invariants == null ?
                null : Collections.unmodifiableSet(invariants);
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
	 * Description of this archetype
	 * 
	 * @return the description
	 */
	public AuthoredResource getDescription() {
		return description;
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
	
	private final AuthoredResource description;

	private final CComplexObject definition;

	private final ArchetypeOntology ontology;
	
	private final Set<Assertion> invariants;

	/* calculated fields */
	private final Map<String, CObject> pathNodeMap;

	private final Map<String, String> pathInputMap;

	private final Map<String, String> inputPathMap;
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