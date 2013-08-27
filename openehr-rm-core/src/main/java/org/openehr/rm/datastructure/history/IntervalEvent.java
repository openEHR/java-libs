/*
 * component:   "openEHR Reference Implementation"
 * description: "Class IntervalEvent"
 * keywords:    "datastructure"
 *
 * author:      "Yin Su Lim <y.lim@chime.ucl.ac.uk>"
 * support:     "CHIME, UCL"
 * copyright:   "Copyright (c) 2006 UCL, UK"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/TRUNK/libraries/src/java/org/openehr/rm/datastructure/history/IntervalEvent.java $"
 * revision:    "$LastChangedRevision: 53 $"
 * last_change: "$LastChangedDate: 2006-08-11 13:20:08 +0100 (Fri, 11 Aug 2006) $"
 */
package org.openehr.rm.datastructure.history;

import java.util.List;
import java.util.Set;

import org.openehr.rm.Attribute;
import org.openehr.rm.FullConstructor;
import org.openehr.rm.common.archetyped.*;
import org.openehr.rm.datastructure.itemstructure.ItemStructure;
import org.openehr.rm.datatypes.quantity.datetime.DvDateTime;
import org.openehr.rm.datatypes.quantity.datetime.DvDuration;
import org.openehr.rm.datatypes.text.DvCodedText;
import org.openehr.rm.datatypes.text.DvText;
import org.openehr.rm.support.identification.UIDBasedID;
import org.openehr.rm.support.terminology.TerminologyService;

/**
 * Defines a single interval event in a series
 *
 * @author Yin Su Lim
 * @version 1.0
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
			@Attribute(name = "uid") UIDBasedID uid,
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
	
	@Override
	public List<Object> itemsAtPath(String path) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public boolean pathExists(String path) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean pathUnique(String path) {
		// TODO Auto-generated method stub
		return false;
	}
	
	/* fields */
	private DvDuration width;
	private DvCodedText mathFunction;
	private int sampleCount;	
}

/*
 *  ***** BEGIN LICENSE BLOCK *****
 *  Version: MPL 1.1/GPL 2.0/LGPL 2.1
 *
 *  The contents of this file are subject to the Mozilla Public License Version
 *  1.1 (the 'License'); you may not use this file except in compliance with
 *  the License. You may obtain a copy of the License at
 *  http://www.mozilla.org/MPL/
 *
 *  Software distributed under the License is distributed on an 'AS IS' basis,
 *  WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
 *  for the specific language governing rights and limitations under the
 *  License.
 *
 *  The Original Code is IntervalEvent.java
 *
 *  The Initial Developer of the Original Code is Yin Su Lim.
 *  Portions created by the Initial Developer are Copyright (C) 2003-2004
 *  the Initial Developer. All Rights Reserved.
 *
 *  Contributor(s):
 *
 * Software distributed under the License is distributed on an 'AS IS' basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
 * for the specific language governing rights and limitations under the
 * License.
 *
 *  ***** END LICENSE BLOCK *****
 */