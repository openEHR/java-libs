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

import org.openehr.rm.ehr.VersionedComposition;
import org.openehr.rm.support.identification.ObjectID;

/**
 * VersionedCompositionDao
 *
 * @author Rong Chen
 * @version 1.0
 */
public interface VersionedCompositionDao {

    /**
     * Save a VersionedComposition
     *
     * @param versionedComposition
     */
    public void save(VersionedComposition versionedComposition);

    /**
     * Find a VersionedComposition by uid
     *
     * @param uid
     * @return versionedComposition or null if not found
     */
    public VersionedComposition findByUid(ObjectID uid);

    /**
     * Update a VersionedComposition
     *
     * @param versionedComposition
     */
    public void update(VersionedComposition versionedComposition);
}