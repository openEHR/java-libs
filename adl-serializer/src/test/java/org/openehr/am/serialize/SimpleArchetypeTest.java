package org.openehr.am.serialize;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.openehr.am.archetype.Archetype;
import org.openehr.am.archetype.assertion.Assertion;
import org.openehr.am.archetype.constraintmodel.CComplexObject;
import org.openehr.am.archetype.ontology.ArchetypeOntology;
import org.openehr.am.archetype.ontology.ArchetypeTerm;
import org.openehr.am.archetype.ontology.OntologyBinding;
import org.openehr.am.archetype.ontology.OntologyDefinitions;
import org.openehr.rm.common.generic.RevisionHistory;
import org.openehr.rm.common.resource.ResourceDescription;
import org.openehr.rm.common.resource.TranslationDetails;
import org.openehr.rm.datatypes.text.CodePhrase;
import org.openehr.rm.support.basic.Interval;
import org.openehr.rm.support.terminology.TerminologyService;
import org.openehr.terminology.SimpleTerminologyService;

/**
 * Testcase that verifies print out of a simple archetype
 * 
 * @author Rong.Chen
 * 
 */
public class SimpleArchetypeTest extends SerializerTestBase {

	public SimpleArchetypeTest(String test) {
		super(test);
	}

	public void testPrintArchetypeLanguagePart() throws Exception {
		String adlVersion = "1.4";
		String id = "adl-test-ENTRY.most_minimal.draft";
		String parentId = null;
		String concept = "at0000";
		CodePhrase originalLanguage = new CodePhrase("ISO_639-1", "en");

		Map<String, TranslationDetails> translations = null;
		ResourceDescription description = null;
		RevisionHistory revisionHistory = null;
		boolean isControlled = false;
		Interval<Integer> occurrences = new Interval<Integer>(1, 1);
		CComplexObject definition = new CComplexObject("/", "ENTRY", 
				occurrences, "at0000", null, null);

		ArchetypeTerm item = new ArchetypeTerm("at0000", "most minimal",
				"most minimal");
		List<ArchetypeTerm> items = new ArrayList<ArchetypeTerm>();
		items.add(item);
		OntologyDefinitions definitions = new OntologyDefinitions("en", items);
		List<OntologyDefinitions> termDefinitionsList = 
			new ArrayList<OntologyDefinitions>();
		termDefinitionsList.add(definitions);

		List<OntologyDefinitions> constraintDefinitionsList = null;
		List<String> languages = new ArrayList<String>();
		languages.add("en");
		List<String> terminologies = null;
		List<OntologyBinding> termBindingList = null;
		List<OntologyBinding> constraintBindingList = null;

		ArchetypeOntology ontology = new ArchetypeOntology("en", languages,
				terminologies, termDefinitionsList, constraintDefinitionsList,
				termBindingList, constraintBindingList);

		Set<Assertion> invariants = null;
		TerminologyService terminologyService = 
				SimpleTerminologyService.getInstance();

		Archetype archetype = new Archetype(adlVersion, id, parentId, concept,
				originalLanguage, translations, description, revisionHistory,
				isControlled, definition, ontology, invariants,
				terminologyService);

		clean();
		outputter.output(archetype, out);
		verifyByFile("adl-test-entry.most_minimal.test.adl");
	}
}
