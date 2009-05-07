package se.acode.openehr.parser;

import org.openehr.am.archetype.Archetype;
import org.openehr.am.archetype.ontology.TermBindingItem;
import org.openehr.am.archetype.ontology.OntologyBinding;

/**
 * TermBindingTest
 *
 * @author Rong Chen
 * @version 1.0
 */
public class TermBindingTest extends ParserTestBase {

    /**
     * Create new test case
     *
     * @param test
     * @throws Exception
     */
    public TermBindingTest(String test) throws Exception {
        super(test);
    }

    /**
     * Verifies term binding by multiple terminolgies
     * 
     * @throws Exception
     */
    public void testTermBindingWithMultiTerminologies() throws Exception {
        ADLParser parser = new ADLParser(loadFromClasspath(
                "adl-test-entry.term_binding.test.adl"));
        Archetype archetype = parser.parse();

        // verify the first term binding
        OntologyBinding binding = archetype.getOntology().getTermBindingList().get(0);
        assertEquals("wrong binding terminology", "SNOMED_CT", binding.getTerminology());

        TermBindingItem item = (TermBindingItem) binding.getBindingList().get(0);

        assertEquals("wrong local code", "at0000", item.getCode());
        assertEquals("wrong terms size", 1, item.getTerms().size());
        assertEquals("wrong term", "[snomed_ct::1000339]", item.getTerms().get(0));

        // verify the second term binding
        binding = archetype.getOntology().getTermBindingList().get(1);
        assertEquals("wrong binding terminology", "ICD10", binding.getTerminology());

        item = (TermBindingItem) binding.getBindingList().get(0);

        assertEquals("wrong local code", "at0000", item.getCode());
        assertEquals("wrong terms size", 2, item.getTerms().size());
        assertEquals("wrong 1st term", "[icd10::1000]", item.getTerms().get(0));
        assertEquals("wrong 2nd term", "[icd10::1001]", item.getTerms().get(1));
    }
    
    public void testPathBasedBinding() throws Exception {
    	ADLParser parser = new ADLParser(loadFromClasspath(
        	"adl-test-entry.term_binding2.test.adl"));
    	Archetype archetype = parser.parse();

    	OntologyBinding binding = archetype.getOntology().getTermBindingList().get(0);
        assertEquals("wrong binding terminology", "LNC205", binding.getTerminology());

        TermBindingItem item = (TermBindingItem) binding.getBindingList().get(0);

        assertEquals("wrong local code path", 
        		"/data[at0002]/events[at0003]/data[at0001]/item[at0004]", 
        		item.getCode());
        assertEquals("wrong terms size", 1, item.getTerms().size());
        assertEquals("wrong term", "[LNC205::8310-5]", item.getTerms().get(0));

    }
}

