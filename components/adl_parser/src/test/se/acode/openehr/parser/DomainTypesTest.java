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
import org.openehr.am.archetype.constraintmodel.domain.*;
import org.openehr.am.archetype.Archetype;
import org.openehr.am.openehrprofile.datatypes.quantity.CDvQuantity;
import org.openehr.am.openehrprofile.datatypes.quantity.CDvQuantityItem;
import org.openehr.rm.datatypes.text.CodePhrase;
import org.openehr.rm.datatypes.text.DvCodedText;
import org.openehr.rm.support.basic.Interval;

import java.io.File;
import java.util.*;

/**
 * Test case tests parsing of domain type constraints extension.
 *
 * @author Rong Chen
 * @version 1.0
 */

public class DomainTypesTest extends ParserTestBase {

    public DomainTypesTest(String test) throws Exception {
        super(test);
    }

    /**
     * The fixture set up called before every test method.
     */
    protected void setUp() throws Exception {
        ADLParser parser = new ADLParser(new File(dir,
                "adl-test-entry.domain_types.test.adl"));
        archetype = parser.parse();
    }

    /**
     * The fixture clean up called after every test method.
     */
    protected void tearDown() throws Exception {
        archetype = null;
    }

    public void testCCodedText() throws Exception {
        CObject node = archetype.node(
                "/types[at0001]/items[at10002]/value");
        assertTrue("CCodedText expected", node instanceof CCodedText);
        CCodedText ccodedtext = (CCodedText) node;
        assertEquals("terminology", "local", ccodedtext.getTerminology());
        String[] codes = {
            "at2001.0", "at2001.1", "at2001.2"
        };
        List<String> codeList = ccodedtext.getCodeList();
        assertEquals("codes.size", codes.length, codeList.size());
        for(int i = 0; i < codes.length; i++) {
            assertEquals("code", codes[i], codeList.get(i));
        }
    }    

    private Archetype archetype;
}
