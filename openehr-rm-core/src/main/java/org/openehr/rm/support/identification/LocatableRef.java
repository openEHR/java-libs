/*
 * component:   "openEHR Reference Implementation"
 * description: "Class LocatableRef"
 * keywords:    "support"
 *
 * author:      "Yin Su Lim <y.lim@chime.ucl.ac.uk>"
 * support:     "CHIME, UCL"
 * copyright:   "Copyright (c) 2006 UCL, UK"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/TRUNK/libraries/src/java/org/openehr/rm/support/identification/LocatableRef.java $"
 * revision:    "$LastChangedRevision: 53 $"
 * last_change: "$LastChangedDate: 2006-08-11 13:20:08 +0100 (Fri, 11 Aug 2006) $"
 */
package org.openehr.rm.support.identification;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.openehr.rm.Attribute;
import org.openehr.rm.FullConstructor;


/**
 * Reference to a LOCATABLE instance inside the top-level content structure 
 * inside a VERSION<T>; the path attribute is applied to the object that 
 * VERSION.data points to.
 *
 * @author Yin Su Lim
 * @version 1.0
 */
public class LocatableRef extends ObjectRef {

    /**
     * Construct a LocatableRef
     *
     * @param id
     * @param namespace
     * @param type
     */
	@FullConstructor
    public LocatableRef(
    		@Attribute(name = "id", required = true)ObjectVersionID id, 
    		@Attribute(name = "namespace", required = true)String namespace,
    		@Attribute(name = "type", required = true)String type, 
    		@Attribute(name = "path")String path) {
        super(id, namespace, type);
        
        if (path != null && StringUtils.isEmpty(path)) {
          throw new IllegalArgumentException("empty path");
        }
        this.path = path;
    }
    
    /**
     * The path to an instance in question, as an absolute path with
     * respect to the object found at VERSION.data. An empty path
     * means that the object referred to by id being specified.
     */
    public String getPath() {
        return path;
    }
    
    /**
     * A URI form of the reference, created by concatenating the
     * following:
     * "ehr://" + id.value + "/" + path
     */
    public String asURI() {
        return "ehr://" + getId().getValue() + "/" + path;
    }
    
    /**
     * Two LocatableReferences equals if they have same values for id,
     * namespace, type and path.
     *
     * @param o
     * @return true if equals
     */
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!( o instanceof ObjectRef )) return false;
        if (!super.equals(o)) return false;
        
        final LocatableRef locRef = (LocatableRef) o;
        
        return new EqualsBuilder()
        .append(path, locRef.path)
        .isEquals();
    }
    
    /**
     * Return a hash code of this locatable reference
     *
     * @return hash code
     */
    public int hashCode() {
        return new HashCodeBuilder()
        .appendSuper(super.hashCode())
        .append(path)
        .toHashCode();
    }
    // POJO start
    LocatableRef() {
    }
    
    void setPath(String path) {
        this.path = path;
    }
    
    /* fields */
    private String path;
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
 *  The Original Code is LocatableRef.java
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