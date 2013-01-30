package br.com.zilics.archetypes.models.rm.utils.xml;

import org.jdom.Namespace;

/**
 * Some constants of the XSD
 * @author Humberto Naves
 *
 */
public final class XmlUtils {
	private XmlUtils() {}

	/**
	 * The default namespace of the XML's
	 */
	public static final Namespace defaultNamespace = Namespace.getNamespace("http://schemas.openehr.org/v1");
	
	/**
	 * The default namespace of the types
	 */
    public static final Namespace xsiNamespace = Namespace.getNamespace("xsi", "http://www.w3.org/2001/XMLSchema-instance");

}
