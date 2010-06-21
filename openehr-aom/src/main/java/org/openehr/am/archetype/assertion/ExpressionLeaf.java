/*
 * component:   "openEHR Reference Implementation"
 * description: "Class ExpressionLeaf"
 * keywords:    "archetype assertion"
 *
 * author:      "Rong Chen <rong@acode.se>"
 * support:     "Acode HB <support@acode.se>"
 * copyright:   "Copyright (c) 2006 Acode HB, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL$"
 * revision:    "$LastChangedRevision$"
 * last_change: "$LastChangedDate$"
 */
 
package org.openehr.am.archetype.assertion;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class ExpressionLeaf extends ExpressionItem {
	
	/**
	 * ReferenceType enum 
	 */
	public enum ReferenceType { CONSTANT, ATTRIBUTE, FUNCTION };
	
	public ExpressionLeaf(String type, Object item, ReferenceType referenceType) {
		super(type);
		this.item = item;
		this.referenceType = referenceType;
	}
	public Object getItem() {
		return item;
	}
	public ReferenceType getReferenceType() {
		return referenceType;
	}
	
	/**
	 * Creates an expression leaf with integer constant
	 * 
	 * @param i
	 * @return
	 */
	public static final ExpressionLeaf intConstant(int i) {
		return new ExpressionLeaf(ExpressionItem.INTEGER, new Integer(i),
				ExpressionLeaf.ReferenceType.CONSTANT);
	}
	
	/**
	 * Creates an expression leaf with real constant
	 * 
	 * @param d
	 * @return
	 */
	public static final ExpressionLeaf realConstant(double d) {
		return new ExpressionLeaf(ExpressionItem.REAL, new Double(d),
				ExpressionLeaf.ReferenceType.CONSTANT);
	}
	
	/**
	 * Creates an expression leaf with string constant
	 * 
	 * @param s
	 * @return
	 */
	public static final ExpressionLeaf stringConstant(String str) {
		return new ExpressionLeaf(ExpressionItem.STRING, str,
				ExpressionLeaf.ReferenceType.CONSTANT);
	}
	
	/**
	 * Creates an expression leaf with path constant
	 * 
	 * @param s
	 * @return
	 */
	public static final ExpressionLeaf pathConstant(String path) {
		return new ExpressionLeaf(ExpressionItem.RM, path,
				ExpressionLeaf.ReferenceType.CONSTANT);
	}
	
	/**
	 * Creates an expression leaf with boolean constant
	 * 
	 * @param s
	 * @return
	 */
	public static final ExpressionLeaf booleanConstant(boolean b) {
		return new ExpressionLeaf(ExpressionItem.BOOLEAN, new Boolean(b),
				ExpressionLeaf.ReferenceType.CONSTANT);
	}
	

	 /**
	     * Equals if two ExpressionLeaf Objects have same values
	     *
	     * @param o
	     * @return true if equals
	     */
	    public boolean equals(Object o) {
	        if (this == o) return true;
	        if (!( o instanceof ExpressionLeaf )) return false;

	        final ExpressionLeaf cobj = (ExpressionLeaf) o;

	        return new EqualsBuilder()	
	        .appendSuper(super.equals(o))
	                .append(item, cobj.item)
	                .append(referenceType, cobj.referenceType)
	                .isEquals();
	    }
	
	   /**
	     * Return a hash code of this object
	     *
	     * @return hash code
	     */
	    public int hashCode() {
	        return new HashCodeBuilder(5, 23)
	                .appendSuper(super.hashCode())
	                .append(item)
	                .append(referenceType)
	                .toHashCode();	                
	    }
	
	private Object item;
	private ReferenceType referenceType;
	
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
 *  The Original Code is ExpressionLeaf.java
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