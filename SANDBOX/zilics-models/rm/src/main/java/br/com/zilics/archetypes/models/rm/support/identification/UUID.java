
package br.com.zilics.archetypes.models.rm.support.identification;

import br.com.zilics.archetypes.models.rm.annotation.RmClass;
import br.com.zilics.archetypes.models.rm.utils.validation.ValidationResult;

/**
 * Model of the DCE Universal Unique Identifier or UUID which takes from of hexadecimal integers separeted by hyphens,
 * following the pattern 8-4-4-12 as defined by the Open Group, DCE 1.1 Remote Procedure Call Specification,
 * Appendix A. Also known as a GUID.
 *
 * @author Humberto
 */
@RmClass("UUID")
public class UUID extends UID {

	private static final long serialVersionUID = -6078481899598114180L;
	public static final String PATTERN = "([0-9a-fA-F])+(-([0-9a-fA-F])+)*";
	
	/**
	 *  {@inheritDoc}
	 */
	@Override
	protected void performValidation(ValidationResult result) {
		super.performValidation(result);
		if (getValue() == null) return;
        try {
        	java.util.UUID.fromString(getValue());
        } catch(IllegalArgumentException ex) {
        	result.addItem(this, "Wrong format", ex);
        }		
	}	

}
