/*
 * component:   "openEHR Reference Implementation"
 * description: "Class VersionTreeID"
 * keywords:    "support"
 *
 * author:      "Yin Su Lim <y.lim@chime.ucl.ac.uk>"
 * support:     "CHIME, UCL"
 * copyright:   "Copyright (c) 2006 UCL, UK"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/TRUNK/libraries/src/java/org/openehr/rm/support/identification/VersionTreeID.java $"
 * revision:    "$LastChangedRevision: 53 $"
 * last_change: "$LastChangedDate: 2006-08-11 13:20:08 +0100 (Fri, 11 Aug 2006) $"
 */
package org.openehr.rm.support.identification;

import org.apache.commons.lang.StringUtils;
import org.openehr.rm.Attribute;
import org.openehr.rm.FullConstructor;
import org.openehr.rm.RMObject;

/**
 * Version tree identifier for one version
 * The format of the identifier is:
 * <trunk_version>[.<branch_number>.<branch_version>]
 * 
 * @author Yin Su Lim
 * @version 1.0
 */
public class VersionTreeID extends RMObject {

    /**
     * Contruct VersionTreeID by string value
     *
     * @param value
     * @throws IllegalArgumentException if value is null or empty
     */
	@FullConstructor
    public VersionTreeID(@Attribute(name = "value", required = true)String value) {
        if (StringUtils.isEmpty(value)) {
            throw new IllegalArgumentException("empty value");
        }
        loadValue(value);
        //this.value = value;
    }
    
    
    /**
     * Contruct VersionTreeID by value of trunkVersion, branchNumber
     * and branchVersion. All valid integers must be >= 1.
     * Special case branchNumber == 0 && branchVersion == 0 indicates no branch.
     *
     * @param trunkVersion
     * @param branchVersion
     * @param branchNumber
     * @throws IllegalArgumentException
     */
    public VersionTreeID(int trunkVersion, int branchNumber,
            int branchVersion) {
        validateValues(trunkVersion, branchNumber, branchVersion);
        String trunk = Integer.toString(trunkVersion);
        this.trunkVersion = trunk;
        if(branchNumber > 0 ) {
            this.value = trunk + "." + Integer.toString(branchNumber) +  "."
                    + Integer.toString(branchVersion);
            this.branchNumber = Integer.toString(branchNumber);
            this.branchVersion = Integer.toString(branchVersion);
        } else {
            this.value = trunk;
        }
    }
    
    private void loadValue(String value) {
        if (!value.matches(PATTERN)) {
            throw new IllegalArgumentException("wrong format");
        }
        int branch = value.indexOf(".");
        if (branch < 0) { // no branch, just trunk
            //validateValues(Integer.parseInt(value), 0, 0);
            this.trunkVersion = value;
            this.value = value;
        } else {
            String[] entries = value.split("\\.");
            //System.out.println("in loadValues, size of entries:" + entries.length);
            validateValues(Integer.parseInt(entries[0]), Integer.parseInt(entries[1]),
                    Integer.parseInt(entries[2]));
            this.trunkVersion = entries[0];
            //never set branchNo or branchV to 0
            if(Integer.parseInt(entries[1]) > 0) {
                this.branchNumber = entries[1];
                this.branchVersion = entries[2];
                this.value = value;
            } else {
                this.value = entries[0];
            }
        }
    }
    
    private void validateValues(int trunk, int branchNo, int branchV) {
        if (trunk < 1 || branchNo < 0 || branchV < 0) {
            throw new IllegalArgumentException("version number smaller than 0");
        }
        //0 for branchNo or branchV is special case,
        //where both must be 0 to indicate no branch
        if (branchNo == 0 || branchV == 0) {
            if (branchV != branchNo) {
                throw new IllegalArgumentException("breach of branch_validity");
            }
        }
    }
    
    
    public String getValue() {
        return value;
    }
    
    /**
     * Trunk version number
     *
     *@return trunkVersion
     */
    public String trunkVersion() {
        return trunkVersion;
    }
    
    /**
     * Number of brnach from the trunk point
     *
     *@return
     */
    public String branchNumber() {
        return branchNumber;
    }
    
    /**
     * Version of the branch
     *
     *@return branchVersion
     */
    public String branchVersion() {
        return branchVersion;
    }
    
    /**
     * True if this version identifier represents a branch
     *
     */
    public boolean isBranch() {
        return branchVersion != null;
    }
    
    /**
     * True if this version is the first copy in a version tree
     * i.e. versionTreeId is 1.
     */
    public boolean isFirst() {
        return trunkVersion.equals("1") && !isBranch();
    }
    
    /**
     * Return the next logical versionTreeId
     * i.e. next logical versionTreeId for 1.2.1 is 1.2.2
     */
    public VersionTreeID next() {
        if (isBranch()) {
            String newBranchVersion = Integer.toString(Integer.valueOf(branchVersion) + 1);
            return new VersionTreeID(trunkVersion + "." + branchNumber +
                    "." +  newBranchVersion);
            
        } else {
            
            return new VersionTreeID(Integer.toString(Integer.valueOf(trunkVersion) + 1));
        }
    }
    
    /**
     * Return true if both have same value and
     *
     * @param o The object to which this object is to be compared
     * @return true if equals
     */
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!( o instanceof VersionTreeID )) return false;
        
        final VersionTreeID vtId = (VersionTreeID) o;
        
        return value.equals(vtId.value);
    }
    
    /**
     * Return a hash code value of this objectID
     *
     * @return hash code
     */
    public int hashCode() {
        return value.hashCode();
    }
    
    /**
     * Return value as string presentation of this objectID
     *
     * @return string presentation
     */
    public String toString() {
        return value;
    }
    
    //POJO start
    VersionTreeID() {
    }
    
    void setValue(String value) {
        this.value = value;
        loadValue(value);
    }
    
    //POJO end
    
    private String PATTERN = "[1-9](\\d)*(\\.(\\d)+\\.(\\d)+)?";
    
    /* field */
    private String value;
    private String trunkVersion;
    private String branchNumber;
    private String branchVersion;
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
 *  The Original Code is VersionTreeID.java
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