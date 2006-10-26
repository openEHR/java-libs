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
package org.openehr.rm.composition;

import org.openehr.rm.composition.content.ContentItem;
import org.openehr.rm.datatypes.text.DvText;
import org.openehr.rm.datatypes.text.DvCodedText;
import org.openehr.rm.datatypes.text.CodePhrase;
import org.openehr.rm.support.identification.ObjectID;
import org.openehr.rm.support.identification.HierarchicalObjectID;
import org.openehr.rm.support.identification.ArchetypeID;
import org.openehr.rm.support.terminology.TestCodeSetAccess;
import org.openehr.rm.support.terminology.TerminologyService;
import org.openehr.rm.support.terminology.TerminologyAccess;
import org.openehr.rm.support.terminology.CodeSetAccess;
import org.openehr.rm.composition.content.navigation.Section;
import org.openehr.rm.composition.content.entry.Observation;
import org.openehr.rm.common.archetyped.Archetyped;

import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import org.openehr.rm.support.terminology.TestTerminologyAccess;

/**
 * CompositionTest
 *
 * @author Rong Chen
 * @version 1.0
 */
public class CompositionTest extends CompositionTestBase {

    public CompositionTest(String test) {
        super(test);
    }

    public void setUp() throws Exception {
        DvText name = new DvText("composition");
        ObjectID id = new HierarchicalObjectID("1.11.2.3.4.5.0");
        List<ContentItem> content = new ArrayList<ContentItem>();
        content.add(section("section one"));
        content.add(section("section two", "observation"));
        DvCodedText category = TestCodeSetAccess.EVENT;
        Archetyped archetypeDetails = new Archetyped(new ArchetypeID(
                "openehr-ehr_rm-Composition.physical_examination.v2"), "1.0");
        
        composition = new Composition(id, "at0001", name, archetypeDetails, null, 
                null, null, content, TestTerminologyAccess.ENGLISH, context(), provider(), 
                category, territory(), ts);
 
    }

    public void tearDown() throws Exception {
        composition = null;
    }

    /**
     * Verifies that a bug related to territory checking is fixed
     *
     * @throws Exception
     */
    public void testTerritoryCheckInConstructor() throws Exception {

        /**
         * Mock terminology service designed to test checking of territory
         */
        TerminologyService tsLocal = new TerminologyService () {

            /**
             * Returns a Terminology of given name
             *
             * @param name not empty and known to this service
             * @return terminology
             * @throws IllegalArgumentException if name null, empty
             *                                  or unknown to this terminology service
             */
            public TerminologyAccess terminology(String name) {

                if( ! TerminologyService.OPENEHR.equals(name)) {
                    return null;
                }
                
                return new TerminologyAccess() {

                    public Set<CodePhrase> codesForGroupID(String groupID) {
                        return null;
                    }

                    public Set<CodePhrase> codesForGroupName(String name, String language) {
                        return null;
                    }

                    public String rubricForCode(String code, String language) {
                        return null;
                    }

                    public boolean hasCodeForGroupName(CodePhrase code, String name,
                                                       String language) {
                        if("composition category".equals(name) &&  "en".equals(language)) {
                            return true;
                        }
                        return false;
                    }

                    public String id() {
                        return null;  // todo: implement this method
                    }

                    public Set<CodePhrase> allCodes() {
                        return null;  // todo: implement this method
                    }

                    public boolean has(CodePhrase code) {
                        return false;  // todo: implement this method
                    }
                };
            }

            /**
             * Returns a CodeSet of given name
             *
             * @param name not empty and known to this service
             * @return codeSet
             * @throws IllegalArgumentException if name is null, empty
             *                                  or unknown to this terminology service
             */
            public CodeSetAccess codeSet(String name) {

                if( ! "countries".equals(name)) {
                    return null;
                }

                return new CodeSetAccess() {
                    public String id() {
                        return null;
                    }

                    public Set<CodePhrase> allCodes() {
                        return null;
                    }

                    public boolean hasLang(CodePhrase code) {
                        return true;
                    }
                    
                    public boolean hasCode(CodePhrase code) {
                        return true;
                    }
                };
            }

            /**
             * Returns ture if terminology of given name known by this service
             *
             * @param name not empty
             * @return true if has given terminology
             * @throws IllegalArgumentException if name is null or empty
             */
            public boolean hasTerminology(String name) {
                return false;
            }

            /**
             * Returns true if code set of given name known by this service
             *
             * @param name not empty
             * @return true if has given codeset
             * @throws IllegalArgumentException if name is null or empty
             */
            public boolean hasCodeSet(String name) {
                return false;
            }
        };
        DvText name = new DvText("composition");
        ObjectID id = new HierarchicalObjectID("1.11.2.4.22.5.2");
        List<ContentItem> content = new ArrayList<ContentItem>();
        content.add(section("section one"));
        content.add(section("section two", "observation"));
        DvCodedText category = TestCodeSetAccess.EVENT;
        Archetyped archetypeDetails = new Archetyped(new ArchetypeID(
                "openehr-ehr_rm-Composition.physical_examination.v2"), "1.0");
        composition = new Composition(id, "at0001", name, archetypeDetails,
                null, null, null, content, TestTerminologyAccess.ENGLISH, context(), 
                provider(), category, territory(), ts);
    }

    public void testValidPath() throws Exception {
        String[] validPathList = {
            "/",  // root
            "/content[section one]", 
            "/content[section two]", 
            "/content[section two]/items[observation]",
            
        };

        for (String path : validPathList) {
            assertTrue("unexpected invalid path: " + path,
                    composition.validPath(path));
        }

        String[] invalidPathList = {
            "", null, "[composition]", "/composition", "/[composition]", // bad root
            "/[composition]/content", // incomplete
            "/[composition]/content[section three]",    // unknown section
            "/[composition]/content[section one]",
            "/[composition]/content[section two]",
            "/[composition]/content[section two]/items[observation]",
            "/[composition]/content[section two]/items[observation]/data",
        };

        for (String path : invalidPathList) {
            assertFalse("unexpected valid path: " + path,
                    composition.validPath(path));
        }
    }

    public void testItemAtPath() throws Exception {
        Section sectionOne = (Section)composition.getContent().get(0);
        Section sectionTwo = (Section)composition.getContent().get(1);
        Observation observation = (Observation)sectionTwo.getItems().get(0);

        assertEquals("section one wrong", sectionOne,
                composition.itemAtPath("/content[section one]"));

        assertEquals("section two wrong", sectionTwo,
                composition.itemAtPath("/content[section two]"));

        assertEquals("observation wrong", observation, composition.itemAtPath(
                "/content[section two]/items[observation]"));
    }

    /* field */
    private Composition composition;
}
