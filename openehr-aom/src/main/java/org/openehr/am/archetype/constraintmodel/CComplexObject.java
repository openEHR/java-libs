/*
 * component:   "openEHR Reference Implementation"
 * description: "Class CComplexObject"
 * keywords:    "archetype"
 *
 * author:      "Rong Chen <rong@acode.se>"
 * support:     "Acode HB <support@acode.se>"
 * copyright:   "Copyright (c) 2006 Acode HB, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/TRUNK/libraries/src/java/org/openehr/am/archetype/constraintmodel/CComplexObject.java $"
 * revision:    "$LastChangedRevision: 2 $"
 * last_change: "$LastChangedDate: 2005-10-12 23:20:08 +0200 (Wed, 12 Oct 2005) $"
 */
package org.openehr.am.archetype.constraintmodel;

import org.openehr.rm.support.basic.Interval;
import org.openehr.rm.support.basic.MultiplicityInterval;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import java.util.*;

/**
 * Constraint on complex objects, ie any object which consists of other object
 * constraints. Instances of this class are mutable.
 *
 * @author Rong Chen
 * @version 1.0
 */
public class CComplexObject extends CDefinedObject {
	
	/**
     * Constructs a complexObjectConstraint
     *
     * @param path
     * @param rmTypeName
     * @param occurrences
     * @param nodeID
     * @param attributes
     * @param invariants
     */
    public CComplexObject(String path, String rmTypeName,
                          MultiplicityInterval occurrences, String nodeID, 
                          List<CAttribute> attributes, CAttribute parent) {

    	// TODO probably need to inherit from CObject directly
        super(attributes == null, path, rmTypeName, occurrences, nodeID, 
        		parent, null);

        this.attributes = new ArrayList<CAttribute>();
        if(attributes != null) {
        	this.attributes.addAll(attributes);
        }	        
    }
    
    /**
     * Create a single required node constrained only by given RM type
     * 
     * @param path
     * @param rmTypeName
     * @return
     */
    public static CComplexObject createSingleRequired(String path, String rmTypeName) {
    	MultiplicityInterval occurrences = new MultiplicityInterval(1, 1);
    	return new CComplexObject(path, rmTypeName, occurrences, null, null, null);
    }
    
    /**
     * Make a copy of this instance
     * 
     * @return
     */
    public CObject copy() {
    	
    	//System.out.println("copying " + getRmTypeName() + ", " + path());
    	
    	List<CAttribute> list = new ArrayList<CAttribute>();
    	for(CAttribute attr : attributes) {
    		list.add(attr.copy());
    	}
    	return new CComplexObject(path(), getRmTypeName(), getOccurrences(),
    			getNodeId(), list, getParent());
    }
    
    /**
     * Adds an attribute constraint to this CComplexObject
     *  
     * @param attribute not null
     */
    public void addAttribute(CAttribute attribute) {
    	if(attribute == null) {
    		throw new IllegalArgumentException("null cattribute");
    	}
    	attributes.add(attribute);
    	setAnyAllowed(false);
    }
    
    /**
     * Removes the attribute of given name
     * 
     * @param name
     */
    public void removeAttribute(String name) {
    	if(name == null) {
    		return;
    	}
    	for(Iterator<CAttribute> it = getAttributes().iterator(); 
    			it.hasNext();) {
    		CAttribute cattr = it.next();
    		if(name.equals(cattr.getRmAttributeName())) {
    			it.remove();    			
    		}
    	}
    }

    /**
     * List of constraints on attributes of the reference model type
     * represented by this object.
     *
     * @return null if allow any
     */
    public List<CAttribute> getAttributes() {
        return attributes;
    }

    /**
     * Get a specific attribute constraint identified by its rmAttributeName.
     * @param rmAttributeName the attribute name of the attribute to be retrieved
     * @return the attribute or null if no specific constraint with that rmAttributeName exists
     */
    public CAttribute getAttribute(String rmAttributeName) {
        if (attributes == null) return null;
        for (CAttribute attribute : attributes) {
        	if (attribute.getRmAttributeName().equals(rmAttributeName)) {
        		return attribute;
        	}
        }
        return null;
    }
    
    /**
     * Checks if a specific attribute constraint identified by its rmAttributeName.
     * @param rmAttributeName the attribute name of the attribute to be retrieved
     * @return the attribute or null if no specific constraint with that rmAttributeName exists
     */
    public boolean hasAttribute(String rmAttributeName) {
        if (attributes == null) return false;
        for (CAttribute attribute : attributes) {
        	if (attribute.getRmAttributeName().equals(rmAttributeName)) {
        		return true;
        	}
        }
        return false;
    }
    
    
    /**
     * True if this node is a valid archetype node.
     *
     * @return true if valid
     */
    public boolean isValid() {
        if (attributes == null) {
            return true;
        }
        return false;  // todo: implement this method
    }

    /**
     * True if the relative path exists at this node.
     *
     * @param path
     * @return ture if has
     * @throws IllegalArgumentException if path null
     */
    public boolean hasPath(String path) {
        return false; // todo fix it
    }

    /**
     * True if constraints represented by other are narrower than this node.
     *
     * @param constraint
     * @return true if subset
     * @throws IllegalArgumentException if constraint null
     */
    public boolean isSubsetOf(ArchetypeConstraint constraint) {
        return false;  // todo: implement this method
    }
    
    /**
     * Equals if two CComplexObject have same values
     *
     * @param o
     * @return true if equals
     */
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!( o instanceof CComplexObject )) return false;

        final CComplexObject ccobj = (CComplexObject) o;

        return new EqualsBuilder()
                .appendSuper(super.equals(o))
                .append(attributes, ccobj.attributes)
                .isEquals();
    }

    /**
     * Return a hash code of this object
     *
     * @return hash code
     */
    public int hashCode() {
        return new HashCodeBuilder(13, 29)
                .appendSuper(super.hashCode())
                .append(attributes)
                .toHashCode();
    }
    
    public boolean isPassThrough(){
    	return passThrough;
    }
    
    public void setIsPassThrough(boolean passThrough){
    	this.passThrough = passThrough;
    }
    
    /* fields */
    private List<CAttribute> attributes;    
    private boolean passThrough;
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
 *  The Original Code is CComplexObject.java
 *
 *  The Initial Developer of the Original Code is Rong Chen.
 *  Portions created by the Initial Developer are Copyright (C) 2003-2006
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