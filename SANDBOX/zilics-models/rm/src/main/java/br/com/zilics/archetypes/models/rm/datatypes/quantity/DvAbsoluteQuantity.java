
package br.com.zilics.archetypes.models.rm.datatypes.quantity;

import br.com.zilics.archetypes.models.rm.annotation.EqualsField;

/**
 * Abstract class defining the concept of quantified entities whose values are absolute
 * with respect to an origin. Dates and Times are the main example.
 * @author Humberto
 */
public abstract class DvAbsoluteQuantity<T extends DvAbsoluteQuantity<T, S>, S extends DvAmount<S>> extends DvQuantified<T> {
	private static final long serialVersionUID = -3208132424627440294L;
	@EqualsField
	private S accuracy;

    /**
     * Accuracy of measurement, expressed as a half-range value 
     * of the diff type for this quantity (i.e. an accuracy of
     * x means +/−x).  
     *
     * @return accuracy
     */
    public S getAccuracy() {
        return accuracy;
    }

    /**
     * Accuracy of measurement, expressed as a half-range value 
     * of the diff type for this quantity (i.e. an accuracy of
     * x means +/−x).  
     *
     * @param accuracy the accuracy
     */
    public void setAccuracy(S accuracy) {
		assertMutable();
        this.accuracy = accuracy;
    }
    
	/**
     * Sum of this quantity and another whose formal type must be the
     * difference type of this quantity.
     *
     * @param s
     * @return result of the addition
     */
    public abstract T add(S s);
    
    /**
     * Difference of this quantity and another whose formal type must
     * be the difference type of this quantity type.
     *
     * @param s
     * @return result of the subtraction
     */
    public abstract T subtract(S s);

    /**
     * Difference of two quantities.
     *
     * @return diff result
     */
    public abstract S diff(T other);
    
}
