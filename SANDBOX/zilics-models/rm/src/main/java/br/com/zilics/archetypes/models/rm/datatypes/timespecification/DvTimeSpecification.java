
package br.com.zilics.archetypes.models.rm.datatypes.timespecification;

import br.com.zilics.archetypes.models.rm.datatypes.basic.DataValue;
import br.com.zilics.archetypes.models.rm.datatypes.encapsulated.DvParsable;

/**
 * This is an abstract class of which all timing specifications are specialisations.
 * Specifies points in time, possibly linked to the calendar, or a real world repeating
 * event, such as "breakfast".
 * @author Humberto
 */
public abstract class DvTimeSpecification extends DataValue {
	private static final long serialVersionUID = -7759114771126685335L;
	private DvParsable value;

    /**
     * Get the value
     * @return the specification, in the HL7v3 syntax
     * for PIVL or EIVL types.
     */
    public DvParsable getValue() {
        return value;
    }

    /**
     * Set the value
     * @param value the specification, in the HL7v3 syntax
     * for PIVL or EIVL types.
     */
    public void setValue(DvParsable value) {
		assertMutable();
        this.value = value;
    }

    /**
     * Customized toString() for the Composition Summary list format.
     * @return the value.
     */
    @Override
	public String toString(){
    	return value.toString();
    }

}
