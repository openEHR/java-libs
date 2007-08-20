/*
 * component:   "openEHR Reference Implementation"
 * description: "Class CPrimitiveObject"
 * keywords:    "archetype"
 *
 * author:      "Rong Chen <rong@acode.se>"
 * support:     "Acode HB <support@acode.se>"
 * copyright:   "Copyright (c) 2004 Acode HB, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/TRUNK/libraries/src/java/org/openehr/am/archetype/constraintmodel/CPrimitiveObject.java $"
 * revision:    "$LastChangedRevision: 2 $"
 * last_change: "$LastChangedDate: 2005-10-12 23:20:08 +0200 (Wed, 12 Oct 2005) $"
 */
package org.openehr.am.archetype.constraintmodel;

import org.openehr.am.archetype.constraintmodel.primitive.CPrimitive;
import org.openehr.rm.support.basic.Interval;

/**
 * PrimitiveObject Constraint
 *
 * @author Rong Chen
 * @version 1.0
 */
public class CPrimitiveObject extends CDefinedObject {

	/**
	 * Constructs a PrimitiveObjectConstraint
	 *
	 * @param path
	 * @param occurrences
	 * @param nodeId
	 * @param parent
	 * @param item
	 */
	public CPrimitiveObject(String path, Interval<Integer> occurrences,
			String nodeId, CAttribute parent, CPrimitive item) {

		super(item == null, path, item == null ? null : item.getType(),
				occurrences, nodeId, parent, 
				item == null ? null : item.assumedValue());
		this.item = item;
	}

	/**
	 * Return true if the constraint has limit the possible value to
	 * be only one, which means the value has been assigned by the archetype
	 * author at design time
	 *
	 * @return true if has
	 */
	public boolean hasAssignedValue() {
		return item.hasAssignedValue();
	}

	/**
	 * Object actually defining the constraint.
	 *
	 * @return primitive constraint null if unspecified
	 */
	public CPrimitive getItem() {
		return item;
	}

	/**
	 * True if this node is a valid archetype node.
	 *
	 * @return ture if valid
	 */
	public boolean isValid() {
		return false; // todo: implement this method
	}

	/**
	 * True if the relative path exists at this node.
	 *
	 * @param path
	 * @return ture if has
	 * @throws IllegalArgumentException if path null
	 */
	public boolean hasPath(String path) {
		return false; // todo: implement this method
	}

	/**
	 * True if constraints represented by other are narrower than this node.
	 *
	 * @param constraint
	 * @return true if subset
	 * @throws IllegalArgumentException if constraint null
	 */
	public boolean isSubsetOf(ArchetypeConstraint constraint) {
		return false; // todo: implement this method
	}

	/* fields */
	private CPrimitive item;
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
 *  The Original Code is CPrimitiveObject.java
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