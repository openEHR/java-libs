/*
 * component:   "openEHR Reference Implementation"
 * description: "Class CDvCodedText"
 * keywords:    "openehr archetype profile"
 *
 * author:      "Rong Chen <rong@acode.se>"
 * support:     "Acode HB <support@acode.se>"
 * copyright:   "Copyright (c) 2006 Acode HB, Sweden"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL$"
 * revision:    "$LastChangedRevision$"
 * last_change: "$LastChangedDate$"
 */

package org.openehr.am.openehrprofile.datatypes.text;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.openehr.am.archetype.constraintmodel.ArchetypeConstraint;
import org.openehr.am.archetype.constraintmodel.CComplexObject;
import org.openehr.am.archetype.constraintmodel.CDomainType;
import org.openehr.am.archetype.ontology.ArchetypeOntology;
import org.openehr.rm.support.basic.Interval;
import org.openehr.rm.support.identification.TerminologyID;

import java.util.List;


/**
 * Express constraints on instances of DV_CODED_TEXT. 
 * 
 * @author Rong Chen
 *
 */
public class CDvCodedText extends CDomainType {
	
	/**
	 * Creates a CDvCodedText
	 * 
	 * @param path
	 * @param rmTypeName
	 * @param occurrences
	 * @param nodeID
	 * @param terminologyId null if unspecified
	 * @param codeList null if unspecified
	 * @param subset null if unspecified
	 * @param query null if unspecified
	 * @throws IllegalArgumentException  
	 */
	public CDvCodedText(String path, Interval<Integer> occurrences, 
			String nodeID, TerminologyID terminologyId, 
			List<String > codeList, String subset, String query) {
		
		super(path, "DvCodedText", occurrences, nodeID);
		
		if(terminologyId != null && codeList == null) {
			throw new IllegalArgumentException("codeList null");			
		}
		
		if(subset != null) {
			if(StringUtils.isEmpty(subset)) {
				throw new IllegalArgumentException("subset empty");
			}
			if(terminologyId == null) {
				throw new IllegalArgumentException("terminologyId null");
			}
		}
		
		if(terminologyId == null && query == null) {
			throw new IllegalArgumentException(
					"both terminologyId and query null");
		}
		
		//Any_allowed_validity: code_list.is_empty implies any_allowed
		
	}
	
	/**
	 * List of codes; may be empty
	 * 
	 * @return empty if unspecifed
	 */
	public List<String> getCodeList() {
		return codeList;
	}

	/*
	 * Constraint in terms of an abstract query
	 * expression to be addressed to a terminology.
	 * 
	 * @return null if unspecified
	 */
	public String getQuery() {
		return query;
	}

	/**
	 * Optional name of subset in terminology
	 * from which codes must come. Only useful
	 * for terminologies which support subsetting.
	 * 
	 * @return list of codes, empty unspecified
	 */
	public String getSubset() {
		return subset;
	}

	/**
	 * Syntax string expressing constraint on allowed primary terms
	 * 
	 * @return terminologyId
	 */
	public TerminologyID getTerminologyId() {
		return terminologyId;
	}

	@Override
	public CComplexObject standardRepresentation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isValid() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasPath(String path) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isSubsetOf(ArchetypeConstraint constraint) {
		// TODO Auto-generated method stub
		return false;
	}
	
	private TerminologyID terminologyId;
	private List<String> codeList;
	private String subset;
	private String query;
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
 *  The Original Code is CDvCodedText.java
 *
 *  The Initial Developer of the Original Code is Rong Chen.
 *  Portions created by the Initial Developer are Copyright (C) 2003-2006
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