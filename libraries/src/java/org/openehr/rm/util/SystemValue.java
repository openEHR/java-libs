/*
 * component:   "openEHR Reference Implementation"
 * description: "Class SystemValue"
 * keywords:    "util"
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
package org.openehr.rm.util;

import java.util.HashMap;
import java.util.Map;

/**
 * Enumeration of values that are supplied by the system
 *
 * @author Rong Chen
 * @version 1.0
 */
public enum SystemValue {

    LANGUAGE ("language"),
    CHARSET ("charset"),
    TERMINOLOGY_SERVICE ("terminologyService"),
    MEASUREMENT_SERVICE ("measurementService"),
    SUBJECT ("subject"),
    PROVIDER ("provider"),
    COMPOSER ("composer"),
    TERRITORY ("territory"),
    CONTEXT ("context"),
    CATEGORY ("category"),
    UID("uid");

    /* field */
    private final String id;
    private static final Map<String, SystemValue> idMap;
    static {
        SystemValue[] list = { LANGUAGE, CHARSET, TERMINOLOGY_SERVICE,
            MEASUREMENT_SERVICE, SUBJECT, PROVIDER, COMPOSER, TERRITORY, CONTEXT,
            CATEGORY, UID
        };
        idMap = new HashMap<String, SystemValue>();
        for(SystemValue value : list) {
            idMap.put(value.id(), value);
        }
    }

    /* constructor */
    private SystemValue(String id) {
        this.id = id;
    }

    /**
     * Id of this system value
     *
     * @return id
     */
    public String id() {
        return id;
    }

    /**
     * Return system value with matching id
     *
     * @param id
     * @return null if not found
     */
    public static SystemValue fromId(String id) {
        return idMap.get(id);
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
 *  The Original Code is SystemValue.java
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