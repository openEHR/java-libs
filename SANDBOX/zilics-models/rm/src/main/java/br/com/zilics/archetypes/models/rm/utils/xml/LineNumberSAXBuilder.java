package br.com.zilics.archetypes.models.rm.utils.xml;

import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.jdom.input.SAXHandler;

/**
 * To create {@link LineNumberElement} instead of {@link Element} 
 * @author Humberto
 *
 */
public class LineNumberSAXBuilder extends SAXBuilder {

	/**
	 * Default constructor
	 */
	public LineNumberSAXBuilder() {
		super();
	}
	
	/**
	 * Delegate constructor
	 * @param validate to pass to the superclass constructor
	 */
	public LineNumberSAXBuilder(boolean validate) {
		super(validate);
	}
	
	/**
	 * Delegate constructor
	 * @param saxDriverClass to pass to the superclass constructor
	 */
	public LineNumberSAXBuilder(String saxDriverClass) {
		super(saxDriverClass);
	}

	/**
	 * Delegate constructor
	 * @param saxDriverClass to pass to the superclass constructor
	 * @param validate to pass to the superclass constructor
	 */
	public LineNumberSAXBuilder(String saxDriverClass, boolean validate) {
		super(saxDriverClass, validate);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
    protected SAXHandler createContentHandler() {
        return new LineNumberSAXHandler(new LineNumberFactory());
    }

}
