/*
 * component:   "openEHR Reference Implementation"
 * description: "Class Observation"
 * keywords:    "composition"
 *
 * author:      "Rong Chen <rong@acode.se>"
 * support:     "Acode HB <support@acode.se>"
 * copyright:   "Copyright (c) 2004 Acode HB, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/TRUNK/libraries/src/java/org/openehr/rm/composition/content/entry/Observation.java $"
 * revision:    "$LastChangedRevision: 2 $"
 * last_change: "$LastChangedDate: 2005-10-12 22:20:08 +0100 (Wed, 12 Oct 2005) $"
 */
package org.openehr.rm.composition.content.entry;

import org.openehr.rm.Attribute;
import org.openehr.rm.FullConstructor;
import org.openehr.rm.common.archetyped.Archetyped;
import org.openehr.rm.common.archetyped.FeederAudit;
import org.openehr.rm.common.archetyped.Link;
import org.openehr.rm.common.archetyped.Locatable;
import org.openehr.rm.common.generic.Participation;
import org.openehr.rm.common.generic.PartyProxy;
import org.openehr.rm.datastructure.itemstructure.ItemSingle;
import org.openehr.rm.support.identification.ObjectID;
import org.openehr.rm.support.identification.ObjectRef;
import org.openehr.rm.support.terminology.TerminologyService;
import org.openehr.rm.datastructure.history.History;
import org.openehr.rm.datastructure.itemstructure.ItemStructure;
import org.openehr.rm.datatypes.text.CodePhrase;
import org.openehr.rm.datatypes.text.DvText;

import java.util.List;
import java.util.Set;

/**
 * Purpose Entry subtype for all clinical data in the past or present,
 * ie which (by the time it is recorded) has already occurred.
 * OBSERVATION data is expressed using the class HISTORY<T>, which
 * guarantees that it is situated in time.
 * <p/>
 * Instances of this class are immutable.
 *
 * @author Rong Chen
 * @version 1.0
 */
public final class Observation extends CareEntry {

    /**
     * Construct an Observation
     *
     * @param uid
     * @param archetypeNodeId
     * @param name
     * @param subject
     * @param provider
     * @param protocol
     * @param actID
     * @param guidelineID
     * @param otherParticipation
     * @param data
     * @param state              null if unspecified
     * @throws IllegalArgumentException if date null
     */
    @FullConstructor
            public Observation(@Attribute(name = "uid") ObjectID uid,
                               @Attribute(name = "archetypeNodeId", required = true) String archetypeNodeId,
                               @Attribute(name = "name", required = true) DvText name,
                               @Attribute(name = "archetypeDetails", required = true) Archetyped archetypeDetails,
                               @Attribute(name = "feederAudit") FeederAudit feederAudit,
                               @Attribute(name = "links") Set<Link> links,
                               @Attribute(name = "parent") Locatable parent,
                               @Attribute(name = "language", required = true) CodePhrase language,
                               @Attribute(name = "charset", required = true) CodePhrase charset, 
                               @Attribute(name = "subject", system = true) PartyProxy subject,
                               @Attribute(name = "provider", system = true) PartyProxy provider,
                               @Attribute(name = "workflowID") ObjectRef workflowID,
                               @Attribute(name = "otherParticipation") List<Participation> otherParticipation,
                               @Attribute(name = "protocol") ItemStructure protocol,
                               @Attribute(name = "guidelineID") ObjectRef guidelineID,                              
                               @Attribute(name = "data", required = true) History<ItemStructure> data,
                               @Attribute(name = "state") History<ItemStructure> state,
                               @Attribute(name = "terminologyService", system = true) TerminologyService terminologyService
                               ) {

        super(uid, archetypeNodeId, name, archetypeDetails, feederAudit, links, parent,
                language, charset, subject, provider, workflowID, otherParticipation, 
                protocol, guidelineID, terminologyService);

        if (data == null) {
            throw new IllegalArgumentException("null data");
        }
        this.data = data;
        this.state = state;
    }

    /**
     * Construct an Observation
     *
     * @param archetypeNodeId
     * @param name
     * @param subject
     * @param provider
     * @param data
     * @throws IllegalArgumentException if date null
     */
    public Observation(String archetypeNodeId, DvText name, Archetyped archetypeDetails,
                CodePhrase language, CodePhrase charset,
                 PartyProxy subject, PartyProxy provider,
                 History<ItemStructure> data, TerminologyService terminologyService) {
        this(null, archetypeNodeId, name, archetypeDetails, null, null, null, language, charset, 
                subject, provider, null, null, null, null, data, null, terminologyService);
    }

    /**
     * The data of this observation, in the form of a history of
     * values which may be of any complexity.
     *
     * @return data
     */
    public History<ItemStructure> getData() {
        return data;
    }

    /**
     * The state of subject of this observation during the observation
     * process, in the form of a history of values which may be of any
     * complexity. Optional.
     *
     * @return state null if unspecified
     */
    public History<ItemStructure> getState() {
        return state;
    }

    /**
     * String The path to an item relative to the root of this
     * archetyped structure.
     *
     * @param item
     * @return string path
     */
    public String pathOfItem(Locatable item) {
        return null;  // todo: implement this method
    }

    /**
     * The item at a path that is relative to this item.
     *
     * @param path
     * @return the item or null if not found
     */
    public Object itemAtPath(String path) {

        Object item = super.itemAtPath(path);
        if (item != null) {
            return item;
        }
        String[] attributeNames = {
            DATA, STATE
        };
        Locatable [] attributes = {
            data, state
        };
        return locateAttribute(path, attributeNames, attributes);
    }

    // POJO start
    Observation() {
    }

    void setData(History<ItemStructure> data) {
        this.data = data;
    }

    void setState(History<ItemStructure> state) {
        this.state = state;
    }
    // POJO end

    /* fields */
    private History<ItemStructure> data;
    private History<ItemStructure> state;
    
    /* static fields */
    public static final String DATA = "data";
    public static final String STATE = "state";
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
 *  The Original Code is Observation.java
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