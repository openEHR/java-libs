package br.com.zilics.archetypes.models.rm.exception;

/**
 * An exception raised while parsing an A-path query
 * @author Humberto Naves
 *
 */
public class PathParseException extends Exception  {
	private static final long serialVersionUID = -1412175660984546421L;

	/**
	 * Constructor
	 * @param message the exception message
	 * @param cause the cause of the exception
	 */
	public PathParseException(String message, Throwable cause) {
		super(message, cause);
	}
}
