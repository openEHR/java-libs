/*
 * component:   "openEHR Reference Implementation"
 * description: "Class Archetype"
 * keywords:    "archetype"
 *
 * author:      "Rong Chen <rong@acode.se>"
 * copyright:   "Copyright (c) 2004 Acode HB, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/TRUNK/libraries/src/java/org/openehr/am/archetype/Archetype.java $"
 * revision:    "$LastChangedRevision: 54 $"
 * last_change: "$LastChangedDate: 2006-08-11 16:37:33 +0200 (Fri, 11 Aug 2006) $"
 */
package org.openehr.am.archetype;

import java.util.*;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.openehr.am.archetype.assertion.Assertion;
import org.openehr.am.archetype.constraintmodel.ArchetypeConstraint;
import org.openehr.am.archetype.constraintmodel.ArchetypeInternalRef;
import org.openehr.am.archetype.constraintmodel.CAttribute;
import org.openehr.am.archetype.constraintmodel.CComplexObject;
import org.openehr.am.archetype.constraintmodel.CObject;
import org.openehr.am.archetype.ontology.ArchetypeOntology;
import org.openehr.am.archetype.ontology.ArchetypeTerm;
import org.openehr.rm.common.generic.RevisionHistory;
import org.openehr.rm.common.resource.AuthoredResource;
import org.openehr.rm.common.resource.ResourceDescription;
import org.openehr.rm.common.resource.TranslationDetails;
import org.openehr.rm.datatypes.text.CodePhrase;
import org.openehr.rm.support.basic.MultiplicityInterval.ExistenceValues;
import org.openehr.rm.support.identification.ArchetypeID;
import org.openehr.rm.support.identification.ObjectID;
import org.openehr.rm.support.terminology.TerminologyService;

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

public class Archetype extends AuthoredResource {

	/**
	 * Constructs an Archetype
	 *
	 * @param adlVersion null if unspecified
	 * @param id
	 * @param parentId
	 * @param concept
	 * @param originalLanguage
	 * @param translations
	 * @param description
	 * @param revisionHistory
	 * @param isControlled
	 * @param definition
	 * @param ontology
	 * @params artefactType
	 * @throws IllegalArgumentException if description null or ontology null
	 */
	public Archetype(ArtefactType artefactType, 
			String adlVersion, String id, String parentId,	String concept, 
			CodePhrase originalLanguage,
			Map<String, TranslationDetails> translations,
			ResourceDescription description, RevisionHistory revisionHistory,
			boolean isControlled, CComplexObject definition, 
			ArchetypeOntology ontology,	Set<Assertion> invariants,
			TerminologyService terminologyService) {	
		
		super(originalLanguage, translations, description, revisionHistory,
			isControlled, terminologyService);
		
		if (artefactType == null) {
			throw new IllegalArgumentException("archetypeType null");
		}
		
		if (id == null) {
			throw new IllegalArgumentException("archetypeId null");
		}
		if (ontology == null) {
			throw new IllegalArgumentException("ontology null");
		}
		if (definition == null) {
			throw new IllegalArgumentException("definition null");
		}
		this.artefactType = artefactType;
		this.adlVersion = adlVersion;
		this.archetypeId = new ArchetypeID(id);
		this.concept = concept;
		this.parentArchetypeId = (parentId == null ? null : new ArchetypeID(
				parentId));
		this.definition = definition;
		this.ontology = ontology;
		this.invariants = invariants;
		this.pathNodeMap = new HashMap<String, CObject>();
		this.pathInputMap = new HashMap<String, String>();
		this.inputPathMap = new HashMap<String, String>();
		reloadNodeMaps();
	}

	public Archetype copy() {
		String parentId = 
			parentArchetypeId == null ? null : parentArchetypeId.toString();
		
		Archetype archetype = new Archetype(artefactType, adlVersion, archetypeId.toString(), 
				parentId, concept, getOriginalLanguage(), getTranslations(), 
				null, getRevisionHistory(), isControlled(), 
				(CComplexObject) definition.copy(),	ontology, invariants, null);
		
		reloadNodeMaps();
		
		// set c_obj.parent()?
		
		return archetype;
	}
	
	/**
	 * Indicates the type of artefact, i.e. archetype, or template. 
	 * If not present (e.g. in serialised forms), assumed to be False
	 * 
	 * @return
	 */
	public boolean isTemplate() {
		return ArtefactType.TEMPLATE.equals(artefactType); 
	}
	
	/**
	 * Indicates the artefact to be an overlay of an archetype or template; 
	 * if True, is_specialised must also be True.
	 * 
	 * @return
	 */
	public boolean isOverlay() {
		return ArtefactType.TEMPLATE_OVERLAY.equals(artefactType);
	}
	
	/**
	 * Reload node maps. It's required when archetype definition is
	 * modified after it's constructed by the parser.
	 */
	public void reloadNodeMaps() {
		pathNodeMap.clear();
		pathInputMap.clear();
		inputPathMap.clear();
		loadMaps(definition, true);
		loadInternalRefs(definition, true, null);
	}
	
