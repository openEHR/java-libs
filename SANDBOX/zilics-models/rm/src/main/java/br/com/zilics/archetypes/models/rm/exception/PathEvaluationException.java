package br.com.zilics.archetypes.models.rm.exception;

/**
 * An exception raised by the Path evaluator
 * @author Humberto Naves
 *
 */
public class PathEvaluationException extends Exception {

	private static final long serialVersionUID = -5793767083066750019L;

	/**
	 * Constructor 
	 * @param message the exception message
	 */
	public PathEvaluationException(String message) {
		super(message);
	}

	/**
	 * Constructor
	 * @param message the exception message
	 * @param cause the cause of the exception
	 */
	public PathEvaluationException(String message, Throwable cause) {
		super(message, cause);
	}

}

