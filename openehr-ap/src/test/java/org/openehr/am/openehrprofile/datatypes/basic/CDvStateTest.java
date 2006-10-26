package org.openehr.am.openehrprofile.datatypes.basic;

import java.util.*;

import junit.framework.TestCase;

/**
 * Testcase for CDvState
 * 
 * @author Rong Chen
 */
public class CDvStateTest extends TestCase {
	
	/**
	 * Test to create a medication order state machine, from p11 of AOP doc 
	 */
	public void testCreateMedicationOrderStateMachine() {
		TerminalState completed = new TerminalState("COMPLETED");
		Transition finish = new Transition("finish", null, null, completed);
		Set<Transition> transitions = new HashSet<Transition>();
		transitions.add(finish);
		NonTerminalState inExecution = 
			new NonTerminalState("IN_EXECUTION", transitions);
		Transition restart = new Transition("restart", inExecution);
		transitions.clear();
		transitions.add(restart);
		NonTerminalState suspended = new NonTerminalState("SUSPENDED", transitions);
		Transition suspend = new Transition("suspend", suspended);
		inExecution.addTransition(suspend);
		
		Transition start = new Transition("start", inExecution);
		transitions.clear();
		transitions.add(start);
		NonTerminalState proposed = new NonTerminalState("PROPOSED", transitions);
		NonTerminalState ordered = new NonTerminalState("ORDERED", transitions);
		NonTerminalState overdue = new NonTerminalState("OVERDUE", transitions);
		
		Transition order = new Transition("order", ordered);
		Transition startFail = new Transition("start fail", overdue);
		proposed.addTransition(order);
		ordered.addTransition(startFail);
		
		TerminalState cancelled = new TerminalState("CANCELLED");
		Transition cancel = new Transition("cancel", cancelled);
		proposed.addTransition(cancel);
		ordered.addTransition(cancel);
		overdue.addTransition(cancel);
		
		// verify the statemachine
		assertEquals("number of transitions of PROPOSED", 3, 
				proposed.getTransitions().size());
		assertEquals("number of transitions of ORDERED", 3, 
				ordered.getTransitions().size());
		assertEquals("number of transitions of OVERDUE", 2, 
				overdue.getTransitions().size());
		assertEquals("number of transitions of IN_EXECUTE", 2, 
				inExecution.getTransitions().size());
		assertEquals("number of transitions of SUSPEND", 1, 
				suspended.getTransitions().size());
	}
}
