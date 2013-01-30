package br.com.zilics.archetypes.models.am.utils.validation;

import java.io.Serializable;

import br.com.zilics.archetypes.models.am.archetype.constraintmodel.CObject;

/**
 * A validation error item.
 * 
 * @author Humberto Naves
 *
 */
public class SemanticValidationResultItem implements Serializable {

	private static final long serialVersionUID = 4150056551458682686L;
	
	private final CObject constraint;
	private final Object value;
	private final String message;
	private final Throwable cause;
	
	/**
	 * Default constructor
	 * @param constraint The constraint that generated the error
	 * @param value the instance that does not match the constraint
	 * @param message The validation error message
	 * @param cause a possible cause of the validation error
	 */
	public SemanticValidationResultItem(CObject constraint, Object value, String message, Throwable cause) {
		if (constraint == null) throw new NullPointerException("Null constraint");
		if (value == null) throw new NullPointerException("Null value");
		if (message == null) throw new NullPointerException("Null message");
		this.constraint = constraint;
		this.value = value;
		this.message = message;
		this.cause = cause;
	}
	
	/**
	 * Get the constraint that generated the error
	 * @return The constraint that generated the error 
	 */
	public CObject getConstraint() {
		return constraint;
	}
	
	/**
	 * Get the instance that does not match the constraint
	 * @return the instance
	 */
	public Object getValue() {
		return value;
	}
	
	/**
	 * Get the validation error message
	 * @return The validation error message
	 */
	public String getMessage() {
		return message;
	}
	
	/**
	 * Get a possible cause of the validation error
	 * @return the cause
	 */
	public Throwable getCause() {
		return cause;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == this) return true;
		if (! (obj instanceof SemanticValidationResultItem)) return false;
		
		SemanticValidationResultItem other = (SemanticValidationResultItem) obj;
		return constraint.equals(other.constraint) && value.equals(other.value) && value.equals(other.value);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		final int PRIME = 17;
		int hash = constraint.hashCode();
		hash = hash * PRIME + value.hashCode();
		hash = hash * PRIME + message.hashCode();
		return hash;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return constraint + "->" + value + ": " + message + ((cause == null) ? "" : " Cause: " + cause);
	}
	

}
