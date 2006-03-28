/*
 * component:   "openEHR Reference Implementation"
 * description: "Class VersionRepository"
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
package org.openehr.rm.common.changecontrol;

import org.openehr.rm.RMObject;
import org.openehr.rm.support.identification.ObjectID;
import org.openehr.rm.support.identification.ObjectReference;
import org.openehr.rm.datatypes.basic.DvState;
import org.openehr.rm.datatypes.quantity.datetime.DvDateTime;
import org.openehr.rm.support.terminology.TerminologyService;

import java.util.*;

/**
 * Version control abstraction, defining semantics for versioning one
 * complex object.
 *
 * @author Rong Chen
 * @version 1.0
 */
public class VersionRepository <T> extends RMObject {

    /**
     * Constructs a VersionRepository with first version
     *
     * @param uid     not null
     * @param ownerID not null
     * @throws IllegalArgumentException
     */
    public VersionRepository(ObjectID uid, ObjectReference ownerID,
                             AuditDetails audit, T data,
                             ObjectReference contribution,
                             DvState lifecycleState,
                             TerminologyService termServ,
                             ObjectReference.Namespace namespace,
                             ObjectReference.Type type) {
        if (uid == null) {
            throw new IllegalArgumentException("null uid");
        }
        if (ownerID == null) {
            throw new IllegalArgumentException("null ownerID");
        }
        this.uid = uid;
        this.ownerID = ownerID;
        idCounter = 0;
        timeCreated = new DvDateTime(); // now
        timeVersionMap = new TreeMap<DvDateTime, Version<T>>();
        idVersionMap = new TreeMap<String, Version<T>>();
        commit(audit, data, contribution, lifecycleState, termServ,
                namespace, type);
    }

    /**
     * Unique identifier of this version repository.
     *
     * @return UID
     */
    public ObjectID getUid() {
        return uid;
    }

    /**
     * Reference to object to which this versioned repository belongs,
     * eg the id of the containing EHR.
     *
     * @return OwnerID
     */
    public ObjectReference getOwnerID() {
        return ownerID;
    }

    /**
     * Time of initial creation of this versioned object.
     *
     * @return time of creation
     */
    public DvDateTime getTimeCreated() {
        return timeCreated;
    }

    /**
     * Return a list of all versions in this object.
     *
     * @return all versions
     */
    public synchronized List<Version<T>> allVersions() {
        // todo: fix the order of this list
        return new ArrayList<Version<T>>(idVersionMap.values());
    }

    /**
     * Return a list of ids of all versions in this object.
     *
     * @return List<String>
     */
    public synchronized List<String> allVersionIDs() {
        // todo: fix the order of list
        return new ArrayList<String>(idVersionMap.keySet());
    }

    /**
     * Return the total number of versions in this object
     *
     * @return version count
     */
    public synchronized int versionCount() {
        return idVersionMap.size();
    }

    /**
     * True if a version with given id exists.
     *
     * @param id
     * @return true if has
     * @throws IllegalArgumentException
     */
    public synchronized boolean hasVersionID(String id) {
        if (id == null) {
            throw new IllegalArgumentException("null id");
        }
        return idVersionMap.containsKey(id);

    }

    /**
     * True if a version for given time exists.
     *
     * @param time
     * @return true if has version
     * @throws IllegalArgumentException if time null
     */
    public synchronized boolean hasVersionAtTime(DvDateTime time) {
        if (time == null) {
            throw new IllegalArgumentException("null time");
        }
        return timeVersionMap.containsKey(time);
    }

    /**
     * Return the version with given id
     *
     * @param id
     * @return null if not found
     * @throws IllegalArgumentException if id null
     */
    public synchronized Version<T> versionWithID(String id) {
        if (id == null) {
            throw new IllegalArgumentException("null id");
        }
        return idVersionMap.get(id);
    }

    /**
     * Return the version for given time
     *
     * @param time
     * @return null if not found
     * @throws IllegalArgumentException if time null
     */
    public synchronized Version<T> versionAtTime(DvDateTime time) {
        if (time == null) {
            throw new IllegalArgumentException("null time");
        }
        return timeVersionMap.get(time);
    }

    /**
     * Return the latest version.
     *
     * @return lastest version
     */
    public synchronized Version<T> latestVersion() {
        return (Version<T>) timeVersionMap.get(timeVersionMap.lastKey());

    }

    /**
     * Return the latest version.
     *
     * @return lastest version
     */
    public synchronized Version<T> firstVersion() {
        return (Version<T>) idVersionMap.get(FIRST);

    }

    /**
     * Commit a new version as draft version
     *
     * @param audit
     * @param data
     * @param contribution
     * @param lifecycleState
     * @param namespace
     * @param type
     */
    public synchronized void commit(AuditDetails audit, T data,
                                    ObjectReference contribution,
                                    DvState lifecycleState,
                                    TerminologyService termServ,
                                    ObjectReference.Namespace namespace,
                                    ObjectReference.Type type) {
        String preceedingVersionID = Integer.toString(idCounter);
        idCounter++;
        String currentVersionID = Integer.toString(idCounter);
        ObjectReference repositoryRef = new ObjectReference(uid,
                namespace, type);
        Version<T> version = new Version<T>(data, null, audit,
                currentVersionID, preceedingVersionID,
                repositoryRef, contribution, lifecycleState, termServ);
        timeVersionMap.put(version.getAudit().getTimeCommitted(), version);
        idVersionMap.put(version.getVersionID(), version);
    }

    // POJO start
    private Long id;

    protected Long getId() {
        return id;
    }

    protected void setId(Long id) {
        this.id = id;
    }

    protected VersionRepository() {
    }

    void setUid(ObjectID uid) {
        this.uid = uid;
    }

    void setOwnerID(ObjectReference ownerID) {
        this.ownerID = ownerID;
    }

    void setTimeCreated(DvDateTime timeCreated) {
        this.timeCreated = timeCreated;
    }

    // in order to skip map timeVersionMap to table
    void setVersions(Set<Version<T>> versions) {
        idVersionMap = new TreeMap<String, Version<T>>();
        timeVersionMap = new TreeMap<DvDateTime, Version<T>>();
        for(Version<T> version : versions) {
            timeVersionMap.put(version.getAudit().getTimeCommitted(), version);
            idVersionMap.put(version.getVersionID(), version);
        }
        idCounter = Integer.parseInt(idVersionMap.lastKey());
    }

    // required to map bidirectional one-to-many relationship
    Set<Version<T>> getVersions() {
        return new HashSet<Version<T>>(timeVersionMap.values());
    }
    // POJO end

    /**
     * version id for the version before the first version
     */
    public static final String NONE = "0";
    public static final String FIRST = "1";

    /* fields */
    private ObjectID uid;
    private ObjectReference ownerID;
    private DvDateTime timeCreated;
    private SortedMap<DvDateTime, Version<T>> timeVersionMap;
    private SortedMap<String, Version<T>> idVersionMap;
    private int idCounter;
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
 *  The Original Code is VersionRepository.java
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