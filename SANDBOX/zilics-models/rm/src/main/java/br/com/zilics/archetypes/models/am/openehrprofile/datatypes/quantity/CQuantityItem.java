
package br.com.zilics.archetypes.models.am.openehrprofile.datatypes.quantity;

import br.com.zilics.archetypes.models.am.AMObject;
import br.com.zilics.archetypes.models.rm.annotation.EqualsField;
import br.com.zilics.archetypes.models.rm.annotation.NotEmpty;
import br.com.zilics.archetypes.models.rm.support.basic.Interval;

/**
 * Constraint instances of {@link br.com.zilics.archetypes.models.rm.datatypes.quantity.DvQuantity},
 * used by {@link CDvQuantity}
 * @author Humberto
 */
public class CQuantityItem extends AMObject {

	private static final long serialVersionUID = 4931211549165847078L;
	@EqualsField
	private Interval<Double> magnitude;
	@EqualsField
    private Interval<Integer> precision;
	@EqualsField
	@NotEmpty
    private String units;
	
	/**
	 * Default constructor
	 */
	public CQuantityItem() {}
	
	/**
	 * Another constructor
	 * 
	 * @param magnitude the interval magnitude of this constraint
	 * @param precision the interval of precision
	 * @param units the units
	 */
	public CQuantityItem(Interval<Double> magnitude, Interval<Integer> precision, String units) {
		this.magnitude = magnitude;
		this.precision = precision;
		this.units = units;
	}

    /**
     * Get the magnitude
     * @return Constraint on the magnitude of the
     * {@link br.com.zilics.archetypes.models.rm.datatypes.quantity.DvQuantity}
     */
    public Interval<Double> getMagnitude() {
        return magnitude;
    }

    /**
     * Set the magnitude
     * @param magnitude Constraint on the magnitude of the
     * {@link br.com.zilics.archetypes.models.rm.datatypes.quantity.DvQuantity}
     */
    public void setMagnitude(Interval<Double> magnitude) {
		assertMutable();
        this.magnitude = magnitude;
    }

    /**
     * Get the precision
     * @return Constraint on the precision of the
     * {@link br.com.zilics.archetypes.models.rm.datatypes.quantity.DvQuantity}. A value
     * of -1 means that precision is unconstrained
     */
    public Interval<Integer> getPrecision() {
        return precision;
    }

    /**
     * Set the precision
     * @param precision Constraint on the precision of the
     * {@link br.com.zilics.archetypes.models.rm.datatypes.quantity.DvQuantity}. A value
     * of -1 means that precision is unconstrained
     */
    public void setPrecision(Interval<Integer> precision) {
		assertMutable();
        this.precision = precision;
    }

    /**
     * Get the units
     * @return Constraint on units of the
     * {@link br.com.zilics.archetypes.models.rm.datatypes.quantity.DvQuantity}
     */
    public String getUnits() {
        return units;
    }

    /**
     * Set the units
     * @param units Constraint on units of the
     * {@link br.com.zilics.archetypes.models.rm.datatypes.quantity.DvQuantity}
     */
    public void setUnits(String units) {
		assertMutable();
        this.units = units;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
    	return "C_QUANTITY_ITEM[" + magnitude + ", " + units + "]";
    }

}
