
package br.com.zilics.archetypes.models.am.openehrprofile.datatypes.basic;

import java.util.Set;

import br.com.zilics.archetypes.models.am.AMObject;
import br.com.zilics.archetypes.models.rm.annotation.EqualsField;
import br.com.zilics.archetypes.models.rm.annotation.NotEmpty;

/**
 * Definition of a state machine in terms of states, transitions events
 * and outputs, and next states
 *
 * @see State
 * @author Humberto
 */
public class StateMachine extends AMObject {

	private static final long serialVersionUID = -1857824916610342804L;
	@NotEmpty
	@EqualsField
	private Set<State> states;

    /**
     * Get the states
     * @return the set of states
     */
    public Set<State> getStates() {
        return getSet(states);
    }

    /**
     * Set the states
     * @param states the set of states
     */
    public void setStates(Set<State> states) {
		assertMutable();
        this.states = states;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
    	return objectToString(states); 
    }


}
