
package br.com.zilics.archetypes.models.am.openehrprofile.datatypes.basic;

import br.com.zilics.archetypes.models.am.archetype.constraintmodel.CDomainType;
import br.com.zilics.archetypes.models.rm.annotation.EqualsField;
import br.com.zilics.archetypes.models.rm.annotation.NotNull;

/**
 * Constrainer type for {@link br.com.zilics.archetypes.models.rm.datatypes.basic.DvState}
 * instances.
 * <p>
 * The attribute <i>value</i> defines a state/event table which
 * constrains the allowed values of the attribute <i>value</i> in
 * a {@link br.com.zilics.archetypes.models.rm.datatypes.basic.DvState} instance,
 * as well as the order of transitions between values.
 * </p>
 *
 * @see StateMachine
 * @author Humberto
 */
public class CDvState extends CDomainType {
	private static final long serialVersionUID = 9171736564496806579L;
	@NotNull
	@EqualsField
	private StateMachine value;

    /**
     * Get the value
     * @return the {@link StateMachine} associated with this constraint
     */
    public StateMachine getValue() {
        return value;
    }

    /**
     * Set the value
     * @param value the {@link StateMachine} associated with this constraint
     */
    public void setValue(StateMachine value) {
		assertMutable();
        this.value = value;
    }
    
    /**
     * {@inheritDoc}
     */
	@Override
    public String getRmTypeName() {
        return "DV_STATE";
    }
    
    
	/**
     * {@inheritDoc}
     */
    @Override
    public CDvState makeSimpleCopy() {
    	CDvState result = new CDvState();
    	this.copyProperties(result);
    	result.setValue(this.getValue());
    	return result;
    }	

	/**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
    	return "C_DV_STATE[" + value + "]"; 
    }

}
