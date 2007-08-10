/*
 * component:   "openEHR Reference Implementation"
 * description: "Class VersionedCompositionTest"
 * keywords:    "unit test"
 *
 * author:      "Rong Chen <rong@acode.se>"
 * support:     "Acode HB <support@acode.se>"
 * copyright:   "Copyright (c) 2004 Acode HB, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/BRANCHES/RM-1.0-update/libraries/src/test/org/openehr/rm/ehr/VersionedCompositionTest.java $"
 * revision:    "$LastChangedRevision: 50 $"
 * last_change: "$LastChangedDate: 2006-08-10 13:21:46 +0200 (Thu, 10 Aug 2006) $"
 */
/**
 * VersionedCompositionTest
 *
 * @author Rong Chen
 * @version 1.0 
 */
package org.openehr.rm.ehr;

import junit.framework.TestCase;

import org.openehr.rm.common.generic.AuditDetails;
import org.openehr.rm.common.generic.Participation;
import org.openehr.rm.common.generic.PartyIdentified;
import org.openehr.rm.common.archetyped.Archetyped;
import org.openehr.rm.composition.CompositionTestBase;
import org.openehr.rm.support.identification.*;
import org.openehr.rm.composition.Composition;
import org.openehr.rm.composition.EventContext;
import org.openehr.rm.composition.content.ContentItem;
import org.openehr.rm.composition.content.entry.Observation;
import org.openehr.rm.composition.content.navigation.Section;
import org.openehr.rm.datastructure.itemstructure.ItemSingle;
import org.openehr.rm.datastructure.itemstructure.ItemStructure;
import org.openehr.rm.datastructure.itemstructure.representation.Element;
import org.openehr.rm.datatypes.quantity.DvInterval;
import org.openehr.rm.datatypes.quantity.datetime.DvDateTime;
import org.openehr.rm.datatypes.text.CodePhrase;
import org.openehr.rm.datatypes.text.DvCodedText;
import org.openehr.rm.datatypes.text.DvText;
import org.openehr.rm.support.terminology.TerminologyService;
import org.openehr.rm.support.terminology.TestCodeSetAccess;
import org.openehr.rm.support.terminology.TestTerminologyAccess;
import org.openehr.rm.support.terminology.TestTerminologyService;

import java.util.ArrayList;
import java.util.List;

public class VersionedCompositionTest extends CompositionTestBase {

