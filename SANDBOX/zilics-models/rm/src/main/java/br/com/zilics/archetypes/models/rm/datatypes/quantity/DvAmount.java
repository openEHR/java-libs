
package br.com.zilics.archetypes.models.rm.datatypes.quantity;

import br.com.zilics.archetypes.models.rm.annotation.EqualsField;
import br.com.zilics.archetypes.models.rm.utils.validation.ValidationResult;


/**
 * Abstract class defining the concept of relative quantified 'amounts'.
 * For relative quantities, the '+' and '-' operators are defined (unlike
 * descendats of {@link br.com.zilics.archetypes.models.rm.datatypes.quantity.DvAbsoluteQuantity}, such as the date/time types).
 * @author Humberto
 */
public abstract class DvAmount<T extends DvAmount<T>> extends DvQuantified<T> {
	private static final long serialVersionUID = -5179483816368075444L;
	@EqualsField
	private double accuracy;
	@EqualsField
    private boolean accuracyIsPercent;

    /**
     * Accuracy of measurement instrument or method which applies
     * to this specific instance of Quantified, expressed either
     * as a half-range percent value (accuracy_is_percent = True)
     * or a half-range quantity.
     * <p/>
     * A value of 0 means that accuracy was not recorded.
     *
     * @return accuracy
     */
    public double getAccuracy() {
        return accuracy;
    }

    /**
     * Accuracy of measurement instrument or method which applies
     * to this specific instance of Quantified, expressed either
     * as a half-range percent value (accuracy_is_percent = True)
     * or a half-range quantity.
     * <p/>
     * A value of 0 means that accuracy was not recorded.
     *
     * @param accuracy the accuracy
     */
    public void setAccuracy(double accuracy) {
		assertMutable();
        this.accuracy = accuracy;
    }

    /**
     * If True, indicates that when this object was created,
     * accuracy was recorded as a percent value; if False, as an
     * absolute quantity value.
     *
     * @return true if accuracy percent value
     */    
    public boolean isAccuracyIsPercent() {
        return accuracyIsPercent;
    }

    /**
     * If True, indicates that when this object was created,
     * accuracy was recorded as a percent value; if False, as an
     * absolute quantity value.
     *
     * @param accuracyIsPercent if accuracy is a percent value
     */    
    public void setAccuracyIsPercent(boolean accuracyIsPercent) {
		assertMutable();
        this.accuracyIsPercent = accuracyIsPercent;
    }

    /**
     * Sum of this quantity and another whose formal type must be the
     * difference type of this quantity.
     *
     * @param q the operand
     * @return the sum between this and q
     */
    public abstract T add(T q);

    /**
     * Difference of this quantity and another whose formal type must
     * be the difference type of this quantity type.
     *
     * @param q the operand
     * @return the difference between this and q
     */
    public abstract T subtract(T q);
    
    /**
     * Negated version of current object, such as used for representing a difference, e.g. a
     * weight loss.
     * @return negated version of this
     */
    public abstract T negate();
    
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected void performValidation(ValidationResult result) {
    	super.performValidation(result);
    	if (accuracyIsPercent) {
    		if (accuracy < 0.0 || accuracy > 100.0)
    			result.addItem(this, "Invalid accuracy: " + accuracy + "%");
    	}
    }

}

