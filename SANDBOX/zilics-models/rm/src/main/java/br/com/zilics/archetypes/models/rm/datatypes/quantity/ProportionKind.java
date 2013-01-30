
package br.com.zilics.archetypes.models.rm.datatypes.quantity;

/**
 * Class of enumaration constants defining types of proportion for the {@link br.com.zilics.archetypes.models.rm.datatypes.quantity.DvProportion} class.
 * @author Humberto
 */
public enum ProportionKind {
    RATIO(0),
    UNITARY(1),
    PERCENT(2),
    FRACTION(3),
    INTEGER_FRACTION(4);
    
    private final int value;
    ProportionKind(int value) {
    	this.value = value;
    }
    
    public int getValue() {
    	return value;
    }
    
    public static ProportionKind byValue(String val) {
    	int value = Integer.parseInt(val);
    	for(ProportionKind proportionKind : ProportionKind.values()) {
    		if (proportionKind.getValue() == value) return proportionKind;
    	}
    	return null;
    }
    
    @Override
    public String toString() {
    	return Integer.toString(this.value);
    }    
}
