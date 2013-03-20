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
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/TRUNK/libraries/src/java/org/openehr/rm/common/archetyped/Archetyped.java $"
 * revision:    "$LastChangedRevision: 2 $"
 * last_change: "$LastChangedDate: 2005-10-12 22:20:08 +0100 (Wed, 12 Oct 2005) $"
 */
package org.openehr.rm.common.archetyped;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.openehr.rm.Attribute;
import org.openehr.rm.FullConstructor;
import org.openehr.rm.RMObject;
import org.openehr.rm.support.identification.ArchetypeID;
import org.openehr.rm.support.identification.TemplateID;

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
     * Creates an Archetyped
     *
     * @param archetypeId	not null
     * @param templateId    null if unspecified
     * @param rmVersion     not null or empty
     * 
     * @throws IllegalArgumentException if archetypeId null
     *                                  or rmVersion empty
     */
	@FullConstructor
    public Archetyped(
    		@Attribute(name = "archetypeId", required = true) ArchetypeID archetypeId,
    		@Attribute(name = "templateId") TemplateID templateId,
    		@Attribute(name = "rmVersion", required = true) String rmVersion) {
        if (archetypeId == null) {
            throw new IllegalArgumentException("null archetypeId");
        }
        if (StringUtils.isEmpty(rmVersion)) {
            throw new IllegalArgumentException("empty rmVersion");
        }
        this.archetypeId = archetypeId;
        this.templateId = templateId;
        this.rmVersion = rmVersion;
    }
    
    /**
     * Creates an Archetyped without a templateId
     *
     * @param archetypeId
     * @param rmVersion
     * @throws IllegalArgumentException if archetypeId null
     *                                  or rmVersion empty
     */
    public Archetyped(ArchetypeID archetypeId, String rmVersion) {
        this(archetypeId, null, rmVersion);
    }
    
    /**
     * Creates an Archetyped without a templateId
     *
     * @param archetypeId
     * @param rmVersion
     * @throws IllegalArgumentException if archetypeId null
     *                                  or rmVersion empty
     */
    public Archetyped(String archetypeId, String rmVersion) {
        this(new ArchetypeID(archetypeId), null, rmVersion);
    }

    /**
     * Globally unique archetype identifier
     *
     * @return archetypeId
     */
    public ArchetypeID getArchetypeId() {
        return archetypeId;
    }
    
    /**
     * Globally unique template identifier
     *
     * @return templateId or null if not specified
     */
    public TemplateID getTemplateId() {
        return templateId;
    }

    /**
     * Version of the openEHR reference model used to create this
     * object
     *
     * @return rmVersion
     */
    public String getRmVersion() {
        return rmVersion;
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
                .append(archetypeId, archetyped.archetypeId)
                .append(templateId, archetyped.templateId)
                .append(rmVersion, archetyped.rmVersion)
                .isEquals();
    }

    /**
     * Hashcode of this object
     *
     * @return hashcode
     */
    public int hashCode() {
        return new HashCodeBuilder(7,19)
                .append(archetypeId)
                .append(templateId)                
                .append(rmVersion)
                .toHashCode();
    }

    // POJO start
    Archetyped() {
    }

    void setArchetypeId(ArchetypeID archetypeID) {
        this.archetypeId = archetypeID;
    }

    void setRmVersion(String rmVersion) {
        this.rmVersion = rmVersion;
    }
    // POJO end

    /* fields */
    private ArchetypeID archetypeId;
    private TemplateID templateId;
    private String rmVersion;
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
 *  Portions created by the Initial Developer are Copyright (C) 2003-2007
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