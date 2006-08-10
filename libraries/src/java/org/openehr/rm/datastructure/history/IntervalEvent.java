/**
 * 
 */
package org.openehr.rm.datastructure.history;

import java.util.Set;

import org.openehr.rm.Attribute;
import org.openehr.rm.FullConstructor;
import org.openehr.rm.common.archetyped.*;
import org.openehr.rm.datastructure.itemstructure.ItemStructure;
import org.openehr.rm.datatypes.quantity.datetime.DvDateTime;
import org.openehr.rm.datatypes.quantity.datetime.DvDuration;
import org.openehr.rm.datatypes.text.DvCodedText;
import org.openehr.rm.datatypes.text.DvText;
import org.openehr.rm.support.identification.ObjectID;
import org.openehr.rm.support.terminology.TerminologyService;

/**
 * Defines a single interval event in a series
 *
 */
public final class IntervalEvent<T extends ItemStructure> extends Event<T> {

	/**
	 * Construct an IntervalEvent
	 * 
	 * @param uid
	 * @param archetypeNodeId
	 * @param name
	 * @param archetypeDetails
	 * @param feederAudit
	 * @param links
	 * @param time
	 * @param data
	 * @param state
	 */
    @FullConstructor
	public IntervalEvent(
			@Attribute(name = "uid") ObjectID uid,
            @Attribute(name = "archetypeNodeId", required = true) String archetypeNodeId,
            @Attribute(name = "name", required = true) DvText name,
            @Attribute(name = "archetypeDetails") Archetyped archetypeDetails,
            @Attribute(name = "feederAudit") FeederAudit feederAudit,
            @Attribute(name = "links") Set<Link> links,
            @Attribute(name = "parent") History<T> parent,
            @Attribute(name = "time", required = true) DvDateTime time,
            @Attribute(name = "data", required = true) T data,
            @Attribute(name = "state") ItemStructure state,
            @Attribute(name = "width", required = true) DvDuration width,
            @Attribute(name = "mathFunction", required = true) DvCodedText mathFunction,
            @Attribute(name = "sampleCount") int sampleCount,
            @Attribute(name = "terminologyService",  system=true) TerminologyService terminologyService) {
		super(uid, archetypeNodeId, name, archetypeDetails, feederAudit, links, parent, 
				time, data, state);
		if (width == null) {
			throw new IllegalArgumentException("null width");
		}
		if (mathFunction == null) {
			throw new IllegalArgumentException("null mathFunction");
		}
		if (terminologyService == null) {
            throw new IllegalArgumentException("null terminologyService");
        }
        //System.out.println("get Terminology.." + terminologyService.terminology(TerminologyService.OPENEHR));
        //System.out.println("get codes...." + terminologyService.terminology(TerminologyService.OPENEHR)
        //.codesForGroupName("event math function", "en"));        
        if (!terminologyService.terminology(TerminologyService.OPENEHR)
                .codesForGroupName("event math function", "en")
                .contains(mathFunction.getDefiningCode())) {
            throw new IllegalArgumentException(
                    "unknown mathFunction: " + mathFunction);
        }
        this.width = width;
        this.mathFunction = mathFunction;
        this.sampleCount = sampleCount;
	}

	/**
	 * Mathematical function of the data of this event, e.g. 
	 * "maximum", "mean" etc. Coded using openEHR Terminology group
	 * "event math function"
	 * 
	 * @return mathFunction.
	 */
	public DvCodedText getMathFunction() {
		return mathFunction;
	}

	/**
	 * Optional count of original samples to which this event corresponds
	 * 
	 * @return sampleCount.
	 */
	public int getSampleCount() {
		return sampleCount;
	}

	/**
	 * Length of the interval during which the state was true.
	 * Void if an instantaneous event.
	 * 
	 * @return width.
	 */
	public DvDuration getWidth() {
		return width;
	}

	/**
	 * Start time of the interval of this event
	 *
	 *@return start time
	 */
	public DvDateTime intervalStartTime() {
		return (DvDateTime) getTime().subtract(width);
		//TODO: check this is correct!
	}
	
	// POJO start
	IntervalEvent() {
	}
	
	void setMathFunction(DvCodedText mathFunction) {
		this.mathFunction = mathFunction;
	}

	void setSampleCount(int sampleCount) {
		this.sampleCount = sampleCount;
	}

	void setWidth(DvDuration width) {
		this.width = width;
	}
	// POJO end
	
	/* fields */
	private DvDuration width;
	private DvCodedText mathFunction;
	private int sampleCount;
	
}
