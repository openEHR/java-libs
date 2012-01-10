package org.openehr.am.archetype.ontology;

import java.util.List;

public class DifferentialArchetypeOntology extends ArchetypeOntology {

	public DifferentialArchetypeOntology(String primaryLanguage,
			List<String> languages, List<String> terminologies,
			List<OntologyDefinitions> termDefinitionsList,
			List<OntologyDefinitions> constDefinitionsList,
			List<OntologyBinding> termBindingList,
			List<OntologyBinding> constraintBindingList) {
		super(primaryLanguage, languages, terminologies, termDefinitionsList,
				constDefinitionsList, termBindingList, constraintBindingList);
		// TODO Auto-generated constructor stub
	}

}
