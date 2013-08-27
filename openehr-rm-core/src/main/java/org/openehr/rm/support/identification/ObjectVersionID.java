/*
 * component:   "openEHR Reference Implementation"
 * description: "Class ObjectID"
 * keywords:    "support"
 *
 * author:      "Rong Chen <rong@acode.se>"
 * support:     "Acode HB <support@acode.se>"
 * copyright:   "Copyright (c) 2004 Acode HB, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/TRUNK/libraries/src/java/org/openehr/rm/support/identification/ObjectVersionID.java $"
 * revision:    "$LastChangedRevision: 2 $"
 * last_change: "$LastChangedDate: 2005-10-12 22:20:08 +0100 (Wed, 12 Oct 2005) $"
 */
package org.openehr.rm.support.identification;

import org.apache.commons.lang.StringUtils;
import org.openehr.rm.Attribute;
import org.openehr.rm.FullConstructor;

/**
 * Globally unique identifier for one version of a versioned object.
 * <p>
 * The syntax of the value attribute is as follows:
 * <blockquote>objectID::creatingSystemID::versionTreeID</blockquote>
 * </p>
 * <p>Instances of this class are immutable.</p>
 * 
 * @author Yin Su Lim
 * @version 1.0
 */

public class ObjectVersionID extends UIDBasedID {

    /**
     * Create ObjectVersionID by string value
     *
     * @param value
     * @throws IllegalArgumentException if value is empty
     */
	@FullConstructor
    public ObjectVersionID(
    		@Attribute(name = "value", required = true)String value) {
        super(value);
        loadValue(value);
    }

    public ObjectVersionID(String objectId, String creatingSystemId, String versionTreeId) {
        this(objectId + "::" + creatingSystemId + "::" + versionTreeId);
    }
 
    public ObjectVersionID(UID objectID, HierObjectID creatingSystemID, 
    		VersionTreeID versionTreeID) {
        this(objectID.toString() + "::" + creatingSystemID.toString() + "::"
                        + versionTreeID.toString());
        this.objectID = objectID;
        this.creatingSystemID = creatingSystemID;
        this.versionTreeID = versionTreeID;
    }
    
    private void loadValue(String value) {
        // Steps for value checking:
        // 1. Check if value contains any :: or starts with ::
        int doubleColons = value.indexOf("::");
        if (doubleColons <= 0) {
                throw new IllegalArgumentException("bad format, missing objectId");
        }
        // 2. Check how many segments in the value
        String[] splits = value.split("::");
        int segments = splits.length;
        if(segments < 3) {
                throw new IllegalArgumentException("bad format, missing creatingSystemId or versionTreeId");
        }
        if(segments > 4) {
                throw new IllegalArgumentException("bad format, too many segments or '::'");
        }
		// 3. Construct objects for each segment
        //the patterns below are for sorting only, the correct syntax
        //checking is handled by the UID sublcasses.
        if (splits[0].matches(UUID.SIMPLE_UUID_PATTERN)) { //pattern for UUID
        		objectID = new UUID(value.substring(0, doubleColons));
        } else if (splits[0].matches("(\\d)+(\\.(\\d)+)*")) { //for ISO_OID
        		objectID = new ISO_OID(value.substring(0, doubleColons));
        } else if (splits[0].matches("(\\w|-)+(\\.(\\w|-)+)*")){ //for InternetID, 
        		objectID = new InternetID(value.substring(0, doubleColons));
        } else {
        		throw new IllegalArgumentException("wrong format: " + splits[0]);
        }
        if(segments == 4) {
                creatingSystemID = new HierObjectID(splits[1] + "::" + splits[2]);
                versionTreeID = new VersionTreeID(splits[3]);
        } else {
                creatingSystemID = new HierObjectID (splits[1]);
                versionTreeID = new VersionTreeID(splits[2]);
        }
    }

	/**
	 * Unique identifier that identifies one version. It is 
	 * normally the unique identifier of the version container 
	 * containing the version referred to by this objectVersionID
	 * instance.
	 * 
	 * @return objectID
	 */
	public UID objectID() {
		return objectID;
	}
	
	/**
	 * Tree identifier of this version with prespect to other versions 
	 * in the same version tree, as either 1 or 3 part dot-separated  numbers 
	 * e.g. "1" , "2.1.4"
	 * 
	 * @return versionTreeID
	 */
	public VersionTreeID versionTreeID() {
		return versionTreeID;
	}
	
	/**
	 * Identifier of the system that created the Version 
	 * corresponding to this ObjectVersionID
	 * 
	 * @return creatingSystemID
	 */
	public HierObjectID creatingSystemID() {
		return creatingSystemID;
	}
	
	@Override
	public UID root() {
		return objectID();
	}

	@Override
	public String extension() {
		return creatingSystemID.getValue() + "::" + versionTreeID.getValue();
	}

	//POJO start
	ObjectVersionID() {
	}
	
    protected void setValue(String value) {
        loadValue(value);
        super.setValue(value);
    }
    // POJO end
      
	/* fields */
	private UID objectID;
	private VersionTreeID versionTreeID;
	private HierObjectID creatingSystemID;
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
 *  The Original Code is ObjectVersionID.java
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