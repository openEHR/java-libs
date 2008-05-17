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
import org.openehr.rm.support.identification.UIDBasedID;
import org.openehr.rm.support.identification.HierObjectID;
import org.openehr.rm.support.identification.ArchetypeID;
import org.openehr.rm.support.terminology.OpenEHRCodeSetIdentifiers;
import org.openehr.rm.support.terminology.TestCodeSetAccess;
import org.openehr.rm.support.terminology.TerminologyService;
import org.openehr.rm.support.terminology.TerminologyAccess;
import org.openehr.rm.support.terminology.CodeSetAccess;
import org.openehr.rm.composition.content.navigation.Section;
import org.openehr.rm.composition.content.entry.Observation;
import org.openehr.rm.common.archetyped.Archetyped;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import org.openehr.rm.support.terminology.TestTerminologyAccess;
import org.openehr.terminology.SimpleTerminologyService;

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
        UIDBasedID id = new HierObjectID("1.11.2.3.4.5.0");
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

					public Set<CodePhrase> codesForGroupId(String arg0) {
						return null;
					}

					public boolean hasCodeForGroupId(String arg0,
							CodePhrase arg1) {
						return true;
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

			public List<String> terminologyIdentifiers() {
				// TODO Auto-generated method stub
				return null;
			}

			public List<String> codeSetIdentifiers() {
				// TODO Auto-generated method stub
				return null;
			}

			public Map<String, String> openehrCodeSets() {
				// TODO Auto-generated method stub
				return null;
			}

			public CodeSetAccess codeSetForId(OpenEHRCodeSetIdentifiers arg0) {
				return new TestCodeSetAccess();
			}
        };
        DvText name = new DvText("composition");
        UIDBasedID id = new HierObjectID("1.11.2.4.22.5.2");
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
    
    public void testBuildCompositionWithOpenEHRTerminology() throws Exception {
    	DvText name = new DvText("composition2");
        UIDBasedID id = new HierObjectID("1.11.2.4.22.5.3");
        List<ContentItem> content = new ArrayList<ContentItem>();
        content.add(section("section one"));
        
        TerminologyService ts = SimpleTerminologyService.getInstance();
        CodePhrase lang = new CodePhrase("ISO_639-1", "en");
        CodePhrase charset = new CodePhrase("IANA_character-sets", "UTF-8");
        CodePhrase event = new CodePhrase("openehr", "433");
        DvCodedText category = new DvCodedText("event", lang, charset, event, 
        		ts);
        CodePhrase territory = new CodePhrase("ISO_3166-1", "SE");
        
        Archetyped archetypeDetails = new Archetyped(new ArchetypeID(
                "openehr-ehr_rm-Composition.physical_examination.v2"), "1.0");
        composition = new Composition(id, "at0001", name, archetypeDetails,
                null, null, null, content, lang, context(), 
                provider(), category, territory, ts);
    }

    public void testItemAtPathWhole() throws Exception {
    	path = "/";
    	value = composition.itemAtPath(path);    		
        assertEquals("unexpected value at path: " + path, composition, value);
    }

    /* field */
    private Composition composition;
    private String path;
    private Object value;
}
