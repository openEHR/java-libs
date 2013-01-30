
package br.com.zilics.archetypes.models.rm.datastructure.history;

import br.com.zilics.archetypes.models.rm.datastructure.itemstructure.ItemStructure;
import br.com.zilics.archetypes.models.rm.datatypes.quantity.datetime.DvDuration;
import br.com.zilics.archetypes.models.rm.datatypes.text.DvCodedText;

/**
 * Defines a single interval event in a series
 *
 * @author Humberto
 * @param <T> IntervalEvent's type
 */
public class IntervalEvent<T extends ItemStructure> extends Event<T> {

	private static final long serialVersionUID = -5130046919336840853L;
	private DvDuration width;
    private Integer sampleCount;
    private DvCodedText mathFunction;

    /**
     * Get the width
     * @return Length of the interval during which the state was true.
     * Void if an instantaneous event.
     */
    public DvDuration getWidth() {
        return width;
    }

    /**
     * Set the width
     * @param width Length of the interval during which the state was true.
     * Void if an instantaneous event.
     */
    public void setWidth(DvDuration width) {
		assertMutable();
        this.width = width;
    }

    /**
     * Get the sampleCount
     * @return Optional count of original samples to which this event corresponds.
     */
    public Integer getSampleCount() {
        return sampleCount;
    }

    /**
     * Set the sampleCount
     * @param sampleCount Optional count of original samples to which this event corresponds.
     */
    public void setSampleCount(Integer sampleCount) {
		assertMutable();
        this.sampleCount = sampleCount;
    }

    /**
     * Get the mathFunction
     * @return Mathematical function of the data of this event, e.g. "maximum",
     * "mean" etc. Coded using <I>open</I>EHR Terminology group "event
     * math function".
     */
    public DvCodedText getMathFunction() {
        return mathFunction;
    }

    /**
     * Set the mathFunction
     * @param mathFunction Mathematical function of the data of this event, e.g. "maximum",
     * "mean" etc. Coded using <I>open</I>EHR Terminology group "event
     * math function".
     */
    public void setMathFunction(DvCodedText mathFunction) {
		assertMutable();
        this.mathFunction = mathFunction;
    }
}
