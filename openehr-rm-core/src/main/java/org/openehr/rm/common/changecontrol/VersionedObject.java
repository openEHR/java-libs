/*
 * component:   "openEHR Reference Implementation"
 * description: "Class VersionedObject"
 * keywords:    "common"
 *
 * author:      "Yin Su Lim <y.lim@chime.ucl.ac.uk>"
 * support:     "CHIME, UCL"
 * copyright:   "Copyright (c) 2006 UCL, UK"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/TRUNK/libraries/src/java/org/openehr/rm/common/changecontrol/VersionedObject.java $"
 * revision:    "$LastChangedRevision: 53 $"
 * last_change: "$LastChangedDate: 2006-08-11 13:20:08 +0100 (Fri, 11 Aug 2006) $"
 */
package org.openehr.rm.common.changecontrol;

import java.util.*;

import org.openehr.rm.RMObject;
import org.openehr.rm.common.generic.Attestation;
import org.openehr.rm.common.generic.AuditDetails;
import org.openehr.rm.common.generic.RevisionHistory;
import org.openehr.rm.common.generic.RevisionHistoryItem;
import org.openehr.rm.datatypes.quantity.datetime.DvDateTime;
import org.openehr.rm.datatypes.text.DvCodedText;
import org.openehr.rm.support.identification.HierObjectID;
import org.openehr.rm.support.identification.ObjectRef;
import org.openehr.rm.support.identification.ObjectVersionID;
import org.openehr.rm.support.terminology.TerminologyService;

/**
 * @author yinsulim
 *
 */
/**
 * @author yinsulim
 *
 * @param <T>
 */
public class VersionedObject<T> extends RMObject {

    /**
     * Constructs a VersionObject with first originalVersion
     */
    public VersionedObject(HierObjectID uid, ObjectRef ownerID,
            DvDateTime timeCreated, ObjectVersionID versionID, T data,
            DvCodedText lifecycleState, AuditDetails commitAudit,
            ObjectRef contribution, String signature,
            TerminologyService terminologyService) {
        
        this(uid, ownerID, timeCreated);
        commitOriginalVersion(versionID, null, data, commitAudit, contribution,
                lifecycleState, signature, terminologyService);
    }
    
    /**
     * Constructs a VersionObject with first importedVersion
     */
    public VersionedObject(HierObjectID uid, ObjectRef ownerID,
            DvDateTime timeCreated, OriginalVersion<T> item, AuditDetails commitAudit,
            ObjectRef contribution, String signature) {
        this(uid, ownerID, timeCreated);
        commitImportedVersion(item, commitAudit, contribution, signature);
    }
    
    /**
     * Constructs a VersionObject with first merged Version
     */
    public VersionedObject(HierObjectID uid, ObjectRef ownerID,
            DvDateTime timeCreated, ObjectVersionID versionID,
            ObjectVersionID precedingVersionID, T data, DvCodedText lifecycleState,
            AuditDetails commitAudit, ObjectRef contribution, 
            Set<ObjectVersionID> otherInputVersionUids, String signature, 
            TerminologyService terminologyService) {
        this(uid, ownerID, timeCreated);
        commitOriginalMergedVersion(versionID, precedingVersionID, data, lifecycleState, 
                commitAudit, contribution, otherInputVersionUids, signature,
                terminologyService);
    }
    
    private VersionedObject(HierObjectID uid, ObjectRef ownerID,
            DvDateTime timeCreated) {
        if (uid == null) {
            throw new IllegalArgumentException("null uid");
        }
        if (ownerID == null) {
            throw new IllegalArgumentException("null ownerID");
        }
        if (timeCreated == null) {
            throw new IllegalArgumentException("null timeCreated");
        }
        this.uid = uid;
        this.ownerID = ownerID;
        this.timeCreated = timeCreated;
        timeVersionMap = new TreeMap<DvDateTime, Version<T>>();
        idVersionMap = new HashMap<ObjectVersionID, Version<T>>();
    }
    
