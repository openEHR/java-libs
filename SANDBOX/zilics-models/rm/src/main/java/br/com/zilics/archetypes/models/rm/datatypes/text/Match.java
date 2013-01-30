
package br.com.zilics.archetypes.models.rm.datatypes.text;

/**
 * Enumeration of match between terms
 *
 * @author Humberto
 */
public enum Match {
    NARROWER ("<"),
    EQUIVALENT ("="),
    BROADER (">"),
    UNKNOWN ("?");

    private Match(String value) {
        this.value = value;
    }

    /**
     * Get the value of the enum
     * @return the value
     */
    public String getValue() {
        return value;
    }

    private final String value;
    
    /**
     * Get an enum constant by its name
     * This method is used by {@link br.com.zilics.archetypes.models.rm.utils.xml.XmlParser}
     * @param val the name of the constant
     * @return the enum constant
     */
    public static Match byValue(String val) {
    	return Match.valueOf(val);
    }
}
