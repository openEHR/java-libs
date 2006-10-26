/*
 * Copyright (C) 2005 Acode HB, Sweden.
 * All Rights Reserved.
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You may obtain a copy of the License at
 * http://www.gnu.org/licenses/gpl.txt
 *
 */

package se.acode.openehr.parser;

import org.openehr.am.archetype.Archetype;
import org.openehr.am.archetype.constraintmodel.CComplexObject;
import org.openehr.am.archetype.constraintmodel.CAttribute;
import org.openehr.am.archetype.constraintmodel.CObject;
import org.openehr.am.archetype.constraintmodel.ArchetypeInternalRef;

import java.util.Map;
import java.util.HashMap;

/**
 * Validator for archetype parsed from ADL files
 * <p/>
 * It checks
 * .Existence of class and atribute
 * .Use node by object path in cADL
 * .Decomposed date with dADL
 * .Term definition and binding checking
 *
 * @author Rong Chen
 * @version 1.0
 */
public class ArchetypeValidator {

    /**
     * Constructs an ArchetypeValidator
     *
     * @param archetype The Archetype to be validated
     */
    public ArchetypeValidator(Archetype archetype) {
        this.archetype = archetype;
    }

    /**
     * Check if information model entity referenced by archetype
     * has right name or type
     *
     * @return true if valid
     */
    // todo: check class, attribute name etc
    private boolean checkInformationModelEntity() {
        return true;
    }

    // todo: fix it
    private boolean checkObjectPatch() {
        return true;
    }

    // todo: check if node occurrences allowed and valid

    // todo: check if archetypeSlot has cyclical reference

    // todo: check if archetypeInternalRef has valid target, cyclical reference

    /**
     * Check internal references
     *
     * @return map of node path, target path if any wrong internal references
     */
    Map<String, String> checkInternalReferences() {
        Map<String, String> errors = new HashMap<String, String>();
        return checkInternalReferences(archetype, archetype.getDefinition(),
                errors);
    }

    private Map<String, String> checkInternalReferences(Archetype archetype,
                                                        CComplexObject ccobj,
                                                        Map<String, String> errors) {
        for (CAttribute cattribute : ccobj.getAttributes()) {
            for (CObject cobj : cattribute.getChildren()) {
                if (cobj instanceof ArchetypeInternalRef) {
                    ArchetypeInternalRef ref = (ArchetypeInternalRef) cobj;
                    CObject target = archetype.node(ref.getTargetPath());
                    if (target == null
                            || !target.getRmTypeName().equals(
                                    cobj.getRmTypeName())) {
                        // either target unknown or wrong type
                        errors.put(ref.path(), ref.getTargetPath());
                    }
                }
                if (cobj instanceof CComplexObject) {
                    checkInternalReferences(archetype, (CComplexObject) cobj,
                            errors);
                }
            }
        }
        return errors;
    }


    /* fields */
    private Archetype archetype;
}
