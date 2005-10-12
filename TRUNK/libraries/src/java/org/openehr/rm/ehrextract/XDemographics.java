/*
 * component:   "openEHR Reference Implementation"
 * description: "Class XDemographics"
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
import org.openehr.rm.demographic.Party;
import org.openehr.rm.datastructure.itemstructure.ItemStructure;

import java.util.Map;

/**
 * Purpose Container class for all demograhic data required in an EHR Extract.
 * The list of Parties must be supplied except in the case when an EHR extract
 * is sent within the one environment, and the receiver system has access to
 * the same demographic server as the sender.
 *
 * @author Rong Chen
 * @version 1.0
 */
public class XDemographics {

    /**
     * Constructs a XDemographics
     *
     * @param parties       null if unspecified
     * @param details       null if unspecified
     * @throws IllegalArgumentException if parties empty
     */
    public XDemographics(Map<ObjectID, Party> parties, ItemStructure details) {
        if(parties != null && parties.isEmpty()) {
            throw new IllegalArgumentException("empty paries");
        }
        this.parties = parties;
        this.details = details;
    }

    /**
     * Other demographic details
     *
     * @return details or null if unspecified
     */
    public ItemStructure getDetails() {
        return details;
    }

    /**
     * Obtain the party for the given key
     *
     * @param key
     * @return null if parties unspecified or party not found for given key
     */
    public Party party(ObjectID key) {
        if(parties == null) {
            return null;
        }
        return parties.get(key);
    }

    /* fields */
    private Map<ObjectID,Party> parties;
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
 *  The Original Code is XDemographics.java
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