/*
 * component:   "openEHR Reference Implementation"
 * description: "Class CDvQuantity"
 * keywords:    "openehr archetype profile"
 *
 * author:      "Rong Chen <rong@acode.se>"
 * support:     "Acode HB <support@acode.se>"
 * copyright:   "Copyright (c) 2006 Acode HB, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL$"
 * revision:    "$LastChangedRevision$"
 * last_change: "$LastChangedDate$"
 */

package org.openehr.am.openehrprofile.datatypes.quantity;

import java.util.*;

import org.openehr.am.archetype.constraintmodel.ArchetypeConstraint;
import org.openehr.am.archetype.constraintmodel.CAttribute;
import org.openehr.am.archetype.constraintmodel.CComplexObject;
import org.openehr.am.archetype.constraintmodel.CDomainType;
import org.openehr.rm.datatypes.quantity.DvQuantity;
import org.openehr.rm.datatypes.text.CodePhrase;
import org.openehr.rm.support.basic.Interval;

/**
 * This class represents constrain instances of DV_QUANTITY.
 * 
 * @author Rong Chen
 * @version 1.0
 */
public class CDvQuantity extends CDomainType<DvQuantity> {

	/**
	 * Constructor
	 * 
	 * @param path
	 * @param list
	 *            null if unspecified
	 * @param property
	 *            null if unspecified, no empty
	 * @throws IllegalArgumentException if list is empty, 
	 *             or both list and property null
	 */
	public CDvQuantity(String path, Interval<Integer> occurrences, 
			String nodeId, CAttribute parent, List<CDvQuantityItem> list,
			CodePhrase property, DvQuantity defaultValue, 
			DvQuantity assumedValue) {

		super(list == null && property == null, path, "DvQuantity",
				occurrences, nodeId, null, null, parent);

		if (list != null && list.isEmpty()) {
			throw new IllegalArgumentException("empty list");
		}
		this.list = list == null ? list 
				: new ArrayList<CDvQuantityItem>(list);
		this.property = property;
	}

	/**
	 * List of value/units pairs.
	 * 
	 * @return
	 */
	public List<CDvQuantityItem> getList() {
		return list == null ? null 
				: Collections.unmodifiableList(list);
	}

	/**
	 * Optional constraint on units property
	 * 
	 * @return
	 */
	public CodePhrase getProperty() {
		return property;
	}	

	@Override
	public boolean hasPath(String path) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isSubsetOf(ArchetypeConstraint constraint) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isValid() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean validValue(DvQuantity arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public CComplexObject standardEquivalent() {
		// TODO Auto-generated method stub
		return null;
	}
	
	/* fields */
	private List<CDvQuantityItem> list;
	private CodePhrase property;	
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
 *  The Original Code is CDvQuantity.java
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