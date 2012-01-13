package org.openehr.am.archetype;

import java.util.Map;
import java.util.Set;

import org.openehr.am.archetype.assertion.Assertion;
import org.openehr.am.archetype.constraintmodel.CComplexObject;
import org.openehr.am.archetype.ontology.FlatArchetypeOntology;
import org.openehr.rm.common.generic.RevisionHistory;
import org.openehr.rm.common.resource.ResourceDescription;
import org.openehr.rm.common.resource.TranslationDetails;
import org.openehr.rm.datatypes.text.CodePhrase;
import org.openehr.rm.support.terminology.TerminologyService;

public class FlatArchetype extends Archetype {

	public FlatArchetype(ArtefactType artefactType, String adlVersion, String id, String parentId,
			String concept, CodePhrase originalLanguage,
			Map<String, TranslationDetails> translations,
			ResourceDescription description, RevisionHistory revisionHistory,
			boolean isControlled, CComplexObject definition,
			FlatArchetypeOntology ontology, Set<Assertion> invariants,
			TerminologyService terminologyService) {
		super(artefactType, adlVersion, id, parentId, concept, originalLanguage, translations,
				description, revisionHistory, isControlled, definition, ontology,
				invariants, terminologyService);
		// TODO Auto-generated constructor stub
	}
	
	public FlatArchetypeOntology getOntology(){
		return (FlatArchetypeOntology) ontology;
	}

}
