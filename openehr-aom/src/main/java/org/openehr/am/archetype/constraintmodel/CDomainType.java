/*
 * component:   "openEHR Reference Implementation"
 * description: "Class CDomainType"
 * keywords:    "archetype"
 *
 * author:      "Rong Chen <rong@acode.se>"
 * support:     "Acode HB <support@acode.se>"
 * copyright:   "Copyright (c) 2004 Acode HB, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/TRUNK/libraries/src/java/org/openehr/am/archetype/constraintmodel/CDomainType.java $"
 * revision:    "$LastChangedRevision: 43 $"
 * last_change: "$LastChangedDate: 2006-08-08 12:54:07 +0200 (Tue, 08 Aug 2006) $"
 */
package org.openehr.am.archetype.constraintmodel;

import org.openehr.rm.support.basic.Interval;

/**
 * Abstract parent type of domain-specific constrainer types, to be defined in
 * external packages.
 *
 * @author Rong Chen
 * @version 1.0
 */
public abstract class CDomainType extends CObject {

	/**
	 * Constructs a DomainTypeConstraint
	 *
	 * @param path
	 * @param rmTypeName
	 * @param occurrences
	 * @param nodeID
	 */
	protected CDomainType(String path, String rmTypeName,
			Interval<Integer> occurrences, String nodeID) {
		super(false, path, rmTypeName, occurrences, nodeID);
	}

	/**
	 * Creates an ObjectConstraint
	 *
	 * @param path
	 * @param rmTypeName
	 */
	protected CDomainType(String path, String rmTypeName) {
		this(path, rmTypeName, null, null);
	}

	/**
	 * Standard form of constraint
	 *
	 * @return Standard form of constraint
	 */
	public abstract CComplexObject standardRepresentation();	
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
 *  The Original Code is CDomainType.java
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