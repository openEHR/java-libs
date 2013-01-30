
package br.com.zilics.archetypes.models.rm.support.identification;

import br.com.zilics.archetypes.models.rm.RMObject;
import br.com.zilics.archetypes.models.rm.annotation.EqualsField;
import br.com.zilics.archetypes.models.rm.annotation.Ignore;
import br.com.zilics.archetypes.models.rm.annotation.NotEmpty;
import br.com.zilics.archetypes.models.rm.annotation.RmClass;
import br.com.zilics.archetypes.models.rm.utils.validation.ValidationResult;

/**
 * Version tree identifier for one version. Lexical form:
 * <br />trunk_version [ ‘.’ branch_number ‘.’ branch_version ]
 *
 * @author Humberto
 */
@RmClass("VERSION_TREE_ID")
public class VersionTreeID extends RMObject {

	private static final long serialVersionUID = -7226070574702723316L;
	public static final String PATTERN = "[1-9](\\d)*(\\.(\\d)+\\.(\\d)+)?";	

	@NotEmpty
	@EqualsField
	private String value;
	
	@Ignore
	private String trunkVersion;
	@Ignore
	private String branchVersion;
	@Ignore
	private String branchNumber;

    /**
     * Get the value
     * @return String form of this identifier.
     */
    public String getValue() {
        return value;
    }

    /**
     * Set the value
     * @param value String form of this identifier.
     */
    public void setValue(String value) {
		assertMutable();
        this.value = value;
    }
    
    /**
     * Trunk version number
     *
     * @return trunkVersion
     */
    public String trunkVersion() {
        return trunkVersion;
    }
    
    /**
     * Number of branch from the trunk point
     *
     * @return branchNumber
     */
    public String branchNumber() {
        return branchNumber;
    }
    
    /**
     * Version of the branch
     *
     * @return branchVersion
     */
    public String branchVersion() {
        return branchVersion;
    }
    
    /**
     * True if this version identifier represents a branch
     * @return true if it is a branch version
     */
    public boolean isBranch() {
        return branchVersion != null;
    }
    
    /**
     * True if this version is the first copy in a version tree
     * i.e. versionTreeId is 1.
     * @return true if it is the first copy
     */
    public boolean isFirst() {
        return "1".equals(trunkVersion) && !isBranch();
    }
    
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
    	return this.getClass().getSimpleName() + "[" + value + "]";
    }
    
    @Override
    protected void performValidation(ValidationResult result) {
    	super.performValidation(result);
    	String value = getValue();

    	trunkVersion = null;
    	branchNumber = null;
    	branchVersion = null;
    	
    	if (value == null) return;
    	
        if (!value.matches(PATTERN)) {
            result.addItem(this, "Wrong format");
            return;
        }

        int branch = value.indexOf(".");
        try {
        	if (branch < 0) {
        		trunkVersion = value;
        	} else {
        		String[] entries = value.split("\\.");
        		validateValues(Integer.parseInt(entries[0]), Integer.parseInt(entries[1]),
        				Integer.parseInt(entries[2]), result);
        		trunkVersion = entries[0];
        		//	never set branchNo or branchV to 0
        		if(Integer.parseInt(entries[1]) > 0) {
        			branchNumber = entries[1];
        			branchVersion = entries[2];
        		}
        	}
        } catch(NumberFormatException ex) {
        	result.addItem(this, "Invalid integer", ex);
        }
    }
    
    private void validateValues(int trunk, int branchNo, int branchV, ValidationResult result) {
        if (trunk < 1 || branchNo < 0 || branchV < 0) {
            result.addItem(this, "Version number smaller than 0");
        }
        //0 for branchNo or branchV is special case,
        //where both must be 0 to indicate no branch
        if (branchNo == 0 || branchV == 0) {
            if (branchV != branchNo) {
                result.addItem(this, "Breach of branch_validity");
            }
        }
    }
    
    

}
