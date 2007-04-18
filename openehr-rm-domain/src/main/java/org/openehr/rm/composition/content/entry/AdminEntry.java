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
import org.openehr.rm.common.generic.Participation;
import org.openehr.rm.common.generic.PartyProxy;
import org.openehr.rm.datastructure.itemstructure.ItemStructure;
import org.openehr.rm.datatypes.text.CodePhrase;
import org.openehr.rm.datatypes.text.DvText;
import org.openehr.rm.support.identification.ObjectID;
import org.openehr.rm.support.identification.ObjectReference;
import org.openehr.rm.support.terminology.TerminologyService;

import org.openehr.rm.common.archetyped.Locatable;

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
	 * @param charset
	 * @param subject
	 * @param provider
	 * @param workflowID
	 * @param otherParticipations
	 * @param terminologyService
	 */
    @FullConstructor
	public AdminEntry(@Attribute(name = "uid") ObjectID uid,
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
                       @Attribute(name = "otherParticipations") List<Participation> otherParticipations, 
                       @Attribute(name = "data", required = true) ItemStructure data,
                       @Attribute(name = "terminologyService", system = true) TerminologyService terminologyService) {
		super(uid, archetypeNodeId, name, archetypeDetails, feederAudit, links, parent,
				language, charset, subject, provider, workflowID, otherParticipations,
				terminologyService);
		if (data == null) {
			throw new IllegalArgumentException("null data");
		}
		this.data = data;
	}

	public ItemStructure getData() {
		return data;
	}

	@Override
	public String pathOfItem(Locatable item) {
		// TODO Auto-generated method stub
		return null;
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
        Object ret = checkAttribute(path, "data", data);
        if (ret == null) {
            throw new IllegalArgumentException("invalid path: " + path);
        }
        return ret;
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