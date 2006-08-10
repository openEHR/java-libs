/*
 * component:   "openEHR Reference Implementation"
 * description: "Class Instruction"
 * keywords:    "composition"
 *
 * author:      "Rong Chen <rong@acode.se>"
 * support:     "Acode HB <support@acode.se>"
 * copyright:   "Copyright (c) 2004 Acode HB, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/TRUNK/libraries/src/java/org/openehr/rm/composition/content/entry/Instruction.java $"
 * revision:    "$LastChangedRevision: 2 $"
 * last_change: "$LastChangedDate: 2005-10-12 22:20:08 +0100 (Wed, 12 Oct 2005) $"
 */
package org.openehr.rm.composition.content.entry;

import org.openehr.rm.Attribute;
import org.openehr.rm.FullConstructor;
import org.openehr.rm.common.archetyped.*;
import org.openehr.rm.common.generic.Participation;
import org.openehr.rm.common.generic.PartyProxy;
import org.openehr.rm.common.generic.PartyRelated;
import org.openehr.rm.support.identification.ObjectID;
import org.openehr.rm.support.identification.ObjectReference;
import org.openehr.rm.support.terminology.TerminologyService;
import org.openehr.rm.datastructure.itemstructure.ItemStructure;
import org.openehr.rm.datatypes.basic.DvState;
import org.openehr.rm.datatypes.encapsulated.DvParsable;
import org.openehr.rm.datatypes.quantity.datetime.DvDateTime;
import org.openehr.rm.datatypes.text.CodePhrase;
import org.openehr.rm.datatypes.text.DvText;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * The root of an action specification.  Instances of this class are immutable.
 *
 * @author Rong Chen
 * @version 1.0
 */
public final class Instruction extends CareEntry {

    /**
     * Constructs an Instruction
     *
     * @param uid
     * @param archetypeNodeId
     * @param name
     * @param archetypeDetails
     * @param feederAudit
     * @param links
     * @param subject
     * @param provider
     * @param protocol
     * @param actID
     * @param guidelineID
     * @param otherParticipations
     * @param state
     * @param action
     * @param profile
     * @param data
     * @throws IllegalArgumentException if state or action null
     */
    @FullConstructor
            public Instruction(@Attribute(name = "uid") ObjectID uid,
                    @Attribute(name = "archetypeNodeId", required = true) String archetypeNodeId,
                    @Attribute(name = "name", required = true) DvText name,
                    @Attribute(name = "archetypeDetails", required = true) Archetyped archetypeDetails,
                    @Attribute(name = "feederAudit") FeederAudit feederAudit,
                    @Attribute(name = "links") Set<Link> links,
                    @Attribute(name = "parent") Locatable parent, 
                    @Attribute(name = "language", required = true) CodePhrase language,
                    @Attribute(name = "charset", required = true) CodePhrase charset, 
                    @Attribute(name = "subject", system = true) PartyProxy subject,
                    @Attribute(name = "provider", system = true) PartyProxy provider,
                    @Attribute(name = "workflowID") ObjectReference workflowID,
                    @Attribute(name = "otherParticipation") List<Participation> otherParticipations,
                    @Attribute(name = "protocol") ItemStructure protocol,
                    @Attribute(name = "guidelineID") ObjectReference guidelineID,
                    @Attribute(name = "narrative", required = true) DvText narrative,
                    @Attribute(name = "activities") List<Activity> activities,
                    @Attribute(name = "expiryTime") DvDateTime expiryTime,
                    @Attribute(name = "wfDefinition") DvParsable wfDeinition,
                    @Attribute(name = "terminologyService", system = true) TerminologyService terminologyService) {
    		super(uid, archetypeNodeId, name, archetypeDetails, feederAudit, links,
                parent, language, charset, subject, provider, workflowID, 
                otherParticipations, protocol, guidelineID, terminologyService);
        if (narrative == null) {
        		throw new IllegalArgumentException("null narrative");
        }
        if (activities != null && activities.size() == 0) {
        		throw new IllegalArgumentException("empty activities");
        }
        this.narrative = narrative;
        this.activities = activities;
        this.expiryTime = expiryTime;
        this.wfDefinition = wfDefinition;
    }

    /**
     * List of all activities in Instruction
     * 
     * @return activities
     */
    public List<Activity> getActivities() {
		return activities;
	}

    /**
     * Optional expiry date/time to assist determination of when an Instruction
     * can be assumed to hav expired. This helps prevent false listing of 
     * Instruction as Active when they clearly must have been terminated.
     * 
     * @return expiryTime
     */
	public DvDateTime getExpiryTime() {
		return expiryTime;
	}
	
	/**
	 * Mandatory human-readable version of what the Instruction is about.
	 * 
	 * @return narrative
	 */
	public DvText getNarrative() {
		return narrative;
	}

	/**
	 * Optional workflow engine executable expression of the Instruction.
	 * 
	 * @return wfDefinition
	 */
	public DvParsable getWfDefinition() {
		return wfDefinition;
	}

	/**
     * Next actions in chain, derived from links attribute - any Link
     * instance with name = "next actions".
     *
     * @return list of instructions, empty list if no next action
     */
    public List<Instruction> nextActions() {
        List<Instruction> list = new ArrayList<Instruction>();
        if (getLinks() == null) {
            return list;
        }
        for (Link link : getLinks()) {
            if ("next actions".equals(link.getMeaning().getValue())) {
                // todo: how to find Instruction from link ?
                //list.add(link)
            }
        }
        return list;
    }

    /**
     * Overall status, derived from the state values of all linked
     * Instructions in the chain.
     *
     * @return status
     */
    public DvState status() {
        // todo: fix it
        return null;
    }

    /**
     * String The path to an item relative to the root of this
     * archetyped structure.
     *
     * @param item
     * @return string path
     */
    public String pathOfItem(Locatable item) {
        return null;  // todo: implement this method
    }

    /**
     * The item at a path that is relative to this item.
     *
     * @param path
     * @return the item or null if not found
     */
    public Object itemAtPath(String path) {
               
        Object item = super.itemAtPath(path);
        if (item != null) {
            return item;
        }
        String tmp = path;
        /*String attr = ROOT + "activities";
        if(tmp.startsWith(attr)) {
            tmp = tmp.substring(attr.length());
            for(Activity activity : activities) {
                //String node = activity.whole().substring(1);
                String node = activity.nodeName();
                if(tmp.startsWith(node)) {
                    if(tmp.equals(node)) {
                        return activity;
                    }
                    String subpath = tmp.substring(node.length());
                    if(activity.validPath(subpath))  {
                        return activity.itemAtPath(subpath);
                    }
                }
            }
        }*/
        Object ret = checkAttribute(tmp, "activities", activities);
        if(ret!=null) {
            return ret;
        } else {
        throw new IllegalArgumentException("invalid path: " + path);
        }
    }
    
    // POJO start
    Instruction() {
    }

	void setActivities(List<Activity> activities) {
		this.activities = activities;
	}

	void setExpiryTime(DvDateTime expiryTime) {
		this.expiryTime = expiryTime;
	}

	void setNarrative(DvText narrative) {
		this.narrative = narrative;
	}

	void setWfDefinition(DvParsable wfDefinition) {
		this.wfDefinition = wfDefinition;
	}      
    // POJO end

    /* fields */
    private DvText narrative;
    private List<Activity> activities;
    private DvDateTime expiryTime;
    private DvParsable wfDefinition;

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
 *  The Original Code is Instruction.java
 *
 *  The Initial Developer of the Original Code is Rong Chen.
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