
package br.com.zilics.archetypes.models.rm.support.identification;

import org.ietf.jgss.GSSException;
import org.ietf.jgss.Oid;

import br.com.zilics.archetypes.models.rm.annotation.RmClass;
import br.com.zilics.archetypes.models.rm.utils.validation.ValidationResult;

/**
 * Models of ISO's Object Identifier(OID) as defined by the standard ISO/IEC 8824.
 * Oids are formed from integers separated by dots. Each non-leaf node in an Oid starting
 * from the left corresponds to an assigning authority, and identifies that authority's namespace,
 * inside which the remaining part of the identifier is locally unique.
 *
 * @author Humberto
 */
@RmClass("ISO_OID")
public class ISO_OID extends UID {

	private static final long serialVersionUID = -4823926363759277333L;
	public static final String PATTERN = "(\\d)+(\\.(\\d)+)*";

	/**
	 *  {@inheritDoc}
	 */
	@Override
    protected void performValidation(ValidationResult result) {
    	super.performValidation(result);
        try {
            new Oid(getValue());
        } catch(GSSException gsse) {
        	result.addItem(this, "Wrong format ", gsse);
        }		
	}
}
