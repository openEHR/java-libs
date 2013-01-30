
package br.com.zilics.archetypes.models.rm.datatypes.quantity;

import br.com.zilics.archetypes.models.rm.annotation.EqualsField;

/**
 * Countable quantities.
 * @author Humberto
 */
public class DvCount extends DvAmount<DvCount> {

	private static final long serialVersionUID = -5659206516551791172L;
	@EqualsField
	private Long magnitude;

	/**
	 * Numeric value of the quantity in canonical (single value) form
	 *
	 * @return the magnitude
	 */
    public Long getMagnitude() {
        return magnitude;
    }

	/**
	 * Numeric value of the quantity in canonical (single value) form
	 *
	 * @param magnitude the numeric value
	 */
    public void setMagnitude(Long magnitude) {
		assertMutable();
        this.magnitude = magnitude;
    }

  
    /**
     * {@inheritDoc}
     */
    @Override
	public String toString(){
        return objectToString(magnitude);
    }

    /**
     * {@inheritDoc}
     */
	@Override
	public boolean isStrictlyComparableTo(DvCount ordered) {
		return true;
	}

    /**
     * {@inheritDoc}
     */
	public int compareTo(DvCount o) {
		if (magnitude < o.magnitude)
			return -1;
		if (magnitude > o.magnitude)
			return 1;
		return 0;
	}

    /**
     * {@inheritDoc}
     */
	@Override
	public Long magnitude() {
		return magnitude;
	}

	private DvCount prepareClone() {
		DvCount result = new DvCount();
		result.setAccuracy(getAccuracy());
		result.setAccuracyIsPercent(isAccuracyIsPercent());
		result.setMagnitudeStatus(getMagnitudeStatus());
		result.setNormalRange(getNormalRange());
		result.setNormalStatus(getNormalStatus());
		result.setOtherReferenceRanges(getOtherReferenceRanges());
		return result;		
	}

	/**
     * {@inheritDoc}
     */
	@Override
	public DvCount add(DvCount q) {
		DvCount result = prepareClone();
		result.setMagnitude(magnitude + q.magnitude);
		return result;
	}

    /**
     * {@inheritDoc}
     */
	@Override
	public DvCount negate() {
		DvCount result = prepareClone();
		result.setMagnitude(-magnitude);
		return result;
	}

    /**
     * {@inheritDoc}
     */
	@Override
	public DvCount subtract(DvCount q) {
		DvCount result = prepareClone();
		result.setMagnitude(magnitude - q.magnitude);
		return result;
	}
}
