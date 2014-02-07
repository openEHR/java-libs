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

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
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
	 * @throws IllegalArgumentException if list is empty
	 */
	public CDvQuantity(String path, Interval<Integer> occurrences, 
			String nodeId, CAttribute parent, List<CDvQuantityItem> list,
			CodePhrase property, DvQuantity defaultValue, 
			DvQuantity assumedValue) {

		super(list == null && property == null, path, "DV_QUANTITY",
				occurrences, nodeId, defaultValue, assumedValue, parent);

		if (list != null && list.isEmpty()) {
			throw new IllegalArgumentException("empty list");
		}
		this.list = list == null ? list 
				: new ArrayList<CDvQuantityItem>(list);
		this.property = property;
	}
	
	public CDvQuantity copy() {
		return new CDvQuantity(path(), getOccurrences(), getNodeId(), 
				getParent(), list,	property, getDefaultValue(), 
				getAssumedValue());
	}
	
	/**
	 * Convenience constructor
	 * 
	 * @param path
	 * @param occurrences
	 * @param list
	 */
	public CDvQuantity(String path, Interval<Integer> occurrences,
			List<CDvQuantityItem> list) {
		this(path, occurrences, null, null, list, null, null, null);
	}
	
	/**
	 * Convenience constructor
	 * 
	 * @param path
	 * @param occurrences
	 * @param list
	 * @param property
	 */
	public CDvQuantity(String path, Interval<Integer> occurrences,
			List<CDvQuantityItem> list, CodePhrase property) {
		this(path, occurrences, null, null, list, property, null, null);
	}
	
	/**
	 * Create a required CDvQuantity with a single item
	 * 
	 * @param path
	 * @param item	not null
	 */
	public static CDvQuantity singleRequired(String path, CDvQuantityItem item) {
		if(item == null) {
			throw new IllegalArgumentException("item null");
		}
		Interval<Integer> occurrences = new Interval<Integer>(1,1);
		List<CDvQuantityItem> list = new ArrayList<CDvQuantityItem>();
		list.add(item);
		return new CDvQuantity(path, occurrences, list);
	}
	
	/**
	 * Create a CDvQuantity that would allow any DV_QUANTITY value
	 * @param path
	 * @return
	 */
	public static CDvQuantity anyAllowed(String path) {
		Interval<Integer> occurrences = new Interval<Integer>(1,1);
		return new CDvQuantity(path, occurrences, null);
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
	 * Removes the given item from the list
	 * 
	 * @param item not null
	 */
	public void removeItem(CDvQuantityItem item) {
		if(list != null && item != null) {
			list.remove(item);
		}
	}
	
	/**
	 * Removes the items that have the given units
	 * 
	 * @param item not null
	 */
	public void removeItemByUnitsList(String[] unitsList) {
		if(list == null || unitsList == null) {
			return;
		}
		for(Iterator<CDvQuantityItem> it =  list.iterator(); it.hasNext(); ) {
			CDvQuantityItem item = it.next();
			for(String units : unitsList) {
				if(units.equals(item.getUnits())) {
					it.remove();
					break;
				}
			}
		}
	}		
	
	/**
	 * Adds a c_dv_quantity_item by given units
	 * 
	 * @param units
	 */
	public void addItem(CDvQuantityItem item) {
		if(list == null) {
			list = new ArrayList<CDvQuantityItem>();			
		}
		list.add(item);
	}
	

	/**
	 * Optional constraint on units property
	 * 
	 * @return
	 */
	public CodePhrase getProperty() {
		return property;
	}
	
	/**
     * Returns true if fields are the same
     */
    public boolean equals(Object o) {
    	if (this == o) return true;
        if (!( o instanceof CDvQuantity )) return false;

        final CDvQuantity cq = (CDvQuantity) o;

        return new EqualsBuilder()
        		.appendSuper(super.equals(o))
                .append(list, cq.list)
                .append(property, cq.property)
                .isEquals();
    }
    
    /**
     * Returns the hashcode of this object
     * 
     * @return hashcode
     */
    public int hashCode() {
        return new HashCodeBuilder(11, 37)
        		.appendSuper(super.hashCode())
                .append(list)
                .append(property)
                .toHashCode();
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
	public boolean validValue(DvQuantity value) {
		if(list == null) {
			return true;
		}
		for(CDvQuantityItem item : list) {
			if( ! item.getUnits().equals(value.getUnits())) {
				continue;
			}
			if(item.getMagnitude() != null 
					&& !item.getMagnitude().has(value.getMagnitude())) {
				continue;
			}
			
			// TODO precision check
			return true;			
		}
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