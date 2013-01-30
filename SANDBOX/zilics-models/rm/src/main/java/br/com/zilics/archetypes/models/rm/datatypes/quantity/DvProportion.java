
package br.com.zilics.archetypes.models.rm.datatypes.quantity;

import br.com.zilics.archetypes.models.rm.annotation.EqualsField;
import br.com.zilics.archetypes.models.rm.annotation.NotNull;
import br.com.zilics.archetypes.models.rm.utils.validation.ValidationResult;

/**
 * Models a ratio of values, i.e. where the numerator and denominator are both pure
 * numbers.
 * @author Humberto
 */
public class DvProportion extends DvAmount<DvProportion> {

	private static final long serialVersionUID = 4462405342142159708L;
	@EqualsField
	private double numerator;
	@EqualsField
    private double denominator = 1.0;
    @NotNull
	@EqualsField
    private ProportionKind type;
	@EqualsField
    private int precision;

    /**
     * The numerator of the proportion
     * @return the numerator
     */
    public double getNumerator() {
        return numerator;
    }

    /**
     * The numerator of the proportion
     * @param numerator the numerator
     */
    public void setNumerator(double numerator) {
		assertMutable();
        this.numerator = numerator;
    }

    /**
     * The denominator of the proportion
     * @return the denominator
     */
    public double getDenominator() {
        return denominator;
    }

    /**
     * The denominator of the proportion
     * @param denominator the denominator
     */
    public void setDenominator(double denominator) {
		assertMutable();
        this.denominator = denominator;
    }

    /**
     * Get the proportion type
     * @return the proportion type
     */
    public ProportionKind getType() {
        return type;
    }

    /**
     * Set the proportion type
     * @param type the proportion type
     */
    public void setType(ProportionKind type) {
		assertMutable();
        this.type = type;
    }

    /**
     * Get the precision
     * @return the precision of this proportion
     */
    public int getPrecision() {
        return precision;
    }

    /**
     * Set the precision
     * @param precision the precision of this proportion
     */
    public void setPrecision(int precision) {
		assertMutable();
        this.precision = precision;
    }

    /**
     * {@inheritDoc}
     */
    @Override
	public String toString() {
    	if(type == null){
    		return "[invalid proportion instance - ProportionKind is null]";
    	}
    	switch (type) {
    	case RATIO:
    		return numerator + " / " + denominator;
    	case UNITARY:
    		return "" + ((int)numerator);
    	case PERCENT:
    		return numerator + "%";
    	case INTEGER_FRACTION:
    		return formatIntegerFraction((int) numerator, (int) denominator);
    	default:
    		return "" + (numerator/denominator);
    	}
    }


    /**
     * Transforms to Integers to it's Integer Fraction notation.
     * @param numerator
     * @param denominator
     * @return
     */
    private static String formatIntegerFraction(int numerator, int denominator) {
    	StringBuilder sb = new StringBuilder();
    	sb.append(numerator/denominator);
    	sb.append(" ").append(numerator%denominator);
    	sb.append("/").append(denominator);
    	return sb.toString();

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isStrictlyComparableTo(DvProportion ordered) {
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	public int compareTo(DvProportion o) {
		if (magnitude() < o.magnitude()) return -1;
		else if (magnitude() > o.magnitude()) return +1;
		return 0;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Double magnitude() {
		return (numerator/denominator);
	}

	
	private DvProportion prepareClone() {
		DvProportion result = new DvProportion();
		result.setAccuracy(getAccuracy());
		result.setAccuracyIsPercent(isAccuracyIsPercent());
		result.setMagnitudeStatus(getMagnitudeStatus());
		result.setNormalRange(getNormalRange());
		result.setNormalStatus(getNormalStatus());
		result.setOtherReferenceRanges(getOtherReferenceRanges());
		result.setPrecision(getPrecision());
		result.setType(getType());
		result.setDenominator(getDenominator());
		return result;		
	}	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public DvProportion add(DvProportion q) {
		DvProportion result = prepareClone();
		result.setDenominator(1.0);
		result.setNumerator(magnitude() + q.magnitude());
		result.setType(ProportionKind.RATIO);
		if ((result.getPrecision() == -1) ||
				(result.getPrecision() > q.getPrecision() && q.getPrecision() != -1))
			result.setPrecision(q.getPrecision());
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public DvProportion negate() {
		DvProportion result = prepareClone();
		result.setNumerator(-getNumerator());
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public DvProportion subtract(DvProportion q) {
		DvProportion result = prepareClone();
		result.setDenominator(1.0);
		result.setNumerator(magnitude() - q.magnitude());
		result.setType(ProportionKind.RATIO);
		if ((result.getPrecision() == -1) ||
				(result.getPrecision() > q.getPrecision() && q.getPrecision() != -1))
			result.setPrecision(q.getPrecision());
		return result;
	}
	
	
	/**
	 * True if numerator and denominator are integers i.e. precision = 0
	 * @return true if both numerator and denominator are integers
	 */
	public boolean isIntegral() {
		return (Math.floor(numerator) == numerator) && (Math.floor(denominator) == denominator);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
    protected void performValidation(ValidationResult result) {
    	super.performValidation(result);
		if (denominator == 0)
			result.addItem(this, "Invalid denominator: " + denominator);
		if (precision == 0) {
			if (!isIntegral())
				result.addItem(this, "Proportion is not integral one, yet precision = 0");
		}
		if (type == ProportionKind.FRACTION || type == ProportionKind.INTEGER_FRACTION)
			if (!isIntegral())
				result.addItem(this, "Invalid proportion type");
		if (type == ProportionKind.UNITARY && denominator != 1.0)
			result.addItem(this, "Invalid proportion type: " + type);
		if (type == ProportionKind.PERCENT && denominator != 100.0)
			result.addItem(this, "Invalid proportion type: " + type);		
	}
}