	/**
	 * Set of language-independent paths extracted
	 * from archetype. Paths obey Xpath-like syntax
	 * and are formed from alternations of
	 * C_OBJECT.node_id and
	 * C_ATTRIBUTE.rm_attribute_name values.
	 * 
	 * @return set of paths
	 */
	public Set<String> physicalPaths() {
		return pathNodeMap.keySet();
	}	
	
	/**
	 * Set of language-dependent paths extracted
	 * from archetype. Paths obey the same syntax as
	 * physical_paths, but with node_ids replaced by
	 * their meanings from the ontology.
	 * 
	 * @param language
	 * @return set of paths
	 */
	public Set<String> logicalPaths(String language) {
		// TODO 
		return null;
	}

	private void loadMaps(CObject node, boolean required) {
		
		if(node != null && node.path() != null) {
			pathNodeMap.put(node.path(), node);
		}

		if (!(node instanceof CComplexObject)) {
			return; // other types of cobject
		}
		CComplexObject parent = (CComplexObject) node;

		if (parent.getAttributes() == null) {
			return; // no attribute
		}
		for (CAttribute attribute : parent.getAttributes()) {
			
			
			// pathNodeMap.put(attribute.path(), attribute);
			
			if (attribute.getExistence().intervalValueEquals(ExistenceValues.NOT_ALLOWED)) {
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
	
	public Map<String, CObject> getPathNodeMap() {
		return pathNodeMap;
	}

	private void loadInternalRefs(CObject node, boolean required, String refPath) {

		if (node instanceof ArchetypeInternalRef) {
			ArchetypeInternalRef ref = (ArchetypeInternalRef) node;
			
			ArchetypeConstraint target = node(ref.getTargetPath());
			if(target instanceof CObject) {
				loadInternalRefs((CObject) target, required
						&& node.isRequired(), ref.path());
			}
		}

		if (!(node instanceof CComplexObject)) {
			return; // other types of cobject
		}
		CComplexObject parent = (CComplexObject) node;

		if (parent.getAttributes() == null) {
			return; // no attribute
		}
		for (CAttribute attribute : parent.getAttributes()) {
			if (attribute.getExistence().intervalValueEquals(ExistenceValues.NOT_ALLOWED)) {
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
	 * The concept name of the archetype in language
	 * language; corresponds to the term definition of
	 * the concept attribute in the archetype ontology.
	 * 
	 * @param language the language of the concept name
	 * @return concept name in the requested language or 
	 * null if the language as a whole or the concept 
	 * in that language does not exist.
	 */
	public String getConceptName(String language) {
		ArchetypeTerm term = null;
	
		String conceptCode = this.getConcept();
		term = this.getOntology().termDefinition(language, conceptCode);
		if (term == null) {
			return null;
		} 
		return term.getItem(ArchetypeTerm.TEXT);
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
		return invariants == null ? null : Collections
				.unmodifiableSet(invariants);
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
	 * Return an object node or attribute at given path
	 *
	 * @param path
	 * @return null if node not found
	 */
	public ArchetypeConstraint node(String path) {
		
		// TODO complete new implementation of path-based node retrieval
		// based on runtime object tree traverse. Map-based path retrieval
		// need to be removed from the code
	
		return pathNodeMap.get(path);
	}
	
	/**
	 * Updates the pathNodeMap with given cobj
	 * 
	 * @param cobj
	 */
	public void updatePathNodeMap(CObject cobj) {
		if(cobj != null) {
			pathNodeMap.put(cobj.path(), cobj);
		}
	}
	
	/**
	 * Updates the pathNodeMap with given path and cobj
	 * 
	 * @param path
	 * @param cobj
	 */
	public void updatePathNodeMap(String path, CObject cobj) {
		if(cobj != null && path != null) {
			pathNodeMap.put(path, cobj);
		}
	}
	
	/**
	 * Updates the pathNodeMap with given cobj
	 * 
	 * @param cobj
	 */
	public void updatePathNodeMapRecursively(CObject cobj) {
		updatePathNodeMap(cobj);
		if(cobj instanceof CComplexObject) {
			CComplexObject ccobj = (CComplexObject) cobj;
			if(ccobj.getAttributes() == null) {
				return;
			}
			for(CAttribute cattr : ccobj.getAttributes()) {
				if(cattr.getChildren() != null) {
					for(CObject child : cattr.getChildren()) {
						updatePathNodeMapRecursively(child);
					}
				}
			}
		}		
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
	private final ArtefactType artefactType;
	
	private final String adlVersion;

	private final ArchetypeID archetypeId;

	private final ObjectID uid = null; // todo: not yet from adl

	private final String concept;

	private final ArchetypeID parentArchetypeId;

	private final CComplexObject definition;

	protected final ArchetypeOntology ontology;

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
 *  Portions created by the Initial Developer are Copyright (C) 2003-2012
 *  the Initial Developer. All Rights Reserved.
 *
 *  Contributor(s): Seref Arikan
 *
 * Software distributed under the License is distributed on an 'AS IS' basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
 * for the specific language governing rights and limitations under the
 * License.
 *
 *  ***** END LICENSE BLOCK *****
 */