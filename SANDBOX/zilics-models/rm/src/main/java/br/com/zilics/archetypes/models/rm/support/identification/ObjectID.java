package br.com.zilics.archetypes.models.rm.support.identification;

import br.com.zilics.archetypes.models.rm.RMObject;
import br.com.zilics.archetypes.models.rm.annotation.EqualsField;
import br.com.zilics.archetypes.models.rm.annotation.NotEmpty;
import br.com.zilics.archetypes.models.rm.annotation.RmClass;

/**
 * Ancestor class of identifiers of informational objects. Ids may be completely
 * meaningless, in which case their only job is to refer to something, or may
 * carry some information to do with the identified object.
 *
 * @author Humberto
 */
@RmClass("OBJECT_ID")
public abstract class ObjectID extends RMObject {

	private static final long serialVersionUID = 7720474854623559621L;
	@NotEmpty
	@EqualsField
    private String value;
	
	/**
	 * Default constructor
	 */
	public ObjectID() {}
	
	/**
	 * Another constructor
	 * @param value the value of this id
	 */
	public ObjectID(String value) {
		this.value = value;
	}
	
    /**
     * Get the value
     * @return The value of the ID.
     */
    public String getValue(){
        return value;
    }

    /**
     * Set the value
     * @param value The value of the ID.
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
    	return value;
    }
    
}