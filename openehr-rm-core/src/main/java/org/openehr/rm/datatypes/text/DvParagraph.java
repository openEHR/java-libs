/*
 * component:   "openEHR Reference Implementation"
 * description: "Class DvParagraph"
 * keywords:    "datatypes"
 *
 * author:      "Rong Chen <rong@acode.se>"
 * support:     "Acode HB <support@acode.se>"
 * copyright:   "Copyright (c) 2004 Acode HB, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/BRANCHES/RM-1.0-update/libraries/src/java/org/openehr/rm/datatypes/text/DvParagraph.java $"
 * revision:    "$LastChangedRevision: 2 $"
 * last_change: "$LastChangedDate: 2005-10-12 23:20:08 +0200 (Wed, 12 Oct 2005) $"
 */
package org.openehr.rm.datatypes.text;

import org.openehr.rm.Attribute;
import org.openehr.rm.FullConstructor;
import org.openehr.rm.datatypes.basic.DataValue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A logical composite text value consisting of a series of text.
 * Instances of this class are immutable.
 *
 * @author Rong Chen
 * @version 1.0
 */
public final class DvParagraph extends DataValue {

    /**
     * Create paragrah by items
     *
     * @param items not null or empty
     * @throws IllegalArgumentException if items null or empty
     */
    @FullConstructor
            public DvParagraph(
            @Attribute(name = "items", required = true) List<DvText> items) {
        if (items == null || items.size() == 0) {
            throw new IllegalArgumentException("null or empty items");
        }
        this.items = new ArrayList<DvText>(items);
    }

    /**
     * Return string form displayable for humans
     *
     * @return string presentation
     */
    public String toString() {
        return items.toString();
    }

    /**
     * Return items of this paragraph
     *
     * @return unmodifiable list of Text
     */
    public List<DvText> getItems() {
        return Collections.unmodifiableList(items);
    }

    // POJO start
    DvParagraph() {
    }

    public void setItems(List<DvText> items) {
        this.items = items;
    }    
    // POJO end

    /* fields */
    private List<DvText> items;

	@Override
	public String getReferenceModelName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String serialise() {
		// TODO Auto-generated method stub
		return null;
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
 *  The Original Code is DvParagraph.java
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