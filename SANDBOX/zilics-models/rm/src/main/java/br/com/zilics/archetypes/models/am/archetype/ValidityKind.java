package br.com.zilics.archetypes.models.am.archetype;

/**
 * An enumeration of 3 values which may commonly occur in constraint models.
 * @author Humberto
 */
public enum ValidityKind {

    /**
     * Constant to indicate mandatory presence of something
     */
    MANDATORY(1001),
    /**
     * Constant to indicate optional presence of something
     */
    OPTIONAL(1002),
    /**
     * Constant to indicate disallowed presence of something
     */
    DISALLOWED(1003);

    private final int value;

    ValidityKind(int value) {
        this.value = value;
    }

    /**
     * Get the value of this constant
     * @return the value
     */
    public int getValue() {
        return value;
    }
    
    /**
     * Get an enum constant by its name
     * This method is used by {@link br.com.zilics.archetypes.models.rm.utils.xml.XmlParser}
     * @param val the name of the constant
     * @return the enum constant
     */
    public static ValidityKind byValue(String val) {
    	int value = Integer.parseInt(val);
    	for(ValidityKind validityKind : ValidityKind.values()) {
    		if (validityKind.getValue() == value) return validityKind;
    	}
    	return null;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
    	return Integer.toString(this.value);
    }    

}
