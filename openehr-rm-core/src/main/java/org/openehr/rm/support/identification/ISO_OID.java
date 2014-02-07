/*
 * component:   "openEHR Reference Implementation"
 * description: "Class ISO_OID"
 * keywords:    "common"
 *
 * author:      "Rong Chen <rong@acode.se>"
 * support:     "Acode HB <support@acode.se>"
 * copyright:   "Copyright (c) 2004 Acode HB, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/BRANCHES/RM-1.0-update/libraries/src/java/org/openehr/rm/support/identification/ISO_OID.java $"
 * revision:    "$LastChangedRevision: 2 $"
 * last_change: "$LastChangedDate: 2005-10-12 23:20:08 +0200 (Wed, 12 Oct 2005) $"
 */
package org.openehr.rm.support.identification;

import org.ietf.jgss.GSSException;
import org.ietf.jgss.Oid;
import org.openehr.rm.Attribute;
import org.openehr.rm.FullConstructor;

/**
 * Purpose Model of ISO s Object Identifier (oid) as defined by the
 * standard ISO/IEC 8824 . Oids are formed from integers separated by
 * dots. Each non-leaf node in an Oid starting from the left
 * corresponds to an assigning authority, and identifies that
 * authority s namespace, inside which the remaining part of the
 * identifier is locally unique.
 *
 * @author Rong Chen
 * @version 1.0
 */
public class ISO_OID extends UID {

    /**
     * Constructs an ISO_OID by string value
     *
     * @param value
     * @throws IllegalArgumentException if value null
     *          or wrong format
     */
	@FullConstructor
    public ISO_OID(@Attribute(name = "value", required = true)String value) {
        super(value);
        try {
            new Oid(value);
        } catch(GSSException gsse) {
            throw new IllegalArgumentException(
                    "The provided value is not a legal format for an OID as defined by the ISO/IEC 8824. " + gsse); // root can only be 0,1,2 and for 0 and 1 then only 0..39 arcs
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
 *  The Original Code is ISO_OID.java
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