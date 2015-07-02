/*
 * component:   "openEHR Reference Implementation"
 * description: "Class EvaluationTest"
 * keywords:    "unit test"
 *
 * author:      "Rong Chen <rong@acode.se>"
 * support:     "Acode HB <support@acode.se>"
 * copyright:   "Copyright (c) 2004 Acode HB, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/BRANCHES/RM-1.0-update/libraries/src/test/org/openehr/rm/composition/content/entry/EvaluationTest.java $"
 * revision:    "$LastChangedRevision: 50 $"
 * last_change: "$LastChangedDate: 2006-08-10 13:21:46 +0200 (Thu, 10 Aug 2006) $"
 */
/**
 * EvaluationTest
 *
 * @author Rong Chen
 * @version 1.0 
 */
package org.openehr.rm.composition.content.entry;

import java.util.ArrayList;
import java.util.List;

import org.openehr.rm.common.archetyped.Archetyped;
import org.openehr.rm.composition.CompositionTestBase;
import org.openehr.rm.datastructure.itemstructure.ItemList;
import org.openehr.rm.datastructure.itemstructure.ItemStructure;
import org.openehr.rm.datastructure.itemstructure.representation.Element;
import org.openehr.rm.support.identification.ArchetypeID;

public class EvaluationTest extends CompositionTestBase {

    public EvaluationTest(String test) {
        super(test);
    }

    /**
     * The fixture clean up called after every test method.
     */
    protected void tearDown() throws Exception {
        evaluation = null;
    }

    /**
     * The fixture set up called before every test method.
     */
    protected void setUp() throws Exception {
        ItemStructure protocol = list("list protocol");
        ItemStructure data = list("list data");
        Archetyped arch = new Archetyped(
                new ArchetypeID("openehr-ehr_rm-evaluation.physical_examination.v3"),
                                "1.1");
        evaluation = new Evaluation(null, "at000", text("evaluation"), arch,
                null, null, null, language("en"), language("en"), subject(), provider(), 
                null, null, protocol, null, data, ts);
    }

    public void testItemAtPath() {
    	path = "/";
    	value = evaluation.itemAtPath(path);
    	assertEquals(evaluation, value);
    }
    
	public void testEquals() throws Exception {
		ItemStructure protocolOne = list("list protocol");
		ItemStructure dataOne = list("list data");
		Archetyped archOne = new Archetyped(new ArchetypeID(
				"openehr-ehr_rm-evaluation.physical_examination.v3"), "1.1");
		Evaluation evaluationOne = new Evaluation(null, "at000",
				text("evaluation"), archOne, null, null, null, language("en"),
				language("en"), subject(), provider(), null, null, protocolOne,
				null, dataOne, ts);

		Evaluation evaluationTwo = new Evaluation(null, "at000",
				text("evaluation"), archOne, null, null, null, language("en"),
				language("en"), subject(), provider(), null, null, protocolOne,
				null, dataOne, ts);

		assertTrue(evaluationOne.equals(evaluationTwo));
	}

	public void testNotEqualsProtocol() throws Exception {
		ItemStructure protocolOne = list("list protocol");
		ItemStructure dataOne = list("list data");
		Archetyped archOne = new Archetyped(new ArchetypeID(
				"openehr-ehr_rm-evaluation.physical_examination.v3"), "1.1");
		Evaluation evaluationOne = new Evaluation(null, "at000",
				text("evaluation"), archOne, null, null, null, language("en"),
				language("en"), subject(), provider(), null, null, protocolOne,
				null, dataOne, ts);

		ItemStructure protocolTwo = buildItem("list protocol");

		Evaluation evaluationTwo = new Evaluation(null, "at000",
				text("evaluation"), archOne, null, null, null, language("en"),
				language("en"), subject(), provider(), null, null, protocolTwo,
				null, dataOne, ts);

		assertFalse(evaluationOne.equals(evaluationTwo));
	}

	public void testNotEqualsData() throws Exception {
		ItemStructure protocolOne = list("list protocol");
		ItemStructure dataOne = list("list data");
		Archetyped archOne = new Archetyped(new ArchetypeID(
				"openehr-ehr_rm-evaluation.physical_examination.v3"), "1.1");
		Evaluation evaluationOne = new Evaluation(null, "at000",
				text("evaluation"), archOne, null, null, null, language("en"),
				language("en"), subject(), provider(), null, null, protocolOne,
				null, dataOne, ts);

		ItemStructure dataTwo = buildItem("list protocol");

		Evaluation evaluationTwo = new Evaluation(null, "at000",
				text("evaluation"), archOne, null, null, null, language("en"),
				language("en"), subject(), provider(), null, null, protocolOne,
				null, dataTwo, ts);

		assertFalse(evaluationOne.equals(evaluationTwo));
	}

	public void testEqualsMixedData() throws Exception {
		ItemStructure protocolOne = list("list protocol");
		ItemStructure dataOne = list("list data");
		Archetyped archOne = new Archetyped(new ArchetypeID(
				"openehr-ehr_rm-evaluation.physical_examination.v3"), "1.1");
		Evaluation evaluationOne = new Evaluation(null, "at000",
				text("evaluation"), archOne, null, null, null, language("en"),
				language("en"), subject(), provider(), null, null, protocolOne,
				null, dataOne, ts);

		ItemStructure dataTwo = buildMixedItem("list data");

		Evaluation evaluationTwo = new Evaluation(null, "at000",
				text("evaluation"), archOne, null, null, null, language("en"),
				language("en"), subject(), provider(), null, null, protocolOne,
				null, dataTwo, ts);

		assertTrue(evaluationOne.equals(evaluationTwo));
	}

	private ItemList buildItem(String name) {
		String[] names = { "field 1" };
		String[] values = { "value 1" };
		List<Element> items = new ArrayList<Element>();
		for (int i = 0; i < names.length; i++) {
			items.add(element(names[i], values[i]));
		}
		return new ItemList("at0100", text(name), items);
	}

	private ItemList buildMixedItem(String name) {
		String[] names = { "field 2", "field 1", "field 3" };
		String[] values = { "value 2", "value 1", "value 3" };
		List<Element> items = new ArrayList<Element>();
		for (int i = 0; i < names.length; i++) {
			items.add(element(names[i], values[i]));
		}
		return new ItemList("at0100", text(name), items);
	}
	
    /* field */
    private Evaluation evaluation;


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
 *  The Original Code is EvaluationTest.java
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