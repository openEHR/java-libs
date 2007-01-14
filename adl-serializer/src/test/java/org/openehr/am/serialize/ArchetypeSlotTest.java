package org.openehr.am.serialize;

import java.util.LinkedHashSet;

import org.openehr.am.archetype.assertion.Assertion;
import org.openehr.am.archetype.assertion.ExpressionBinaryOperator;
import org.openehr.am.archetype.assertion.ExpressionItem;
import org.openehr.am.archetype.assertion.ExpressionLeaf;
import org.openehr.am.archetype.assertion.OperatorKind;
import org.openehr.am.archetype.constraintmodel.ArchetypeSlot;
import org.openehr.rm.support.basic.Interval;

/**
 * Testcase for ArchetypeSlot print in ADL serialization
 */ 

public class ArchetypeSlotTest extends SerializerTestBase {

	public ArchetypeSlotTest(String test) {
		super(test);
	}

	public void testPrintArchetypeSlot() throws Exception {
		final ExpressionItem concept = new ExpressionLeaf(
				ExpressionItem.ARCHETYPE, "domain_concept",
				ExpressionLeaf.ReferenceType.ATTRIBUTE);
		
		// Use linked hashsets to get the ordering right when we run [clean, install].
		final LinkedHashSet<Assertion> includes = new LinkedHashSet<Assertion>();
		final LinkedHashSet<Assertion> excludes = new LinkedHashSet<Assertion>();
		ExpressionLeaf aid = new ExpressionLeaf(ExpressionItem.ARCHETYPE,
				"/blood_pressure.v1/", ExpressionLeaf.ReferenceType.CONSTANT);
		ExpressionItem expression = new ExpressionBinaryOperator(
				ExpressionItem.BOOLEAN, OperatorKind.OP_MATCHES, false,
				concept, aid);
		String stringExpression = "domain_concept matches {/blood_pressure.v1/}";
		Assertion assertion = new Assertion(expression, stringExpression);
		includes.add(assertion);
		aid = new ExpressionLeaf(ExpressionItem.ARCHETYPE,
				"/blood_pressure.v2/", ExpressionLeaf.ReferenceType.CONSTANT);
		expression = new ExpressionBinaryOperator(ExpressionItem.BOOLEAN,
				OperatorKind.OP_MATCHES, false, concept, aid);
		stringExpression = "domain_concept matches {/blood_pressure.v2/}";
		assertion = new Assertion(expression, stringExpression);
		includes.add(assertion);

		aid = new ExpressionLeaf(ExpressionItem.ARCHETYPE, "/.*/",
				ExpressionLeaf.ReferenceType.CONSTANT);
		expression = new ExpressionBinaryOperator(ExpressionItem.BOOLEAN,
				OperatorKind.OP_MATCHES, false, concept, aid);
		stringExpression = "domain_concept matches {/.*/}";
		assertion = new Assertion(expression, stringExpression);
		excludes.add(assertion);

		Interval<Integer> occurrences = new Interval<Integer>(1, null, true,
				false);
		ArchetypeSlot slot = new ArchetypeSlot("/path", "OBSERVATION",
				occurrences, "at0001", null, includes, excludes);

		clean();
		outputter.printArchetypeSlot(slot, 0, out);
		verify("allow_archetype OBSERVATION[at0001] occurrences matches {1..*} matches {\r\n"
				+ "    include\r\n"
				+ "        domain_concept matches {/blood_pressure.v1/}\r\n"
				+ "        domain_concept matches {/blood_pressure.v2/}\r\n"
				+ "    exclude\r\n"
				+ "        domain_concept matches {/.*/}\r\n"
				+ "}\r\n");
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
 *  The Original Code is ArchetypeSlotTest.java
 *
 *  The Initial Developer of the Original Code is Mattias Forss.
 *  Portions created by the Initial Developer are Copyright (C) 2006
 *  the Initial Developer. All Rights Reserved.
 *
 *  Contributor(s): Rong Chen
 *
 * Software distributed under the License is distributed on an 'AS IS' basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
 * for the specific language governing rights and limitations under the
 * License.
 *
 *  ***** END LICENSE BLOCK *****
 */
