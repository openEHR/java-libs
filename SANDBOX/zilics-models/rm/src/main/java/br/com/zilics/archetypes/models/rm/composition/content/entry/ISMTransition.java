
package br.com.zilics.archetypes.models.rm.composition.content.entry;

import br.com.zilics.archetypes.models.rm.annotation.RmClass;
import br.com.zilics.archetypes.models.rm.common.archetyped.Locatable;
import br.com.zilics.archetypes.models.rm.datatypes.text.DvCodedText;

/**
 * Model of a transition in the Instruction State machine, caused by a careflow step.
 * The attributes document the careflow step as well as the ISM transition.
 *
 * @author Humberto
 */
@RmClass("ISM_TRANSITION")
public class ISMTransition extends Locatable {

	private static final long serialVersionUID = 2274327384558601254L;
	private DvCodedText currentState;
    private DvCodedText transition;
    private DvCodedText careflowStep;

    /**
     * Get the current state
     * @return the ISM current state
     */
    public DvCodedText getCurrentState() {
        return currentState;
    }

    /**
     * Set the current state
     * @param currentState the ISM current state
     */
    public void setCurrentState(DvCodedText currentState) {
		assertMutable();
        this.currentState = currentState;
    }

    /**
     * Get the ISM transition state
     * @return the ISM transition state which occured to arrive in the currentState field
     */
    public DvCodedText getTransition() {
        return transition;
    }

    /**
     * Set the ISM transition state
     * @param transition the ISM transition state which occured to arrive in the currentState field
     */
    public void setTransition(DvCodedText transition) {
		assertMutable();
        this.transition = transition;
    }

    /**
     * Get the step in the careflow process
     * @return the step in the careflow process which occured as part of generating this action
     */
    public DvCodedText getCareflowStep() {
        return careflowStep;
    }

    /**
     * Set the step in the careflow process
     * @param careflowStep the step in the careflow process which occured as part of generating this action
     */
    public void setCareflowStep(DvCodedText careflowStep) {
		assertMutable();
        this.careflowStep = careflowStep;
    }
}
