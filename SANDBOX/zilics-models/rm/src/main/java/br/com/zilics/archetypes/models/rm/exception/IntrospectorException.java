package br.com.zilics.archetypes.models.rm.exception;

/**
 * Any exception raised by the Introspector
 * @author Humberto Naves
 *
 */
public class IntrospectorException extends Exception {

	private static final long serialVersionUID = 8870506799695139186L;
	
	/**
	 * Constructor 
	 * @param message the exception message
	 */
	public IntrospectorException(String message) {
		super(message);
	}

	/**
	 * Constructor
	 * @param message the exception message
	 * @param cause the cause of the exception
	 */
	public IntrospectorException(String message, Throwable cause) {
		super(message, cause);
	}

}
