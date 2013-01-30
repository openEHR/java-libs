
package br.com.zilics.archetypes.models.rm.datatypes.quantity;

import br.com.zilics.archetypes.models.rm.annotation.EqualsField;
import br.com.zilics.archetypes.models.rm.annotation.NotNull;
import br.com.zilics.archetypes.models.rm.utils.validation.ValidationResult;

/**
 * Quantified type representing "scientific" quantities, i.e. quantities expressed as
 * a magnitude and units.
 * @author Humberto
 */
public class DvQuantity extends DvAmount<DvQuantity> {

	private static final long serialVersionUID = 1977253896557668878L;
	@NotNull
	@EqualsField
    private String units;
	@EqualsField
	private double magnitude;
    private int precision;

    /**
     * Default constructor
     */
    public DvQuantity() {}
    
    /**
     * Another constructor
     * @param units the units parameters
     * @param magnitude the magnitude of this quantity
     * @param precision the precision
     */
    public DvQuantity(String units, double magnitude, int precision) {
    	this.units = units;
    	this.magnitude = magnitude;
    	this.precision = precision;
    }
    
    /**
     * Magnitude of this Quantity instance
     *
     * @return the magnitude
     */
    public double getMagnitude() {
        return magnitude;
    }

    /**
     * Magnitude of this Quantity instance
     *
     * @param magnitude set the magnitude
     */
    public void setMagnitude(double magnitude) {
		assertMutable();
        this.magnitude = magnitude;
    }
    
    /**
     * Units of this quantity, expressed in UCUM unit
     * syntax, e.g. "kg/m2", "mm[Hg]", "ms-1", "km/h".
     * @return units
     */
    public String getUnits() {
        return units;
    }

    /**
     * Units of this quantity
     * 
     * @param units Units of this quantity
     */
    public void setUnits(String units) {
		assertMutable();
        this.units = units;
    }

    /**
     * Precision of this Quantity instance
     *
     * @return precision
     */    
    public int getPrecision() {
        return precision;
    }

    /**
     * Precision of this Quantity instance
     *
     * @param precision precision of this quantity instance
     */    
    public void setPrecision(int precision) {
		assertMutable();
        this.precision = precision;
    }
    
    /**
     * True if precision = 0; quantity represents an integral number
     *
     * @return true if integral
     */
    public boolean isIntegral() {
        return precision == 0;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
	public String toString(){
        return magnitude + " " + units;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Double magnitude() {
    	return magnitude;
    }


    /**
     * {@inheritDoc}
     */
    @Override
	public boolean isStrictlyComparableTo(DvQuantity ordered) {
    	//TODO: Change this
        return objectEquals(ordered.units, units);
	}

    /**
     * {@inheritDoc}
     */
	public int compareTo(DvQuantity o) {
        if (magnitude > o.magnitude) {
            return 1;
        } else if (magnitude < o.magnitude) {
            return -1;
        }
        return 0;
	}

	
	private DvQuantity prepareClone(int precision) {
		DvQuantity result = new DvQuantity();
		result.setAccuracy(getAccuracy());
		result.setAccuracyIsPercent(isAccuracyIsPercent());
		result.setMagnitudeStatus(getMagnitudeStatus());
		result.setNormalRange(getNormalRange());
		result.setNormalStatus(getNormalStatus());
		result.setOtherReferenceRanges(getOtherReferenceRanges());
		if (precision == -1)
			result.setPrecision(getPrecision());
		else {
			if (getPrecision() == -1) {
				result.setPrecision(precision);
			} else {
				result.setPrecision(precision < getPrecision() ? precision : getPrecision());
			}
		}
		result.setUnits(getUnits());
		return result;		
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public DvQuantity add(DvQuantity q) {
		DvQuantity result = prepareClone(q.precision);
		result.setMagnitude(getMagnitude() + q.getMagnitude());
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public DvQuantity negate() {
		DvQuantity result = prepareClone(-1);
		result.setMagnitude(-getMagnitude());
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public DvQuantity subtract(DvQuantity q) {
		DvQuantity result = prepareClone(q.precision);
		result.setMagnitude(getMagnitude() - q.getMagnitude());
		return result;
	}
	
	/**
	 * {@inheritDoc}
	 * 
	 */
	@Override
    protected void performValidation(ValidationResult result) {
    	super.performValidation(result);
		if (precision < -1)
			result.addItem(this, "Invalid precision: " + precision);
	}
}
