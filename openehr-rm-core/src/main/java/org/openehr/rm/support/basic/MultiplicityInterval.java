package org.openehr.rm.support.basic;



public class MultiplicityInterval extends Interval<Integer>{

	/*
	 * A set of values used to express existence requirements.
	 */
	public enum ExistenceValues{
		REQUIRED, OPTIONAL, NOT_ALLOWED
	}
	
	/*
	 * Factory method to create intances that comply with pre defined Existence values
	 */
	public static MultiplicityInterval createNew(ExistenceValues value){
		MultiplicityInterval instance = null;
		
		if(value.equals(ExistenceValues.REQUIRED)){
			instance = new MultiplicityInterval(1, 1);
		}else if(value.equals(ExistenceValues.OPTIONAL))
			instance = new MultiplicityInterval(0,1);
		else if(value.equals(ExistenceValues.NOT_ALLOWED))
			instance = new MultiplicityInterval(0,0);
		
		if(instance != null){//the following should be true for all assignments above
			instance.setLowerIncluded(true);
			instance.setUpperIncluded(true);
		}
		
		return instance;
	}
	
	public MultiplicityInterval(Integer lower, Integer upper) {
		super(lower, upper);				
	}
	
	/**
	 * Convenience constructor for an unbounded upper instance configuration.
	 * @param lower
	 * @param isOpen
	 * @param isOptional
	 * @param isProhibited
	 */
	public MultiplicityInterval(Integer lower){
		//null upper parameter makes upper unbounded
		this(lower,null);
	}
	
	public MultiplicityInterval(Interval<Integer> interval){
		this(interval.lower, interval.upper);
		lowerIncluded = interval.lowerIncluded;
		upperIncluded = interval.upperIncluded;
		//upper unbounded does not require setting
	}
	
//	TODO: some of the initialization functions (make_xyz) from Eiffel code can't be implemented as java
	//constructors. would probably require a factory implementation
	
	/**
	 * Perform union operation and modify the instance on which union is called accordingly.
	 * @param other The other MultiplicityInterval instance
	 */
	public void union(MultiplicityInterval other){
		if(isUpperUnbounded() || other.isUpperUnbounded())//one of these has an upper bound
			upper = null;
		else
			upper = other.upper.intValue() > upper.intValue() ? other.upper : upper;
		
		lower = other.lower.intValue() < lower.intValue() ? other.lower : lower; 		
	}
	
	public void add(MultiplicityInterval other){
		if(isUpperUnbounded() || other.isUpperUnbounded())
			upper = null;
		else
			upper = upper + other.upper;
		
		lower = other.lower + lower;
	}
	
	/**
	 * Compare intervals of two instances
	 * @param other The {@link MultiplicityInterval} to compare 
	 * @return true if intervals are equal, false if not
	 */
	public boolean equalInterval(MultiplicityInterval other){
		return (lower.intValue() == other.lower.intValue()) &&
				(isLowerIncluded() == other.isLowerIncluded()) &&
				(isUpperIncluded() == other.isUpperIncluded()) &&
				(isLowerUnbounded() == other.isLowerUnbounded()) &&
				(isUpperUnbounded() == other.isUpperUnbounded());
	}
	
	/**
	 * Return a string representation of this {@link MultiplicityInterval} instance, 
	 * using one of Number, Number..* or Number..Number formats
	 * @return The string representation of the {@link MultiplicityInterval} instance.
	 */
	public String asString(){
		StringBuffer result = new StringBuffer();
		if(isUpperUnbounded())
			result.append(String.valueOf(upper.intValue()) + "..*");
		else if(lower.intValue() != upper.intValue())
			result.append(String.valueOf(lower.intValue()) + ".." + String.valueOf(upper.intValue()));
		else
			result.append(String.valueOf(lower.intValue()));
		return result.toString();
	}
	
	public boolean isOpen(){
		return isUpperUnbounded() && (lower == 0);
	}
	
	public boolean isOptional(){
		if(upper != null)
			return (lower == 0) && upper == 1;
		else
			return false; //interpret every other configuration as non-optional. Todo: check validity of this
	}
	
	public boolean isProhibited(){
		if(upper != null )
			return lower == 0 && upper == 0;
		else
			return false;//interpret every other configuration as not prohibited. Todo: check validity of this
	}
	
	/*
	 * Returns interval information using existence terms expressed with ExistenceValues
	 */
	public boolean intervalValueEquals(ExistenceValues value){
		if(value.equals(ExistenceValues.REQUIRED))
			return !(this.isLowerUnbounded()) && !(this.isUpperUnbounded()) && //to avoid any null pointer exceptions
					this.lower == 1 && this.upper == 1;
		else if(value.equals(ExistenceValues.OPTIONAL))
			return !(this.isLowerUnbounded()) && !(this.isUpperUnbounded()) && //to avoid any null pointer exceptions
					this.lower == 0 && this.upper == 1;
		else //NOT ALLOWED
			return !(this.isLowerUnbounded()) && !(this.isUpperUnbounded()) && //to avoid any null pointer exceptions
					this.lower == 0 && this.upper == 0;
	}
	
	

}
