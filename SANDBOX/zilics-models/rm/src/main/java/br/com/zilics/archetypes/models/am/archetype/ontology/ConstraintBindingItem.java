package br.com.zilics.archetypes.models.am.archetype.ontology;

import br.com.zilics.archetypes.models.am.AMObject;
import br.com.zilics.archetypes.models.rm.annotation.EqualsField;
import br.com.zilics.archetypes.models.rm.annotation.NotEmpty;

/**
 * Binding of constraint corresponding to a_code in target external
 * terminology a_terminology_id, as a string, which is usually a formal
 * query expression.
 * @author Humberto
 */
public class ConstraintBindingItem extends AMObject {

	private static final long serialVersionUID = 1953430683124221156L;
	@NotEmpty
    @EqualsField
	private String code;
	@NotEmpty
    @EqualsField
    private String value;

	/**
	 * Default constructor
	 */
	public ConstraintBindingItem() {}
	
	/**
	 * Another constructor
	 * @param code the code of this binding
	 * @param value the value of this binding
	 */
	public ConstraintBindingItem(String code, String value) {
		this.code = code;
		this.value = value;
	}
    /**
     * Get the code
     * @return The term code
     */
    public String getCode() {
        return code;
    }

    /**
     * Set the code
     * @param code The term code
     */
    public void setCode(String code) {
		assertMutable();
        this.code = code;
    }

    /**
     * Get the value
     * @return The binding of the term
     */
    public String getValue() {
        return value;
    }

    /**
     * Set the value
     * @param value The binding of the term
     */
    public void setValue(String value) {
		assertMutable();
        this.value = value;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
    	return "CONSTRAINT_BINDING_ITEM[" + code + ", " + value + "]";
    }

}
