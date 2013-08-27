/*
 * component:   "openEHR Reference Implementation"
 * description: "Class EHRAccess"
 * keywords:    "ehr"
 *
 * author:      "Yin Su Lim <y.lim@chime.ucl.ac.uk>"
 * support:     "CHIME, UCL"
 * copyright:   "Copyright (c) 2006 UCL, UK"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/TRUNK/libraries/src/java/org/openehr/rm/ehr/EHRAccess.java $"
 * revision:    "$LastChangedRevision: 53 $"
 * last_change: "$LastChangedDate: 2006-08-11 13:20:08 +0100 (Fri, 11 Aug 2006) $"
 */
package org.openehr.rm.ehr;

import java.util.List;
import java.util.Set;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.openehr.rm.Attribute;
import org.openehr.rm.common.archetyped.Archetyped;
import org.openehr.rm.common.archetyped.FeederAudit;
import org.openehr.rm.common.archetyped.Link;
import org.openehr.rm.common.archetyped.Locatable;
import org.openehr.rm.common.archetyped.Pathable;
import org.openehr.rm.datatypes.text.DvText;
import org.openehr.rm.security.AccessControlSettings;
import org.openehr.rm.support.identification.UIDBasedID;

/**
 * EHR-wide access control object. All access decisions to data in the EHR must be made
 * in accordance with the policies and rules in this object.
 *
 * @author Yin Su Lim
 * @version 1.0
 */
public class EHRAccess extends Locatable {
    
    /** Creates a new instance of EHRAccess */
    public EHRAccess(
            @Attribute(name = "uid") UIDBasedID uid,
            @Attribute(name = "archetypeNodeId", required = true) String archetypeNodeId,
            @Attribute(name = "name", required = true) DvText name,
            @Attribute(name = "archetypeDetails") Archetyped archetypeDetails,
            @Attribute(name = "feederAudit") FeederAudit feederAudit,
            @Attribute(name = "links") Set<Link> links,
            @Attribute(name = "parent") Pathable parent,
            @Attribute(name = "settings") AccessControlSettings settings) {
        
        super(uid, archetypeNodeId, name, archetypeDetails, feederAudit, links, parent);
        if(settings == null) {
            throw new IllegalArgumentException("null settings");
        }
        this.settings = settings;
    }

    /**
     * The name of the access control scheme in use; corresponds to the concrete instance
     * of the settings attribute.
     */
    public String scheme() {
        return ""; //TODO: implement here or in AccessContolSettings
    }
    
    /**
     * Access control settings for the EHR. Instance is a subtype of the type 
     * AccessControlSettings, allowing for the use of different access control schemes.
     */
    public AccessControlSettings getSettings() {
        return settings;
    }
    
    public String pathOfItem(Locatable item) {
        return "";
    }
 
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!( o instanceof EHRAccess)) return false;
        if (!super.equals(o)) return false;

        final EHRAccess ehrA = (EHRAccess) o;
        return new EqualsBuilder()
                .append(settings, ehrA.settings)
                .isEquals();
    }
    
        /**
     * Return a hash code of this actor
     *
     * @return hash code
     */
    public int hashCode() {
        return new HashCodeBuilder(19, 83)
                .appendSuper(super.hashCode())
                .append(settings)
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
    EHRAccess() {}
    
    void setSettings(AccessControlSettings settings) {
        this.settings = settings;
    }
    
    /* private fields */
    private AccessControlSettings settings;
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
 *  The Original Code is EHRAccess.java
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