    public VersionedCompositionTest(String test) {
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

    public void testCreateVersionedComposition() throws Exception {
        VersionedComposition vc = versionedComposition("at0001", "first");
        assertEquals("size", 1, vc.allVersions().size());
    }

    public void testCommit() throws Exception {
        VersionedComposition vc = versionedComposition("at0001", "first");

        assertExceptionThrown(vc, composition("at0002", "second"));
        assertExceptionThrown(vc, compositionPersistent("at0001", "second"));
        
        // todo: need to test different category

        vc.commitOriginalVersion(new ObjectVersionID("1.2.4.7::1.2.40.14.1.2.2::2"), 
                new ObjectVersionID("1.2.4.7::1.2.40.14.1.2.2::1"), 
                composition("at0001", "subsequent"),
                audit(TestCodeSetAccess.AMENDMENT), contribution("1.5.25.6.2.1.2"),
                 TestCodeSetAccess.AMENDMENT, null, ts);
    }

    private void assertExceptionThrown(VersionedComposition vc,
                                       Composition data) throws Exception {
         try {
            vc.commitOriginalVersion(new ObjectVersionID("1.7.5.2::1.2.40.14.1.2.2::2"), 
                new ObjectVersionID("1.7.5.2::1.2.40.14.1.2.2::1"), data,
                audit(TestCodeSetAccess.AMENDMENT), contribution("1.5.25.6.2.1.2"),
                 TestCodeSetAccess.AMENDMENT, null, ts);
            fail("exception should be thrown");
        } catch(Exception e) {
            assertTrue(e instanceof IllegalArgumentException);
        }
    }

    // test versioned composition
    private VersionedComposition versionedComposition(String node, String text)
            throws Exception {
        HierObjectID id = new HierObjectID("1.2.4.7");
        Composition firstData = composition(node, text);
        ObjectRef ehrRef = new ObjectRef(
                new HierObjectID("1.2.0.0.0.1.2"),
                ObjectRef.Namespace.LOCAL,
                ObjectRef.Type.EHR);
  
        return new VersionedComposition(id, ehrRef, new DvDateTime(), 
                new ObjectVersionID("1.2.4.7::1.2.40.14.1.2.2::1"), firstData, 
                TestCodeSetAccess.CREATION, audit(TestCodeSetAccess.CREATION), contribution("1.2.3.1"), 
                null,ts);
    }

    // test audit
    private AuditDetails audit(DvCodedText changeType) throws Exception {

        return new AuditDetails("/", provider(), new DvDateTime(),
                changeType, new DvText("desc"),
                TestTerminologyService.getInstance());
    }

    // test contribution
    private ObjectRef contribution(String id) throws Exception {
        return new ObjectRef(new HierObjectID(id),
                ObjectRef.Namespace.LOCAL,
                ObjectRef.Type.CONTRIBUTION);
    }

    // test composition
    private Composition composition(String node, String text)
            throws Exception {
        DvText name = new DvText(text, lang, charset, ts);
        ObjectID id = new HierObjectID("1.11.2.5.1.66.3");
        List<ContentItem> content = new ArrayList<ContentItem>();
        content.add(section());
        DvCodedText category = TestCodeSetAccess.EVENT;
        Archetyped archetypeDetails = new Archetyped(new ArchetypeID(
                "openehr-ehr_rm-Composition.physical_examination.v2"), "1.0");
        return new Composition(id, node, name, archetypeDetails, null, 
                null, null, content, TestTerminologyAccess.ENGLISH, context(), 
                provider(), category, territory(), ts);
    }

    private Composition compositionPersistent(String node, String text)
            throws Exception {
        DvText name = new DvText(text, lang, charset, ts);
        ObjectID id = new HierObjectID("1.11.2.5.1.66.3");
        List<ContentItem> content = new ArrayList<ContentItem>();
        content.add(section());
        DvCodedText category = TestCodeSetAccess.PERSISTENT;
        Archetyped archetypeDetails = new Archetyped(new ArchetypeID(
                "openehr-ehr_rm-Composition.physical_examination.v2"), "1.0");
        return new Composition(id, node, name, archetypeDetails, null, 
                null, null, content, TestTerminologyAccess.ENGLISH, null, 
                provider(), category, territory(), ts);
    }
    
    private Section section() throws Exception {
        DvText name = new DvText("test section", lang, charset, ts);
        List<ContentItem> items = new ArrayList<ContentItem>();
        items.add(observation());
        return new Section("at0000", name, items);
    }

    // test element
    private Element element() throws Exception {
        DvText name = new DvText("test element", lang, charset, ts);
        DvText value = new DvText("test value", lang, charset, ts);
        return new Element("at0000", name, value);
    }

    private ItemSingle itemSingle() throws Exception {
        DvText name = new DvText("test item single", lang, charset, ts);
        return new ItemSingle("at0000", name, element());
    }

    private CodePhrase lang = TestCodeSetAccess.ENGLISH;
    private CodePhrase charset = TestCodeSetAccess.LATIN_1;
    private TerminologyService ts = TestTerminologyService.getInstance();
}
/*
 *  ***** BEGIN LICENSE BLOCK *****
 *  Version: MPL 1.1/GPL 2.0/LGPL 2.1
 *
 *  The contents of this file are subject to the Mozilla Public License Version
 *  1.1 (the 'License'); you may not use this file except in compliance with
 *  the License. You may obtain a copy of the License at
 *  http://www.mozilla.org/MPL/
 *
 *  Software distributed under the License is distributed on an 'AS IS' basis,
 *  WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
 *  for the specific language governing rights and limitations under the
 *  License.
 *
 *  The Original Code is VersionedCompositionTest.java
 *
 *  The Initial Developer of the Original Code is Rong Chen.
 *  Portions created by the Initial Developer are Copyright (C) 2003-2004
 *  the Initial Developer. All Rights Reserved.
 *
 *  Contributor(s):
 *
 * Software distributed under the License is distributed on an 'AS IS' basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
 * for the specific language governing rights and limitations under the
 * License.
 *
 *  ***** END LICENSE BLOCK *****
 */