    /**
     * Add a new ImportedVersion
     *
     * @param commitAudit
     * @param contribution
     * @param item
     */
    public synchronized void commitImportedVersion(OriginalVersion<T> item, AuditDetails commitAudit, 
            ObjectRef contribution, String signature) {

        commitVersionCheck(item.getUid(), item.getPrecedingVersionUid());
        Version<T> version = new ImportedVersion<T>(item, commitAudit, contribution, signature);
        addVersion(version);
    }

    private synchronized void commitVersionCheck(ObjectVersionID vUid, ObjectVersionID precedingVUid) {
        if (versionCount() > 0 && !hasVersionID(precedingVUid)) {
            throw new IllegalArgumentException("precedingVersionID not found");
        }
        if(!vUid.objectID().equals(this.uid.root())) {
            throw new IllegalArgumentException("ownerID different from versionedObject");
        }        
    }
    
    private synchronized void addVersion(Version<T> version) {
        if (!version.getUid().versionTreeID().isBranch()) {
            int trunkNo = Integer.parseInt(version.getUid().versionTreeID().trunkVersion());
            if (trunkNo != trunkCounter + 1) {
                throw new IllegalArgumentException("invlalid trunk no in uid");
            } else {
                trunkCounter++;
                latestTrunkUid = version.getUid();
            }
        }
        timeVersionMap.put(version.getCommitAudit().getTimeCommitted(), version);
        idVersionMap.put(version.getUid(), version);
    }
    
    /**
     * Add a new original Version
     * @param versionID
     * @param precedingVersionID
     * @param data
     * @param commitAudit
     * @param contribution
     * @param lifecycleState
     * @param terminologyService
     */
    public synchronized void commitOriginalVersion(ObjectVersionID versionID,
            ObjectVersionID precedingVersionID, T data, AuditDetails commitAudit,
            ObjectRef contribution, DvCodedText lifecycleState, String signature, 
            TerminologyService terminologyService) {
        
        commitVersionCheck(versionID, precedingVersionID);
        Version<T> version = new OriginalVersion<T>(versionID, precedingVersionID,
                data, lifecycleState, commitAudit, contribution, signature, null, null, 
                //false,  
                terminologyService);
        addVersion(version);
    }
    
    /**
     * Add a new original MergedVersion
     * @param versionID
     * @param precedingVersionID
     * @param data
     * @param commitAudit
     * @param contribution
     * @param lifecycleState
     * @param terminologyService
     */
    public synchronized void commitOriginalMergedVersion(ObjectVersionID versionID,
            ObjectVersionID precedingVersionID, T data, DvCodedText lifecycleState, 
            AuditDetails commitAudit, ObjectRef contribution,
            Set<ObjectVersionID> otherInputVersionUids, String signature,
            TerminologyService terminologyService) {
        
        commitVersionCheck(versionID, precedingVersionID);
        Version<T> version = new OriginalVersion<T>(versionID, precedingVersionID,
                data, lifecycleState, commitAudit, contribution, signature, otherInputVersionUids, 
                null, 
                // true, 
                terminologyService);
        addVersion(version);
    }
    
    /**
     * Unique identifier of this version repository.
     *
     * @return UID
     */
    public HierObjectID getUid() {
        return uid;
    }
    
