package br.com.zilics.archetypes.models.rm.exception;

/**
 * An exception raised by the {@link br.com.zilics.archetypes.models.rm.utils.xml.XmlSerializer}
 * @author Humberto Naves
 *
 */
public class XmlSerializerException extends Exception {

	private static final long serialVersionUID = 8733983113349512875L;


	/**
	 * Default constructor
	 */
	public XmlSerializerException() {
        super();
    }


	/**
	 * Constructor
	 * @param message the exception message
	 */
    public XmlSerializerException(String message) {
        super(message);
    }

    /**
     * Constructor
	 * @param message the exception message
	 * @param cause the cause of the exception
     */
    public XmlSerializerException(String message, Throwable cause) {
        super(message, cause);
    }
}
