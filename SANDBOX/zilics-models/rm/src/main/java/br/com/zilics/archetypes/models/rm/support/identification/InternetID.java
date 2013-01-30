
package br.com.zilics.archetypes.models.rm.support.identification;

import br.com.zilics.archetypes.models.rm.annotation.RmClass;
import br.com.zilics.archetypes.models.rm.utils.validation.ValidationResult;

/**
 * Model of a reverse Internet domain, as used to uniquely identify an Internet domain.
 * In the form of a dot-separated string in the reverse order of a domain name, specified
 * by <a href="http://www.ietf.org/rfc/rfc1034.txt">IETF RFC 1034</a>.
 *
 * @author Humberto
 */
@RmClass("INTERNET_ID")
public class InternetID extends UID {

	private static final long serialVersionUID = 5533905417265958186L;

	public static final String PATTERN = "[a-zA-Z]([a-zA-Z0-9-]*[a-zA-Z0-9])?(\\.[a-zA-Z]([a-zA-Z0-9-]*[a-zA-Z0-9])?)*";

	/**
	 *  {@inheritDoc}
	 */
	@Override
    protected void performValidation(ValidationResult result) {
    	super.performValidation(result);
    	if (getValue() == null) return;
        if (!getValue().matches(PATTERN)) {
        	result.addItem(this, "Wrong format");
        }		
	}	
	
}
