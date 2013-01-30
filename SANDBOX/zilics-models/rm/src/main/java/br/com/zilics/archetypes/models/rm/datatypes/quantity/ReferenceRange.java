
package br.com.zilics.archetypes.models.rm.datatypes.quantity;

import br.com.zilics.archetypes.models.rm.RMObject;
import br.com.zilics.archetypes.models.rm.annotation.EqualsField;
import br.com.zilics.archetypes.models.rm.annotation.NotNull;
import br.com.zilics.archetypes.models.rm.datatypes.text.DvText;
import br.com.zilics.archetypes.models.rm.utils.validation.ValidationResult;

/**
 * Defines a named range to be associated with any ORDERED datum.
 * Each such range is particular to the patient and context, e.g. sex, age,
 * and any other factor which affects ranges.
 * @author Humberto
 * @param <T> ReferenceRange's type
 */
public class ReferenceRange<T extends DvOrdered<T>> extends RMObject {

	private static final long serialVersionUID = 2668711788694273277L;
	@NotNull
	@EqualsField
	private DvText meaning;
	@NotNull
	@EqualsField
    private DvInterval<T> range;

    /**
     * Term whose value indicates the meaning of this range, e.g. "normal", "critical",
     * "therapeutic" etc.
     * @return the meaning of this range
     */
    public DvText getMeaning() {
        return meaning;
    }

    /**
     * Term whose value indicates the meaning of this range, e.g. "normal", "critical",
     * "therapeutic" etc.
     * @param meaning the meaning of this range
     */
    public void setMeaning(DvText meaning) {
		assertMutable();
        this.meaning = meaning;
    }

    /**
     * The data range
     * @return the data range
     */
    public DvInterval<T> getRange() {
        return range;
    }

    /**
     * The data range
     * @param range the data range
     */
    public void setRange(DvInterval<T> range) {
		assertMutable();
        this.range = range;
    }
    
    /**
     * Indicates if the value is inside the range
     *
     * @param value value to test
     * @return true if has the value
     */
    public boolean isInRange(T value) {
    	return range.has(value);
    }
    
    
    /**
     * {@inheritDoc}
     */
    @Override
	public String toString(){
        return  meaning + ", " + range;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected void performValidation(ValidationResult result) {
    	super.performValidation(result);
    	if (range == null) return;
    	if (!(( range.isLowerUnbounded() ||
                (range.getLower() != null && range.getLower().isSimple()) )
                &&
                ( range.isUpperUnbounded() ||
                (range.getUpper() != null && range.getUpper().isSimple() ))))
    		result.addItem(this, "Range is not simple");
    		
    }

}
