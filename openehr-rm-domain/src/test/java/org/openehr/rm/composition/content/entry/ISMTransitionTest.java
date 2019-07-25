package org.openehr.rm.composition.content.entry;

import java.util.ArrayList;
import java.util.List;

import org.openehr.rm.datatypes.text.CodePhrase;
import org.openehr.rm.datatypes.text.DvCodedText;
import org.openehr.rm.datatypes.text.DvText;
import org.openehr.rm.support.terminology.TerminologyService;
import org.openehr.rm.support.terminology.TestTerminologyAccess;
import org.openehr.rm.support.terminology.TestTerminologyService;
import org.openehr.terminology.SimpleTerminologyService;

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

        List<DvText> reason = new ArrayList<DvText>();
        reason.add(new DvText("test reason 1"));
        reason.add(new DvText("test reason 2"));
        TerminologyService ts = TestTerminologyService.getInstance();

        ISMTransition ismt = new ISMTransition(currentState, transition,
                careflowStep, reason, ts);

        assertEquals("currentState wrong", currentState, ismt.getCurrentState());
        assertEquals("transition wrong", transition, ismt.getTransition());
        assertEquals("careflowStep wrong", careflowStep, ismt.getCareflowStep());
        assertEquals("reason wrong", reason, ismt.getReason());
    }

    public void testWithOpenEHRTerminology() throws Exception {
        TerminologyService ts = SimpleTerminologyService.getInstance();
        CodePhrase lang = new CodePhrase("ISO_639-1", "en");
        CodePhrase encoding = new CodePhrase("IANA_character-sets", "UTF-8");
        DvCodedText planned = new DvCodedText("planned", lang, encoding,
                new CodePhrase("openehr", "526"), ts);

        new ISMTransition(planned, null, null, null, ts);
    }
}
