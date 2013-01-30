
package br.com.zilics.archetypes.models.am.openehrprofile.datatypes.basic;

import br.com.zilics.archetypes.models.am.AMObject;
import br.com.zilics.archetypes.models.rm.annotation.EqualsField;
import br.com.zilics.archetypes.models.rm.annotation.NotEmpty;


/**
 * Abstract definition of one state in a state machine
 * @author Humberto
 */
public abstract class State extends AMObject {
	private static final long serialVersionUID = -1970203392927154975L;
	@NotEmpty
	@EqualsField
	private String name;

    /**
     * Get the name
     * @return The name of this state
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name
     * @param name The name of this state
     */
    public void setName(String name) {
		assertMutable();
        this.name = name;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
    	return "STATE[" + name + "]"; 
    }
    
}
