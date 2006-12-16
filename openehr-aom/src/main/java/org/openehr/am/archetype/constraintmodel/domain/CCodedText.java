/*
 * component:   "openEHR Reference Implementation"
 * description: "Class CCodedText"
 * keywords:    "archetype"
 *
 * author:      "Rong Chen <rong@acode.se>"
 * support:     "Acode HB <support@acode.se>"
 * copyright:   "Copyright (c) 2004 Acode HB, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/TRUNK/libraries/src/java/org/openehr/am/archetype/constraintmodel/domain/CCodedText.java $"
 * revision:    "$LastChangedRevision: 2 $"
 * last_change: "$LastChangedDate: 2005-10-12 23:20:08 +0200 (Wed, 12 Oct 2005) $"
 */
package org.openehr.am.archetype.constraintmodel.domain;

import org.apache.commons.lang.StringUtils;
import org.openehr.am.archetype.constraintmodel.ArchetypeConstraint;
import org.openehr.am.archetype.constraintmodel.CComplexObject;
import org.openehr.am.archetype.constraintmodel.CDomainType;

import java.util.*;

/**
 * This class represents a constraint for coded text. Instances of this class
 * are immutable.
 *
 * @author Rong Chen
 * @version 1.0
 * @deprecated use CDvCodedText from openehr-ap instead
 */
public final class CCodedText extends CDomainType {

    /**
     * Constructs a constraint of coded text by terminogy and code list
     *
     * @param path
     * @param terminology
     * @param codeList    List<String> empty list means any code
     * @throws IllegalArgumentException if either terminology or codeList null
     */
    public CCodedText(String path, String terminology,
                      List<String> codeList) {

        super(path, "DvCodedText");

        if (( StringUtils.isEmpty(terminology) || codeList == null )) {
            throw new IllegalArgumentException(
                    "either terminology or codeList null");
        }
        this.terminology = terminology;
        this.codeList = new ArrayList<String>(codeList);
        this.reference = null;
    }

    /**
     * Constructs a constraint of coded text by reference
     *
     * @param path
     * @param reference
     */
    public CCodedText(String path, String reference) {

        super(path, "DvCodedText", null, null);

        if (StringUtils.isEmpty(reference)) {
            throw new IllegalArgumentException("reference empty");
        }
        this.reference = reference;
        this.terminology = null;
        this.codeList = null;
    }

    /**
     * Standard form of constraint
     *
     * @return Standard form of constraint
     */
    public CComplexObject standardRepresentation() {
        return null;  // todo: implement this method
    }

    /**
     * True if this node is a valid archetype node.
     *
     * @return ture if valid
     */
    public boolean isValid() {
        return false;  // todo: implement this method
    }

    /**
     * True if the relative path exists at this node.
     *
     * @param path
     * @return ture if has
     * @throws IllegalArgumentException if path null
     */
    public boolean hasPath(String path) {
        return false;  // todo: implement this method
    }

    /**
     * True if constraints represented by other are narrower than this node.
     *
     * @param constraint
     * @return true if subset
     * @throws IllegalArgumentException if constraint null
     */
    public boolean isSubsetOf(ArchetypeConstraint constraint) {
        return false;  // todo: implement this method
    }

    /**
     * The terminology where the code list is from
     *
     * @return null if not code list is not used
     */
    public String getTerminology() {
        return terminology;
    }

    /**
     * List of code defined in the used terminology
     *
     * @return null if no code list is specified
     *         or unmodifiable List<DvCodedText>
     */
    public List<String> getCodeList() {
        return codeList == null ?
                null : Collections.unmodifiableList(codeList);
    }

    /**
     * Reference to constraint of term
     *
     * @return null if not used
     */
    public String getReference() {
        return reference;
    }

    /**
     * Return true if the constraint has limit the possible value to
     * be only one, which means the value has been assigned by the archetype
     * author at design time
     *
     * @return true if has
     */
    public boolean hasAssignedValue() {
        // todo: query by reference like "ac0003"
        return codeList != null && codeList.size() == 1;
    }

    /* fields */
    private final String terminology;
    private final List<String> codeList;
    private final String reference;
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
 *  The Original Code is CCodedText.java
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