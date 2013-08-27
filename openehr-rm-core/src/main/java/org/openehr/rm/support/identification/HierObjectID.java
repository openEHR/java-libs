/*
 * component:   "openEHR Reference Implementation"
 * description: "Class HierObjectID"
 * keywords:    "common"
 *
 * author:      "Rong Chen <rong@acode.se>"
 * support:     "Acode HB <support@acode.se>"
 * copyright:   "Copyright (c) 2004 Acode HB, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/TRUNK/libraries/src/java/org/openehr/rm/support/identification/HierObjectID.java $"
 * revision:    "$LastChangedRevision: 2 $"
 * last_change: "$LastChangedDate: 2005-10-12 22:20:08 +0100 (Wed, 12 Oct 2005) $"
 */
package org.openehr.rm.support.identification;

import org.apache.commons.lang.StringUtils;
import org.openehr.rm.Attribute;
import org.openehr.rm.FullConstructor;

/**
 * Hierarchical object identifiers.
 * <p/>
 * The syntax of the value attribute is as follows:
 * <blockquote>root::extension</blockquote>
 * with the extension bit optional
 * </p>
 * <p>Instances of this class are immutable.</p>
 *
 * @author Yin Su Lim
 * @version 1.0
 */
public class HierObjectID extends UIDBasedID {

    /**
     * Create HierObjectID by string value
     *
     * @param value
     * @throws IllegalArgumentException if value is null
     *                                  
     */
	@FullConstructor
    public HierObjectID(
    		@Attribute(name = "value", required = true)String value) {
        super(value);
        loadValue(value);
    }

	/**
     * Constructs a HierObjectID by root and extension
     *
     * @param root
     * @param extension
     * @throws IllegalArgumentException if root is null
     */
    public HierObjectID(String root, String extension) {
    	this(extension == null ? root : root + "::" + extension);
    }
 
	/**
     * Constructs a HierObjectID by root (UID) and extension
     *
     * @param root
     * @param extension
     * @throws IllegalArgumentException if root is null 
     */
    public HierObjectID(UID root, String extension) {
        if(root == null) {
        	throw new IllegalArgumentException("null root");
        }    	
        if(StringUtils.isEmpty(extension)) {
        	super.setValue(root.getValue());
        } else {
            super.setValue(root.getValue() + "::" + extension);
        }
        this.root = root;
        this.extension = extension;        
    }
 
    /** 
     * Load value into root and extension
     * @param value
     */
    private void loadValue(String value) {
        int doubleColons = value.indexOf("::");
        // Check for root segment
        if (doubleColons == 0) {
            throw new IllegalArgumentException("bad format, missing root");
        }
        //the patterns below are for sorting only, the correct syntax
        //checking is handled by the UID sublcasses.
        String rootStr = null;
        if(doubleColons > 0) {
            rootStr = value.substring(0, doubleColons);
        } else {
            rootStr = value;
        }
        if (rootStr.matches(UUID.SIMPLE_UUID_PATTERN)) {
            root = new UUID(rootStr);
        } else if (rootStr.matches("(\\d)+(\\.(\\d)+)*")) { //for ISO_OID
            //System.out.println("in ISO");
            root = new ISO_OID(rootStr);
        } else if (rootStr.matches("(\\w)+(\\.(\\w)+)*")){ //for InternetID, 
            root = new InternetID(rootStr);
        } else {
            throw new IllegalArgumentException("wrong format");
        }
        
        if( 0 < doubleColons && doubleColons < (value.length() - 2)) {
            extension = value.substring(doubleColons + 2);
        }           
    }

    /**
     * The identifier of the conceptual namespace in which the object exists, 
     * within the identification scheme
     * 
     * @return root
     */
    @Override
    public UID root() {
    	return root;
    }
    
    /**
     * A local identifier of the object within the context of the 
     * root identifier
     * 
     * @return extension
     */
    @Override
    public String extension() {
    	return extension;
    }
    
    // POJO start
    @Override
    protected void setValue(String value) {
        loadValue(value);
        super.setValue(value);
    }
    // POJO end

    /* fields */
    private UID root;   // mandatory
    private String extension;
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
 *  The Original Code is HierObjectID.java
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