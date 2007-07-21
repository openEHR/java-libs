package org.openehr.rm.composition.content.entry;

import org.openehr.rm.datatypes.text.CodePhrase;
import org.openehr.rm.datatypes.text.DvCodedText;
import org.openehr.rm.support.terminology.TerminologyService;
import org.openehr.rm.support.terminology.TestTerminologyAccess;
import org.openehr.rm.support.terminology.TestTerminologyService;

import junit.framework.TestCase;

public class ISMTransitionTest extends TestCase {

	/**
	 * Verifies a bug fix in the constructor of ISMTransition
	 * 
	 * @throws Exception
	 */
	public void testConstructor() throws Exception {
		DvCodedText currentState = new DvCodedText("current state", 
        		TestTerminologyAccess.SOME_STATE);
        
        DvCodedText transition = new DvCodedText("transition", 
        		TestTerminologyAccess.SOME_TRANSITION);
        
        CodePhrase definingCode = new CodePhrase("test", "124");
        DvCodedText careflowStep = new DvCodedText("care flow Step", 
        		definingCode);
        
        TerminologyService ts = TestTerminologyService.getInstance();
        
        ISMTransition ismt = new ISMTransition(currentState, transition, 
        		careflowStep, ts);
        
        assertEquals("currentState wrong", currentState, ismt.getCurrentState());
        assertEquals("transition wrong", transition, ismt.getTransition());
        assertEquals("careflowStep wrong", careflowStep, ismt.getCareflowStep());
	}
}
