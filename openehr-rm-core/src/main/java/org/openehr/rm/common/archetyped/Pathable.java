/*
 * component:   "openEHR Reference Implementation"
 * description: "Class Pathable"
 * keywords:    "common"
 *
 * author:      "Rong Chen <rong@acode.se>"
 * copyright:   "Copyright (c) 2007 Cambio Healthcare Systems, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL$"
 * revision:    "$LastChangedRevision$"
 * last_change: "$LastChangedDate$"
 */
package org.openehr.rm.common.archetyped;

import java.util.*;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.openehr.rm.RMObject;

/**
 * Abstract parent of all classes whose instances are reachable by paths, and 
 * which know how to locate child object by paths. 
 * 
 * @author Rong Chen
 */
public abstract class Pathable extends RMObject {
	
	/**
	 * Creates a Pathable
	 * 
	 * @param parent null if not present
	 */
	public Pathable(Pathable parent) {
		this.parent = parent;
	}
	
	/**
	 * Creates a pathable without parent
	 */
	public Pathable() {
		this(null);
	}
	
	/**
	 * Parent of this node in compositional hierarchy
	 * 
	 * @return parent or null if not specified
	 */
	public Pathable getParnent() {
		return this.parent;
	}
	
	/**
     * The item at a path (relative to this item); only valid for unique paths,
     * i.e. paths that resolve to a single item.
     *
     * @param path not null 
     * @return the item
     * @throws IllegalArgumentException if path invalid
     */
    public abstract Object itemAtPath(String path);
    
    /**
     * List of items corresponding to a nonunique path.
     * 
     * @param path
     * @return
     */
    public abstract List<Object> itemsAtPath(String path);
    
    /**
     * The path to an item relative to the root of this archetyped structure.
     * 
     * @param item
     */
    public abstract String pathOfItem(Pathable item);

    /**
     * True if the path exists in the data with respect to the current item
     * 
     * @param path
     * @return
     */
    public abstract boolean pathExists(String path);
    
    /**
     * True if the path corresponds to a single item in the data.
     * @param path
     * @return
     */
    public abstract boolean pathUnique(String path);
    
    /**
     * Equals if two actors has same values
     *
     * @param o
     * @return equals if true
     */
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!( o instanceof Pathable )) return false;
        
        final Pathable path = (Pathable) o;
        return new EqualsBuilder()
                .append(parent, path.parent)
                .isEquals();

    }

    /**
     * Return a hash code of this actor
     *
     * @return hash code
     */
    public int hashCode() {
        return new HashCodeBuilder(11, 29)
                .appendSuper(super.hashCode())
                .append(parent)
                .toHashCode();
    }
    
	private Pathable parent;
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
 *  The Original Code is Pathable.java
 *
 *  The Initial Developer of the Original Code is Rong Chen.
 *  Portions created by the Initial Developer are Copyright (C) 2007
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