/*
 * component:   "openEHR Reference Implementation"
 * description: "Class PartyReference"
 * keywords:    "common"
 *
 * author:      "Rong Chen <rong@acode.se>"
 * support:     "Acode HB <support@acode.se>"
 * copyright:   "Copyright (c) 2004 Acode HB, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/TRUNK/libraries/src/java/org/openehr/rm/support/identification/PartyReference.java $"
 * revision:    "$LastChangedRevision: 2 $"
 * last_change: "$LastChangedDate: 2005-10-12 22:20:08 +0100 (Wed, 12 Oct 2005) $"
 */
package org.openehr.rm.support.identification;

/**
 * Identifier for parties in a demographic service. There are
 * typically a number of subtypes of the "PARTY" class, including
 * "PERSON", "ORGANISATION", etc.
 *
 * @author Rong Chen
 * @version 1.0
 */
public class PartyReference extends ObjectRef {

    // POJO start
    PartyReference() {
    }
    // POJO end

    /**
     * Construt a PartyReference
     *
     * @param id
     * @throws IllegalArgumentException if id or type null
     */
    public PartyReference(ObjectID id, ObjectRef.Type type) {
        super(id, ObjectRef.Namespace.DEMOGRAPHIC,
                type);
        if (!type.equals(ObjectRef.Type.PARTY) &&
        		!type.equals(ObjectRef.Type.PERSON) && 
        		!type.equals(ObjectRef.Type.ORGANISATION) &&
        		!type.equals(ObjectRef.Type.GROUP) &&
        		!type.equals(ObjectRef.Type.AGENT) && 
        		!type.equals(ObjectRef.Type.ROLE)) {
        		throw new IllegalArgumentException("incorrect type for party");
        }
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
 *  The Original Code is PartyReference.java
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