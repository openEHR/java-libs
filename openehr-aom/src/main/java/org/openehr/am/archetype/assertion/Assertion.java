/*
 * component:   "openEHR Reference Implementation"
 * description: "Class Assertion"
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

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * Structural model of a typed first order predicate logic assertion, in the
 * form of an expression tree, including optional variable definitions.
 * 
 * @author Rong Chen
 */
public class Assertion {

	/**
	 * Creates an Assertion with tag and expression
     *
     * @param tag	optional null if unspecified 
     * @param expression not null
     * @param stringExpression
     * @param variables	null if unspecified
     * @throws IllegalArgumentException if tag empty, expression null,
	 *		expression not boolean type
     */
	public Assertion(String tag, ExpressionItem expression,
			String stringExpression, List<AssertionVariable> variables) {
		
		if(tag != null && StringUtils.isEmpty(tag)) {
			throw new IllegalArgumentException("tag empty");
		}
		if(expression == null) {
			throw new IllegalArgumentException("expression null");
		}
		
		if( ! expression.isTypeBoolean()) {
			throw new IllegalArgumentException("expression not boolean type");
		}
		this.tag = tag;
		this.expression = expression;
		this.stringExpression = stringExpression;
		this.variables = variables;
	}
	
	/**
	 * Creates an Assertion without tag
     *
     * @param expression not null
     * @param stringExpression
     * @throws IllegalArgumentException if tag empty, expression null,
	 *		expression not boolean type
     */
	public Assertion(ExpressionItem expression,
			String stringExpression) {
		this(null, expression, stringExpression, null);
	}

	public ExpressionItem getExpression() {
		return expression;
	}

	public String getStringExpression() {
		return stringExpression;
	}

	public String getTag() {
		return tag;
	}

	public List<AssertionVariable> getVariables() {
		return variables;
	}
	
	/**
	 * Returns string expression of this assertion
     *
     * @return string expression
     */
	public String toString() {
		return stringExpression;
	}

	/**	 
     * Equals if two Assertion Objects have same values
     *
     * @param o
     * @return true if equals
     */
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!( o instanceof Assertion )) return false;

        final Assertion cobj = (Assertion) o;
        return new EqualsBuilder()
                .append(tag, cobj.tag)
                .append(expression, cobj.expression)
                .append(stringExpression, cobj.stringExpression)
                .append(variables, cobj.variables)
                .isEquals();
    }

    /**
     * Return a hash code of this object
     *
     * @return hash code
     */
    public int hashCode() {
        return new HashCodeBuilder(7, 19)           
                .append(tag)
                .append(expression)
                .append(stringExpression)
                .append(variables)
                .toHashCode();
    }	
    
	/* fields */
	private String tag;
	private ExpressionItem expression;
	private String stringExpression;
	private List<AssertionVariable> variables;
}

/*
 * ***** BEGIN LICENSE BLOCK ***** Version: MPL 1.1/GPL 2.0/LGPL 2.1
 * 
 * The contents of this file are subject to the Mozilla Public License Version
 * 1.1 (the 'License'); you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at http://www.mozilla.org/MPL/
 * 
 * Software distributed under the License is distributed on an 'AS IS' basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License for
 * the specific language governing rights and limitations under the License.
 * 
 * The Original Code is Assertion.java
 * 
 * The Initial Developer of the Original Code is Rong Chen. Portions created by
 * the Initial Developer are Copyright (C) 2003-2010 the Initial Developer. All
 * Rights Reserved.
 * 
 * Contributor(s): Sebastian Garde
 * 
 * Software distributed under the License is distributed on an 'AS IS' basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License for
 * the specific language governing rights and limitations under the License.
 * 
 * ***** END LICENSE BLOCK *****
 */