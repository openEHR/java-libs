/*
 * component:   "openEHR Reference Implementation"
 * description: "Class ArchetypeID"
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
import org.openehr.rm.Attribute;
import org.openehr.rm.FullConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

/**
 * Identifier for archetypes, instances of this class are immutable.
 *
 * @author Rong Chen
 * @version 1.0
 */
public final class ArchetypeID extends ObjectID {

    /**
     * Constructs an ArchetypeID by a string value
     *
     * @param value
     * @throws IllegalArgumentException if value wrong format
     */
	@FullConstructor
    public ArchetypeID(
    		@Attribute(name = "value", required = true) String value) {
        super(value);
        loadValue(value);
    }

    private void loadValue(String value) {
        StringTokenizer tokens = new StringTokenizer(value,
                AXIS_SEPARATOR);
        if (tokens.countTokens() != 3) {
            throw new IllegalArgumentException("bad format, wrong number of \"" +
                    AXIS_SEPARATOR
                    + "\", " + value);
        }
        qualifiedRmEntity = tokens.nextToken();
        domainConcept = tokens.nextToken();
        versionID = tokens.nextToken();

        tokens = new StringTokenizer(qualifiedRmEntity,
                SECTION_SEPARATOR);
        if (tokens.countTokens() != 3) {
            throw new IllegalArgumentException("bad format, wrong number of \"" +
                    SECTION_SEPARATOR
                    + "\" , " + value);
        }
        rmOriginator = tokens.nextToken();
        rmName = tokens.nextToken();
        rmEntity = tokens.nextToken();

        tokens = new StringTokenizer(domainConcept, SECTION_SEPARATOR);
        if (tokens.countTokens() < 1) {
            throw new IllegalArgumentException(
            		"bad format, too few sections for domainConcept, " + value);
        }
        conceptName = tokens.nextToken();
        specialisation = new ArrayList<String>();
        while(tokens.hasMoreTokens()) {
        	specialisation.add(tokens.nextToken());
        } 
        validateAll();
    }


    // POJO start
    ArchetypeID() {
    }

    protected void setValue(String value) {
        loadValue(value);
        super.setValue(value);
    }
    // POJO end

    /**
     * Create an ArchetypeID by axis and section values
     *
     * @param rmOriginator
     * @param rmName
     * @param rmEntity
     * @param conceptName
     * @param specialisation
     * @param versionID
     * @throws IllegalArgumentException if any axis or section value
     *                                  has wrong format
     */
    public ArchetypeID(String rmOriginator, String rmName,
                       String rmEntity, String conceptName,
                       String specialisation, String versionID) {
        super(toValue(rmOriginator, rmName, rmEntity, conceptName,
                specialisation, versionID));
        this.qualifiedRmEntity = toQualifiedRmEntity(rmOriginator,
                rmName, rmEntity);
        this.domainConcept = toDomainConcept(conceptName,
                specialisation);
        this.rmOriginator = rmOriginator;
        this.rmName = rmName;
        this.rmEntity = rmEntity;
        this.conceptName = conceptName;
        this.specialisation = new ArrayList<String>();
        if(specialisation != null) {        	
        	this.specialisation.add(specialisation);
        }
        this.versionID = versionID;
        validateAll();
    }

    // validate all axis and section values
    private void validateAll() {
        validateName(rmOriginator, "rm_originator");
        validateName(rmName, "rm_name");
        validateName(rmEntity, "rm_entity");
        validateName(conceptName, "concept_name");
        
        if (specialisation != null) {
        	for(String name : specialisation) {
        		validateName(name, "specialisation");
        	}
        }
        validateVersionID(versionID);
    }

    // create value out of axis and section values
    private static String toValue(String rmOriginator,
                                  String rmName,
                                  String rmEntity,
                                  String conceptName,
                                  String specialisation,
                                  String versionID) {
        return new StringBuffer(toQualifiedRmEntity(rmOriginator,
                rmName, rmEntity))
                .append(AXIS_SEPARATOR)
                .append(toDomainConcept(conceptName, specialisation))
                .append(AXIS_SEPARATOR)
                .append(versionID)
                .toString();
    }

    private static String toQualifiedRmEntity(String rmOriginator,
                                              String rmName,
                                              String rmEntity) {
        return new StringBuffer(rmOriginator)
                .append(SECTION_SEPARATOR)
                .append(rmName)
                .append(SECTION_SEPARATOR)
                .append(rmEntity)
                .toString();
    }

