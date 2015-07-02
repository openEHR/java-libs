/*
 * component:   "openEHR Reference Implementation"
 * description: "Class Action"
 * keywords:    "composition"
 *
 * author:      "Yin Su Lim <y.lim@chime.ucl.ac.uk>"
 * support:     "CHIME, UCL"
 * copyright:   "Copyright (c) 2006 UCL, UK"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/TRUNK/libraries/src/java/org/openehr/rm/composition/content/entry/Action.java $"
 * revision:    "$LastChangedRevision: 53 $"
 * last_change: "$LastChangedDate: 2006-08-11 13:20:08 +0100 (Fri, 11 Aug 2006) $"
 */
package org.openehr.rm.composition.content.entry;

import java.util.List;
import java.util.Set;

import org.openehr.rm.Attribute;
import org.openehr.rm.FullConstructor;
import org.openehr.rm.common.archetyped.Archetyped;
import org.openehr.rm.common.archetyped.FeederAudit;
import org.openehr.rm.common.archetyped.Link;
import org.openehr.rm.common.archetyped.Pathable;
import org.openehr.rm.common.generic.Participation;
import org.openehr.rm.common.generic.PartyProxy;
import org.openehr.rm.datastructure.itemstructure.ItemStructure;
import org.openehr.rm.datatypes.quantity.datetime.DvDateTime;
import org.openehr.rm.datatypes.text.CodePhrase;
import org.openehr.rm.datatypes.text.DvText;
import org.openehr.rm.support.identification.UIDBasedID;
import org.openehr.rm.support.identification.ObjectRef;
import org.openehr.rm.support.terminology.TerminologyService;
import org.openehr.rm.common.archetyped.Locatable;

/**
 * Used to record a clinical action that has been performed, which may have been ad hoc, 
 * or due to the execution of an Acitivity in an Instruction workflow. Every Action
 * corresponds to a careflow step of some kind or another.
 *
 * @author Yin Su Lim
 * @version 1.0
 */
public final class Action extends CareEntry {
    
    /**
     * @param uid
     * @param archetypeNodeId
     * @param name
     * @param archetypeDetails
     * @param feederAudit
     * @param links
     * @param parent
     * @param language
     * @param encoding
     * @param subject
     * @param provider
     * @param workflowId
     * @param otherParticipations
     * @param protocol
     * @param guidelineId
     * @param terminologyService
     */
    @FullConstructor
            public Action(@Attribute(name = "uid") UIDBasedID uid,
            @Attribute(name = "archetypeNodeId", required = true) String archetypeNodeId,
            @Attribute(name = "name", required = true) DvText name,
            @Attribute(name = "archetypeDetails", required = true) Archetyped archetypeDetails,
            @Attribute(name = "feederAudit") FeederAudit feederAudit,
            @Attribute(name = "links") Set<Link> links,
            @Attribute(name = "parent") Pathable parent,
            @Attribute(name = "language", required = true) CodePhrase language,
            @Attribute(name = "encoding", required = true) CodePhrase encoding,
            @Attribute(name = "subject", required = true) PartyProxy subject,
            @Attribute(name = "provider") PartyProxy provider,
            @Attribute(name = "workflowId") ObjectRef workflowId,
            @Attribute(name = "otherParticipations") List<Participation> otherParticipations,
            @Attribute(name = "protocol") ItemStructure protocol,
            @Attribute(name = "guidelineId") ObjectRef guidelineId,
            @Attribute(name = "time", required = true) DvDateTime time,
            @Attribute(name = "description", required = true) ItemStructure description,
            @Attribute(name = "ismTransition", required = true) ISMTransition ismTransition,
            @Attribute(name = "instructionDetails") InstructionDetails instructionDetails,
            @Attribute(name = "terminologyService", system = true) TerminologyService terminologyService
            ){
        super(uid, archetypeNodeId, name, archetypeDetails, feederAudit, links, parent,
                language, encoding, subject, provider, workflowId, otherParticipations,
                protocol, guidelineId, terminologyService);
        if (time == null) {
            throw new IllegalArgumentException("null time");
        }
        if (description == null) {
            throw new IllegalArgumentException("null description");
        }
        if (ismTransition == null) {
            throw new IllegalArgumentException("null ismTransition");
        }
        this.time = time;
        this.description = description;
        this.ismTransition = ismTransition;
        this.instructionDetails = instructionDetails;
    }
    
    /**
     * Description of the activity to be performed, in the form of an
     * archetyped structure.
     *
     * @return description
     */
    public ItemStructure getDescription() {
        return description;
    }
    
    /**
     * Details of the Instruction that caused this ACtion to be performed,
     * if there was one.
     *
     * @return instructionDetails
     */
    public InstructionDetails getInstructionDetails() {
        return instructionDetails;
    }
    
    /**
     * Detials of transition in the Instruction state machine caused by
     * this Action.
     *
     * @return ismTransition
     */
    public ISMTransition getIsmTransition() {
        return ismTransition;
    }
    
    /**
     * Point in time at which this action completed.
     *
     * @return time
     */
    public DvDateTime getTime() {
        return time;
    }
    
    @Override
	public String pathOfItem(Pathable arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Object> itemsAtPath(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean pathExists(String arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean pathUnique(String arg0) {
		// TODO Auto-generated method stub
		return false;
	}
    
    //POJO start
    Action() {
    }
    
    void setDescription(ItemStructure description) {
        this.description = description;
    }
    
    void setInstructionDetails(InstructionDetails instructionDetails) {
        this.instructionDetails = instructionDetails;
    }
    
    void setIsmTransition(ISMTransition ismTransition) {
        this.ismTransition = ismTransition;
    }
    
    void setTime(DvDateTime time) {
        this.time = time;
    }
    //POJO end
    
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime
				* result
				+ ((instructionDetails == null) ? 0 : instructionDetails
						.hashCode());
		result = prime * result
				+ ((ismTransition == null) ? 0 : ismTransition.hashCode());
		result = prime * result + ((time == null) ? 0 : time.hashCode());
		return result;
	}

	@Override
	// Generated by Eclipse
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Action other = (Action) obj;
		if (description == null) {
			if (other.description != null) {
				return false;
			}
		} else if (!description.equals(other.description)) {
			return false;
		}
		if (instructionDetails == null) {
			if (other.instructionDetails != null) {
				return false;
			}
		} else if (!instructionDetails.equals(other.instructionDetails)) {
			return false;
		}
		if (ismTransition == null) {
			if (other.ismTransition != null) {
				return false;
			}
		} else if (!ismTransition.equals(other.ismTransition)) {
			return false;
		}
		if (time == null) {
			if (other.time != null) {
				return false;
			}
		} else if (!time.equals(other.time)) {
			return false;
		}
		if (getProtocol() == null) {
			if (other.getProtocol() != null) {
				return false;
			}
		} else if (other.getProtocol() == null) {
			return false;
		} else if (!getProtocol().equals(other.getProtocol())) {
			return false;
		}
		return true;
	}

    /* fields */
    private DvDateTime time;
    private ItemStructure description;
    private ISMTransition ismTransition;
    private InstructionDetails instructionDetails;
    
    /* static fields*/
    public static final String DESCRIPTION = "description";
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
 *  The Original Code is Action.java
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