/*
 * component:   "openEHR Reference Implementation"
 * description: "Class EHRStatus"
 * keywords:    "ehr"
 *
 * author:      "Yin Su Lim <y.lim@chime.ucl.ac.uk>"
 * support:     "CHIME, UCL"
 * copyright:   "Copyright (c) 2006 UCL, UK"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/TRUNK/libraries/src/java/org/openehr/rm/ehr/EHRStatus.java $"
 * revision:    "$LastChangedRevision: 53 $"
 * last_change: "$LastChangedDate: 2006-08-11 13:20:08 +0100 (Fri, 11 Aug 2006) $"
 */
package org.openehr.rm.ehr;

import java.util.List;
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
import org.openehr.rm.common.generic.PartySelf;
import org.openehr.rm.datastructure.itemstructure.ItemStructure;
import org.openehr.rm.datatypes.text.DvText;
import org.openehr.rm.support.identification.UIDBasedID;

/**
 * Single object per EHR giving various EHR-wide information.
 *
 * @author Yin Su Lim
 * @version 1.0
 */
public class EHRStatus extends Locatable {

    @FullConstructor public EHRStatus(
            @Attribute(name = "uid", required = true) UIDBasedID uid,
            @Attribute(name = "archetypeNodeId", required = true) String archetypeNodeId,
            @Attribute(name = "name", required = true) DvText name,
            @Attribute(name = "archetypeDetails", required = true) Archetyped archetypeDetails,
            @Attribute(name = "feederAudit") FeederAudit feederAudit,
            @Attribute(name = "links") Set<Link> links,
            @Attribute(name = "parent") Pathable parent,
            @Attribute(name = "subject", system = true) PartySelf subject,
            @Attribute(name = "isQueryable", required = true) boolean isQueryable,
            @Attribute(name = "isModifiable", required = true) boolean isModifiable,
            @Attribute(name = "otherDetails") ItemStructure otherDetails ) {
        super(uid, archetypeNodeId, name, archetypeDetails, feederAudit, links, parent);
        if (subject == null) {
            throw new IllegalArgumentException("null subject");
        }
        if (parent != null) {
            throw new IllegalArgumentException("parent must be null");
        }
        if (!isArchetypeRoot()) {
            throw new IllegalArgumentException("not archetype root");
        }
        this.subject = subject;
        this.isQueryable = isQueryable;
        this.isModifiable = isModifiable;
        this.otherDetails = otherDetails;
    }
    
    /**
     * The subject of this EHR. The externalRef attribute can be used to contain a direct
     * reference to the subject in a demographic or identity service. Alternatively, the
     * association between patients and their records may be done elsewhere for security
     * reasons.
     */
    public PartySelf getSubject() {
        return subject;
    }
    
    /**
     * True if this EHR should be included in population queries, i.e. if this EHR
     * is considered active in the population.
     */
    public boolean getIsQueryable() {
        return isQueryable;
    }
    
    /**
     * True if this EHR is allowed to be written to.
     */
    public boolean getIsModifiable() {
        return isModifiable;
    }
    
    /**
     * Any other details of the EHR summary object, in the form of an archetyped itemStructure.
     */
    public ItemStructure getOtherDetails() {
        return otherDetails;
    }
    
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!( o instanceof EHRStatus )) return false;
        if (!super.equals(o)) return false;
        
        final EHRStatus ehrS = (EHRStatus) o;
        
        return new EqualsBuilder()
        .append(subject, ehrS.subject)
        .append(isQueryable, ehrS.isQueryable)
        .append(isModifiable, ehrS.isModifiable)
        .append(otherDetails, ehrS.otherDetails)
        .isEquals();
    }
    
    public int hashCode() {
        return new HashCodeBuilder(79, 19)
        .appendSuper(super.hashCode())
        .append(subject)
        .append(isQueryable)
        .append(isModifiable)
        .append(otherDetails)
        .toHashCode();
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
    
    //POJO
    void setSubject(PartySelf subject) {
        this.subject = subject;
    }
    
    void setIsQueryable(boolean isQueryable) {
        this.isQueryable = isQueryable;
    }
    
    void setIsModifiable(boolean isModifiable) {
        this.isModifiable = isModifiable;
    }
    
    void setOtherDetails(ItemStructure otherDetails) {
        this.otherDetails = otherDetails;
    }
    
    EHRStatus() {}
    
    /* fields */
    private PartySelf subject;
    private boolean isQueryable;
    private boolean isModifiable;
    private ItemStructure otherDetails;

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
 *  The Original Code is EHRStatus.java
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