/*
 * component:   "openEHR Reference Implementation"
 * description: "Class DomainTypeTest"
 * keywords:    "archetype"
 *
 * author:      "Rong Chen <rong@acode.se>"
 * support:     "Acode HB <support@acode.se>"
 * copyright:   "Copyright (c) 2004,2005,2006 Acode HB, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL:$"
 * revision:    "$LastChangedRevision: $"
 * last_change: "$LastChangedDate: $"
 */
package org.openehr.am.serialize;

import java.util.*;

import org.openehr.am.openehrprofile.datatypes.quantity.CDvQuantity;
import org.openehr.am.openehrprofile.datatypes.quantity.CDvQuantityItem;
import org.openehr.rm.datatypes.text.CodePhrase;
import org.openehr.rm.support.basic.Interval;

public class CDvQuantityTest extends SerializerTestBase {

	public void testPrintCDvQuantity() throws Exception {
		CDvQuantityItem item1 = new CDvQuantityItem(new Interval<Double>(0.0,
				200.0), new Interval<Integer>(2, 2), "year");
		CDvQuantityItem item2 = new CDvQuantityItem(new Interval<Double>(1.0,
				36.0), "month");
		List<CDvQuantityItem> list = new ArrayList<CDvQuantityItem>();
		list.add(item1);
		list.add(item2);
		CodePhrase property = new CodePhrase("openehr", "128");
		Interval<Integer> occurrences = new Interval<Integer>(1, 1);
		CDvQuantity cquantity = new CDvQuantity("/path", occurrences, null, null, 
				list, property, null, null);

		clean();
		outputter.printCDvQuantity(cquantity, 0, out);
		verifyByFile("c-dv-quantity-test.adl");
	}

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
 *  The Original Code is DomainTypeTest.java
 *
 *  The Initial Developer of the Original Code is Rong Chen.
 *  Portions created by the Initial Developer are Copyright (C) 2004-2006
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
