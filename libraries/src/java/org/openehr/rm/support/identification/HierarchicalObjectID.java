/*
 * component:   "openEHR Reference Implementation"
 * description: "Class HierarchicalObjectID"
 * keywords:    "common"
 *
 * author:      "Rong Chen <rong@acode.se>"
 * support:     "Acode HB <support@acode.se>"
 * copyright:   "Copyright (c) 2004 Acode HB, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL$"
 * revision:    "$LastChangedRevision$"
 * last_change: "$LastChangedDate$"
 */
package org.openehr.rm.support.identification;

/**
 * Hierarhical object identifiers.
 * <p/>
 * The syntax of the value attribute is as follows:
 * <blockquote>[context_id '.' ] local_id '(' version_id ')'</blockquote>
 * </p>
 * <p>Instances of this class are immutable.</p>
 *
 * @author Rong Chen
 * @version 1.0
 */
public class HierarchicalObjectID extends ObjectID {

    /**
     * Create objectID by string value and versionID
     *
     * @param value
     * @throws IllegalArgumentException if value is null
     *                                  or localID is missing in the value
     */
    public HierarchicalObjectID(String value) {
        super(value);
        loadValue(value);
    }

    private void loadValue(String value) {
        int leftBrace = value.indexOf("(");
        int rightBrace = value.lastIndexOf(")");

        // both -1 no versionID
        if (leftBrace == rightBrace) {
            versionID = null;

            // shortest formt: C.L(V)
        } else if (leftBrace > 2
                && rightBrace == value.length() - 1) {
            versionID = value.substring(leftBrace + 1, rightBrace);
            value = value.substring(0, leftBrace);

        } else {
            throw new IllegalArgumentException(
                    "bad format: " + value);
        }
        int dotIndex = value.lastIndexOf(".");
        if (dotIndex <= 0) {
            contextID = null;
        } else {
            contextID = new ISO_OID(value.substring(0, dotIndex));
        }
        localID = value.substring(dotIndex + 1);
    }

    // POJO start
    HierarchicalObjectID() {
    }

    protected void setValue(String value) {
        loadValue(value);
        super.setValue(value);
    }
    // POJO end


    /**
     * Constructs a HierarchicalObjectID by contextID, localID
     * and versionID
     *
     * @param contextID null if not present
     * @param localID
     * @param versionID null if not present
     * @throws IllegalArgumentException if localID is mepty
     */
    public HierarchicalObjectID(String contextID, String localID,
                                String versionID) {
        super(toValue(contextID, localID, versionID));
        this.contextID = ( contextID == null ?
                null : new ISO_OID(contextID) );
        this.localID = localID;
        this.versionID = versionID;
    }

    private static String toValue(String contextID, String localID,
                                  String versionID) {
        return new StringBuffer(
                contextID == null ? "" : contextID + ".")
                .append(localID).append(
                        versionID == null ?
                "" : "(" + versionID + ")")
                .toString();
    }

    /**
     * Return version of information pointed to by this ID,
     * if versioning is supported.
     *
     * @return versionID null if not present
     */
    public String versionID() {
        return versionID;
    }

    /**
     * The identifier of the conceptual namespace in which the object
     * exists, within the identification scheme. May be Void.
     *
     * @return contextID null if not present
     */
    public UID contextID() {
        return contextID;
    }

    /**
     * The local identifier of the object within the context.
     *
     * @return localID
     */
    public String localID() {
        return localID;
    }

    /**
     * Return true if this HierarhicalObjectID has context ID
     *
     * @return true if has contextID
     */
    public boolean hasContextID() {
        return contextID != null;
    }

    /* fields */
    private UID contextID;   // optional
    private String localID;
    private String versionID; // optional
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
 *  The Original Code is HierarchicalObjectID.java
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