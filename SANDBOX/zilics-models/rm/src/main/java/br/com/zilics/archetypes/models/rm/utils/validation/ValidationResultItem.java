package br.com.zilics.archetypes.models.rm.utils.validation;

import java.io.Serializable;

import br.com.zilics.archetypes.models.rm.RMObject;

/**
 * A validation error item.
 * @author Humberto Naves
 */
public final class ValidationResultItem implements Serializable {
	private static final long serialVersionUID = -4303910504580058143L;

	private final RMObject problematicObject;
	private final String message;
	private final Throwable cause;
	
	/**
	 * Default constructor
	 * @param problematicObject the object that generated this error
	 * @param message the error message
	 * @param cause any possible exception that caused the error
	 */
	public ValidationResultItem(RMObject problematicObject, String message, Throwable cause) {
		if (problematicObject == null)
			throw new NullPointerException("Null problematic object");
		if (message == null)
			throw new NullPointerException("Null message");
		
		this.problematicObject = problematicObject;
		this.message = message;
		this.cause = cause;
	}
	
	/**
	 * Get the object with invalid state
	 * @return the object with invalid state
	 */
	public RMObject getProblematicObject() {
		return problematicObject;
	}
	
	/**
	 * Get the error message 
	 * @return the error message
	 */
	public String getMessage() {
		return message;
	}
	
	/**
	 * Get any possible exception that caused the validation error
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
		if (!(obj instanceof ValidationResultItem)) return false;
		ValidationResultItem other = (ValidationResultItem) obj;
		
		return message.equals(other.message) &&
			problematicObject.equals(other.problematicObject);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		final int PRIME = 37;
		int hash = message.hashCode();
		hash = hash * PRIME + problematicObject.hashCode();
		hash = hash * PRIME + ((cause == null) ? 0 : cause.hashCode());
		return hash;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Problem: ").append(message);
		sb.append("\nObject: ").append(problematicObject);
		if (cause != null)
			sb.append("\nCause: ").append(cause);
		return sb.toString();
	}
	
}
