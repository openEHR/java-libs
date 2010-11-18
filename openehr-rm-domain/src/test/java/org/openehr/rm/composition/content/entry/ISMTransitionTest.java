package org.openehr.rm.composition.content.entry;

import junit.framework.TestCase;

import org.openehr.rm.datatypes.text.CodePhrase;
import org.openehr.rm.datatypes.text.DvCodedText;
import org.openehr.rm.support.terminology.TerminologyService;
import org.openehr.rm.support.terminology.TestTerminologyAccess;
import org.openehr.rm.support.terminology.TestTerminologyService;
import org.openehr.terminology.SimpleTerminologyService;

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
	
	public void testWithOpenEHRTerminology() throws Exception {
		TerminologyService ts = SimpleTerminologyService.getInstance();
		CodePhrase lang = new CodePhrase("ISO_639-1", "en");
		CodePhrase encoding = new CodePhrase("IANA_character-sets", "UTF-8");
		DvCodedText planned = new DvCodedText("planned", lang, encoding, 
    			new CodePhrase("openehr", "526"), ts);
        
		new ISMTransition(planned, null, null, ts);        
	}
}
