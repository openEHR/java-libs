
package br.com.zilics.archetypes.models.am.archetype.constraintmodel.primitive;

import br.com.zilics.archetypes.models.am.AMObject;


/**
 * Abstract supertype of all primitive types
 *
 * @author Humberto
 */
public abstract class CPrimitive extends AMObject {

	private static final long serialVersionUID = 7494321762000048066L;
	
	/**
	 * Get the assumed value
	 * @return the assumed value
	 */
	public abstract Object getAssumedValue();
	
	/**
	 * Get the type of this primitive
	 * @return the type of this primitive
	 */
	public abstract String getType();

	/**
	 * If this constraint has an assumed value
	 * @return true if it has an assumed value
	 */
	public boolean hasAssumedValue() {
		return (getAssumedValue() != null);
	}
	
	/**
	 * Return the default value of this constraint
	 * @return the default value
	 */
	public abstract Object defaultValue();
	
	/**
	 * Verifies if the given value matches the constraint 
	 * @param value The value to check
	 * @return true if it is a valid value
	 */
	public abstract boolean isValidValue(Object value);
	
	/**
	 * Check if an value is of the same type of this constraint
	 * @param value The value to check
	 * @return true if it is of a valid type
	 */
	public abstract boolean isValidType(Object value);
	
}
