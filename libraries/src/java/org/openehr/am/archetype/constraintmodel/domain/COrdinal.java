/*
 * component:   "openEHR Reference Implementation"
 * description: "Class COrdinal"
 * keywords:    "archetype"
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
package org.openehr.am.archetype.constraintmodel.domain;

import org.openehr.am.archetype.constraintmodel.ArchetypeConstraint;
import org.openehr.am.archetype.constraintmodel.CComplexObject;
import org.openehr.am.archetype.constraintmodel.CDomainType;
import org.openehr.am.archetype.constraintmodel.DVObjectCreationException;
import org.openehr.am.archetype.ontology.ArchetypeOntology;
import org.openehr.am.archetype.ontology.DefinitionItem;
import org.openehr.rm.datatypes.basic.DataValue;
import org.openehr.rm.datatypes.quantity.DvOrdinal;
import org.openehr.rm.datatypes.text.CodePhrase;
import org.openehr.rm.datatypes.text.DvCodedText;
import org.openehr.rm.support.terminology.TerminologyService;
import org.openehr.rm.util.SystemValue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * This class represents a constraint of domain type ordinal. Instances of this
 * class are immutable.
 *
 * @author Rong Chen
 * @version 1.0
 */
public final class COrdinal extends CDomainType {

    /**
     * Constructs a ordinal constraint by a list of ordinal
     *
     * @param path
     * @param list List<Ordinal>
     * @throws IllegalArgumentException if list null or empty
     */
    public COrdinal(String path, List<Ordinal> list) {

        super(path, "DvOrdinal");

        if (list == null || list.isEmpty()) {
            throw new IllegalArgumentException("list null or empty");
        }
        this.list = new ArrayList<Ordinal>(list);
    }


    /**
     * Standard form of constraint
     *
     * @return Standard form of constraint
     */
    public CComplexObject standardRepresentation() {
        return null;  // todo: implement this method
    }

    /**
     * True if this node is a valid archetype node.
     *
     * @return ture if valid
     */
    public boolean isValid() {
        return false;  // todo: implement this method
    }

    /**
     * True if the relative path exists at this node.
     *
     * @param path
     * @return ture if has
     * @throws IllegalArgumentException if path null
     */
    public boolean hasPath(String path) {
        return false;  // todo: implement this method
    }

    /**
     * True if constraints represented by other are narrower than this node.
     *
     * @param constraint
     * @return true if subset
     * @throws IllegalArgumentException if constraint null
     */
    public boolean isSubsetOf(ArchetypeConstraint constraint) {
        return false;  // todo: implement this method
    }

    /**
     * List of ordinal
     *
     * @return unmodifiable List<Ordinal>
     */
    public List<Ordinal> getList() {
        return Collections.unmodifiableList(list);
    }

    /**
     * Create an DvOrdinal from a string value
     *
     * @param value        index of the ordinal in the list
     * @param systemValues
     * @return a DataValue
     * @throws DVObjectCreationException if value has wrong format
     *                                   or index value out of range
     */
    public DvOrdinal createObject(String value,
                                  Map<SystemValue, Object> systemValues,
                                  ArchetypeOntology ontology)
            throws DVObjectCreationException {
        int i = 0;
        try {
            i = Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw DVObjectCreationException.BAD_FORMAT;

        }
        if (i < 0 || i > list.size() - 1) {
            throw DVObjectCreationException.BAD_INDEX;
        }
        Ordinal o = list.get(i);
        CodePhrase language = (CodePhrase) systemValues.get(
                SystemValue.LANGUAGE);
        CodePhrase charset = (CodePhrase) systemValues.get(SystemValue.CHARSET);
        TerminologyService ts = (TerminologyService)
                systemValues.get(SystemValue.TERMINOLOGY_SERVICE);
        DefinitionItem item = ontology.definition(language.getCodeString(),
                o.getCode());
        CodePhrase definingCode = new CodePhrase(o.getTerminology(),
                o.getCode());
        return new DvOrdinal(o.getValue(), new DvCodedText(item.getText(),
                language, charset, definingCode, ts));
    }

    /**
     * Return true if the constraint has limit the possible value to
     * be only one, which means the value has been assigned by the archetype
     * author at design time
     *
     * @return true if has
     */
    public boolean hasAssignedValue() {
        return list.size() == 1;
    }

    /**
     * Return assigned value as data value instance
     *
     * @param systemValues
     * @param ontology
     * @return datavalue or null if not assigned
     */
    public DataValue assignedValue(Map<SystemValue, Object> systemValues,
                                   ArchetypeOntology ontology) {
        if (list.size() != 1) {
            return null;
        }
        Ordinal o = list.get(0);
        CodePhrase language = (CodePhrase) systemValues.get(
                SystemValue.LANGUAGE);
        CodePhrase charset = (CodePhrase) systemValues.get(SystemValue.CHARSET);
        TerminologyService ts = (TerminologyService)
                systemValues.get(SystemValue.TERMINOLOGY_SERVICE);

        DefinitionItem item = ontology.definition(language.getCodeString(),
                o.getCode());
        CodePhrase definingCode = new CodePhrase(o.getTerminology(),
                o.getCode());
        return new DvOrdinal(o.getValue(), new DvCodedText(item.getText(),
                language, charset, definingCode, ts));
    }

    /* fields */
    private final List<Ordinal> list;
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
 *  The Original Code is COrdinal.java
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