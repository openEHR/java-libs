/*
 * component:   "openEHR Reference Implementation"
 * description: "Class MultipleTerminologyTest"
 * keywords:    "archetype"
 *
 * author:      "Rong Chen <rong@acode.se>"
 * support:     "Acode HB <support@acode.se>"
 * copyright:   "Copyright (c) 2004,2005,2006 Acode HB, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL:$"
 * revision:    "$LastChangedRevision: $"
 * last_change: "$LastChangedDate: $"
 */
package org.openehr.am.serialize;

import java.util.ArrayList;
import java.util.List;

import org.openehr.am.archetype.ontology.ArchetypeOntology;
import org.openehr.am.archetype.ontology.DefinitionItem;
import org.openehr.am.archetype.ontology.OntologyBinding;
import org.openehr.am.archetype.ontology.OntologyBindingItem;
import org.openehr.am.archetype.ontology.OntologyDefinitions;
import org.openehr.am.archetype.ontology.Query;
import org.openehr.am.archetype.ontology.QueryBindingItem;
import org.openehr.am.archetype.ontology.TermBindingItem;

/**
 * Multi-terminology ontology serialization test
 * 
 * @author Rong Chen
 */
public class MultipleTerminologyTest extends SerializerTestBase {
	
	public MultipleTerminologyTest(String test) {
		super(test);
	}
	
	public void testPrintOntology() throws Exception {
        DefinitionItem item = new DefinitionItem("at0001",
                "text at0001", "desc at0001");
        List<DefinitionItem> items = new ArrayList<DefinitionItem>();
        items.add(item);
        OntologyDefinitions definitions = new OntologyDefinitions("en", items);
        List<OntologyDefinitions> termDefinitionsList =
                new ArrayList<OntologyDefinitions>();
        termDefinitionsList.add(definitions);

        List<String> languages = new ArrayList<String>();
        languages.add("en");
        List<String> terminologies = new ArrayList<String>();
        terminologies.add("SNOMED_CT");
        terminologies.add("ICD10");
        
        List<String> terms = new ArrayList<String>();
        terms.add("[snomed_ct::1000339]");
        TermBindingItem termBindItem = new TermBindingItem("at0001",terms); 
        List<OntologyBindingItem> termBindList = new ArrayList<OntologyBindingItem>();
        termBindList.add(termBindItem);
        OntologyBinding ontologyBind = new OntologyBinding("SNOMED_CT",termBindList);
        List<OntologyBinding> termBindingList = new ArrayList<OntologyBinding>();
        termBindingList.add(ontologyBind);
        
        terms = new ArrayList<String>();
        terms.add("[icd10::1000]");
        terms.add("[icd10::1001]");        
        termBindItem = new TermBindingItem("at0001",terms); 
        termBindList = new ArrayList<OntologyBindingItem>();
        termBindList.add(termBindItem);        
        ontologyBind = new OntologyBinding("ICD10",termBindList);        
        termBindingList.add(ontologyBind);
        
        Query query = new Query("http://terminology.org?terminology_id=snomed_ct&&has_relation=[102002];with_target=[128004]");
        QueryBindingItem queryBindItem = new QueryBindingItem("ac0001",query); 
        List<OntologyBindingItem> constraintBindList = new ArrayList<OntologyBindingItem>();
        constraintBindList.add(queryBindItem);
        ontologyBind = new OntologyBinding("SNOMED_CT",constraintBindList);
        List<OntologyBinding> constraintBindingList = new ArrayList<OntologyBinding>();
        constraintBindingList.add(ontologyBind);
        
        query = new Query("http://terminology.org?terminology_id=icd10&&has_relation=[102002];with_target=[128004]");
        queryBindItem = new QueryBindingItem("ac0001",query); 
        constraintBindList = new ArrayList<OntologyBindingItem>();
        constraintBindList.add(queryBindItem);
        ontologyBind = new OntologyBinding("ICD10",constraintBindList);
        constraintBindingList.add(ontologyBind);
        
        ArchetypeOntology ontology = new ArchetypeOntology("en", languages,
                terminologies, termDefinitionsList, null, termBindingList, constraintBindingList);
        clean();
        outputter.printOntology(ontology, out);
        verifyByFile("multi-terminology.adl");
    }
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
 *  The Original Code is MultipleTerminologyTest.java
 *
 *  The Initial Developer of the Original Code is Rong Chen.
 *  Portions created by the Initial Developer are Copyright (C) 2004-2006
 *  the Initial Developer. All Rights Reserved.
 *
 *  Contributor(s): Mattias Forss
 *
 * Software distributed under the License is distributed on an 'AS IS' basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
 * for the specific language governing rights and limitations under the
 * License.
 *
 *  ***** END LICENSE BLOCK *****
 */

