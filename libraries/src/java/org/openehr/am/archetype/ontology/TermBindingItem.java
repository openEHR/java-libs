/*
 * component:   "openEHR Reference Implementation"
 * description: "Class TermBindingItem"
 * keywords:    "archetype"
 *
 * author:      "Rong Chen <rong@acode.se>"
 * support:     "Acode HB <support@acode.se>"
 * copyright:   "Copyright (c) 2004 Acode HB, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$Source: /usr/local/cvsroot/acode/openehr-kernel/src/java/org/openehr/am/archetype/ontology/TermBindingItem.java,v $"
 * revision:    "$Revision$"
 * last_change: "$Date$"
 */
package org.openehr.am.archetype.ontology;

import java.util.List;
import java.util.Collections;

/**
 * OntologyBindingItem that defines the binding by a list of external terms
 *
 * @author Rong Chen
 * @version 1.0
 */
public class TermBindingItem extends OntologyBindingItem {

    /**
     * Creates a binding item with a list of terms
     *
     * @param code
     * @param terms
     */
    public TermBindingItem(String code, List<String> terms) {
        super(code);
        this.terms = terms;
    }

    /**
     * Gets an unmodifiable list of terms
     *
     * @return terms
     */
    public List<String> getTerms() {
        return Collections.unmodifiableList(terms);
    }

    private List<String> terms;
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
 *  The Original Code is TermBindingItem.java
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