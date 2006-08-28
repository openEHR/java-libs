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
import org.openehr.am.serialize.ADLSerializer;

import java.io.File;
import java.io.StringWriter;

/**
 * Test case tests the ADL output of an Archetype can be parsed to
 * the same archetype.
 *
 * @author Rong Chen
 * @version 1.0
 */
public class RoundTripTest extends ParserTestBase {

    public RoundTripTest(String test) {
        super(test);
    }

    public void testRoundTrip() throws Exception {
        
    	// first round parse ADL into AOM
    	ADLParser parser = new ADLParser(new File(dir,
                "openEHR-EHR-COMPOSITION.round_trip.test.adl"));
        Archetype archetypeOne = parser.parse();

        // output AOM into ADL
        ADLSerializer outputter = new ADLSerializer();
        StringWriter writer = new StringWriter();
        outputter.output(archetypeOne, writer);

        String adlOutput = writer.toString();
        // System.out.println(adlOutput);
        
        // second round parse ADL into AOM
        parser = new ADLParser(adlOutput);
        Archetype archetypeTwo = parser.parse();

        // compare the AOM from two round of parsing
        // verify header - id, concenpt, parent
        assertEquals("id not same", archetypeOne.getArchetypeId(),
                archetypeTwo.getArchetypeId());
        assertEquals("concept not same", archetypeOne.getConcept(),
                archetypeTwo.getConcept());
        assertEquals("parent not same", archetypeOne.getParentArchetypeId(),
                archetypeTwo.getParentArchetypeId());

        // verify definition
        assertEquals("definition not same", archetypeOne.getDefinition(),
                archetypeTwo.getDefinition());

        // verify ontology
        assertEquals("ontology not same", archetypeOne.getOntology(),
                archetypeTwo.getOntology());
    }
}
