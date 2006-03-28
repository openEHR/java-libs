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

import java.io.File;

/**
 * Test case tests parsing with a number of ADL files.
 *
 * @author Rong Chen (rong@acode.se)
 * @version 1.0
 */

public class ADLParserBatchParsingTest extends ADLParserTestBase {

    /**
     * Create a new test case
     *
     * @param test
     */
    public ADLParserBatchParsingTest(String test) {
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

    /**
     * Tests parsing ADL files
     *
     * @throws Exception
     */
    public void testParsingADLFiles() throws Exception {
        // System.out.println("Parsing ADL files from " + dir.getAbsolutePath());

        File[] files = dir.listFiles();
        int count  = 0;
        ADLParser parser = null;
        for(int i = 0; i < files.length; i++) {
            if( ! files[i].getName().endsWith(".adl")) {
                continue;
            }
            System.out.println("Parsing: " + files[i]);
            if(parser == null) {
                parser = new ADLParser(files[i]);
            } else {
                parser.reInit(files[i]);
            }
            try {
              parser.parse();
            } catch(ParseException e) {
                throw new Exception("Failed parsing " + files[i], e);
            }
            count++;
        }
        System.out.println("Total successfully parsed ADL files: " + count);
    }

    /**
     * File directory where ADL files are loaded
     */
    private File dir = new File("res" + File.separator + "batch_test");
}