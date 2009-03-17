/*
 * Copyright (C) 2005 Acode HB, Sweden.
 * All Rights Reserved.
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You may obtain a copy of the License at
 * http://www.gnu.org/licenses/gpl.txt
 *
 */

package se.acode.openehr.parser;

import org.openehr.am.archetype.Archetype;
import org.openehr.am.archetype.constraintmodel.*;
import org.openehr.am.archetype.constraintmodel.primitive.CString;
import org.openehr.rm.support.basic.Interval;

/**
 * Test case tests parsing objet structures with archetypes.
 *
 * @author Rong Chen
 * @version 1.0
 */
public class StructureTest extends ParserTestBase {

    public void setUp() throws Exception {
        ADLParser parser = new ADLParser(loadFromClasspath(
                "adl-test-entry.structure_test1.test.adl"));
        definition = parser.parse().getDefinition();
    }

    public void tearDown() throws Exception {
        definition = null;

    }

    public StructureTest(String test) {
        super(test);
    }

    public void testStructure() throws Exception {

        // root object
        CComplexObject obj = definition;
        Interval<Integer> occurrences = new Interval<Integer>(1, 1);
        assertCComplexObject(obj, "ENTRY", "at0000", occurrences, 2);

        // first attribute of root object
        CAttribute attr = (CAttribute) obj.getAttributes().get(0);
        assertCAttribute(attr, "subject_relationship", 1);

        // 2nd level object
        obj = (CComplexObject) attr.getChildren().get(0);
        assertCComplexObject(obj, "RELATED_PARTY", null, occurrences, 1);

        // attribute of 2nd level object
        attr = (CAttribute) obj.getAttributes().get(0);
        assertCAttribute(attr, "relationship", 1);

        // leaf object
        obj = (CComplexObject) attr.getChildren().get(0);
        assertCComplexObject(obj, "TEXT", null, occurrences, 1);

        // attribute of leaf object
        attr = (CAttribute) obj.getAttributes().get(0);
        assertCAttribute(attr, "value", 1);

        // primitive constraint of leaf object
        CString str = (CString) ((CPrimitiveObject) attr.getChildren().get(0)).getItem();
        assertEquals("pattern", null, str.getPattern());
        assertEquals("set.size", 1, str.getList().size());
        assertTrue("set has", str.getList().contains("self"));
    }

    public void testExistenceCardinalityAndOccurrences() throws Exception {
        // second attribute of root object
        CAttribute attr = (CAttribute) definition.getAttributes().get(1);
        Cardinality card = new Cardinality(true, false, interval(0, 8));
        assertCAttribute(attr, "members", CAttribute.Existence.OPTIONAL, card, 2);

        // 1st PERSON
        CComplexObject obj = (CComplexObject) attr.getChildren().get(0);
        assertCComplexObject(obj, "PERSON", null, interval(1, 1), 1);

        // 2nd PERSON
        obj = (CComplexObject) attr.getChildren().get(1);
        assertCComplexObject(obj, "PERSON", null,
                new Interval(new Integer(0), null, true, false), 1);
    }
    
    public void testParseCommentWithSlashChar() throws Exception {
        ADLParser parser = new ADLParser(loadFromClasspath(
                "adl-test-entry.structure_test2.test.adl"));
        Archetype archetype = parser.parse();        
        assertNotNull(archetype);        
    }
    
    public void __testParseCommentWithSlashCharAfterSlot() throws Exception {
        ADLParser parser = new ADLParser(loadFromClasspath(
                "openEHR-EHR-CLUSTER.auscultation.v1.adl"));
        Archetype archetype = parser.parse();        
        assertNotNull(archetype);        
    }

    private CComplexObject definition;
}
