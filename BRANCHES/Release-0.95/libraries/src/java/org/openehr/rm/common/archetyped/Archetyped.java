/*
 * component:   "openEHR Reference Implementation"
 * description: "Class Archetyped"
 * keywords:    "common"
 *
 * author:      "Rong Chen <rong@acode.se>"
 * support:     "Acode HB <support@acode.se>"
 * copyright:   "Copyright (c) 2004 Acode HB, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL$"
 * revision:    "$LastChangedRevision$"
 * last_change: "$LastChangedDate$"
 */
package org.openehr.rm.common.archetyped;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.openehr.rm.RMObject;
import org.openehr.rm.support.identification.AccessGroupReference;
import org.openehr.rm.support.identification.ArchetypeID;

/**
 * Archetypes act as the configuration basis for the particular
 * structures of instances defined by the reference model. To enable
 * archetypes to be used to create valid data, key classes in the
 * reference model act as "root" points for archetyping; accordingly,
 * these classes have the archetype_details attribute set.
 * An instance of the class <code>Archetyped</code> contains the
 * relevant archetype identification information, allowing generating
 * archetypes to be matched up with data instances.
 * <p/>
 * Instances of this class are immutable.
 *
 * @author Rong Chen
 * @version 1.0
 */
public final class Archetyped extends RMObject {

    /**
     * Construcst an Archetyped
     *
     * @param archetypeID
     * @param accessControl
     * @param referenceModelVersion
     * @throws IllegalArgumentException if archetypeID null
     *                                  or referenceModelVersion empty
     */
    public Archetyped(ArchetypeID archetypeID,
                      AccessGroupReference accessControl,
                      String referenceModelVersion) {
        if (archetypeID == null) {
            throw new IllegalArgumentException("null archetypeID");
        }
        if (StringUtils.isEmpty(referenceModelVersion)) {
            throw new IllegalArgumentException("empty referenceModelVersion");
        }
        this.archetypeID = archetypeID;
        this.accessControl = accessControl;
        this.referenceModelVersion = referenceModelVersion;
    }

    /**
     * Globally unique archetype identifier
     *
     * @return archetypeID
     */
    public ArchetypeID getArchetypeID() {
        return archetypeID;
    }

    /**
     * The access control settings of this component
     *
     * @return accessGroupReference
     */
    public AccessGroupReference getAccessControl() {
        return accessControl;
    }

    /**
     * Version of the openEHR reference model used to create this
     * object
     *
     * @return referenceModelVersion
     */
    public String getReferenceModelVersion() {
        return referenceModelVersion;
    }

    /**
     * Equals if have same values
     *
     * @param o
     * @return true if equals
     */
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!( o instanceof Archetyped )) return false;

        final Archetyped archetyped = (Archetyped) o;

        return new EqualsBuilder()
                .append(archetypeID, archetyped.archetypeID)
                .append(accessControl, archetyped.accessControl)
                .append(referenceModelVersion,
                        archetyped.referenceModelVersion)
                .isEquals();
    }

    /**
     * Hashcode of this object
     *
     * @return hashcode
     */
    public int hashCode() {
        return new HashCodeBuilder(7,19)
                .append(archetypeID)
                .append(accessControl)
                .append(referenceModelVersion)
                .toHashCode();
    }

    // POJO start
    Archetyped() {
    }

    void setArchetypeID(ArchetypeID archetypeID) {
        this.archetypeID = archetypeID;
    }

    void setAccessControl(AccessGroupReference accessControl) {
        this.accessControl = accessControl;
    }

    void setReferenceModelVersion(String referenceModelVersion) {
        this.referenceModelVersion = referenceModelVersion;
    }
    // POJO end

    /* fields */
    private ArchetypeID archetypeID;
    private AccessGroupReference accessControl;
    private String referenceModelVersion;
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
 *  The Original Code is Archetyped.java
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