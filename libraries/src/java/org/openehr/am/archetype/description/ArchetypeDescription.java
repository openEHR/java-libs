/*
 * component:   "openEHR Reference Implementation"
 * description: "Class ArchetypeDescription"
 * keywords:    "archetype"
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
package org.openehr.am.archetype.description;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.List;
import java.util.Map;

/**
 * Defines the descriptive meta-data of an archetype.
 *
 * @author Rong Chen
 * @version 1.0
 */
public class ArchetypeDescription {

    /**
     * Constructs an ArchetypeDescription
     *
     * @param originalAuthor
     * @param originalAuthorOrganisation
     * @param lifecycleState
     * @param archetypePackageURI
     * @param details
     * @param otherDetails
     * @throws IllegalArgumentException if originalAuthor null or empty,
     *         or orignalAuthorOrgnaisation empty or details null or empty
     */
    public ArchetypeDescription(String originalAuthor,
                                String originalAuthorOrganisation,
                                String lifecycleState,
                                String archetypePackageURI,
                                List<ArchetypeDescriptionItem> details,
                                Map<String,String> otherDetails) {
        if(StringUtils.isEmpty(originalAuthor)) {
            throw new IllegalArgumentException("originalAuthor null");
        }
        if(originalAuthorOrganisation != null &&
                StringUtils.isEmpty(originalAuthorOrganisation)) {
            throw new IllegalArgumentException(
                    "originalAuthorOrganisation empty");
        }
        if(details == null || details.isEmpty()) {
            throw new IllegalArgumentException("null or empty details");
        }
        this.originalAuthor = originalAuthor;
        this.originalAuthorOrganisation = originalAuthorOrganisation;
        this.lifecycleState = lifecycleState;
        this.archetypePackageURI = archetypePackageURI;
        this.details = details;
        this.otherDetails = otherDetails;
    }

    /**
     * Original author of this archetype
     *
     * @return Original author
     */
    public String getOriginalAuthor() {
        return originalAuthor;
    }

    /**
     * Original author organisation.
     *
     * @return Original author organisation.
     */
    public String getOriginalAuthorOrganisation() {
        return originalAuthorOrganisation;
    }

    /**
     * Lifecycle state of the archetype, typically including states such as:
     * initial, submitted, experimental, awaiting_approval, approved,
     * superseded, obsolete.
     *
     * @return life cycle state
     */
    public String getLifecycleState() {
        return lifecycleState;
    }

    /**
     * URI of package to which this archetype belongs.
     *
     * @return package URI
     */
    public String getArchetypePackageURI() {
        return archetypePackageURI;
    }

    /**
     * Details of all parts of archetype description which are natural
     * language-dependent.
     *
     * @return List of ArchetypeDescriptionItem
     */
    public List<ArchetypeDescriptionItem> getDetails() {
        return details;
    }

    /**
     * Additional non language-senstive archetype meta-data, as a list of
     * name/value pairs.
     *
     * @return other details
     */
    public Map<String, String> getOtherDetails() {
        return otherDetails;
    }

   /**
     * String representation of this object
     *
     * @return string form
     */
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    /* fields */
    private String originalAuthor;
    private String originalAuthorOrganisation;
    private String lifecycleState;
    private String archetypePackageURI;
    private List<ArchetypeDescriptionItem> details;
    private Map<String,String> otherDetails;
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
 *  The Original Code is ArchetypeDescription.java
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