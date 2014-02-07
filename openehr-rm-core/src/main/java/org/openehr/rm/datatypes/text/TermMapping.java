/*
 * component:   "openEHR Reference Implementation"
 * description: "Class TermMapping"
 * keywords:    "datatypes"
 *
 * author:      "Rong Chen <rong@acode.se>"
 * support:     "Acode HB <support@acode.se>"
 * copyright:   "Copyright (c) 2004 Acode HB, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/BRANCHES/RM-1.0-update/libraries/src/java/org/openehr/rm/datatypes/text/TermMapping.java $"
 * revision:    "$LastChangedRevision: 50 $"
 * last_change: "$LastChangedDate: 2006-08-10 13:21:46 +0200 (Thu, 10 Aug 2006) $"
 */
package org.openehr.rm.datatypes.text;

import org.openehr.rm.datatypes.basic.DataValue;
import org.openehr.rm.support.terminology.TerminologyService;

/**
 * A mapping of a term to a text item. Instances of this class are
 * immutable.
 *
 * @author Rong Chen
 * @version 1.0
 */
public final class TermMapping extends DataValue {

    /**
     * Constructs a TermMapping by target, match and purpose
     *
     * @param target             not null
     * @param match              not null
     * @param purpose            null if unspecified
     * @param terminologyService
     * @throws IllegalArgumentException if any of arguments invalid
     */
    public TermMapping(CodePhrase target, Match match,
                       DvCodedText purpose,
                       TerminologyService terminologyService) {
        if (target == null) {
            throw new IllegalArgumentException("null target");
        }
        if (purpose != null) {
            if (terminologyService == null) {
                throw new IllegalArgumentException("null terminologyService");
            }
            if (!terminologyService.terminology(TerminologyService.OPENEHR)
            		.codesForGroupName("term mapping purpose", "en")
            		.contains(purpose.getDefiningCode())) {
                throw new IllegalArgumentException(
                        "unknown term mapping purpose: " + purpose);
            }
        }
        if (match == null) {
            throw new IllegalArgumentException("null match");
        }
        this.target = target;
        this.match = match;
        this.purpose = purpose;
    }

    /**
     * Constructs a TermMapping by target and match
     *
     * @param target
     * @param match
     */
    public TermMapping(CodePhrase target, Match match) {
        this(target, match, null, null);
    }

    /**
     * The target term of the mapping.
     *
     * @return target
     */
    public CodePhrase getTarget() {
        return target;
    }

    /**
     * The relative match of the target term with respect to the
     * mapped text item. Result meanings: "  > : the mapping is to a
     * broader termm, eg, orginal text =  arbovirus infection ,
     * target =  viral infection  "  = : the mapping is to a
     * (supposedly) equivalent to the original item "  < :
     * the mapping is to a narrower term. e.g. original text =
     * diabetes , mapping =  diabetes mellitus . "  ? : the kind of
     * mapping is unknown.
     *
     * @return match
     */
    public Match getMatch() {
        return match;
    }

    /**
     * Purpose of the mapping e.g.  automated data mining ,
     * billing ,  interoperability
     *
     * @return purpose
     */
    public DvCodedText getPurpose() {
        return purpose;
    }

    /**
     * The mapping is to an equivalent term.
     *
     * @return is equivalent
     */
    public boolean equivalent() {
        return Match.EQUIVALENT.equals(match);
    }

    /**
     * The mapping is to a broader term.
     *
     * @return true if broader
     */
    public boolean broader() {
        return Match.BROADER.equals(match);
    }

    /**
     * The mapping is to a narrower term.
     *
     * @return true if narrower
     */
    public boolean narrower() {
        return Match.NARROWER.equals(match);
    }

    /**
     * The kind of mapping is unknown
     *
     * @return true if unknown
     */
    public boolean unkonwn() {
        return Match.UNKNOWN.equals(match);
    }

    // POJO start
    private TermMapping() {
    }

    private void setTarget(CodePhrase target) {
        this.target = target;
    }

    private void setMatch(Match match) {
        this.match = match;
    }

    private void setPurpose(DvCodedText purpose) {
        this.purpose = purpose;
    }

    private String getMatchString() {
        return match.toString();
    }

    private void setMatchString(String value) {
        this.match = Match.valueOf(value);
    }

    // POJO end

    /* fields */
    private CodePhrase target;
    private Match match;
    private DvCodedText purpose;
	@Override
	public String getReferenceModelName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String serialise() {
		// TODO Auto-generated method stub
		return null;
	}
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
 *  The Original Code is TermMapping.java
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