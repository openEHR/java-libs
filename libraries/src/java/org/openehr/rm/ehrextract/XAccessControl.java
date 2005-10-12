/*
 * component:   "openEHR Reference Implementation"
 * description: "Class XAccessControl"
 * keywords:    "ehr_extract"
 *
 * author:      "Rong Chen <rong@acode.se>"
 * support:     "Acode HB <support@acode.se>"
 * copyright:   "Copyright (c) 2004,2005 Acode HB, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL$"
 * revision:    "$LastChangedRevision$"
 * last_change: "$LastChangedDate$"
 */
package org.openehr.rm.ehrextract;

import org.openehr.rm.support.identification.ObjectID;
import org.openehr.rm.datastructure.itemstructure.ItemStructure;

import java.util.Map;

/**
 * Container class for all access control data required in an EHR Extract.
 * The list of Access groups must be supplied except in the case when an EHR
 * extract is sent within the one environment, and the receiver system has
 * access to the same access control server as the sender.
 *
 * @author Rong Chen
 * @version 1.0
 */
public class XAccessControl {

    /**
     * Constructs a XAccessControl
     *
     * @param groups        null if unspecified, not empty
     * @param details       null if unspecified
     * @throws IllegalArgumentException if groups empty
     */
    public XAccessControl(Map<ObjectID, Object> groups,
                          ItemStructure details) {
        if(groups != null && groups.isEmpty()) {
            throw new IllegalArgumentException("empty groups");
        }
        this.groups = groups;
        this.details = details;
    }

    /**
     * Other access control details
     *
     * @return details or null if unspecified
     */
    public ItemStructure getDetails() {
        return details;
    }

    /**
     * Obtain the access group for the given key
     *
     * @param key
     * @return null if groups unspecified or group not found for given key
     */
    // todo: return type should be AccessGroup
    public Object group(ObjectID key) {
        if(groups == null) {
            return null;
        }
        return groups.get(key);
    }

    /* fields */
    private Map<ObjectID, Object> groups; // todo: value should be AccessGroup
    private ItemStructure details;
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
 *  The Original Code is XAccessControl.java
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