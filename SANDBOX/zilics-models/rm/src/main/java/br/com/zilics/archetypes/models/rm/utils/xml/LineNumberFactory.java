package br.com.zilics.archetypes.models.rm.utils.xml;

import org.jdom.DefaultJDOMFactory;
import org.jdom.Element;
import org.jdom.Namespace;

/**
 * To create {@link LineNumberElement} instead of {@link Element} 
 * @author Humberto Naves
 *
 */
public class LineNumberFactory extends DefaultJDOMFactory {

	/**
	 * {@inheritDoc}
	 */
    @Override
    public Element element(String name) {
        return new LineNumberElement(name);
    }

	/**
	 * {@inheritDoc}
	 */
    @Override
    public Element element(String name, Namespace namespace) {
        return new LineNumberElement(name, namespace);
    }

	/**
	 * {@inheritDoc}
	 */
    @Override
    public Element element(String name, String uri) {
        return new LineNumberElement(name, uri);
    }

	/**
	 * {@inheritDoc}
	 */
    @Override
    public Element element(String name, String prefix, String uri) {
        return new LineNumberElement(name, prefix, uri);
    }
}
