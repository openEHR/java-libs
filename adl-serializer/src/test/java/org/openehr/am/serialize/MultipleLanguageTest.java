/*
 * component:   "openEHR Reference Implementation"
 * description: "Class MultipleLanguageTest"
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
import org.openehr.am.archetype.ontology.OntologyDefinitions;

/**
 * Multi-language ontology serialization test
 * 
 * @author Rong Chen
 */
public class MultipleLanguageTest extends SerializerTestBase {
	
	public MultipleLanguageTest(String test) {
		super(test);
	}
	
	public void testPrintOntology() throws Exception {
        DefinitionItem item = new DefinitionItem("at0001",
                "text at0001", "desc at0001");
        List<DefinitionItem> items = new ArrayList<DefinitionItem>();
        items.add(item);
        item = new DefinitionItem("at0002", "text at0002", "desc at0002");
        items.add(item);
        OntologyDefinitions definitions = new OntologyDefinitions("en", items);
        List<OntologyDefinitions> termDefinitionsList =
                new ArrayList<OntologyDefinitions>();
        termDefinitionsList.add(definitions);
        
        item = new DefinitionItem("at0001", "text at0001", "desc at0001");
        items = new ArrayList<DefinitionItem>();
        items.add(item);
        item = new DefinitionItem("at0002", "text at0002", "desc at0002");
        items.add(item);
        definitions = new OntologyDefinitions("zh", items);
        termDefinitionsList.add(definitions);
        
        item = new DefinitionItem("ac0001", "text ac0001", "desc ac0001");
        items = new ArrayList<DefinitionItem>();
        items.add(item);
        item = new DefinitionItem("ac0002", "text ac0002", "desc ac0002");
        items.add(item);
        definitions = new OntologyDefinitions("en", items);
        List<OntologyDefinitions> constraintDefinitionsList =
                new ArrayList<OntologyDefinitions>();        
        constraintDefinitionsList.add(definitions);
        
        item = new DefinitionItem("ac0001", "text ac0001", "desc ac0001");
        items = new ArrayList<DefinitionItem>();
        items.add(item);
        item = new DefinitionItem("ac0002", "text ac0002", "desc ac0002");
        items.add(item);
        definitions = new OntologyDefinitions("zh", items);
        constraintDefinitionsList.add(definitions);
        
        
        List<String> languages = new ArrayList<String>();
        languages.add("en");
        languages.add("zh");        
        
        ArchetypeOntology ontology = new ArchetypeOntology("en", languages,
                null, termDefinitionsList, constraintDefinitionsList, 
                null, null);
        clean();
        outputter.printOntology(ontology, out);
        verifyByFile("multi-language.adl");
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
 *  The Original Code is MultipleLanguageTest.java
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
