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
 * file:        "$URL$"
 * revision:    "$LastChangedRevision$"
 * last_change: "$LastChangedDate$"
 */
package org.openehr.am.archetype.constraintmodel;

import org.apache.commons.lang.StringUtils;
import org.openehr.am.archetype.Archetype;
import org.openehr.rm.util.RMObjectBuilder;
import org.openehr.rm.common.archetyped.Archetyped;
import org.openehr.rm.support.basic.Interval;

import java.util.Map;
import java.util.HashMap;
import java.util.Set;

/**
 * A constraint defined by proxy, using a reference to an object constraint
 * defined elsewhere in the same archetype.
 *
 * @author Rong Chen
 * @version 1.0
 */
public class ArchetypeInternalRef extends CObject {
    /**
     * Construct an ArchetypeInternalRef
     *
     * @param path
     * @param rmTypeName
     * @param targetPath
     * @throws IllegalArgumentException if targetpath null or empty
     */
    public ArchetypeInternalRef(String path, String rmTypeName,
                                String targetPath) {
        super(false, path, rmTypeName);

        if (StringUtils.isEmpty(targetPath)) {
            throw new IllegalArgumentException("null or emtpy targetPath");
        }
        // todo: and then ultimate_root.has_path(target_path)
        this.targetPath = targetPath;
    }

    /**
     * Creates an ObjectConstraint
     *
     * @param path
     * @param rmTypeName
     * @param occurrences null indicates {1..1}
     * @param nodeID
     * @param targetPath
     * @throws IllegalArgumentException if rmTypeName null or empty,
     *                                  or nodeID null or empty
     */
    public ArchetypeInternalRef(String path, String rmTypeName,
                                Interval<Integer> occurrences, String nodeID,
                                String targetPath) {
        super(false, path, rmTypeName, occurrences, nodeID);
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

    /**
     * Create an object based on this object constraint
     *
     * @param objectMap
     * @param errorMap
     * @param archetype
     * @param builder
     * @param archetypeDetails
     * @return null if this node is optional
     */
    public Object createObject(Map<String, Object> objectMap,
                               Set<String> inputPaths,
                               Map<String, ErrorType> errorMap,
                               Archetype archetype,
                               RMObjectBuilder builder,
                               Archetyped archetypeDetails) {

        if (!isRequired(objectMap.keySet())) {
            return null;
        }

        // select values for the reference node
        Map<String, Object> map = new HashMap<String,Object>();
        map.putAll(objectMap);
        for(String path : map.keySet()) {
            int index = path.indexOf(USE_NODE);
            if(path.startsWith(path() + USE_NODE)) {
                String refpath = path.substring(index + USE_NODE.length());
                map.put(refpath, objectMap.get(path));
            }
        }
        return archetype.node(targetPath).createObject(map, inputPaths,
                errorMap, archetype, builder, archetypeDetails);
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