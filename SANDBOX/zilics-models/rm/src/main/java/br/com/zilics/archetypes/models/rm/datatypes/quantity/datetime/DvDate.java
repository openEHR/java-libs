
package br.com.zilics.archetypes.models.rm.datatypes.quantity.datetime;


/**
 * Represents an absolute point in time, as measured on the Gregorian Calendar, and
 * specified only to the day.
 * @author Humberto
 */
public class DvDate extends DvTemporal<DvDate> {

	private static final long serialVersionUID = 7682036604418521566L;
	private String value;

	/**
	 * Default constructor
	 */
	public DvDate() {}
	
	/**
	 * Another constructor
	 * @param value the value of this data value
	 */
	public DvDate(String value) {
		this.value = value;
	}
	
    /**
     * Get the value
     * @return ISO8601 date string.
     */
    public String getValue() {
        return value;
    }

    /**
     * Set the value
     * @param value ISO8601 date string.
     */
    public void setValue(String value) {
		assertMutable();
        this.value = value;
    }

    /**
     * Customized toString() for the Composition Summary list format.
     * @return the Date value
     */
    @Override
	public String toString(){
        return getValue();
    }

	@Override
	public boolean isStrictlyComparableTo(DvDate ordered) {
		// TODO Auto-generated method stub
		return false;
	}

	public int compareTo(DvDate o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Number magnitude() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DvDate add(DvDuration s) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DvDuration diff(DvDate other) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DvDate subtract(DvDuration s) {
		// TODO Auto-generated method stub
		return null;
	}
}
