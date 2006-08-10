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
package org.openehr.rm.composition.content.navigation;

import org.openehr.rm.composition.CompositionTestBase;
import org.openehr.rm.composition.content.ContentItem;
import org.openehr.rm.composition.content.entry.Observation;
import org.openehr.rm.datatypes.text.DvText;

import java.util.List;
import java.util.ArrayList;

/**
 * SectionTest
 *
 * @author Rong Chen
 * @version 1.0
 */
public class SectionTest extends CompositionTestBase {

    public SectionTest(String test) {
        super(test);
    }

    public void setUp() throws Exception {
        List<ContentItem> items = new ArrayList<ContentItem>();
        items.add(observation("observation 2"));
        items.add(section("section 3"));
        Section section2 =
                new Section("at0000", new DvText("section 2"), items);
        items = new ArrayList<ContentItem>();
        items.add(section2);
        items.add(observation("observation 1"));
        section = new Section("at0000", new DvText("section"), items);
    }

    public void tearDown() throws Exception {
        section = null;
    }

    public void testValidPath() throws Exception {
        String[] validPathList = {
            "/",
            "/items[observation 1]",
            "/items[section 2]",
            "/items[section 2]/items[section 3]",
            "/items[section 2]/items[observation 2]",
        };

        for (String path : validPathList) {
            assertTrue("unexpected invalid path: " + path,
                    section.validPath(path));
        }

        String[] invalidPathList = {
            "", null, "[section]", "/section", // bad root
            "/[section]/items", // incomplete
            "/[section]/items[section 3]", // unknown section
            "/[section]/items[observation 3]",    // unknown entry
            "/[section]",
            "/[section]/items[observation 1]",
            "/[section]/items[section 2]",
            "/[section]/items[section 2]/items[section 3]",
            "/[section]/items[section 2]/items[observation 2]",
        };

        for (String path : invalidPathList) {
            assertFalse("unexpected valid path: " + path,
                    section.validPath(path));
        }
    }

    public void testItemAtPath() throws Exception {
        Observation observationOne = (Observation) section.getItems().get(1);
        Section sectionTwo = (Section) section.getItems().get(0);
        Observation observationTwo = (Observation)
                ( (Section) section.getItems().get(0) ).getItems().get(0);
        Section sectionThree = (Section) ( (Section) section.getItems().get(0) )
                .getItems().get(1);

        assertEquals("section two wrong", sectionTwo,
                section.itemAtPath("/items[section 2]"));

        assertEquals("section three wrong", sectionThree,
                section.itemAtPath("/items[section 2]/items[section 3]"));

        assertEquals("observation one wrong", observationOne,
                section.itemAtPath("/items[observation 1]"));

        assertEquals("observation two wrong", observationTwo,
                section.itemAtPath("/items[section 2]/items[observation 2]"));

    }

    /* fields */
    private Section section;
}
