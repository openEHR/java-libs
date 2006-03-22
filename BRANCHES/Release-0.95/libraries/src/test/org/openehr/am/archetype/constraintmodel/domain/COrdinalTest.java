/*
 * component:   "openEHR Reference Implementation"
 * description: "Class COrdinalTest"
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
/**
 * COrdinalTest
 *
 * @author Rong Chen
 * @version 1.0 
 */
package org.openehr.am.archetype.constraintmodel.domain;

import org.openehr.am.archetype.ConstraintTestBase;
import org.openehr.am.archetype.constraintmodel.DVObjectCreationException;
import org.openehr.am.archetype.ontology.ArchetypeOntology;
import org.openehr.am.archetype.ontology.TestArchetypeOntology;
import org.openehr.rm.datatypes.quantity.DvOrdinal;
import org.openehr.rm.datatypes.text.CodePhrase;

import java.util.ArrayList;
import java.util.List;

public class COrdinalTest extends ConstraintTestBase {

    public COrdinalTest(String test) {
        super(test);
    }

    /**
     * The fixture set up called before every test method.
     */
    protected void setUp() throws Exception {
    }

    /**
     * The fixture clean up called after every test method.
     */
    protected void tearDown() throws Exception {
    }

    public void testCreateDVObject() throws Exception {
        List<Ordinal> list = new ArrayList<Ordinal>();
        for (int i = 0; i < 6; i++) {
            list.add(new Ordinal(i, "local", "at000" + i));
        }
        COrdinal cordinal = new COrdinal("path", list);

        // wrong format
        try {
            cordinal.createObject("bad", sysmap(), ontology);
            fail("exception should be thrown");
        } catch (Exception e) {
            assertTrue(e instanceof DVObjectCreationException);
        }

        // index out of range
        try {
            cordinal.createObject("15", sysmap(), ontology);
            fail("exception should be thrown");
        } catch (Exception e) {
            assertTrue(e instanceof DVObjectCreationException);
        }

        // right value
        DvOrdinal o = null;
        o = cordinal.createObject("3", sysmap(), ontology);
        assertEquals("value", 3, o.getValue());
        assertEquals("symbol.value", "text of code 3",
                o.getSymbol().getValue());
        assertEquals("symbol.definingCode", new CodePhrase("local", "at0003"),
                o.getSymbol().getDefiningCode());
    }

    public void testAssignedValue() throws Exception {

        // not assigned
        List<Ordinal> list = new ArrayList<Ordinal>();
        for (int i = 0; i < 6; i++) {
            list.add(new Ordinal(i, "local", "at000" + i));
        }
        COrdinal cordinal = new COrdinal("path", list);
        assertFalse(cordinal.hasAssignedValue());
        assertTrue(
                cordinal.assignedValue(sysmap(), null) == null);

        // assigned
        list = new ArrayList<Ordinal>();
        list.add(new Ordinal(1, "local", "at0000"));
        cordinal = new COrdinal("path", list);
        assertTrue(cordinal.hasAssignedValue());
        DvOrdinal o = (DvOrdinal) cordinal.assignedValue(sysmap(), ontology);
        assertEquals("value", 1, o.getValue());
        assertEquals("symbol.value", "text of code 0",
                o.getSymbol().getValue());
        assertEquals("symbol.definingCode", new CodePhrase("local", "at0000"),
                o.getSymbol().getDefiningCode());
    }

    /* fields */
    private final ArchetypeOntology ontology = TestArchetypeOntology.getInstance();
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
 *  The Original Code is COrdinalTest.java
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