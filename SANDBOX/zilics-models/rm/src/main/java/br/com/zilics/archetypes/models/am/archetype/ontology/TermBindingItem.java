
package br.com.zilics.archetypes.models.am.archetype.ontology;

import br.com.zilics.archetypes.models.am.AMObject;
import br.com.zilics.archetypes.models.rm.annotation.EqualsField;
import br.com.zilics.archetypes.models.rm.annotation.NotEmpty;
import br.com.zilics.archetypes.models.rm.annotation.NotNull;
import br.com.zilics.archetypes.models.rm.datatypes.text.CodePhrase;

/**
 * Binding of term corresponding to a_code in target external
 * terminology a_terminology_id as a {@link br.com.zilics.archetypes.models.rm.datatypes.text.CodePhrase}
 *
 * @author Humberto
 */
public class TermBindingItem extends AMObject {

	private static final long serialVersionUID = -9146239515676826527L;
	@NotEmpty
    @EqualsField
	private String code;
	@NotNull
    @EqualsField
    private CodePhrase value;

	/**
	 * Default constructor
	 */
	public TermBindingItem() {}
	
	/**
	 * Another constructor 
	 * @param code the code of this binding
	 * @param value the value of this binding
	 */
	public TermBindingItem(String code, CodePhrase value) {
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
    public CodePhrase getValue() {
        return value;
    }

    /**
     * Set the value
     * @param value The binding of the term
     */
    public void setValue(CodePhrase value) {
		assertMutable();
        this.value = value;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
    	return "TERM_BINDING_ITEM[" + code + ", " + value + "]";
    }
    
}
