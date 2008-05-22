/*
 * component:   "openEHR Reference Implementation"
 * description: "Class InternetID"
 * keywords:    "support"
 *
 * author:      "Yin Su Lim <y.lim@chime.ucl.ac.uk>"
 * support:     "CHIME, UCL"
 * copyright:   "Copyright (c) 2006 UCL, UK"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/TRUNK/libraries/src/java/org/openehr/rm/support/identification/InternetID.java $"
 * revision:    "$LastChangedRevision: 53 $"
 * last_change: "$LastChangedDate: 2006-08-11 13:20:08 +0100 (Fri, 11 Aug 2006) $"
 */
package org.openehr.rm.support.identification;

import org.openehr.rm.Attribute;
import org.openehr.rm.FullConstructor;

/**
 * Model of a reverse internet domain, as used to uniquely identify an internet domain.
 * 
 * @author Yin Su Lim
 * @version 1.0
 *
 */
public class InternetID extends UID {

    /**
     * @param value
     */
	@FullConstructor
    public InternetID(@Attribute(name = "value", required = true)String value) {
        super(value);
        if (!value.matches(PATTERN)) {
            throw new IllegalArgumentException("wrong format");
        }
                /* or checking using java.net.URL ?
                try {
                        URL url = new URL("http", value, 0, "");
                } catch (MalformedURLException e) {
                        throw new IllegalArgumentException("wrong format");
                }
                 * but this won't check the format of host or domain.
                 */
    }
    
    private static String PATTERN = "[a-zA-Z]([a-zA-Z0-9-]*[a-zA-Z0-9])?(\\.[a-zA-Z]([a-zA-Z0-9-]*[a-zA-Z0-9])?)*";
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
 *  The Original Code is InternetID.java
 *
 *  The Initial Developer of the Original Code is Yin Su Lim.
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