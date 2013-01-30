
package br.com.zilics.archetypes.models.rm.support.identification;


import br.com.zilics.archetypes.models.rm.annotation.Ignore;
import br.com.zilics.archetypes.models.rm.annotation.RmClass;
import br.com.zilics.archetypes.models.rm.utils.validation.ValidationResult;

/**
 * Concrete type corresponding to hierarchical identifiers of the form
 * defined by {@link br.com.zilics.archetypes.models.rm.support.identification.UIDBasedID}.
 *
 * @author Humberto
 */
@RmClass("HIER_OBJECT_ID")
public class HierObjectID extends UIDBasedID {

	private static final long serialVersionUID = 3958028864015153903L;

	@Ignore
	private String extension;
	@Ignore
	private UID root;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String extension() {
		return extension;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UID root() {
		return root;
	}
	
	/**
	 *  {@inheritDoc}
	 */
	@Override
    protected void performValidation(ValidationResult result) {
    	super.performValidation(result);
		String value = getValue();
		if (value == null) return;
		
		root = null;
		extension = null;

		int doubleColons = value.indexOf("::");
        // Check for root segment
        if (doubleColons == 0) {
            result.addItem(this, "Bad format, missing root");
            return;
        }
        //the patterns below are for sorting only, the correct syntax
        //checking is handled by the UID subclasses.
        String rootStr = null;
        if(doubleColons > 0) {
            rootStr = value.substring(0, doubleColons);
        } else {
            rootStr = value;
        }
        if (rootStr.matches(UUID.PATTERN)) { // for UUID
            root = new UUID();
            root.setValue(rootStr);
        } else if (rootStr.matches(ISO_OID.PATTERN)) { //for ISO_OID
            root = new ISO_OID();
            root.setValue(rootStr);
        } else if (rootStr.matches(InternetID.PATTERN)){ //for InternetID, 
            root = new InternetID();
            root.setValue(rootStr);
        } else {
            result.addItem(this, "Wrong format");
            return;
        }
        
        root.validate(result);
        
        if( 0 < doubleColons && doubleColons < (value.length() - 2)) {
            extension = value.substring(doubleColons + 2);
        }           
	}	
	

}
