/*
 * component:   "openEHR Reference Implementation"
 * description: "Class History"
 * keywords:    "datastructure"
 *
 * author:      "Rong Chen <rong@acode.se>"
 * support:     "Acode HB <support@acode.se>"
 * copyright:   "Copyright (c) 2004 Acode HB, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/TRUNK/libraries/src/java/org/openehr/rm/datastructure/history/History.java $"
 * revision:    "$LastChangedRevision: 2 $"
 * last_change: "$LastChangedDate: 2005-10-12 22:20:08 +0100 (Wed, 12 Oct 2005) $"
 */
package org.openehr.rm.datastructure.history;

import java.util.List;
import java.util.Set;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.openehr.rm.Attribute;
import org.openehr.rm.FullConstructor;
import org.openehr.rm.common.archetyped.Archetyped;
import org.openehr.rm.common.archetyped.FeederAudit;
import org.openehr.rm.common.archetyped.Link;
import org.openehr.rm.common.archetyped.Pathable;
import org.openehr.rm.datastructure.DataStructure;
import org.openehr.rm.datastructure.itemstructure.ItemStructure;
import org.openehr.rm.datastructure.itemstructure.representation.Item;
import org.openehr.rm.datatypes.quantity.datetime.DvDateTime;
import org.openehr.rm.datatypes.quantity.datetime.DvDuration;
import org.openehr.rm.datatypes.text.DvText;
import org.openehr.rm.support.identification.UIDBasedID;

/**
 * Root object of a linear history, i.e. time series structure. For a periodic 
 * series of events, period will be set, and the time of each Event in the 
 * History must correspond; i.e. the EVENT.offset must be a multiple of period 
 * for each Event. Missing events in a period History are however allowed.
 *
 * @author Rong Chen
 * @version 1.0
 */
public class History <T extends ItemStructure> extends DataStructure {

    /**
     * Construct a DataStructure
     *
     * @param uid
     * @param archetypeNodeId
     * @param name
     * @param archetypeDetails
     * @param feederAudit
     * @param links
     * @throws IllegalArgumentException if origin null
     */
    @FullConstructor
    public History(@Attribute(name = "uid") UIDBasedID uid,
                  @Attribute(name = "archetypeNodeId", required=true) String archetypeNodeId,
                  @Attribute(name = "name", required=true) DvText name,
                  @Attribute(name = "archetypeDetails") Archetyped archetypeDetails,
                  @Attribute(name = "feederAudit") FeederAudit feederAudit,
                  @Attribute(name = "links") Set<Link> links,
                  @Attribute(name = "parent") Pathable parent, 
                  @Attribute(name = "origin", required=true) DvDateTime origin,
                  @Attribute(name = "events") List<Event<T>> events,
                  @Attribute(name = "period") DvDuration period,
                  @Attribute(name = "duration") DvDuration duration,
                  @Attribute(name = "summary") ItemStructure summary){
        super(uid, archetypeNodeId, name, archetypeDetails, feederAudit,
                links, parent);
        if (origin == null) {
            throw new IllegalArgumentException("null origin");
        }
        if (events != null && events.size() == 0) {
        		throw new IllegalArgumentException("empty events");
        }
        if (events == null && summary == null) {
        		throw new IllegalArgumentException("null events and summary");
        }
        this.origin = origin;
        setEvents(events);
        this.period = period;
        this.duration = duration;
        this.summary = summary;
    }
    
    /**
     * Convenient constructor 
     * 
     * @param archetypeNodeId
     * @param name
     * @param origin
     * @param events
     */
    public History(String archetypeNodeId, DvText name, DvDateTime origin,
    		List<Event<T>> events) {
    	this(null, archetypeNodeId, name, null, null, null, null, origin,
    			events, null, null, null);
    }
    
    /**
     * Convenient constructor 
     * 
     * @param archetypeNodeId
     * @param name
     * @param origin
     * @param events
     */
    public History(String archetypeNodeId, String name, DvDateTime origin,
    		List<Event<T>> events) {
    	this(archetypeNodeId, new DvText(name), origin, events);
    }

    /**
     * Time origin of this event history. The first event is not
     * necessarily at the origin point.
     *
     * @return origin of this event history
     */
    public DvDateTime getOrigin() {
        return origin;
    }

    /** 
     * The events in the series
     * 
     * @return events
     */
    public List<Event<T>> getEvents() {
		return events;
	}

    /**
     * Period between samples in this segment if periodic
     * 
     * @return period
     */
    public DvDuration getPeriod() {
            return period;
    }

	/**
	 * Duration of the entire History; either corresponds
	 * to the duration of all the events, and/or the 
	 * duration represented by the summary, if exists
	 * 
	 * @return duration of History
	 */
	public DvDuration getDuration() {
		return duration;
	}

	/**
	 * Optional summary data expressing text/image which
	 * summarises entire History
	 * 
	 * @return summary of entire History
	 */
	public ItemStructure getSummary() {
		return summary;
	}

	/**
	 * Indicates whether history is periodic
	 * 
	 * @return true if period not null
	 */
	public boolean isPeriodic() {
		return period != null;
	}
        
    @Override
    public String pathOfItem(Pathable item) {
        throw new org.apache.commons.lang.NotImplementedException(); //TODO: implement
    }
	
	/**
     * Two History objects equal if both has same values
     *
     * @param o
     * @return true if equals
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!( o instanceof History )) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        
        final History history = (History) o;

        return new EqualsBuilder()
            .append(origin, history.origin)
            .append(events, history.events)
            .append(period, history.period)
            .append(duration, history.duration)
            .append(summary, history.summary)
            .isEquals();
    }

    /**
     * Return a hash code of this History
     *
     * @return hash code
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder(43, 53)
                .appendSuper(super.hashCode())
                .append(origin)
                .append(events)
                .append(period)
                .append(duration)
                .append(summary)
                .toHashCode();
    }   

    /* token used in query path */
    public static final String ORIGIN_IS = "origin=";

    /* fields */
    private DvDateTime origin;
    private List <Event<T>> events;
    private DvDuration period;
    private DvDuration duration;
    private ItemStructure summary;
    
    // POJO start
    protected History() {
    }

    void setEvents(List<Event<T>> events) {
        if (events!= null) {          
            for(Event<T> event : events) {
                event.assignParent(this);
            }
            this.events = events;            
        } 		
    }

    void setPeriod(DvDuration period) {
            this.period = period;
    }

    void setOrigin(DvDateTime origin) {
        this.origin = origin;
    }

	void setDuration(DvDuration duration) {
		this.duration = duration;
	}

	void setSummary(ItemStructure summary) {
		this.summary = summary;
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

	@Override
	public Item asHierarchy() {
		// TODO Auto-generated method stub
		return null;
	}
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
 *  The Original Code is History.java
 *
 *  The Initial Developer of the Original Code is Rong Chen.
 *  Portions created by the Initial Developer are Copyright (C) 2003-2008
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