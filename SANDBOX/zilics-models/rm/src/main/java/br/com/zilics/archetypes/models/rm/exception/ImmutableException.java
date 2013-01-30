package br.com.zilics.archetypes.models.rm.exception;

import br.com.zilics.archetypes.models.rm.RMObject;

/**
 * An exception that is raised while attempting to change an immutable object
 * @author Humberto Naves
 *
 */
public class ImmutableException extends RuntimeException {
	private static final long serialVersionUID = -6331110911633107135L;

	private final RMObject rmObject;
	
	/**
	 * Default constructor
	 * @param message the message of the exception
	 * @param rmObject the immutable object that was the source of the exception
	 */
	public ImmutableException(String message, RMObject rmObject) {
		super(message);
		this.rmObject = rmObject;
	}
	
	/**
	 * The source of the exception
	 * @return the immutable object
	 */
	public RMObject getRMObject() {
		return rmObject;
	}
}