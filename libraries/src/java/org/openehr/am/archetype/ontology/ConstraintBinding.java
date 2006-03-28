/*
 * component:   "openEHR Reference Implementation"
 * description: "Class ConstraintBinding"
 * keywords:    "archetype"
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
package org.openehr.am.archetype.ontology;

import org.apache.commons.lang.StringUtils;

/**
 * This class represents a term constraint binding defined by a query
 *
 * @author Rong Chen
 * @version 1.0
 */
public final class ConstraintBinding {

    /**
     * Constructs a term constraint binding
     *
     * @param target
     * @param conditions
     * @throws IllegalArgumentException if either target or conditions empty
     */
    public ConstraintBinding(String target, String conditions) {
        if(StringUtils.isEmpty(target)) {
            throw new IllegalArgumentException("null or empty target");
        }
        if(StringUtils.isEmpty(conditions)) {
            throw new IllegalArgumentException("null or empty conditions");
        }
        this.target = target;
        this.conditions = conditions;
    }

    /**
     * Target of this binding
     *
     * @return target
     */
    public String getTarget() {
        return target;
    }

    /**
     * Conditions of this constraint
     *
     * @return conditions
     */
    public String getConditions() {
        return conditions;
    }

    /* fields */
    private String target;
    private String conditions;
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
 *  The Original Code is ConstraintBinding.java
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