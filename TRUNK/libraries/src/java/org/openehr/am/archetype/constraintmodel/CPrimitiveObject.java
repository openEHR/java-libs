/*
 * component:   "openEHR Reference Implementation"
 * description: "Class CPrimitiveObject"
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

import org.openehr.am.archetype.constraintmodel.primitive.CPrimitive;
import org.openehr.am.archetype.ontology.ArchetypeOntology;
import org.openehr.am.archetype.Archetype;
import org.openehr.rm.util.SystemValue;
import org.openehr.rm.util.RMObjectBuilder;
import org.openehr.rm.common.archetyped.Archetyped;
import org.apache.log4j.Logger;

import java.util.Map;
import java.util.Set;

/**
 * PrimitiveObjectConstraint
 *
 * @author Rong Chen
 * @version 1.0
 */
public class CPrimitiveObject extends LeafConstraint {

    /**
     * Return true if the constraint has limit the possible value to
     * be only one, which means the value has been assigned by the archetype
     * author at design time
     *
     * @return true if has
     */
    public boolean hasAssignedValue() {
        return item.hasAssignedValue();
    }

    /**
     * Return assigned value as data value instance
     *
     * @param systemValues
     * @return object or null if not assigned
     */
    public Object assignedValue(Map<SystemValue, Object> systemValues,
                                ArchetypeOntology ontology) {
        return item.assignedValue();
    }

    /**
     * Constructs a PrimitiveObjectConstraint
     *
     * @param path
     * @param item
     * @throws IllegalArgumentException if item null
     */
    public CPrimitiveObject(String path, CPrimitive item) {

        super(false, path, item.getType());

        if (item == null) {
            throw new IllegalArgumentException("null item");
        }
        this.item = item;
    }

    /**
     * Object actually defining the constraint.
     *
     * @return primitive constraint
     */
    public CPrimitive getItem() {
        return item;
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
     * Create an datavalue from a string value
     *
     * @param value
     * @param systemValues
     * @return a DataValue
     */
    public Object createObject(String value,
                               Map<SystemValue, Object> systemValues,
                               ArchetypeOntology ontology)
            throws DVObjectCreationException {

        return item.createObject(value);
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

        logger.debug("fetching value of primitve type " + item.getType());

        return objectMap.get(path());
    }

    /* fields */
    private CPrimitive item;

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
 *  The Original Code is CPrimitiveObject.java
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