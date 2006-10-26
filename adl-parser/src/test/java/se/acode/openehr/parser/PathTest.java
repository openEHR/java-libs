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

import org.openehr.am.archetype.constraintmodel.CComplexObject;
import org.openehr.am.archetype.constraintmodel.CObject;
import org.openehr.am.archetype.constraintmodel.CAttribute;

/**
 * Test case tests archetype path logic.
 *
 * @author Rong Chen
 * @version 1.0
 */
public class PathTest extends ParserTestBase {

    public PathTest(String test) {
        super(test);
    }

    public void setUp() throws Exception {
        ADLParser parser = new ADLParser(loadFromClasspath(
                "adl-test-car.paths.test.adl"));
        definition = parser.parse().getDefinition();
    }

    public void testPath() throws Exception {

        // root path CAR
        assertEquals("root", "/", definition.path());

        // wheels attribute
        CAttribute wheels = definition.getAttributes().get(0);
        assertEquals("wheels", "/wheels", wheels.path());

        // first WHEEL node
        CObject firstWheel = wheels.getChildren().get(0);
        assertEquals("first wheel", "/wheels[at0001]",
                firstWheel.path());

        // description and parts of first WHEEL
        CComplexObject firstWheelObj = (CComplexObject) firstWheel;
        CAttribute description = firstWheelObj.getAttributes().get(0);
        assertEquals("first wheel description",
                "/wheels[at0001]/description", description.path());
        CAttribute parts = firstWheelObj.getAttributes().get(1);
        assertEquals("first wheel parts",
                "/wheels[at0001]/parts", parts.path());

        // WHEEL_PART node
        CObject wheelParts = parts.getChildren().get(0);
        assertEquals("wheelPart", "/wheels[at0001]/parts[at0002]",
                wheelParts.path());

        // something and something_else of WHEEL_PART node
        CComplexObject wheelPartsObj = (CComplexObject) wheelParts;
        assertEquals("something of WHEEL_PART",
                "/wheels[at0001]/parts[at0002]/something",
                wheelPartsObj.getAttributes().get(0).path());

        assertEquals("something_else of WHEEL_PART",
                "/wheels[at0001]/parts[at0002]/something_else",
                wheelPartsObj.getAttributes().get(1).path());
    }

    private CComplexObject definition;
}
