/*
 * component:   "openEHR Reference Implementation"
 * description: "Class ArchetypeInternalRef"
 * keywords:    "archetype"
 *
 * author:      "Rong Chen <rong@acode.se>"
 * support:     "Acode HB <support@acode.se>"
 * copyright:   "Copyright (c) 2004 Acode HB, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/TRUNK/libraries/src/java/org/openehr/am/archetype/constraintmodel/ArchetypeInternalRef.java $"
 * revision:    "$LastChangedRevision: 2 $"
 * last_change: "$LastChangedDate: 2005-10-12 23:20:08 +0200 (Wed, 12 Oct 2005) $"
 */
package org.openehr.am.archetype.constraintmodel;

import org.apache.commons.lang.StringUtils;
import org.openehr.rm.support.basic.Interval;

/**
 * A constraint defined by proxy, using a reference to an object constraint
 * defined elsewhere in the same archetype.
 *
 * @author Rong Chen
 * @version 1.0
 */
public class ArchetypeInternalRef extends CObject {
    
	/**
     * Creates an ObjectConstraint
     *
     * @param path
     * @param rmTypeName
     * @param occurrences null indicates {1..1}
     * @param nodeID
     * @param parent
     * @param targetPath not null or empty
     * @throws IllegalArgumentException if rmTypeName null or empty,
     *                                  or nodeID null or empty
     */
    public ArchetypeInternalRef(String path, String rmTypeName,
                                Interval<Integer> occurrences, String nodeID,
                                CAttribute parent, String targetPath) {
        
    	super(false, path, rmTypeName, occurrences, nodeID, parent);        
    	
    	if (StringUtils.isEmpty(targetPath)) {
            throw new IllegalArgumentException("null or emtpy targetPath");
        }
        // TODO: and then ultimate_root.has_path(target_path)
        this.targetPath = targetPath;
    }

    /**
     * Reference to an object node using archetype path notation.
     *
     * @return target path
     */
    public String getTargetPath() {
        return targetPath;
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

    /* fields */
    private String targetPath;

    /* static fields */
    public static final String USE_NODE = " use_node ";
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
 *  The Original Code is ArchetypeInternalRef.java
 *
 *  The Initial Developer of the Original Code is Rong Chen.
 *  Portions created by the Initial Developer are Copyright (C) 2003-2006
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