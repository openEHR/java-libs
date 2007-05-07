/*
 * component:   "openEHR Reference Implementation"
 * description: "Class EHR"
 * keywords:    "ehr"
 *
 * author:      "Rong Chen <rong@acode.se>"
 * support:     "Acode HB <support@acode.se>"
 * copyright:   "Copyright (c) 2004 Acode HB, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/TRUNK/libraries/src/java/org/openehr/rm/ehr/EHR.java $"
 * revision:    "$LastChangedRevision: 2 $"
 * last_change: "$LastChangedDate: 2005-10-12 22:20:08 +0100 (Wed, 12 Oct 2005) $"
 */
package org.openehr.rm.ehr;

import org.openehr.rm.Attribute;
import org.openehr.rm.FullConstructor;
import org.openehr.rm.RMObject;
import org.openehr.rm.support.identification.HierarchicalObjectID;
import org.openehr.rm.support.identification.ObjectReference;
import org.openehr.rm.datatypes.quantity.datetime.DvDateTime;

import java.util.List;

/**
 * The EHR class is the centre node of the EHR  repository  for a
 * subject of care.
 *
 * @author Rong Chen
 * @version 1.0
 */
public class EHR extends RMObject {

    /**
     * Constructs an EHR
     *
     * @param systemID		required
     * @param ehrID			required
     * @param timeCreated 	required
     * @param contributions	required
     * @param ehrStatus		required
     * @param directory		null if not specified
     * @param compositions	required
     * @throws IllegalArgumentException 
     */
    @FullConstructor public EHR (@Attribute(name = "systemID", required = true) HierarchicalObjectID systemID,
                                @Attribute(name = "ehrID", required=true) HierarchicalObjectID ehrID,
                                @Attribute(name = "timeCreated", required=true) DvDateTime timeCreated,
                                @Attribute(name = "contributions", required=true) List<ObjectReference> contributions,
                                @Attribute(name = "ehrStatus", required = true) ObjectReference ehrStatus,
                                @Attribute(name = "directory") ObjectReference directory,
                                @Attribute(name = "compositions", required=true) List<ObjectReference> compositions) {
        
    		if (systemID == null) {
    			throw new IllegalArgumentException("null systemID");
    		}
    		if (ehrID == null) {
    			throw new IllegalArgumentException("null ehrID");
    		}
    		if (timeCreated == null) {
    			throw new IllegalArgumentException("null timeCreated");
    		}
    		if (contributions == null) {
    			throw new IllegalArgumentException("null contributions");
    		}    		
        for (ObjectReference ref : contributions) {
            if (!ObjectReference.Type.CONTRIBUTION.equals(ref.getType())) {
                throw new IllegalArgumentException(
                        "non-contribution type object reference");
            }
        }
        if (compositions == null) {
            throw new IllegalArgumentException("null compositions");
        }
        for (ObjectReference ref : compositions) {
            if (!ObjectReference.Type.VERSIONED_COMPOSITION.equals(
                    ref.getType())) {
                throw new IllegalArgumentException(
                        "non-versioned_composition type object reference");
            }
        }
        if (directory != null && !ObjectReference.Type.VERSIONED_FOLDER.equals(
                directory.getType())) {
        		throw new IllegalArgumentException(
        				"non-versioned_folder type object reference");
        }

        this.systemID = systemID;
        this.ehrID = ehrID;
        this.timeCreated = timeCreated;
        this.contributions = contributions;
        this.ehrStatus = ehrStatus;
        this.directory = directory;
        this.compositions = compositions;
    }
    
    /**
     * The id of this EHR
     * 
     * @return ehrID
     */
    public HierarchicalObjectID getEhrID() {
		return ehrID;
	}

    /**
     * EHR_STATUS object for this EHR
     *  
     * @return ehrStatus
     */
	public ObjectReference getEhrStatus() {
		return ehrStatus;
	}

	/**
	 * The id of the EHR system on which this EHR was created
	 * 
	 * @return systemID
	 */
	public HierarchicalObjectID getSystemID() {
		return systemID;
	}

	/**
     * Time of creation of the repository
     *
     * @return time of creation
     */
    public DvDateTime getTimeCreated() {
        return timeCreated;
    }

    /**
     * List of contributions causing changes to this EHR. Each
     * contribution contains a list of versions, which may include
     * references to any number of VERSION instances, ie items of
     * type VERSIONED_COMPOSITION and DIRECTORY.
     *
     * @return List of ObjectReference
     */
    public List<ObjectReference> getContributions() {
        return contributions;
    }

    /**
     * Optional directory structure for this EHR.
     *
     * @return directory
     */
    public ObjectReference getDirectory() {
        return directory;
    }

    /**
     * Master list of all composition references in this EHR
     *
     * @return list of objectReference
     */
    public List<ObjectReference> getCompositions() {
        return compositions;
    }

    // POJO start
    EHR() {
    }

    private Long id;

    Long getId() {
        return id;
    }

    void setId(Long id) {
        this.id = id;
    }

    void setEhrID(HierarchicalObjectID ehrID) {
		this.ehrID = ehrID;
	}

	void setEhrStatus(ObjectReference ehrStatus) {
		this.ehrStatus = ehrStatus;
	}

	void setSystemID(HierarchicalObjectID systemID) {
		this.systemID = systemID;
	}

	void setTimeCreated(DvDateTime timeCreated) {
        this.timeCreated = timeCreated;
    }

    void setContributions(List<ObjectReference> contributions) {
        this.contributions = contributions;
    }

    void setDirectory(ObjectReference directory) {
        this.directory = directory;
    }

    void setCompositions(List<ObjectReference> compositions) {
        this.compositions = compositions;
    }
    // POJO end

    /* fields */
    HierarchicalObjectID systemID;
    HierarchicalObjectID ehrID;
    DvDateTime timeCreated;
    List<ObjectReference> contributions;
    ObjectReference ehrStatus;
    ObjectReference directory;
    List<ObjectReference> compositions;
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
 *  The Original Code is EHR.java
 *
 *  The Initial Developer of the Original Code is Rong Chen.
 *  Portions created by the Initial Developer are Copyright (C) 2003-2007
 *  the Initial Developer. All Rights Reserved.
 *
 *  Contributor(s): Erik Sundvall
 *
 * Software distributed under the License is distributed on an 'AS IS' basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
 * for the specific language governing rights and limitations under the
 * License.
 *
 *  ***** END LICENSE BLOCK *****
 */