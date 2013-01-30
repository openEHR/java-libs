
package br.com.zilics.archetypes.models.rm.datatypes.quantity.datetime;


/**
 * Represents an absolute point in time from an origin usually interpreted as meaning
 * the start of the current day, specified to the seconds.
 * @author Humberto
 */
public class DvTime extends DvTemporal<DvTime> {

	private static final long serialVersionUID = 1969663785477221018L;
	private String value;

	/**
	 * Default constructor
	 */
	public DvTime() {}
	
	/**
	 * Another constructor
	 * @param value the value of this data value
	 */
	public DvTime(String value) {
		this.value = value;
	}

    /**
     * Get the value
     * @return ISO8601 time string.
     */
    public String getValue() {
        return value;
    }

    /**
     * Set the value
     * @param value ISO8601 time string.
     */
    public void setValue(String value) {
		assertMutable();
        this.value = value;
    }

    @Override
	public String toString(){
        return getValue();
    }

	@Override
	public boolean isStrictlyComparableTo(DvTime ordered) {
		// TODO Auto-generated method stub
		return false;
	}

	public int compareTo(DvTime o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Number magnitude() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DvTime add(DvDuration s) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DvDuration diff(DvTime other) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DvTime subtract(DvDuration s) {
		// TODO Auto-generated method stub
		return null;
	}
}
