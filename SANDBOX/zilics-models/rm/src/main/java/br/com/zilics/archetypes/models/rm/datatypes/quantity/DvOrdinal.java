package br.com.zilics.archetypes.models.rm.datatypes.quantity;


import br.com.zilics.archetypes.models.rm.annotation.EqualsField;
import br.com.zilics.archetypes.models.rm.annotation.NotNull;
import br.com.zilics.archetypes.models.rm.datatypes.text.DvCodedText;
import br.com.zilics.archetypes.models.rm.utils.validation.ValidationResult;

/**
 * Models ranking and scores, e.g. pain, Apgar values, etc, where is a) implied
 * ordering, b) no implication that the distance between each value is constant,
 * and c) the total number of values is finite. Note that although the term
 * 'ordinal' in mathematics means natural numbers only, here any integer is
 * allowed, since negative and zero values are often used by medical
 * professionals for values around a neutral point. Examples of sets of ordinal
 * values:
 * 
 * <pre>
 * -3,-2,-1,0,1,2,3 -- reflex response values
 * 0,1,2 -- Apgar values
 * </pre>
 * 
 * @author Humberto
 */
public class DvOrdinal extends DvOrdered<DvOrdinal> {

	private static final long serialVersionUID = 8015794239597790750L;
	@NotNull
	@EqualsField
	private Integer value;
	@NotNull
	@EqualsField
	private DvCodedText symbol;
	private int limitsIndex;

	/**
	 * Default constructor
	 */
	public DvOrdinal() {}
	
	/**
	 * Another constructor
	 * @param value the integer value of this ordinal
	 * @param symbol the corresponding symbol
	 */
	public DvOrdinal(Integer value, DvCodedText symbol) {
		this.value = value;
		this.symbol = symbol;
	}
	
	/**
	 * Value in ordered enumeration of values. Any integer value can be used.
	 * 
	 * @return the value of this ordinal
	 */
	public Integer getValue() {
		return value;
	}

	/**
	 * Value in ordered enumeration of values. Any integer value can be used.
	 * 
	 * @param value
	 *            the value of this ordinal
	 */
	public void setValue(Integer value) {
		assertMutable();
		this.value = value;
	}

	/**
	 * Coded textual representation of this value in the enumeration, which may
	 * be strings made from "+" symbols, or other enumerations of terms such as
	 * "mild", "moderate", "severe", or even the same number series as the
	 * values, e.g. "1", "2", "3". Codes come from archetype.
	 * 
	 * @return textual representation
	 */
	public DvCodedText getSymbol() {
		return symbol;
	}

	/**
	 * Coded textual representation of this value in the enumeration, which may
	 * be strings made from "+" symbols, or other enumerations of terms such as
	 * "mild", "moderate", "severe", or even the same number series as the
	 * values, e.g. "1", "2", "3". Codes come from archetype.
	 * 
	 * @param symbol
	 *            textual representation
	 */
	public void setSymbol(DvCodedText symbol) {
		assertMutable();
		this.symbol = symbol;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return objectToString(symbol);
	}
	
    /**
     * Limits of the ordinal enumeration, to allow comparison of an
     * ordinal value to its limits.
     *
     * @return reference range
     */
    public ReferenceRange<DvOrdinal> limits() {
        return getOtherReferenceRanges().get(limitsIndex);
    }


    /**
     * {@inheritDoc}
     */
	@Override
	public boolean isStrictlyComparableTo(DvOrdinal ordered) {
		if (!symbol.getDefiningCode().getTerminologyId().equals(ordered.symbol.getDefiningCode().getTerminologyId())) {
			return false;
		}
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	public int compareTo(DvOrdinal o) {
		if (value > o.value)
			return 1;
		if (value < o.value)
			return -1;
		return 0;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
    protected void performValidation(ValidationResult result) {
    	super.performValidation(result);
        int index = -1;
        if (getOtherReferenceRanges() != null) {
            for (int i = 0, j = getOtherReferenceRanges().size(); i < j; i++) {
                ReferenceRange<DvOrdinal> range = getOtherReferenceRanges().get(i);
                if ("limits".equals(range.getMeaning().getValue())) {
                    index = i;
                    break;
                }
            }
            if (index < 0)
            	result.addItem(this, "No limits in otherReferenceRanges");
        }
        this.limitsIndex = index;
    }

}
