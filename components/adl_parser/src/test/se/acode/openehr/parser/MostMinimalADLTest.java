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

import java.io.File;
import java.io.StringWriter;

/**
 * MostMinimalADLTest
 *
 * @author Rong Chen
 * @version 1.0
 */
public class MostMinimalADLTest extends ParserTestBase {

    /**
     * Create new test case
     *
     * @param test
     * @throws Exception
     */
    public MostMinimalADLTest(String test) throws Exception {
        super(test);
    }

    public void testParse() throws Exception {
        ADLParser parser = new ADLParser(new File(dir,
                "adl-test-entry.most_minimal.test.adl"));
        Archetype archetype = parser.parse();
        assertNotNull(archetype);
    }
}

