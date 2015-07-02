/*
 * component:   "openEHR Reference Implementation"
 * description: "Class Evaluation"
 * keywords:    "composition"
 *
 * author:      "Rong Chen <rong@acode.se>"
 * support:     "Acode HB <support@acode.se>"
 * copyright:   "Copyright (c) 2004 Acode HB, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/TRUNK/libraries/src/java/org/openehr/rm/composition/content/entry/Evaluation.java $"
 * revision:    "$LastChangedRevision: 2 $"
 * last_change: "$LastChangedDate: 2005-10-12 22:20:08 +0100 (Wed, 12 Oct 2005) $"
 */
package org.openehr.rm.composition.content.entry;

import org.openehr.rm.Attribute;
import org.openehr.rm.FullConstructor;
import org.openehr.rm.common.archetyped.Archetyped;
import org.openehr.rm.common.archetyped.FeederAudit;
import org.openehr.rm.common.archetyped.Link;
import org.openehr.rm.common.archetyped.Locatable;
import org.openehr.rm.common.archetyped.Pathable;
import org.openehr.rm.common.generic.Participation;
import org.openehr.rm.common.generic.PartyProxy;
import org.openehr.rm.support.identification.UIDBasedID;
import org.openehr.rm.support.identification.ObjectRef;
import org.openehr.rm.support.terminology.TerminologyService;
import org.openehr.rm.datastructure.itemstructure.ItemStructure;
import org.openehr.rm.datatypes.text.CodePhrase;
import org.openehr.rm.datatypes.text.DvText;

import java.util.List;
import java.util.Set;

/**
 * Entry type for evaluation statements. Instances of this class are immutable.
 *
 * @author Rong Chen
 * @version 1.0
 */
public final class Evaluation extends CareEntry {

    /**
     * Create an Evaluation
     *
     * @param uid
     * @param archetypeNodeId
     * @param name
     * @param subject
     * @param provider
     * @param protocol           null if unspecified
     * @param actID              null if unspecified
     * @param guidelineId        null if unspecified
     * @param otherParticipations null if unspecified
     * @param data
     * @throws IllegalArgumentException if data null
     */
    @FullConstructor
            public Evaluation(@Attribute(name = "uid") UIDBasedID uid,
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
                              @Attribute(name = "data", required = true) ItemStructure data,
                              @Attribute(name = "terminologyService", system = true) TerminologyService terminologyService) {
        super(uid, archetypeNodeId, name, archetypeDetails, feederAudit, links,
                parent, language, encoding, subject, provider, workflowId, 
                otherParticipations, protocol, guidelineId, terminologyService);

        if (data == null) {
            throw new IllegalArgumentException("null data");
        }
        this.data = data;
    }

    /**
     * The data of this evaluation, in the form of a spatial
     * data structure.
     *
     * @return data of this evaluation
     */
    public ItemStructure getData() {
        return data;
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

    // POJO start
    Evaluation() {
    }

    void setData(ItemStructure data) {
        this.data = data;
    }
    // POJO end


    @Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		if (!super.equals(obj)) {
			return false;
		}
		Evaluation other = (Evaluation) obj;
		if (data == null) {
			if (other.data != null) {
				return false;
			}
		} else if (other.data == null) {
			return false;
		} else if (!data.equals(other.data)) {
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
    
    @Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result
				+ ((getProtocol() == null) ? 0 : getProtocol().hashCode());
		return result;
	}
    
    
    /* fields */
    private ItemStructure data;
    
    /* static fields */
    public static final String DATA = "data";	
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
 *  The Original Code is Evaluation.java
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