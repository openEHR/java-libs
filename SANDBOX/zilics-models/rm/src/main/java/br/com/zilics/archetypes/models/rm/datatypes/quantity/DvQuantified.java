
package br.com.zilics.archetypes.models.rm.datatypes.quantity;

import java.util.HashSet;
import java.util.Set;

import br.com.zilics.archetypes.models.rm.annotation.EqualsField;
import br.com.zilics.archetypes.models.rm.utils.validation.ValidationResult;

/**
 * Abstract class defining the concept of a true quantified values,i.e. values
 * which are not only ordered, but which have a precise magnitude.
 * @author Humberto
 */
public abstract class DvQuantified<T extends DvQuantified<T>> extends DvOrdered<T> {
	private static final long serialVersionUID = -3888863726311214635L;
	@EqualsField
	private String magnitudeStatus;
    private static final Set<String> validMagnitudestatus;
    
    static {
    	validMagnitudestatus = new HashSet<String>();
    	validMagnitudestatus.add("=");
    	validMagnitudestatus.add("<");
    	validMagnitudestatus.add(">");
    	validMagnitudestatus.add("<=");
    	validMagnitudestatus.add(">=");
    	validMagnitudestatus.add("~");
    }

    /**
     * Optional status of magnitude with values:
     *  =”   : magnitude is a point value
     *  "<"  : value is < magnitude
     *  ">"  : value is > magnitude
     *  "<=" : value is <= magnitude
     *  ">=" : value is >= magnitude
     *  "~"  : value is approximately magnitude
     * If not present, meaning is "=".
     * @return status of magnitude
     */
    public String getMagnitudeStatus() {
        return magnitudeStatus;
    }

    /**
     * Optional status of magnitude with values:
     *  =”   : magnitude is a point value
     *  "<"  : value is < magnitude
     *  ">"  : value is > magnitude
     *  "<=" : value is <= magnitude
     *  ">=" : value is >= magnitude
     *  "~"  : value is approximately magnitude
     * If not present, meaning is "=".
     * @param magnitudeStatus status of magnitude
     */
    public void setMagnitudeStatus(String magnitudeStatus) {
		assertMutable();
        this.magnitudeStatus = magnitudeStatus;
    }
    
    /**
     * Test whether a string value is one of the valid values
     * for the magnitudeStatus attribute.
     * @return is a valid magnitude status?
     */
    public boolean isValidMagnitudeStatus() {
    	if (magnitudeStatus == null) return true;
    	return validMagnitudestatus.contains(magnitudeStatus);
    }
    
    /**
     * Numeric value of the quantity in canonical (single value) form
     *
     * @return magnitude
     */
    public abstract Number magnitude();
    
    /**
     * {@inheritDoc}
     * 
     */
    @Override
    protected void performValidation(ValidationResult result) {
    	super.performValidation(result);
    	if (!isValidMagnitudeStatus())
    		result.addItem(this, "Invalid magnitudeStatus: " + magnitudeStatus);
    }
}
