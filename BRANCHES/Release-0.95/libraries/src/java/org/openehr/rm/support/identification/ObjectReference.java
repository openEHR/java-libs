/*
 * component:   "openEHR Reference Implementation"
 * description: "Class ObjectReference"
 * keywords:    "common"
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
package org.openehr.rm.support.identification;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
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
public class ObjectReference extends RMObject {

    /**
     * List of known object reference types
     */
    public enum Type {
        PARTY,
        ACCESS_GROUP,
        CONTRIBUTION,
        VERSION,
        VERSIONED_COMPOSITION,
        VERSIONED_PARTY,                
        VERSION_REPOSITORY,
        EHR,
        COMPOSITION,
        FOLDER,
        ARCHETYPE,
        GUIDELINE,
        UNKNOWN        
    }

    /**
     * List of known object reference namespaces
     */
    public enum Namespace {
        LOCAL,
        UNKNOWN,
        ACCESS_CONTROL,
        DEMOGRAPHIC
    }

    /**
     * Constructs an ObjectReference by id, namespace and type
     *
     * @param id        not null
     * @param namespace not null or empty
     * @param type      not null or empty
     * @throws IllegalArgumentException if any invalid parameter
     */
    public ObjectReference(ObjectID id, Namespace namespace,
                           Type type) {
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
    public Namespace getNamespace() {
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
    public Type getType() {
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
        if (!( o instanceof ObjectReference )) return false;

        final ObjectReference objRef = (ObjectReference) o;

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
     * Return string presentation of this ObjectReference
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
    ObjectReference() {
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
        this.namespace = Namespace.valueOf(namespace);
    }

    void setTypeString(String type) {
        this.type = Type.valueOf(type);
    }
    // POJO end

    /* fields */
    private ObjectID id;
    private Namespace namespace;
    private Type type;
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
 *  The Original Code is ObjectReference.java
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