    /**
     * Reference to object to which this versioned repository belongs,
     * eg the id of the containing EHR.
     *
     * @return OwnerID
     */
    public ObjectRef getOwnerID() {
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
    public synchronized List<ObjectVersionID> allVersionIDs() {
        // todo: fix the order of list
        return new ArrayList<ObjectVersionID>(idVersionMap.keySet());
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
    public synchronized boolean hasVersionID(ObjectVersionID id) {
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
     * True if version with an id is an OriginalVersion
     *
     * @param uid
     */
    public boolean isOriginalVersion(ObjectVersionID uid) {
        if (!idVersionMap.containsKey(uid)) {
            throw new IllegalArgumentException("versionID not found");
        }
        Version<T> version = idVersionMap.get(uid);
        return version instanceof OriginalVersion;
    }   
    
    /**
     * Return the version with given id
     *
     * @param id
     * @return null if not found
     * @throws IllegalArgumentException if id null
     */
    public synchronized Version<T> versionWithID(ObjectVersionID id) {
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
     * Return the most recetly added trunk version
     *
     */
    public synchronized Version<T> latestTrunkVersion() {
        return (Version<T>) idVersionMap.get(latestTrunkUid);
        
    }
    
    /**
     * Return the lifecycle state from the latest trunk version.
     *
     */
    public DvCodedText latestTrunkLifeCycleSate() {
        Version<T> trunk = latestTrunkVersion();
        return latestTrunkVersion().getLifecycleState();
    }
    
    /**
     * History of all audits and attestations in this versioned repository
     *
     * @return revisionHistory
     */
    public RevisionHistory revisionHistory() {
        ArrayList<Version<T>> versions = new ArrayList<Version<T>> (timeVersionMap.values());
        ArrayList<RevisionHistoryItem> revHistoryItems = new ArrayList<RevisionHistoryItem>();
        for(Version<T> version : versions) {
            ArrayList<AuditDetails> audits = new ArrayList<AuditDetails>();
            audits.add(version.getCommitAudit());
            if (version instanceof OriginalVersion) {
                OriginalVersion<T> orgVersion = (OriginalVersion<T>) version;
                audits.addAll(orgVersion.getAttestations());
            }
            revHistoryItems.add(new RevisionHistoryItem(audits, version.getUid()));
        }
        return new RevisionHistory(revHistoryItems);
    }
    
    
    /**
     * Add a new attestation to the specified version. Attestations can only
     * be added to OriginalVersion
     *
     * @param attestation
     * @param versionID
     */
    public synchronized void commitAttestation(Attestation attestation,
            ObjectVersionID versionID) {
        if (attestation == null) {
            throw new IllegalArgumentException("null attestation");
        }
        if (isOriginalVersion(versionID)) {
            OriginalVersion<T> oVersion = (OriginalVersion<T>) idVersionMap.get(versionID);
            oVersion.getAttestations().add(attestation);
        } else {
            throw new IllegalArgumentException("attestatios cannot be added to importedVersion");
        }
    }
    
    //POJO start
    protected VersionedObject() {
    }
    
    private Long id;
    
    protected Long getId() {
        return id;
    }
    
    protected void setId(Long id) {
        this.id = id;
    }
    
    void setUid(HierObjectID uid) {
        this.uid = uid;
    }
    
    void setOwnerID(ObjectRef ownerID) {
        this.ownerID = ownerID;
    }
    
    void setTimeCreated(DvDateTime timeCreated) {
        this.timeCreated = timeCreated;
    }
    
    // in order to skip map timeVersionMap to table
    void setVersions(Set<Version<T>> versions) {
        idVersionMap = new HashMap<ObjectVersionID, Version<T>>();
        timeVersionMap = new TreeMap<DvDateTime, Version<T>>();
        for(Version<T> version : versions) {
            addVersion(version);
        }
    }
    
    // required to map bidirectional one-to-many relationship
    Set<Version<T>> getVersions() {
        return new HashSet<Version<T>>(timeVersionMap.values());
    }
    
    /* fields */
    private HierObjectID uid;
    private ObjectRef ownerID;
    private DvDateTime timeCreated;
    
    private SortedMap<DvDateTime, Version<T>> timeVersionMap;
    private HashMap<ObjectVersionID, Version<T>> idVersionMap; 
    private int trunkCounter;
    private ObjectVersionID latestTrunkUid;
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
 *  The Original Code is VersionedObject.java
 *
 *  The Initial Developer of the Original Code is Yin Su Lim.
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