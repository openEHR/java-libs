/*
 * component:   "openEHR Reference Implementation"
 * description: "Class ArchetypeConstraint"
 * keywords:    "archetype"
 *
 * author:      "Rong Chen <rong@acode.se>"
 * support:     "Acode HB <support@acode.se>"
 * copyright:   "Copyright (c) 2004 Acode HB, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/TRUNK/libraries/src/java/org/openehr/am/archetype/constraintmodel/ArchetypeConstraint.java $"
 * revision:    "$LastChangedRevision: 2 $"
 * last_change: "$LastChangedDate: 2005-10-12 23:20:08 +0200 (Wed, 12 Oct 2005) $"
 */
package org.openehr.am.archetype.constraintmodel;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * Purpose Archetype equivalent to LOCATABLE class in openEHR Common reference
 * model. Defines common constraints for any inheritor of LOCATABLE in any
 * reference model.
 *
 * @author Rong Chen
 * @version 1.0
 */
public abstract class ArchetypeConstraint {

    /**
     * Path separator in archetype path
     */
    public static final String PATH_SEPARATOR = "/";

    /**
     * Constructor
     *
     * @param anyAllowed
     * @param path     
     * @param parent
     */
    protected ArchetypeConstraint(boolean anyAllowed, String path) {
        if (StringUtils.isEmpty(path)) {
            throw new IllegalArgumentException("path null");
        }
        this.anyAllowed = anyAllowed;
        this.path = path;                
    }

    /**
     * Path of this node relative to root of archetype.
     *
     * @return path
     */
    public String path() {
        return path;
    }
    
    public void setPath(String path) {
    	this.path = path;
    }

    /**
     * True if this node is a valid archetype node.
     *
     * @return true if valid
     */
    public abstract boolean isValid();
    
    /**
     * Returns true if the constraint is a root node
     * 
     * @return
     */
    public boolean isRoot() {
    	return PATH_SEPARATOR.equals(path);
    }

    /**
     * True if the relative path exists at this node.
     *
     * @param path
     * @return true if has
     * @throws IllegalArgumentException if path null
     */
    public abstract boolean hasPath(String path);

    /**
     * True if constraints represented by other are narrower than this node.
     *
     * @param constraint
     * @return true if subset
     * @throws IllegalArgumentException if constraint null
     */
    public abstract boolean isSubsetOf(ArchetypeConstraint constraint);

    /**
     * True if any possible instance value of this type is considered valid
     *
     * @return anyAllowed
     */
    public boolean isAnyAllowed() {
        return anyAllowed;
    }
    
    public void setAnyAllowed(boolean anyAllowed) {
    	this.anyAllowed = anyAllowed;
    }

    /**
     * True if hide_on_form is set on the template
     * 
     * @return
     */
    public boolean isHiddenOnForm() {
    	return hiddenOnForm;
    }
    
    public String getAnnotation() {
		return annotation;
	}
    
    public void setAnnotation(String annotation) {
    	this.annotation = annotation;
    }
    
    /**
     * See adl 1.5 specifications for congruent
     */
    public boolean isCongruent(){
    	return isCongruent;
    }
    
    /**
     * See adl 1.5 specifications for congruent
     */
    public void setIsCongruent(boolean isCongruent){
    	this.isCongruent = isCongruent; 
    }
    
    
    /**
     * See adl 1.5 specifications for ArchetypeConstraint type
     * @param otherNode Node to compare to
     * @return comparison result
     */
    public  boolean nodeConformsTo(ArchetypeConstraint otherNode){return false;};
    
    /**
     * See adl 1.5 specifications for ArchetypeConstraint type
     * @param otherNode
     * @return comparison result
     */
    public boolean nodeCongruentTo(ArchetypeConstraint otherNode){return false;};
    
    /**
     * Get parent constraint
     * @return Parent ArchetypeConstraint
     */
    public ArchetypeConstraint getParent(){
    	return parent;
    }
    
    /**
     * Set parent ArchetypeConstraint
     * @param parent
     */
    public void setParent(ArchetypeConstraint parent){
    	this.parent = parent;
    }
    
    
    /**
     * String representation of this object
     *
     * @return string form
     */
    public String toString() {
        return ToStringBuilder.reflectionToString(this,
                ToStringStyle.MULTI_LINE_STYLE);
    }

    /**
     * Equals if two ArchetypeConstraint have same value
     *
     * @param o
     * @return true if equals
     */
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!( o instanceof ArchetypeConstraint )) return false;

        final ArchetypeConstraint ac = (ArchetypeConstraint) o;

        return new EqualsBuilder()
                .append(anyAllowed, ac.anyAllowed)
                .append(path, ac.path)
                .isEquals();
    }

    /**
     * Return a hash code of this object
     *
     * @return hash code
     */
    public int hashCode() {
        return new HashCodeBuilder()
                .append(anyAllowed)
                .append(path)
                .append(hiddenOnForm)
                .toHashCode();
    }    

    /* fields */
    private boolean anyAllowed;
    private String path;
    protected boolean isCongruent;
    protected ArchetypeConstraint parent;
    
    // TODO experimental feature in ADL 1.5
    private boolean hiddenOnForm;    
    private String annotation;
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
 *  The Original Code is ArchetypeConstraint.java
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