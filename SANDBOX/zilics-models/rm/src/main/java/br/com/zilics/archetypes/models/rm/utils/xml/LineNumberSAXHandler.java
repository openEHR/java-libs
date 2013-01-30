package br.com.zilics.archetypes.models.rm.utils.xml;

import org.jdom.JDOMFactory;
import org.jdom.input.SAXHandler;
import org.xml.sax.Attributes;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

/**
 * To create {@link LineNumberElement} instead of {@link org.jdom.Element}  
 * @author Humberto Naves
 *
 */
public class LineNumberSAXHandler  extends SAXHandler {


	/**
	 * Another delegate constructor
	 * @param f to pass to the superclass constructor
	 */
    public LineNumberSAXHandler(JDOMFactory f) {
        super(f);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void startElement(
            String namespaceURI,
            String localName,
            String qName,
            Attributes atts)
            throws SAXException {
        super.startElement(namespaceURI, localName, qName, atts);
        Locator l = getDocumentLocator();
        // set the start line number
        if (l != null) {
            ((LineNumberElement) getCurrentElement()).setStartLine(l.getLineNumber());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void endElement(String namespaceURI, String localName, String qName)
    throws SAXException {
        Locator l = getDocumentLocator();
        // set the end line number
        if (l != null) {
            ((LineNumberElement) getCurrentElement()).setEndLine(l.getLineNumber());
        }
        super.endElement(namespaceURI, localName, qName);
    }

}
