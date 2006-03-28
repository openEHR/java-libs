/*
 * component:   "openEHR Reference Implementation"
 * description: "Class XComposition"
 * keywords:    "ehr_extract"
 *
 * author:      "Rong Chen <rong@acode.se>"
 * support:     "Acode HB <support@acode.se>"
 * copyright:   "Copyright (c) 2004,2005 Acode HB, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL$"
 * revision:    "$LastChangedRevision$"
 * last_change: "$LastChangedDate$"
 */
package org.openehr.rm.ehrextract;

import org.openehr.rm.datatypes.uri.DvEHRURI;
import org.openehr.rm.ehr.VersionedComposition;

/**
 * Container for Composition in extract. Indicates whether it was part of the
 * primary set and what it s original path was.
 *
 * @author Rong Chen
 * @version 1.0
 */
public class XComposition {

    /**
     * Constructs a XComposition
     *
     * @param primary
     * @param originalPath      not null
     * @param composition       not null
     * @throws IllegalArgumentException if originalPath null
     *                                  or composition null
     */
    public XComposition(boolean primary, DvEHRURI originalPath,
                   VersionedComposition composition) {
        if(originalPath == null) {
            throw new IllegalArgumentException("null originalPath");
        }
        if(composition == null) {
            throw new IllegalArgumentException("null composition");
        }
        this.primary = primary;
        this.originalPath = originalPath;
        this.composition = composition;
    }

    /**
     * True if the Composition in this container was part of the primary set
     * for the Extract , ie not added due to link-following
     *
     * @return primary
     */
    public boolean isPrimary() {
        return primary;
    }

    /**
     * The original path of the Composition in the source EHR, used for
     * matching compositions in the receiver's EHR.
     *
     * @return originalPath
     */
    public DvEHRURI getOriginalPath() {
        return originalPath;
    }

    /**
     * The composition content
     *
     * @return composition
     */
    public VersionedComposition getComposition() {
        return composition;
    }

    /* fields */
    private boolean primary;
    private DvEHRURI originalPath;
    private VersionedComposition composition;
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
 *  The Original Code is XComposition.java
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