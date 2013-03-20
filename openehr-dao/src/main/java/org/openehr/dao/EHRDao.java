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

import org.openehr.rm.ehr.EHR;
import org.openehr.rm.support.identification.PartyRef;

/**
 * Data access object for EHR
 *
 * @author Rong Chen
 * @version 1.0
 */
public interface EHRDao {

    /**
     * Find EHR by subject
     *
     * @param subject
     * @return ehr or null if not found
     */
    public EHR findBySubject(PartyRef subject);

    /**
     * Save a new EHR object
     *
     * @param ehr
     * @throws EHRAlreadyExistsException if ehr exists for given subject
     */
    public void save(EHR ehr) throws EHRAlreadyExistsException;

    /**
     * Update an existing EHR
     *
     * @param ehr
     */
    public void update(EHR ehr) throws EHRNotFoundException;
}