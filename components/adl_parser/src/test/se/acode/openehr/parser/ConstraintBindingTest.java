/*
 * Copyright (C) 2004 Rong Chen, Acode HB, Sweden
 * All rights reserved.
 *
 * The contents of this software are subject to the FSF GNU Public License 2.0;
 * you may not use this software except in compliance with the License. You may
 * obtain a copy of the License at http://www.fsf.org/licenses/gpl.html
 *
 * Software distributed under the License is distributed on an 'AS IS' basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
 * for the specific language governing rights and limitations under the
 * License.
 */
package se.acode.openehr.parser;

import org.openehr.am.archetype.Archetype;
import org.openehr.am.archetype.ontology.QueryBindingItem;
import org.openehr.am.archetype.ontology.TermBindingItem;
import org.openehr.am.archetype.ontology.OntologyBinding;
import org.openehr.am.archetype.output.ADLOutputter;

import java.io.File;
import java.io.StringWriter;
import java.util.List;

/**
 * TermBindingTest
 *
 * @author Rong Chen
 * @version 1.0
 */
public class ConstraintBindingTest extends ADLParserTestBase {

    /**
     * Create new test case
     *
     * @param test
     * @throws Exception
     */
    public ConstraintBindingTest(String test) throws Exception {
        super(test);
    }

    /**
     * Verifies constraint binding by multiple terminolgies
     * 
     * @throws Exception
     */
    public void testConstraintBindingWithMultiTerminologies() throws Exception {
        ADLParser parser = new ADLParser(new File(dir,
                "adl-test-entry.constraint_binding.test.adl"));
        Archetype archetype = parser.parse();
        List<OntologyBinding> list = archetype.getOntology().getConstraintBindingList();
        
        assertEquals("unexpected number of onotology binding", 2, list.size());

        // verify the first constraint binding
        OntologyBinding binding = list.get(0);
        assertEquals("unexpected binding terminology", "SNOMED_CT", binding.getTerminology());

        QueryBindingItem item = (QueryBindingItem) binding.getBindingList().get(0);

        assertEquals("unexpected local code", "ac0001", item.getCode());
        assertEquals("exexpected query", 
        		"http://terminology.org?terminology_id=snomed_ct&&has_relation=102002;with_target=128004", 
        		item.getQuery().getUrl());

        // verify the second constraint binding
        binding = list.get(1);
        assertEquals("unexpected binding terminology", "ICD10", binding.getTerminology());

        item = (QueryBindingItem) binding.getBindingList().get(0);

        assertEquals("unexpected local code", "ac0001", item.getCode());
        assertEquals("exexpected query", 
        		"http://terminology.org?terminology_id=icd10&&has_relation=a2;with_target=b19", 
        		item.getQuery().getUrl());
    }
}

