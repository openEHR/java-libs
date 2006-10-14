/*
 * component:   "openEHR Reference Implementation"
 * description: "Class ExpressionBinaryOperator"
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

public class ExpressionBinaryOperator extends ExpressionOperator {
	
	public ExpressionBinaryOperator(String type, OperatorKind operator,
			boolean precedenceOverridden, ExpressionItem leftOperand, 
			ExpressionItem rightOperand) {
		super(type, operator, precedenceOverridden);		
		this.leftOperand = leftOperand;
		this.rightOperand = rightOperand;
	}
	
	public ExpressionItem getLeftOperand() {
		return leftOperand;
	}
	
	public ExpressionItem getRightOperand() {
		return rightOperand;
	}
	
	private ExpressionItem leftOperand;
	private ExpressionItem rightOperand;	
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
 *  The Original Code is ExpressionBinaryOperator.java
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