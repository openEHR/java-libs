/*
 * component:   "openEHR Reference Implementation"
 * description: "Class PartyRef"
 * keywords:    "common"
 *
 * author:      "Rong Chen <rong@acode.se>"
 * support:     "Acode HB <support@acode.se>"
 * copyright:   "Copyright (c) 2004 Acode HB, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/TRUNK/libraries/src/java/org/openehr/rm/support/identification/PartyRef.java $"
 * revision:    "$LastChangedRevision: 2 $"
 * last_change: "$LastChangedDate: 2005-10-12 22:20:08 +0100 (Wed, 12 Oct 2005) $"
 */
package org.openehr.rm.support.identification;

import org.openehr.rm.Attribute;
import org.openehr.rm.FullConstructor;

/**
 * Identifier for parties in a demographic service. There are
 * typically a number of subtypes of the "PARTY" class, including
 * "PERSON", "ORGANISATION", etc.
 *
 * @author Rong Chen
 * @version 1.0
 */
public class PartyRef extends ObjectRef {

    // POJO start
	PartyRef() {
    }
    // POJO end

    /**
     * Construt a PartyRef
     *
     * @param id
     * @throws IllegalArgumentException if id or type null
     */
	@FullConstructor
    public PartyRef(
    		@Attribute(name = "id", required = true)ObjectID id, 
    		@Attribute(name = "type", required = true)String type) {
        super(id, "DEMOGRAPHIC", type);        
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
 *  The Original Code is PartyRef.java
 *
 *  The Initial Developer of the Original Code is Rong Chen.
 *  Portions created by the Initial Developer are Copyright (C) 2003-2008
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