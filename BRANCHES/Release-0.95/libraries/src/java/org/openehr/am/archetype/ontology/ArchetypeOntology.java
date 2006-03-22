/*
 * component:   "openEHR Reference Implementation"
 * description: "Class ArchetypeOntology"
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
package org.openehr.am.archetype.ontology;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class represents ontology section from an archetype
 *
 * @author Rong Chen
 * @version 1.0
 */
public class ArchetypeOntology {

    /**
     * Creates an ArchetypeOntology
     *
     * @param primaryLanguage
     * @param languages
     * @param terminologies
     * @param termDefinitionsList
     * @param constDefinitionsList
     * @param termBindingList
     * @param constraintBindingList
     */
    public ArchetypeOntology(String primaryLanguage,
                             List<String> languages,
                             List<String> terminologies,
                             List<OntologyDefinitions> termDefinitionsList,
                             List<OntologyDefinitions> constDefinitionsList,
                             List<OntologyBinding> termBindingList,
                             List<OntologyBinding> constraintBindingList) {
        this.primaryLanguage = primaryLanguage;
        this.languages = languages;
        this.terminologies = terminologies;
        this.termDefinitionsList = termDefinitionsList;
        this.constraintDefinitionsList = constDefinitionsList;
        this.termBindingList = termBindingList;
        this.constraintBindingList = constraintBindingList;

        // load defintionsMap
        definitionsMap = new HashMap<String, Map<String, DefinitionItem>>();
        loadDefs(definitionsMap, termDefinitionsList);
        loadDefs(definitionsMap, constDefinitionsList);
    }

    private void loadDefs(Map<String, Map<String, DefinitionItem>> map,
                          List<OntologyDefinitions> list) {
        if (list == null) {
            return;
        }
        Map<String, DefinitionItem> codeMap = null;
        for (OntologyDefinitions defs : list) {
            codeMap = map.get(defs.getLanguage());
            if (null == codeMap) {
              codeMap = new HashMap<String, DefinitionItem>();
            }
            for (DefinitionItem item : defs.getDefinitions()) {
                codeMap.put(item.getCode(), item);
            }
            map.put(defs.getLanguage(), codeMap);
        }
    }

    public String getPrimaryLanguage() {
        return primaryLanguage;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public List<String> getTerminologies() {
        return terminologies;
    }

    public List<OntologyDefinitions> getTermDefinitionsList() {
        return termDefinitionsList;
    }

    public List<OntologyDefinitions> getConstraintDefinitionsList() {
        return constraintDefinitionsList;
    }

    public List<OntologyBinding> getTermBindingList() {
        return termBindingList;
    }

    public List<OntologyBinding> getConstraintBindingList() {
        return constraintBindingList;
    }

    // null if not found
    public DefinitionItem definition(String language, String code) {
        Map<String, DefinitionItem> map = definitionsMap.get(language);
        if (map == null) {
            return null;
        }
        return map.get(code);
    }

    // null if not found
    public DefinitionItem definition(String code) {
        return definition(primaryLanguage, code);
    }

    /**
     * String representation of this object
     *
     * @return string form
     */
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("primaryLanguage", primaryLanguage)
                .append("languages", languages)
                .append("terminologies", terminologies)
                .append("termDefinitionsList", termDefinitionsList)
                .append("constraintDefinitionsList", constraintDefinitionsList)
                .append("termBindingList", termBindingList)
                .append("constraintBindingList", constraintBindingList)
                .toString();
    }

    /**
     * Equals if two has the same values
     *
     * @param o
     * @return true if equals
     */
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!( o instanceof ArchetypeOntology )) return false;

        final ArchetypeOntology ao = (ArchetypeOntology) o;

        return new EqualsBuilder()
                .append(primaryLanguage, ao.primaryLanguage)
                .append(languages, ao.languages)
                .append(terminologies, ao.terminologies)
                .append(termDefinitionsList, ao.termDefinitionsList)
                .append(constraintDefinitionsList,
                        ao.constraintDefinitionsList)
                .append(termBindingList, ao.termBindingList)
                .append(constraintBindingList, ao.constraintBindingList)
                .isEquals();
    }

    /**
     * Return a hash code of this object
     *
     * @return hash code
     */
    public int hashCode() {
        return new HashCodeBuilder(7, 29)
                .append(primaryLanguage)
                .append(languages)
                .append(terminologies)
                .append(termDefinitionsList)
                .append(constraintDefinitionsList)
                .append(termBindingList)
                .append(constraintBindingList)
                .toHashCode();
    }

    /* fields */
    private String primaryLanguage;
    private List<String> languages;
    private List<String> terminologies;
    private List<OntologyDefinitions> termDefinitionsList;
    private List<OntologyDefinitions> constraintDefinitionsList;
    private List<OntologyBinding> termBindingList;
    private List<OntologyBinding> constraintBindingList;

    /* calculated fields */
    // outer map language as key, inner map code as key
    private Map<String, Map<String, DefinitionItem>> definitionsMap;
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
 *  The Original Code is ArchetypeOntology.java
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