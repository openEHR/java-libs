
package br.com.zilics.archetypes.models.am.openehrprofile.datatypes.basic;

import java.util.Set;

import br.com.zilics.archetypes.models.rm.annotation.EqualsField;
import br.com.zilics.archetypes.models.rm.annotation.NotEmpty;

/**
 * Definition of a non-terminal state in a state machine, i.e. one that has
 * transitions
 * @author Humberto
 */
public class NonTerminalState extends State {
	private static final long serialVersionUID = 8967748818700144110L;
	@EqualsField
	@NotEmpty
	private Set<Transition> transitions;

    /**
     * Get the transitions
     * @return the set of transitions from the current state
     */
    public Set<Transition> getTransitions() {
        return getSet(transitions);
    }

    /**
     * Set the transitions
     * @param transitions the set of transitions from the current state
     */
    public void setTransitions(Set<Transition> transitions) {
		assertMutable();
        this.transitions = transitions;
    }
}
