/*
 * component:   "openEHR Reference Implementation"
 * description: "Class TerminologyID"
 * keywords:    "common"
 *
 * author:      "Rong Chen <rong@acode.se>"
 * support:     "Acode HB <support@acode.se>"
 * copyright:   "Copyright (c) 2004 Acode HB, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/BRANCHES/RM-1.0-update/libraries/src/java/org/openehr/rm/support/identification/TerminologyID.java $"
 * revision:    "$LastChangedRevision: 2 $"
 * last_change: "$LastChangedDate: 2005-10-12 23:20:08 +0200 (Wed, 12 Oct 2005) $"
 */
package org.openehr.rm.support.identification;

import org.apache.commons.lang.StringUtils;
import org.openehr.rm.Attribute;
import org.openehr.rm.FullConstructor;

/**
 * Terminology identifier. Instances of this class are immutable.
 *
 * @author Rong Chen
 * @version 1.0
 */
public final class TerminologyID extends ObjectID {

    /**
     * Constructs a TerminologyID by name and version
     *
     * @param name
     * @param version null if not present
     * @throws IllegalArgumentException if name is empty
     */
	public TerminologyID(String name, String version) {

        super(toValue(name, version));

        this.name = name;
        this.version = version;
    }

    /**
     * Constructs a TerminologyID by string value
     *
     * @param value
     * @throws IllegalArgumentException if value empty or wrong format
     */
	@FullConstructor
    public TerminologyID(
    		@Attribute(name = "value", required = true)String value) {
        super(value);
        loadValue(value);
    }

    private void loadValue(String value) {
        int leftBrace = value.indexOf("(");
        int rightBrace = value.lastIndexOf(")");
        if (leftBrace > 1 && rightBrace == value.length() - 1) {
            name = value.substring(0, leftBrace);
            version = value.substring(leftBrace + 1, rightBrace);
        } else {
            name = value;
            version = null;
        }
    }

    private static String toValue(String name, String version) {
        if (StringUtils.isEmpty(name)) {
            throw new IllegalArgumentException("empty name");
        }
        return name + ( version == null ?
                "" : "(" + version + ")" );
    }

    /**
     * Name of this terminology ID
     *
     * @return name
     */
    public String name() {
        return name;
    }

    /**
     * Return version of information pointed to by this ID,
     * if versioning is supported.
     *
     * @return versionID null if not present
     */
    public String versionID() {
        return version;
    }

    // POJO start
    TerminologyID() {
    }

    void setValue(String value) {
        loadValue(value);
        super.setValue(value);
    }
    // POJO end

    /* fields */
    private String name;
    private String version;
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
 *  The Original Code is TerminologyID.java
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