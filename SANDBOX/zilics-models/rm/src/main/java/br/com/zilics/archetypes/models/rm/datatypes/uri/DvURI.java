
package br.com.zilics.archetypes.models.rm.datatypes.uri;

import br.com.zilics.archetypes.models.rm.annotation.RmClass;
import br.com.zilics.archetypes.models.rm.datatypes.basic.DataValue;

/**
 * Purpose A reference to an object which conforms to the Universal
 * Resource Identifier (URI) standard, as defined by W3C RFC 2936.
 * <p>
 * See "Universal Resource Identifiers in WWW" by Tim Berners-Lee at
 * <a href="http://www.ietf.org/rfc/rfc2396.txt"><i>
 * http://www.ietf.org/rfc/rfc2396.txt</i></a>
 * This is a World-Wide Web RFC for global identification of resources.
 * </p>
 * <p>
 * See <a href="http://www.w3.org/Addressing"><i>
 * http://www.w3.org/Addressing</i><a> for a starting point on URIs.
 * </p>
 * <p>
 * See <a href="http://www.ietf.org/rfc/rfc2806.txt"><i>
 * http://www.ietf.org/rfc/rfc2806.txt</i></a>
 * for new URI types like telephone, fax and modem numbers.
 * </p>
 *
 * @author Humberto
 */
@RmClass("DV_URI")
public class DvURI extends DataValue {

	private static final long serialVersionUID = 9137497721169565230L;
	private String value;

    /**
     * Get the value
     * @return Value of URI as a string.
     */
    public String getValue() {
        return value;
    }

    /**
     * Set the value
     * @param value Value of URI as a string.
     */
    public void setValue(String value) {
		assertMutable();
        this.value = value;
    }

    /**
     * Customized toString() for the Composition Summary list format.
     * @return the value
     */
    @Override
	public String toString(){
    return this.value;
    }
}
