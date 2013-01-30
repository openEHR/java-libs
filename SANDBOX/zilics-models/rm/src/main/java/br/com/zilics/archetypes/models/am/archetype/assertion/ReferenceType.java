package br.com.zilics.archetypes.models.am.archetype.assertion;

/**
 * Kind of Reference
 * @author Humberto
 */
public enum ReferenceType {
    /**
     * Unbound reference
     */
    UNBOUND,
    /**
     * Constant reference
     */
    CONSTANT,
    /**
     * Attribute reference
     */
    ATTRIBUTE,
    /**
     * Function reference
     */
    FUNCTION,
    /**
     * Constraint reference
     */
    CONSTRAINT;
    
    /**
     * Get an enum constant by its name
     * This method is used by {@link br.com.zilics.archetypes.models.rm.utils.xml.XmlParser}
     * @param val the name of the constant
     * @return the enum constant
     */
    public static ReferenceType byValue(String val) {
    	return ReferenceType.valueOf(val);
    }
    
};

