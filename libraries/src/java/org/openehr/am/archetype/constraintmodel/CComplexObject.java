/*
 * component:   "openEHR Reference Implementation"
 * description: "Class CComplexObject"
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

import org.openehr.am.archetype.ontology.DefinitionItem;
import org.openehr.am.archetype.Archetype;
import org.openehr.rm.RMObject;
import org.openehr.rm.common.archetyped.Archetyped;
import org.openehr.rm.datatypes.text.CodePhrase;
import org.openehr.rm.datatypes.text.DvText;
import org.openehr.rm.support.basic.Interval;
import org.openehr.rm.support.terminology.TerminologyService;
import org.openehr.rm.util.*;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.log4j.Logger;

import java.util.*;

/**
 * Constraint on complex objects, ie any object which consists of other object
 * constraints. Instances of this class are immutable.
 *
 * @author Rong Chen
 * @version 1.0
 */
public final class CComplexObject extends CObject {

    /**
     * Constructs a complexObjectConstraint
     *
     * @param path
     * @param rmTypeName
     * @param occurrences
     * @param nodeID
     * @param attributes
     * @param invariants
     */
    public CComplexObject(String path, String rmTypeName,
                          Interval<Integer> occurrences,
                          String nodeID, List<CAttribute> attributes,
                          Set<Object> invariants) {

        super(attributes == null, path, rmTypeName, occurrences, nodeID);

        if (attributes != null && attributes.isEmpty()) {
            throw new IllegalArgumentException("empty attributes");
        }
        if (invariants != null && invariants.isEmpty()) {
            throw new IllegalArgumentException("empty invariants");
        }
        this.attributes = ( attributes == null
                ? null : new ArrayList<CAttribute>(attributes) );
        this.invariants =
                ( invariants == null ? null : new HashSet<Object>(invariants) );
    }

    /**
     * List of constraints on attributes of the reference model type
     * represented by this object.
     *
     * @return Unmodifiable List<CAttribute>, null if allow any
     */
    public List<CAttribute> getAttributes() {
        return attributes == null ?
                null : Collections.unmodifiableList(attributes);
    }

    /**
     * Invariant statements about this object. Statements are expressed in
     * first order predicate logic, and usually refer to at least two
     * attributes.
     *
     * @return invariants
     */
    public Set<Object> getInvariants() {
        return invariants == null ?
                null : Collections.unmodifiableSet(invariants);
    }

    /**
     * True if this node is a valid archetype node.
     *
     * @return ture if valid
     */
    public boolean isValid() {
        if (attributes == null) {
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
        return false; // todo fix it
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
     * Create RMObject by given object map
     * <p/>
     * Meaning this RMObject is the term definition of the node id,
     * if name attribute is missing, meaning is used for name.
     *
     * @param objectMap        path as key, object as value
     * @param errorMap         path as key, error as value
     * @param archetype
     * @param builder
     * @param archetypeDetails null if not archetype root
     * @return RMObject constructed or null
     */
    public Object createObject(Map<String, Object> objectMap,
                               Set<String> inputPaths,
                               Map<String, ErrorType> errorMap,
                               Archetype archetype,
                               RMObjectBuilder builder,
                               Archetyped archetypeDetails) {

        logger.debug("creating complex object of type " + getRmTypeName()
                + " at " + path());

        if (!isRequired(inputPaths)) {

            logger.debug("optional object node skipped..");

            return null;
        }

        // start object creation
        Map<String, Object> valueMap = new HashMap<String, Object>();

        // loop through all attributes
        for (CAttribute cattribute : attributes) {
            if (cattribute.isNotAllowed()) {
                continue;
            }
            Object value = objectMap.get(cattribute.path());

            // has not yet created
            if (value == null) {

                // optional attribute
                if (!cattribute.isRequired(inputPaths)) {
                    continue; // skip creation of descendant nodes
                }

                // create this attribute
                value = cattribute.createAttribute(objectMap, inputPaths,
                        errorMap, archetype, builder);
            }
            if (value != null) {
                valueMap.put(cattribute.getRmAttributeName(), value);
            }
        }

        // fix nodeId and name
        CodePhrase lang = (CodePhrase)
                builder.getSystemValue(SystemValue.LANGUAGE);
        CodePhrase charset = (CodePhrase)
                builder.getSystemValue(SystemValue.CHARSET);
        TerminologyService ts = (TerminologyService) builder.getSystemValue(
                SystemValue.TERMINOLOGY_SERVICE);
        DefinitionItem item = archetype.getOntology().definition(
                lang.getCodeString(), getNodeID());

        if (item != null) {
            DvText name = new DvText(item.getText(), lang, charset, ts);
            valueMap.put("archetypeNodeId", getNodeID());
            if (!valueMap.containsKey("name")) {
                valueMap.put("name", name);
            }
        }

        if (archetypeDetails != null) {
            valueMap.put("archetypeDetails", archetypeDetails);
        }

        valueMap.put("uid", builder.getSystemValue(SystemValue.UID));

        // construct
        RMObject ret = null;
        try {
            ret = builder.construct(getRmTypeName(), valueMap);
        } catch (RMObjectBuildingException e) {

            logger.warn("failed to build complex object node", e);

            // make sure same error doesn't raise more than one report
            boolean reported = false;
            for (String path : errorMap.keySet()) {

                logger.debug("within error map: " + path);

                if (path.startsWith(path())) {
                    reported = true;
                }
            }
            if (!reported) {
                errorMap.put(path(), e.getErrorType());
            }
        }
        return ret;
    }

    /**
     * Equals if two CComplexObject have same values
     *
     * @param o
     * @return true if equals
     */
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!( o instanceof CComplexObject )) return false;

        final CComplexObject ccobj = (CComplexObject) o;

        return new EqualsBuilder()
                .appendSuper(super.equals(o))
                .append(attributes, ccobj.attributes)
                .append(invariants, ccobj.invariants)
                .isEquals();
    }

    /**
     * Return a hash code of this object
     *
     * @return hash code
     */
    public int hashCode() {
        return new HashCodeBuilder(13, 29)
                .appendSuper(super.hashCode())
                .append(attributes)
                .append(invariants)
                .toHashCode();
    }

    /* fields */
    private final List<CAttribute> attributes;
    private final Set<Object> invariants;  // Set<Assertion>

    /* static field */
    private Logger logger = Logger.getLogger(CComplexObject.class);
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
 *  The Original Code is CComplexObject.java
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