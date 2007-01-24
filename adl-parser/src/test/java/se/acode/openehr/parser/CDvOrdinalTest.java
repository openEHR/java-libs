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

import org.openehr.am.archetype.constraintmodel.CObject;
import org.openehr.am.archetype.Archetype;
import org.openehr.am.openehrprofile.datatypes.quantity.CDvOrdinal;
import org.openehr.am.openehrprofile.datatypes.quantity.Ordinal;
import org.openehr.rm.datatypes.text.CodePhrase;

import java.util.*;

/**
 * Test case tests parsing of domain type constraints extension.
 *
 * @author Rong Chen
 * @version 1.0
 */

public class CDvOrdinalTest extends ParserTestBase {

    public CDvOrdinalTest(String test) throws Exception {
        super(test);
    }

    /**
     * The fixture set up called before every test method.
     */
    protected void setUp() throws Exception {
        ADLParser parser = new ADLParser(loadFromClasspath(
                "adl-test-entry.c_dv_ordinal.test.adl"));
        archetype = parser.parse();
    }

    /**
     * The fixture clean up called after every test method.
     */
    protected void tearDown() throws Exception {
        archetype = null;
    }

    public void testCDvOrdinal() throws Exception {
        CObject node = archetype.node(
                "/types[at0001]/items[at10001]/value");
        String[] codes = {
            "at0003.0", "at0003.1", "at0003.2", "at0003.3", "at0003.4"
        };
        String terminology = "local";
        assertCDvOrdinal(node, terminology, codes, null);
    }
    
    public void testCDvOrdinalWithAssumedValue() throws Exception {
        CObject node = archetype.node(
                "/types[at0001]/items[at10002]/value");
        String[] codes = {
            "at0003.0", "at0003.1", "at0003.2", "at0003.3", "at0003.4"
        };
        String terminology = "local";
        Ordinal assumed = new Ordinal(0, new CodePhrase(terminology, codes[0]));
        assertCDvOrdinal(node, terminology, codes, assumed);
    }
    
    private void assertCDvOrdinal(CObject node, String terminoloy,
    		String[] codes,	Ordinal assumedValue) {
    	
    	assertTrue("CDvOrdinal expected", node instanceof CDvOrdinal);
        CDvOrdinal cordinal = (CDvOrdinal) node;
        
        List codeList = Arrays.asList(codes);
        Set<Ordinal> list = cordinal.getList();
        assertEquals("codes.size", codes.length, list.size());
        for(Ordinal ordinal : list) {
            assertEquals("terminology", "local", 
            		ordinal.getSymbol().getTerminologyID().getValue());
            assertTrue("code missing", 
            		codeList.contains(ordinal.getSymbol().getCodeString()));
        }
        assertEquals("assumedValue wrong", assumedValue, 
        		cordinal.getAssumedValue());
    }

    private Archetype archetype;
}
