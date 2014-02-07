/*
 * component:   "openEHR Reference Implementation"
 * description: "Class AdminEntry"
 * keywords:    "composition"
 *
 * author:      "Yin Su Lim <y.lim@chime.ucl.ac.uk>"
 * support:     "CHIME, UCL"
 * copyright:   "Copyright (c) 2006 UCL, UK"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/TRUNK/libraries/src/java/org/openehr/rm/composition/content/entry/AdminEntry.java $"
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
import org.openehr.rm.datatypes.text.CodePhrase;
import org.openehr.rm.datatypes.text.DvText;
import org.openehr.rm.support.identification.UIDBasedID;
import org.openehr.rm.support.identification.ObjectRef;
import org.openehr.rm.support.terminology.TerminologyService;

/**
 * Entry subtype for administrative information, i.e. information about setting up 
 * the clinical process, but not itself clinically relevant. Archetypes will define 
 * contained information
 * 
 * @author Yin Su Lim
 * @version 1.0
 */
public class AdminEntry extends Entry {

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
	 * @param terminologyService
	 */
    @FullConstructor
	public AdminEntry(@Attribute(name = "uid") UIDBasedID uid,
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
                       @Attribute(name = "data", required = true) ItemStructure data,
                       @Attribute(name = "terminologyService", system = true) TerminologyService terminologyService) {
		super(uid, archetypeNodeId, name, archetypeDetails, feederAudit, links, parent,
				language, encoding, subject, provider, workflowId, otherParticipations,
				terminologyService);
		if (data == null) {
			throw new IllegalArgumentException("null data");
		}
		this.data = data;
	}

    /**
     * Gets data of this adminEntry
     * 
     * @return data
     */
	public ItemStructure getData() {
		return data;
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
	AdminEntry() {
	}
	
	void setData(ItemStructure data) {
		this.data = data;
	}
	//POJO end
	
	/* field */
	private ItemStructure data;
	
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
 *  The Original Code is AdminEntry.java
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