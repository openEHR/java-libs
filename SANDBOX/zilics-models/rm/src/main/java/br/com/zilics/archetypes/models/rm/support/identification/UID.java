
package br.com.zilics.archetypes.models.rm.support.identification;

import br.com.zilics.archetypes.models.rm.RMObject;
import br.com.zilics.archetypes.models.rm.annotation.EqualsField;
import br.com.zilics.archetypes.models.rm.annotation.NotEmpty;
import br.com.zilics.archetypes.models.rm.annotation.RmClass;

/**
 * Abstract parent of classes representing unique identifiers which
 * identify information entities in a durable way. UIDs only ever identify one IE
 * in time or space and are never re-used.
 *
 * @author Humberto
 */
@RmClass("UID")
public abstract class UID extends RMObject {
	private static final long serialVersionUID = 5772826537250354657L;
	@NotEmpty
	@EqualsField
    private String value;

    /**
     * Get the value
     * @return The value of this UID.
     */
    public String getValue(){
        return value;
    }

    /**
     * Set the value
     * @param value The value of this UID.
     */
    public void setValue(String value){
		assertMutable();
        this.value = value;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
    	return this.getClass().getSimpleName() + "[" + value + "]";
    }
}
