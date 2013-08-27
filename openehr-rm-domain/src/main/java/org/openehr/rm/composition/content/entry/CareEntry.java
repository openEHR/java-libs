/*
 * component:   "openEHR Reference Implementation"
 * description: "Class CareEntry"
 * keywords:    "composition"
 *
 * author:      "Yin Su Lim <y.lim@chime.ucl.ac.uk>"
 * support:     "CHIME, UCL"
 * copyright:   "Copyright (c) 2006 UCL, UK"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/TRUNK/libraries/src/java/org/openehr/rm/composition/content/entry/CareEntry.java $"
 * revision:    "$LastChangedRevision: 53 $"
 * last_change: "$LastChangedDate: 2006-08-11 13:20:08 +0100 (Fri, 11 Aug 2006) $"
 */
package org.openehr.rm.composition.content.entry;

import java.util.List;
import java.util.Set;

import org.openehr.rm.common.archetyped.Archetyped;
import org.openehr.rm.common.archetyped.FeederAudit;
import org.openehr.rm.common.archetyped.Link;
import org.openehr.rm.common.archetyped.Pathable;
import org.openehr.rm.common.generic.Participation;
import org.openehr.rm.common.generic.PartyProxy;
import org.openehr.rm.datastructure.itemstructure.ItemStructure;
import org.openehr.rm.datatypes.text.CodePhrase;
import org.openehr.rm.datatypes.text.DvText;
import org.openehr.rm.support.identification.UIDBasedID;
import org.openehr.rm.support.identification.ObjectRef;
import org.openehr.rm.support.terminology.TerminologyService;

/**
 * The abstract parent of all clinical Entry subtypes. A CareEntry defines 
 * protocol and guideline attributes for all clinical Entry subtypes.
 *
 * @author Yin Su Lim
 * @version 1.0
 */
public abstract class CareEntry extends Entry {

    /**
     * Construct an Entry
     *
     * @param archetypeNodeId
     * @param name
     * @param subject
     * @param provider
     * @param protocol            null if unspecified
     * @param actID               null if unspecified
     * @param guidelineId         null if unspecified
     * @param otherParticipations null if unspecified
     * @throws IllegalArgumentException if archetypeNodeId or name null,
     *                                  or subject or provider null or invalid
     */
    protected CareEntry(UIDBasedID uid, String archetypeNodeId, DvText name,
                 Archetyped archetypeDetails, FeederAudit feederAudit,
                 Set<Link> links, Pathable parent, CodePhrase language,
                 CodePhrase encoding, PartyProxy subject, 
                 PartyProxy provider, ObjectRef workflowId,
                 List<Participation> otherParticipations,
                 ItemStructure protocol, ObjectRef guidelineId, 
                 TerminologyService terminologyService) {

        super(uid, archetypeNodeId, name, archetypeDetails, feederAudit, links, parent, 
        		language, encoding, subject, provider, workflowId, otherParticipations,
        		terminologyService);
        this.protocol = protocol;
        this.guidelineId = guidelineId;
    }

    /**
     * Optional external identifier of guideline creating this action 
     * if relevant
     * 
     * @return guidelineId
     */
    public ObjectRef getGuidelineId() {
    	return guidelineId;
    }
    
    /**
     * Description of the method the information in this entry was arrived at.
     * 
     * @return protocol
     */
    public ItemStructure getProtocol() {
    	return protocol;
    }
    
    //POJO start
    CareEntry() {
    }
    
	void setGuidelineId(ObjectRef guidelineId) {
		this.guidelineId = guidelineId;
	}
	void setProtocol(ItemStructure protocol) {
		this.protocol = protocol;
	}
	//POJO end
	
    /* fields */        
   private ItemStructure protocol;
   private ObjectRef guidelineId;

   /* static fields */
   public static final String PROTOCOL = "protocol";
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
 *  The Original Code is CareEntry.java
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