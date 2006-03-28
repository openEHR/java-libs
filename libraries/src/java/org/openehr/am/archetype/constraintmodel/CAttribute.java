/*
 * component:   "openEHR Reference Implementation"
 * description: "Class CAttribute"
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
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.openehr.am.archetype.Archetype;
import org.openehr.rm.util.RMObjectBuilder;

import java.util.*;

/**
 * This class represents a constraint on any kind of attribute node.
 *
 * @author Rong Chen
 * @version 1.0
 */
public abstract class CAttribute extends ArchetypeConstraint {

    /**
     * enumeration of attribute existence
     */
    public enum Existence {
        REQUIRED, OPTIONAL, NOT_ALLOWED
    };

    /**
     * Constructs an AttributeConstraint
     *
     * @param path
     * @param rmAttributeName
     * @param existence
     * @param children        List<CObject>
     * @throws IllegalArgumentException if rmAttributeName empty
     *                                  or existence null or children null
     */
    public CAttribute(String path, String rmAttributeName,
                      Existence existence, List<CObject> children) {

        super(children == null, path);

        if (StringUtils.isEmpty(rmAttributeName)) {
            throw new IllegalArgumentException("rmTypeName null");
        }
        if (existence == null) {
            throw new IllegalArgumentException("existence null");
        }
        this.rmAttributeName = rmAttributeName;
        this.existence = existence;
        this.children = (children == null ? null
                : new ArrayList<CObject>(children));
    }

    /**
     * Reference model attribute within the enclosing type represented by
     * a object constraint
     *
     * @return reference model attribute name
     */
    public String getRmAttributeName() {
        return rmAttributeName;
    }

    /**
     * Existence of this attribute constaint
     *
     * @return existence
     */
    public Existence getExistence() {
        return existence;
    }

    /**
     * Return true if this attribute is required
     *
     * @return true if required
     */
    public boolean isRequired() {
        return Existence.REQUIRED.equals(existence);
    }

    /**
     * Return true if this attribute is required
     * or there is any submitted input value for its descendants
     *
     * @return true if required
     */
    public boolean isRequired(Set<String> paths) {
        if (isRequired()) {
            return true;
        }

        // if any submitted value for descendant nodes
        for (String path : paths) {
            if (path.startsWith(childNodePathBase())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Return true if this attribute is not allowed
     *
     * @return true if not allowed
     */
    public boolean isNotAllowed() {
        return Existence.NOT_ALLOWED.equals(existence);
    }

    /**
     * List of children object constraint
     *
     * @return unmodifialbe List<CObject> or null if not present
     */
    public List<CObject> getChildren() {
        return children == null ? null
                : Collections.unmodifiableList(children);
    }

    /**
     * Create attribute by given object map
     * <p/>
     * Meaning this RMObject is the term definition of the node id,
     * if name attribute is missing, meaning is used for name.
     *
     * @param objectMap path as key, object as value
     * @param errorMap  path as key, error as value
     * @param archetype
     * @param builder
     * @return RMObject constructed or null
     */
    public abstract Object createAttribute(Map<String, Object> objectMap,
                                           Set<String> inputPaths,
                                           Map<String, ErrorType> errorMap,
                                           Archetype archetype,
                                           RMObjectBuilder builder);

    /**
     * True if constraints represented by other are narrower than this node.
     *
     * @param constraint
     * @return true if subset
     * @throws IllegalArgumentException if constraint null
     */
    public abstract boolean isSubsetOf(ArchetypeConstraint constraint);

    /**
     * Return path of parent object node
     *
     * @return parent node path
     */
    public String parentNodePath() {

        // remove both "/."  and attribute name
        return path().substring(0,
                path().length() - rmAttributeName.length() - 2);
    }

    /**
     * Return path base for the children nodes
     *
     * @return child node path base
     */
    public String childNodePathBase() {
        return parentNodePath() + PATH_SEPARATOR + rmAttributeName;
    }

    /**
     * Two CAttributes equals if have same values
     *
     * @param o
     * @return true if equals
     */
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!( o instanceof CAttribute )) return false;

        final CAttribute cattr = (CAttribute) o;

        return new EqualsBuilder()
                .appendSuper(super.equals(o))
                .append(rmAttributeName, cattr.rmAttributeName)
                .append(existence, cattr.existence)
                .append(children, cattr.children)
                .isEquals();
    }

    /**
     * Return a hash code of this object
     *
     * @return hash code
     */
    public int hashCode() {
        return new HashCodeBuilder(7, 19)
                .appendSuper(super.hashCode())
                .append(rmAttributeName)
                .append(existence)
                .append(children)
                .toHashCode();
    }


    /* fields */
    private final String rmAttributeName;
    private final Existence existence;
    final List<CObject> children;
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
 *  The Original Code is CAttribute.java
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