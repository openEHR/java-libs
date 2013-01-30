package br.com.zilics.archetypes.models.rm.exception;

import org.jdom.Element;

/**
 * An exception raised by the {@link br.com.zilics.archetypes.models.rm.utils.xml.XmlParser}
 * @author Humberto Naves
 *
 */
public class XmlParserException extends Exception {

	private static final long serialVersionUID = -8698102377586483963L;
	private final Element element;

	/**
	 * Default constructor
	 * @param element the XML element that generated the exception
	 */
    public XmlParserException(Element element) {
        super();
        this.element = element;
    }

    /**
     * Constructor
     * @param message the exception message
     * @param element the XML element that generated the exception
     */
    public XmlParserException(String message, Element element) {
        super(message);
        this.element = element;
    }
    
    /**
     * Constructor
     * @param message the exception message
     * @param cause the cause of the exception
     * @param element the XML element that generated the exception
     */
    public XmlParserException(String message, Throwable cause, Element element) {
    	super(message, cause);
    	this.element = element;
    }

    /**
     * Get the XML element that generated the exception
     * @return The XML element that generated the exception
     */
    public Element getElement() {
        return element;
    }


    /**
     * {@inheritDoc}
     */
    @Override
	public String toString() {
    	if (element != null)
    		return super.toString() + " at " + element.toString();
    	else
    		return super.toString();
    }

}
