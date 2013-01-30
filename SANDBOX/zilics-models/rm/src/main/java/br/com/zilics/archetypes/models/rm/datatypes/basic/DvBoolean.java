
package br.com.zilics.archetypes.models.rm.datatypes.basic;

import br.com.zilics.archetypes.models.rm.annotation.NotNull;
import br.com.zilics.archetypes.models.rm.annotation.EqualsField;


/**
 * Items which are truly boolean data, such as true/false or yes/no answers.
 * @author Humberto
 */
public class DvBoolean extends DataValue {

	private static final long serialVersionUID = 7002816660515256782L;
	@NotNull
	@EqualsField
	private Boolean value;

    /**
     * Get the value of the item
     * @return Boolean value of this item.
     */
    public Boolean getValue() {
        return value;
    }

    /**
     * Set the value of the item
     * @param value Boolean value of this item.
     */
    public void setValue(Boolean value) {
		assertMutable();
        this.value = value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
	public String toString(){
    	return objectToString(value);
    }
}
