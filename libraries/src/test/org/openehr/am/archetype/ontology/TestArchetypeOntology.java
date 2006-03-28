/*
 * component:   "openEHR Reference Implementation"
 * description: "Class TestArchetypeOntology"
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
package org.openehr.am.archetype.ontology;

import org.openehr.rm.support.terminology.TestCodeSet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * TestArchetypeOntology used by other test case
 *
 * @author Rong Chen
 * @version 1.0
 */
public class TestArchetypeOntology {

    public static ArchetypeOntology getInstance() {
        List<DefinitionItem> items = new ArrayList<DefinitionItem>();
        for(int i = 0; i < 10; i++) {
            items.add(new DefinitionItem("at000" + i, "text of code " + i,
                    "desc of code " + i));
        }
        String lang = TestCodeSet.ENGLISH.getCodeString();
        OntologyDefinitions defs = new OntologyDefinitions(lang, items);
        List<OntologyDefinitions> defsList = new ArrayList<OntologyDefinitions>();
        defsList.add(defs);
        return new ArchetypeOntology(lang, Arrays.asList(new String[] { lang }),
                null, defsList, null, null, null);
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
 *  The Original Code is TestArchetypeOntology.java
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