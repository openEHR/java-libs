
package br.com.zilics.archetypes.models.rm.datatypes.quantity;

import java.util.List;

import br.com.zilics.archetypes.models.rm.annotation.EqualsField;
import br.com.zilics.archetypes.models.rm.datatypes.basic.DataValue;
import br.com.zilics.archetypes.models.rm.datatypes.text.CodePhrase;

/**
 * Abstract class defining the concept of ordered values, which includes
 * ordinals as weel true quantities. It defines the functions '<' and isStricltComparableTo,
 * the latter of which must evaluate to True of instances being compared whith
 * the '<' function, or used as limits in the {@link br.com.zilics.archetypes.models.rm.datatypes.quantity.DvInterval} class.
 *
 * @author Humberto
 */
public abstract class DvOrdered<T extends DvOrdered<T>> extends DataValue implements Comparable<T> {
	private static final long serialVersionUID = -6724684886577184585L;
	@EqualsField
	private CodePhrase normalStatus;
	@EqualsField
    private DvInterval<T> normalRange;
	@EqualsField
    private List<ReferenceRange<T>> otherReferenceRanges;

    /**
     * Optional normal status indicator of value with respect to normal range for this
     * value. Often included by lab, even if the 0..1 normal range itself is not included.
     * Coded by ordinals in series HHH, HH, H, (nothing), L, LL, LLL; see openEHR terminology group abnormal statuses.
     * @return normalStatus
     */
    public CodePhrase getNormalStatus() {
        return normalStatus;
    }

    /**
     * Optional normal status indicator of value with respect to normal range for this
     * value. Often included by lab, even if the normal range itself is not included.
     * Coded by ordinals in series HHH, HH, H, (nothing), L, LL, LLL; see openEHR terminology group abnormal statuses.
     * @param normalStatus Normal Status
     */
    public void setNormalStatus(CodePhrase normalStatus) {
		assertMutable();
        this.normalStatus = normalStatus;
    }


    /**
     * Optional Normal Range
     * @return Optional Normal Range
     */
    public DvInterval<T> getNormalRange() {
        return normalRange;
    }

    /**
     * Optional Normal Range
     * @param normalRange Optional Normal Range
     */
    public void setNormalRange(DvInterval<T> normalRange) {
		assertMutable();
        this.normalRange = normalRange;
    }

    /**
     * Optional tagged other reference ranges for this value in its particular measure-
     * ment context
     * @return otherReferenceRanges
     */
    public List<ReferenceRange<T>> getOtherReferenceRanges() {
        return getList(otherReferenceRanges);
    }

    /**
     * Optional tagged other reference ranges for this value in its particular measure-
     * ment context
     * @param otherReferenceRanges Other reference ranges
     */
    public void setOtherReferenceRanges(List<ReferenceRange<T>> otherReferenceRanges) {
		assertMutable();
        this.otherReferenceRanges = otherReferenceRanges;
    }
    
    /**
     * Value is in the normal range if there is one, otherwise True
     *
     * @return true if normal
     */
    @SuppressWarnings("unchecked")
	public boolean isNormal() throws IllegalStateException {
    	if(normalRange != null) {
    		return normalRange.has((T) this);
    	} else {
    		return normalStatus.getCodeString().equals("N");
    	}
    }

    /**
     * True if this quantity has no reference ranges
     *
     * @return true if has no reference range
     */
    public boolean isSimple() {
        return (normalRange == null) && (otherReferenceRanges == null);
    }

    /**
     * Tests if two instances are strictly comparable.
     *
     * @param ordered
     * @return true if two instances are strictly comparable
     */
    public abstract boolean isStrictlyComparableTo(T ordered);
    
}
