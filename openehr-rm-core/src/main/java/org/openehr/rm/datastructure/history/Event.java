/*
 * component:   "openEHR Reference Implementation"
 * description: "Class Event"
 * keywords:    "datastructure"
 *
 * author:      "Rong Chen <rong@acode.se>"
 * support:     "Acode HB <support@acode.se>"
 * copyright:   "Copyright (c) 2004 Acode HB, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/TRUNK/libraries/src/java/org/openehr/rm/datastructure/history/Event.java $"
 * revision:    "$LastChangedRevision: 2 $"
 * last_change: "$LastChangedDate: 2005-10-12 22:20:08 +0100 (Wed, 12 Oct 2005) $"
 */
package org.openehr.rm.datastructure.history;

import java.util.Set;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.openehr.rm.Attribute;
import org.openehr.rm.FullConstructor;
import org.openehr.rm.common.archetyped.Archetyped;
import org.openehr.rm.common.archetyped.FeederAudit;
import org.openehr.rm.common.archetyped.Link;
import org.openehr.rm.common.archetyped.Locatable;
import org.openehr.rm.common.archetyped.Pathable;
import org.openehr.rm.datastructure.itemstructure.ItemStructure;
import org.openehr.rm.datatypes.quantity.datetime.DvDateTime;
import org.openehr.rm.datatypes.quantity.datetime.DvDuration;

import org.openehr.rm.datatypes.text.DvText;
import org.openehr.rm.support.identification.UIDBasedID;

/**
 * Defines the abstract notion of a single event in a series. This class is
 * generic, allowing types to be generated which are locked to a particular 
 * spatial types, such as EVENT<ItemList> Subtypes express point or interval
 * data.
 *
 * @author Rong Chen
 * @version 1.0
 */
public abstract class Event <T extends ItemStructure> extends Locatable {

    /**
     * Constructs an Event
     *
     * @param uid
     * @param archetypeNodeId
     * @param name
     * @param archetypeDetails
     * @param feederAudit
     * @param links
     * @param parent	null if unspecified
     * @param time		not null
     * @param data		not null
     * @param state
     * @throws IllegalArgumentException if time, data or parent null
     */
    @FullConstructor public Event(
            		   @Attribute(name = "uid") UIDBasedID uid,
                    @Attribute(name = "archetypeNodeId", required = true) String archetypeNodeId,
                    @Attribute(name = "name", required = true) DvText name,
                    @Attribute(name = "archetypeDetails") Archetyped archetypeDetails,
                    @Attribute(name = "feederAudit") FeederAudit feederAudit,
                    @Attribute(name = "links") Set<Link> links,
                    @Attribute(name = "parent") History<T> parent,
                    @Attribute(name = "time", required = true) DvDateTime time,
                    @Attribute(name = "data", required = true) T data,
                    @Attribute(name = "state") ItemStructure state) {
        super(uid, archetypeNodeId, name, archetypeDetails, feederAudit, links, parent);
        if (time == null) {
        	throw new IllegalArgumentException("null time");
        }
        if (data == null) {
        	throw new IllegalArgumentException("null data");
        }
        //this.parent = parent;
        this.time = time;
        this.data = data;
        this.state = state;
    }

    /**
     * The data of this event
     * 
     * @return data
     */
    public T getData() {
		return data;
	}
    
    /** 
     * Optional state data for this event
     * 
     * @return state
     */
    public ItemStructure getState() {
            return state;
    }

    /**
     * Time of this event. If the width is non-zero, 
     * it is the time point of the trailing edge of 
     * the event.
     * 
     * @return time
     */
    public DvDateTime getTime() {
            return time;
    }

    /**
     * Redefinition of LOCATABLE.parent to be of type 
     * History
     * 
     * @return parent null if not known
     */
    public History<T> getParent() {
    	return (History<T>) super.getParent();
    }

    /**
     * Offset of this event from origin
     * 
     * @return offset = time - parent.origin
     */
    public DvDuration offset() {
        return DvDuration.getDifference(getParent().getOrigin(), time);
    }
    
    /**
     * To assign parent object. This function can only be called 
     * once for the lifetime of an Event. Once set, the parent
     * cannot be modified.
     * This method does not include this object into the events 
     * list of the parent. To make sure the bi-directional relationships 
     * between History and Event work properly, try create a list of Event
     * with null parent, then when the list of Event is assigned to History,
     * this method will be called to complete the links.
     * 
     * @param history
     * @throws IllegalArgumentException if the given history object does not contain
     * 		a copy of this Event object, or if this.parent is not null
     */
    void assignParent(History<T> parent) {
        if (getParent() == null) {
               super.setParent(parent); 
 
        } else {
            //TODO: throw or not throw?
            throw new IllegalArgumentException("parent object existing");
        }
    }

    /**
     * String The path to an item relative to the root of this
     * archetyped structure.
     *
     * @param item
     * @return string path
     */
    public String pathOfItem(Pathable item) {
        return null;  // todo: implement this method
    }
    
    /**
     * Two events are equal if both have the same values
     *
     * @param o
     * @return true if equals
     */
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!( o instanceof Event )) return false;
        
        final Event event = (Event) o;

        return new EqualsBuilder()
            .append(time, event.time)
            .append(data, event.data)
            .append(state, event.state)
            .isEquals();
    }

    /**
     * Return a hash code of this event
     *
     * @return hash code
     */
    public int hashCode() {
        return new HashCodeBuilder(13, 41)
                .append(time)
                .append(data)
                .append(state)
                .toHashCode();
    }   

    // POJO start
    Event() {
    }

	void setData(T data) {
		this.data = data;
	}

	void setState(ItemStructure state) {
		this.state = state;
	}

	void setTime(DvDateTime time) {
		this.time = time;
	}    

	// POJO end

    /* fields */
    private DvDateTime time;
    private T data;
    private ItemStructure state;
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
 *  The Original Code is Event.java
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