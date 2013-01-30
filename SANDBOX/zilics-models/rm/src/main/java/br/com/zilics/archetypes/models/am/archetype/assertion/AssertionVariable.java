
package br.com.zilics.archetypes.models.am.archetype.assertion;

import br.com.zilics.archetypes.models.am.AMObject;
import br.com.zilics.archetypes.models.rm.annotation.EqualsField;
import br.com.zilics.archetypes.models.rm.annotation.NotEmpty;


/**
 * Definition of a named variable used in an assertion expression.
 * <p>Note: the definition of named variables may change;
 * still under development in ADL2</p>
 *
 * @author Humberto
 */
public class AssertionVariable extends AMObject {
	private static final long serialVersionUID = -742702776137562554L;
	@NotEmpty
	@EqualsField
	private String name;
	@NotEmpty
	@EqualsField
    private String definition;

    /**
     * Get the name
     * @return Name of variable
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name
     * @param name Name of variable
     */
    public void setName(String name) {
		assertMutable();
        this.name = name;
    }

    /**
     * Get the definition
     * @return Formal definition of the variable.
     */
    public String getDefinition() {
        return definition;
    }

    /**
     * Set the definition
     * @param definition Formal definition of the variable.
     */
    public void setDefinition(String definition) {
		assertMutable();
        this.definition = definition;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
    	return getName();
    }    

}
