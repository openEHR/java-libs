/*
 * component:   "openEHR Reference Implementation"
 * description: "Class ObjectRef"
 * keywords:    "common"
 *
 * author:      "Rong Chen <rong@acode.se>"
 * support:     "Acode HB <support@acode.se>"
 * copyright:   "Copyright (c) 2004 Acode HB, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/TRUNK/libraries/src/java/org/openehr/rm/support/identification/ObjectRef.java $"
 * revision:    "$LastChangedRevision: 2 $"
 * last_change: "$LastChangedDate: 2005-10-12 22:20:08 +0100 (Wed, 12 Oct 2005) $"
 */
package org.openehr.rm.support.identification;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.openehr.rm.Attribute;
import org.openehr.rm.FullConstructor;
import org.openehr.rm.RMObject;

/**
 * Class describing a reference to another object, which may exist
 * locally or be maintained outside the current namespace, eg in
 * another service. Services are usually external, eg available in
 * a LAN (including on the same host) or the internet via Corba, SOAP,
 * or some other distributed protocol. However, in small systems they
 * may be part of the same executable as the data containing the Id.
 *
 * @author Rong Chen
 * @version 1.0
 */
public class ObjectRef extends RMObject {

    

    /**
     * Constructs an ObjectRef by id, namespace and type
     *
     * @param id        not null
     * @param namespace not null or empty
     * @param type      not null or empty
     * @throws IllegalArgumentException if any invalid parameter
     */
    @FullConstructor
    public ObjectRef(
    		@Attribute(name = "id", required = true)ObjectID id, 
    		@Attribute(name = "namespace", required = true)String namespace,
    		@Attribute(name = "type", required = true)String type) {
        if (id == null) {
            throw new IllegalArgumentException("null id");
        }
        if (namespace == null) {
            throw new IllegalArgumentException("null namespace");
        }
        if (type == null) {
            throw new IllegalArgumentException("null type");
        }
        this.id = id;
        this.namespace = namespace;
        this.type = type;
    }

    /**
     * Globally unique id of an object, regardless of where
     * it is stored
     *
     * @return ID
     */
    public ObjectID getId() {
        return id;
    }

    /**
     * Namespace to which this identifier belongs in the local system
     * context (and possibly in any other openEHR compliant
     * environment) eg "terminology", "demographic".
     * These names are not yet standardised. Legal values for the
     * namespace are "local" | "unknown" |
     * "[a-zAZ][ a-zA-Z0-9_-:/&+?]*"
     *
     * @return namespace
     */
    public String getNamespace() {
        return namespace;
    }

    /**
     * Name of the class of object to which this identifier type
     * refers, eg "PARTY", "PERSON", "GUIDELINE" etc. These class
     * names are from the relevant reference model. The type name
     * "ANY" can be used to indicate that any type is accepted
     * (eg if the type is unknown).
     *
     * @return type
     */
    public String getType() {
        return type;
    }

    /**
     * Two ObjectReferences equals if they have same values for id,
     * namespace and type.
     *
     * @param o
     * @return true if equals
     */
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!( o instanceof ObjectRef )) return false;

        final ObjectRef objRef = (ObjectRef) o;

        return new EqualsBuilder()
                .append(id, objRef.id)
                .append(namespace, objRef.namespace)
                .append(type, objRef.type)
                .isEquals();
    }

    /**
     * Return a hash code of this object reference
     *
     * @return hash code
     */
    public int hashCode() {
        return new HashCodeBuilder()
                .append(id)
                .append(namespace)
                .append(type)
                .toHashCode();
    }

    /**
     * Return string presentation of this ObjectRef
     *
     * @return string presentation
     */
    public String toString() {
        return new StringBuffer()
                .append("id: ")
                .append(id)
                .append(", namespace: ")
                .append(namespace)
                .append(", type: ")
                .append(type)
                .toString();
    }

    // POJO start
    ObjectRef() {
    }

    private void setOid(ObjectID id) {
        this.id = id;
    }

    private ObjectID getOid() {
        return id;
    }

    String getNamespaceString() {
        return namespace.toString();
    }

    String getTypeString() {
        return type.toString();
    }

    void setNamespaceString(String namespace) {
        this.namespace = namespace;
    }

    void setTypeString(String type) {
        this.type = type;
    }
    // POJO end

    /* fields */
    private ObjectID id;
    private String namespace;
    private String type;
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
 *  The Original Code is ObjectRef.java
 *
 *  The Initial Developer of the Original Code is Rong Chen.
 *  Portions created by the Initial Developer are Copyright (C) 2003-2008
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