
package br.com.zilics.archetypes.models.am.openehrprofile.datatypes.basic;

import br.com.zilics.archetypes.models.am.AMObject;
import br.com.zilics.archetypes.models.rm.annotation.EqualsField;
import br.com.zilics.archetypes.models.rm.annotation.NotEmpty;
import br.com.zilics.archetypes.models.rm.annotation.NotNull;


/**
 * Definition of a state machine transition
 * @author Humberto
 */
public class Transition extends AMObject {

	private static final long serialVersionUID = -3258274865701106853L;
	@NotEmpty
	@EqualsField
	private String event;
	@EqualsField
    private String guard;
	@EqualsField
    private String action;
    @NotNull
	@EqualsField
    private State nextState;

    /**
     * Get the event
     * @return Event which fires this transition
     */
    public String getEvent() {
        return event;
    }

    /**
     * Set the event
     * @param event Event which fires this transition
     */
    public void setEvent(String event) {
		assertMutable();
        this.event = event;
    }


    /**
     * Get the guard
     * @return Guard condition which must be true for this transition to fire
     */
    public String getGuard() {
        return guard;
    }

    /**
     * Set the guard
     * @param guard Guard condition which must be true for this transition to fire
     */
    public void setGuard(String guard) {
		assertMutable();
        this.guard = guard;
    }


    /**
     * Get the action
     * @return Side-effect action to execute during the firing of this transition
     */
    public String getAction() {
        return action;
    }

    /**
     * Set the action
     * @param action Side-effect action to execute during the firing of this transition
     */
    public void setAction(String action) {
		assertMutable();
        this.action = action;
    }

    /**
     * Get the nextState
     * @return Target state of transition
     */
    public State getNextState() {
        return nextState;
    }

    /**
     * Set the nextState
     * @param nextState Target state of transition
     */
    public void setNextState(State nextState) {
		assertMutable();
        this.nextState = nextState;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
    	return "TRANSITION[" + event + ", " + nextState + "]"; 
    }
    

}
