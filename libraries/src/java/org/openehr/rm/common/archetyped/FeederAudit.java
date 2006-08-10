/*
 * component:   "openEHR Reference Implementation"
 * description: "Class FeederAudit"
 * keywords:    "common"
 *
 * author:      "Rong Chen <rong@acode.se>"
 * support:     "Acode HB <support@acode.se>"
 * copyright:   "Copyright (c) 2004 Acode HB, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/TRUNK/libraries/src/java/org/openehr/rm/common/archetyped/FeederAudit.java $"
 * revision:    "$LastChangedRevision: 2 $"
 * last_change: "$LastChangedDate: 2006-03-09 22:20:08 +0100 (Wed, 12 Oct 2005) $"
 */
package org.openehr.rm.common.archetyped;

import java.util.List;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.openehr.rm.RMObject;
import org.openehr.rm.datatypes.basic.DvIdentifier;

/**
 * Audit and other meta-data for systems in the feeder chain. Instances of this class are
 * immutable
 *
 * @author Yin Su Lim
 * @version 1.0
 */
public final class FeederAudit extends RMObject {

   /**
     * Constrcuts a FeederAuditDetails
     *
     * @param originatingSystemAudit		not null
     * @param originatingSystemItemIDs 	null if not specified
     * @param feederSystemAudit			null if not specified
     * @param feederSystemItemIDs			null if not specified
     * @throws IllegalArgumentException if originatingSystemAudit null,
     * 	originatingSystemItemIDs or feederSystemItemIDs empty
     */
    public FeederAudit(FeederAuditDetails originatingSystemAudit,
    		List<DvIdentifier> originatingSystemItemIDs,
    		FeederAuditDetails feederSystemAudit,
    		List<DvIdentifier> feederSystemItemIDs) {
        if (originatingSystemAudit == null) {
            throw new IllegalArgumentException("null originatingSystemAudit");
        }
        if (originatingSystemItemIDs != null && originatingSystemItemIDs.size() == 0) {
            throw new IllegalArgumentException("empty originatingSystemItemIDs");
        }
        if (feederSystemItemIDs != null && feederSystemItemIDs.size() == 0) {
            throw new IllegalArgumentException("empty feederSystemItemIDs");
        }
        this.originatingSystemAudit = originatingSystemAudit;
        this.originatingSystemItemIDs = originatingSystemItemIDs;
        this.feederSystemAudit = feederSystemAudit;
        this.feederSystemItemIDs = feederSystemItemIDs;
    }

    /**
     * Audit information for the information item from the originating system
     * 
     * @return originatingSystemAudit
     */
	public FeederAuditDetails getOriginatingSystemAudit() {
		return originatingSystemAudit;
	}

	/**
	 * Identifiers used for the item in the originating system
	 * 
	 * @return originatingSystemItemIDs
	 */
	public List<DvIdentifier> getOriginatingSystemItemIDs() {
		return originatingSystemItemIDs;
	}
	
	/**
	 * Audit information for the information item from the feeder system,
	 * if different from the originating system
	 * 
	 * @return feederSystemAudit
	 */
    public FeederAuditDetails getFeederSystemAudit() {
		return feederSystemAudit;
	}

    /**
     * Identifiers used for the item in the feeder system, where the feeder
     * system is distinct from the originating system
     * 
     * @return feederSystemItemIDs
     */
	public List<DvIdentifier> getFeederSystemItemIDs() {
		return feederSystemItemIDs;
	}

    /**
     * Equals if have same values
     *
     * @param o
     * @return true if equals
     */
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!( o instanceof FeederAudit )) return false;

        final FeederAudit fa = (FeederAudit) o;

        return new EqualsBuilder()
                .append(originatingSystemAudit, fa.originatingSystemAudit)
                .append(originatingSystemItemIDs, fa.originatingSystemItemIDs)
                .append(feederSystemAudit, fa.feederSystemAudit)
                .append(feederSystemItemIDs, fa.feederSystemItemIDs)
                .isEquals();
    }

    /**
     * Hashcode of this object
     *
     * @return hashcode
     */
    public int hashCode() {
        return new HashCodeBuilder(7,19)
                .append(originatingSystemAudit)
                .append(originatingSystemItemIDs)
                .append(feederSystemAudit)
                .append(feederSystemItemIDs)
                .toHashCode();
    }
    
	// POJO start
	FeederAudit() {		
	}
	
    void setOriginatingSystemAudit(FeederAuditDetails originatingSystemAudit) {
        this.originatingSystemAudit = originatingSystemAudit;
    }

    void setOriginatingSystemItemIDs(List<DvIdentifier> originatingSystemItemIDs) {
        this.originatingSystemItemIDs = originatingSystemItemIDs;
    }

    void setFeederSystemAudit(FeederAuditDetails feederSystemAudit) {
        this.feederSystemAudit = feederSystemAudit;
    }

    void setFeederSystemItemIDs(List<DvIdentifier> feederSystemItemIDs) {
        this.feederSystemItemIDs = feederSystemItemIDs;
    }

    // POJO end

    /* fields */
    private FeederAuditDetails originatingSystemAudit;
    private List<DvIdentifier> originatingSystemItemIDs;
    private FeederAuditDetails feederSystemAudit;
    private List<DvIdentifier> feederSystemItemIDs;

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
 *  The Original Code is FeederAudit.java
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