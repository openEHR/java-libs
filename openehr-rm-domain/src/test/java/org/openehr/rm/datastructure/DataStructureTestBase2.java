/*
 * component:   "openEHR Reference Implementation"
 * description: "Class DataStructureTestBase"
 * keywords:    "unit test"
 *
 * author:      "Rong Chen <rong@acode.se>"
 * support:     "Acode HB <support@acode.se>"
 * copyright:   "Copyright (c) 2004,2006 Acode HB, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/BRANCHES/RM-1.0-update/libraries/src/test/org/openehr/rm/datastructure/DataStructureTestBase.java $"
 * revision:    "$LastChangedRevision: 2 $"
 * last_change: "$LastChangedDate: 2005-10-12 23:20:08 +0200 (Wed, 12 Oct 2005) $"
 */
/**
 * ItemStructureTest
 *
 * @author Rong Chen
 * @version 1.0
 */
package org.openehr.rm.datastructure;

import java.util.List;

import junit.framework.TestCase;

import org.openehr.rm.datastructure.itemstructure.ItemStructure;
import org.openehr.rm.datastructure.itemstructure.representation.Cluster;
import org.openehr.rm.datastructure.itemstructure.representation.Element;
import org.openehr.rm.datastructure.itemstructure.representation.Item;
import org.openehr.rm.datatypes.basic.DataValue;
import org.openehr.rm.datatypes.quantity.DvQuantity;
import org.openehr.rm.datatypes.quantity.DvProportion;
import org.openehr.rm.datatypes.quantity.ProportionKind;
import org.openehr.rm.datatypes.text.CodePhrase;
import org.openehr.rm.datatypes.text.DvCodedText;
import org.openehr.rm.datatypes.text.DvText;
import org.openehr.rm.support.identification.TerminologyID;

public class DataStructureTestBase2 extends TestCase {

	public DataStructureTestBase2(String test) {
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

	// create a cluster
	protected Cluster cluster(String archetypeNodeId, String name,
			List<Item> items) {
		return new Cluster(archetypeNodeId, text(name), items);
	}

	// create an element by same name, value and code
	protected Element element(String name) {
		return element(name, name, name, name);
	}

	// create an element by name and code
	protected Element element(String name, String code) {
		return element(name, name, code, code);
	}

	// create an element by name and value
	protected Element element(String name, DataValue value) {
		return new Element("at001", text(name), value);
	}

	// create an element by name, and ratio
	protected Element element(String name, double numerator, double denominator) {
		return element(name, proportion(numerator, denominator));
	}

	// create an element with text value
	protected Element element(String archetypeNodeId, String name, String value) {
		return new Element(archetypeNodeId, text(name), text(value));
	}

	// create an element with codedText value
	protected Element element(String archetypeNodeId, String name,
			String value, String code) {
		return new Element(archetypeNodeId, text(name), codedText(value, code));
	}

	// create an element with quanity value
	protected Element element(String archetypeNodeId, String name, double value) {
		return new Element(archetypeNodeId, text(name), new DvQuantity(value));
	}

	// create a text
	protected DvText text(String value) {
		return new DvText(value);
	}

	// create a codeText
	protected DvCodedText codedText(String value, String code) {
		CodePhrase codePhrase = new CodePhrase(new TerminologyID("SNOMED CT"),
				code);
		return new DvCodedText(value, codePhrase);
	}

	// create a quantityRatio
	protected DvProportion proportion(double numerator, double denominator) {
		return new DvProportion(numerator, denominator, ProportionKind.FRACTION, 0);
	}

	protected static final String sep = ItemStructure.PATH_SEPARATOR;
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
 *  The Original Code is DataStructureTestBase.java
 *
 *  The Initial Developer of the Original Code is Rong Chen.
 *  Portions created by the Initial Developer are Copyright (C) 2003-2006
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