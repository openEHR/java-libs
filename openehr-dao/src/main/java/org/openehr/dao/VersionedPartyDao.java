/*
 * Copyright (C) 2004 Rong Chen, ACODE HB, Sweden
 * All rights reserved.
 *
 * The contents of this software are subject to the FSF GNU Public License 2.0;
 * you may not use this software except in compliance with the License. You may
 * obtain a copy of the License at http://www.fsf.org/licenses/gpl.html
 *
 * Software distributed under the License is distributed on an 'AS IS' basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
 * for the specific language governing rights and limitations under the
 * License.
 */

package org.openehr.dao;

import org.openehr.rm.support.identification.ObjectID;
import org.openehr.rm.support.identification.ArchetypeID;
import org.openehr.rm.demographic.VersionedParty;

import java.util.Set;

/**
 * VersionedPartyDao
 *
 * @author Rong Chen
 * @version 1.0
 */
public interface VersionedPartyDao {

    /**
     * Save a VersionedParty
     *
     * @param versionedParty
     */
    public void save(VersionedParty versionedParty);

    /**
     * Find a VersionedParty by uid
     *
     * @param uid
     * @return versionedParty or null if not found
     */
    public VersionedParty findByUid(ObjectID uid);


    /**
     * Find a set of VersionedParty by archetypeId
     *
     * @param archetypeId
     * @return empty set if not found
     */
    public Set<VersionedParty> findByArchetypeID(ArchetypeID archetypeId);

    /**
     * Update a VersionedParty
     *
     * @param versionedParty
     */
    public void update(VersionedParty versionedParty);

    /**
     * Remove all repositories -  !!! FOR TESTING ONLY !!!
     *
     * @throws UnsupportedOperationException in production environment
     */
    public void removeAll();    
}