    private static String toDomainConcept(String conceptName,
                                          List<String> specialisation) {
        //return conceptName + ( specialisation == null
        //        ? "" : SECTION_SEPARATOR + specialisation );
        
        StringBuffer buf = new StringBuffer(conceptName);
        if(specialisation != null && !specialisation.isEmpty()) {
        	for(int i = 0, j = specialisation.size(); i < j; i++) {
            	buf.append(specialisation.get(i));
            	if(i != j - 1) {
            		buf.append(SECTION_SEPARATOR);
            	}
            }
        }
        return buf.toString();        
    }
    
    private static String toDomainConcept(String conceptName,
			String specialisation) {
		return conceptName + ( specialisation == null
		 ? "" : SECTION_SEPARATOR + specialisation );	
	}
    
    

    private static void validateName(String value, String label) {
        if (!NAME_PATTERN.matcher(value).matches()) {
            throw new IllegalArgumentException("wrong format of "
                    + label + ": " + value);
        }
    }

    private static void validateVersionID(String version) {
        if (!VERSION_PATTERN.matcher(version).matches()) {
            throw new IllegalArgumentException(
                    "wrong format of versionID, " + version);
        }
    }

    /**
     * Globally qualified reference model entity,
     * eg "openehr-ehr_rm-entry"
     *
     * @return qualifiedRmEntity
     */
    public String qualifiedRmEntity() {
        return qualifiedRmEntity;
    }

    /**
     * Name of the concept represented by this archetype, including
     * specialisation, eg "biochemistry result-cholesterol"
     *
     * @return domainConcept
     */
    public String domainConcept() {
        return domainConcept;
    }

    /**
     * Name of the concept represented by this archetype
     * without specialisation
     *
     * @return conceptName
     */
    public String conceptName() {
        return conceptName;
    }

    /**
     * Organisation originating the reference model on which this
     * archetype is based, eg "openehr", "cen", "hl7"
     *
     * @return rmOriginator
     */
    public String rmOriginator() {
        return rmOriginator;
    }

    /**
     * Name of the reference model, eg "rim", "ehr_rm", "en13606"
     *
     * @return rmName
     */
    public String rmName() {
        return rmName;
    }

    /**
     * Name of the ontological level within the reference model to
     * which this archetype is targeted, eg for openEHR, "folder",
     * "composition", "section", "entry"
     *
     * @return rmEntity
     */
    public String rmEntity() {
        return rmEntity;
    }

    /**
     * Name of specialisation of concept, if this archetype is a
     * specialisation of another archetype, eg "cholesterol"
     *
     * @return specialisation list or empty if no specialisation
     */
    public List<String> specialisation() {
        return specialisation;
    }

    /**
     * Return localID
     *
     * @return localID
     */
    public String localID() {
        return getValue();
    }

    /**
     * Return versionID
     *
     * @return versionID
     */
    public String versionID() {
        return versionID;
    }

    /**
     * The contextID
     *
     * @return always null
     */
    public UID contextID() {
        return null;
    }

    /**
     * Return true if both arcetypeId has the same value, and versionID is
     * not included in comparison
     *
     * @return true if equals
     */
    public boolean equalsIgnoreVersionID(ArchetypeID id) {
        return new EqualsBuilder()
                .append(qualifiedRmEntity, id.qualifiedRmEntity)
                .append(domainConcept, id.domainConcept)
                .isEquals();
    }

    /**
     * A base of the archetypeId is the value of it without versionId
     *
     * @return base part of the archetypeId
     */
    public String base() {
        return new StringBuffer(toQualifiedRmEntity(rmOriginator,
                rmName, rmEntity))
                .append(AXIS_SEPARATOR)
                .append(toDomainConcept(conceptName, specialisation))
                .toString();
    }

    /* static fields */
    private static final String AXIS_SEPARATOR = ".";
    private static final String SECTION_SEPARATOR = "-";

    private static Pattern NAME_PATTERN =
            Pattern.compile("[a-zA-Z][a-zA-Z0-9()_/%$#&]*");
    private static Pattern VERSION_PATTERN =
            Pattern.compile("[a-zA-Z0-9]+");

    /* fields */
    private String qualifiedRmEntity;   // calculated
    private String rmOriginator;
    private String rmName;
    private String rmEntity;
    private String domainConcept;       // calculated
    private String conceptName;
    private List<String> specialisation;
    private String versionID;
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
 *  The Original Code is ArchetypeID.java
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