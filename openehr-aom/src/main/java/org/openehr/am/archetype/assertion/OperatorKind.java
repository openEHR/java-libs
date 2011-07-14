/*
 * component:   "openEHR Reference Implementation"
 * description: "Class OperatorKind"
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

public enum OperatorKind {
    /**
     * Equals operator ("=" or "==")
     */
    OP_EQ(2001, "="),
    
    /**
     * Not equals operator ("!=" or "/=" or "<>")
     */
    OP_NE(2002, "!="),
    
    /**
     * Less-than or equals operator ("<=")
     */
    OP_LE(2003, "<="),
    
    /**
     * Less-than operator ("<")
     */
    OP_LT(2004, "<"),
    
    /**
     * Grater-than or equals operator (">=")
     */
    OP_GE(2005, ">="),
    
    /**
     * Grater-than operator (">")
     */
    OP_GT(2006, ">"),
    
    /**
     * Matches operator ("matches" or "is_in")
     */
    OP_MATCHES(2007, "matches"),
    
    /**
     * Not logical operator
     */
    OP_NOT(2010, "not"),
    
    /**
     * And logical operator
     */
    OP_AND(2011, "and"),
    
    /**
     * Or logical operator
     */
    OP_OR(2012, "or"),
    
    /**
     * Xor logical operator
     */
    OP_XOR(2013, "xor"),
    
    /**
     * Implies logical operator
     */
    OP_IMPLIES(2014, "implies"),
    
    /**
     * For-all quantifier operator
     */
    OP_FOR_ALL(2015, "for_all"),
    
    /**
     * Exists quantifier operator
     */
    OP_EXISTS(2016, "exists"),
    
    /**
     * Plus operator ("+")
     */
    OP_PLUS(2020, "+"),
    
    /**
     * Minus operator ("-")
     */
    OP_MINUS(2021, "-"),
    
    /**
     * Multiply operator ("*")
     */
    OP_MULTIPLY(2022, "*"),
    
    /**
     * Divide operator ("/")
     */
    OP_DIVIDE(2023, "/"),
    
    /**
     * Exponent operator ("^")
     */
    OP_EXP(2024, "^");
    
    private int value;
    private String sign;
    
    OperatorKind(int value, String sign) {
        this.value = value;
        this.sign = sign;
    }
    
    public int getValue() {
        return value;
    }
    
    public String toString() {
    	return sign;
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
 *  The Original Code is OperatorKind.java
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