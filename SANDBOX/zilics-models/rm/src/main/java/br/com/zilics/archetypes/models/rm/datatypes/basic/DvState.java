
package br.com.zilics.archetypes.models.rm.datatypes.basic;

import br.com.zilics.archetypes.models.rm.annotation.EqualsField;
import br.com.zilics.archetypes.models.rm.annotation.NotNull;
import br.com.zilics.archetypes.models.rm.datatypes.text.DvCodedText;

/**
 * For representing state values which obey a defined state machine, such as a
 * variable representing the states of an instruction or care process.
 * @author Humberto
 */
public class DvState extends DataValue {

	private static final long serialVersionUID = -807320587178416425L;
	@NotNull
	@EqualsField
	private DvCodedText value;
	@NotNull
	@EqualsField
    private Boolean terminal;

    /**
     * Get the value
     * @return The state name.
     *
     * State names are determined by a state/event table defined in archetypes,
     * and coded using <I>open</I>EHR Terminology or local archetype terms, as specified
     * by the archetype.
     */
    public DvCodedText getValue() {
        return value;
    }

    /**
     * Set the value
     * @param value The state name.
     *
     * State names are determined by a state/event table defined in archetypes,
     * and coded using <I>open</I>EHR Terminology or local archetype terms, as specified
     * by the archetype.
     */
    public void setValue(DvCodedText value) {
		assertMutable();
        this.value = value;
    }

    /**
     * Get the terminal
     * @return Indicates whether this state is a terminal state, such as "aborted",
     * "completed",etc, from which no futher transitions are possible.
     */
    public Boolean getTerminal() {
        return terminal;
    }

    /**
     * Set the terminal
     * @param terminal Indicates whether this state is a terminal state, such as "aborted",
     * "completed",etc, from which no futher transitions are possible.
     */
    public void setTerminal(Boolean terminal) {
		assertMutable();
        this.terminal = terminal;
    }

    
    /**
     * {@inheritDoc}
     */
    @Override
	public String toString(){
    	return objectToString(value);
    }    
}
