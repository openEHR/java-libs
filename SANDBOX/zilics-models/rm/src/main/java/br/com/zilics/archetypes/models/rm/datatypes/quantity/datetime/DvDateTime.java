
package br.com.zilics.archetypes.models.rm.datatypes.quantity.datetime;


/**
 * Represents an absolute point in time, specified to the second.
 * @author Humberto
 */
public class DvDateTime extends DvTemporal<DvDateTime> {

	private static final long serialVersionUID = -9214892630260815974L;
	private String value;

	/**
	 * Default constructor
	 */
	public DvDateTime() {}
	
	/**
	 * Another constructor
	 * @param value the value of this data value
	 */
	public DvDateTime(String value) {
		this.value = value;
	}

    /**
     * Get the value
     * @return ISO8601 date/time string.
     */
    public String getValue() {
        return value;
    }

    /**
     * Get the value
     * @param value ISO8601 date/time string.
     */
    public void setValue(String value) {
		assertMutable();
        this.value = value;
    }

    @Override
	public String toString() {
    	return (getValue());
    	//return DateTimeParserUtils.parseDate(getValue());

    }

	@Override
	public boolean isStrictlyComparableTo(DvDateTime ordered) {
		// TODO Auto-generated method stub
		return false;
	}

	public int compareTo(DvDateTime o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Number magnitude() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DvDateTime add(DvDuration s) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DvDuration diff(DvDateTime other) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DvDateTime subtract(DvDuration s) {
		// TODO Auto-generated method stub
		return null;
	}

}
