package org.openehr.am.openehrprofile.datatypes.quantity;

import org.apache.commons.lang.StringUtils;
import org.openehr.rm.support.basic.Interval;

/**
 * Constrain instances of DV_QUANTITY.
 * 
 * @author Rong Chen
 */
public class CDvQuantityItem {	
	
	/**
	 * Constructor
	 * 
	 * @param value 
	 * @param units not null or empty
	 * @throws IllegalArgumentException if units null or empty
	 */
	public CDvQuantityItem(Interval<Double> value, String units) {
		if(StringUtils.isEmpty(units)) {
			throw new IllegalArgumentException("units null or empty");
		}
		this.value = value;
		this.units = units;
	}
	
	/**
	 * Constraint on units
	 * 
	 * @return units
	 */
	public String getUnits() {
		return units;
	}
	
	/**
	 * Value must be inside the supplied interval.
	 * 
	 * @return value interval
	 */
	public Interval<Double> getValue() {
		return value;
	}
	
	/* fields */
	private Interval<Double> value;
	private String units;	
}
