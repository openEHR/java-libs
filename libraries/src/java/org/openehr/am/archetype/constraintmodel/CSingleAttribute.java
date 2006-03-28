/*
 * component:   "openEHR Reference Implementation"
 * description: "Class CSingleAttribute"
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

import org.openehr.am.archetype.Archetype;
import org.openehr.rm.util.RMObjectBuilder;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Concrete model of constraint on a single-valued attribute node. The meaning
 * of the inherited children attribute is that they are alternatives.
 * Instances of this class are immutable.
 *
 * @author Rong Chen
 * @version 1.0
 */
public final class CSingleAttribute extends CAttribute {

    /**
     * Create a constraint for single-valued attribute node
     *
     * @param path
     * @param rmAttributeName
     * @param existence
     * @param alternatives    null if allow any
     */
    public CSingleAttribute(String path, String rmAttributeName,
                            Existence existence, List<CObject> alternatives) {
        super(path, rmAttributeName, existence, alternatives);
    }

    /**
     * List of alternative constraints for the single child of this attribute
     * within the data.
     *
     * @return unmodifiable list of alternatives, null if anyAllowed
     */
    public List<CObject> alternatives() {
        return getChildren();
    }

    /**
     * True if this node is a valid archetype node.
     *
     * @return true if valid
     */
    public boolean isValid() {
        if (isAnyAllowed()) {
            return true;
        }
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
     * Create attribute by given value map
     * <p/>
     * Meaning this RMObject is the term definition of the node id,
     * if name attribute is missing, meaning is used for name.
     *
     * @param objectMap path as key, object as value
     * @param errorMap  path as key, error as value
     * @param archetype
     * @param builder
     * @return attribute constructed or null
     */
    public Object createAttribute(Map<String, Object> objectMap,
                                  Set<String> inputPaths,
                                  Map<String, ErrorType> errorMap,
                                  Archetype archetype,
                                  RMObjectBuilder builder) {

        logger.debug("creating single attribute " + getRmAttributeName());

        for (CObject cobj : children) {
            Object value = cobj.createObject(objectMap, inputPaths, errorMap,
                    archetype, builder, null);

            if (value != null) {

                logger.debug("single attribute " + getRmAttributeName()
                        + " successfuly created..");

                return value;
            }
        }

        logger.warn("failed to create single attribute "
                + getRmAttributeName());

        return null; // failed to create this attribute
    }

    /* static fields */
    private Logger logger = Logger.getLogger(Archetype.class);
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
 *  The Original Code is CSingleAttribute.java
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