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

import java.util.List;

import org.openehr.am.archetype.constraintmodel.ArchetypeConstraint;
import org.openehr.am.archetype.constraintmodel.CAttribute;
import org.openehr.am.archetype.constraintmodel.CComplexObject;
import org.openehr.am.archetype.constraintmodel.CDomainType;
import org.openehr.rm.datatypes.text.CodePhrase;
import org.openehr.rm.support.basic.Interval;
import org.openehr.rm.support.identification.TerminologyID;

/**
 * Express constraints on instances of DV_CODED_TEXT.
 * 
 * @author Rong Chen
 * 
 */
public class CCodePhrase extends CDomainType<CodePhrase> {

	/**
	 * Creates a CDvCodedText
	 * 
	 * @param path
	 * @param rmTypeName
	 * @param occurrences
	 * @param nodeID
	 * @param parent
	 * @param terminologyId
	 *            null if unspecified
	 * @param codeList
	 *            null if unspecified
	 * @throws IllegalArgumentException 
	 */
	public CCodePhrase(String path, Interval<Integer> occurrences,
			String nodeID, CAttribute parent, TerminologyID terminologyId, 
			List<String> codeList, CodePhrase defaultValue, 
			CodePhrase assumedValue) {

		super(terminologyId == null && codeList == null,
				path, "CodePhrase", occurrences, nodeID, defaultValue, 
				assumedValue, parent);

		if (codeList != null) {
			if(codeList.isEmpty()) {
				throw new IllegalArgumentException("codeList empty");
			}
			if (terminologyId == null) {
				throw new IllegalArgumentException("terminologyId null");
			}		
		}

		this.terminologyId = terminologyId;
		this.codeList = codeList;		
	}	

	/**
	 * List of codes
	 * 
	 * @return null if unspecifed
	 */
	public List<String> getCodeList() {
		return codeList;
	}	

	/**
	 * Syntax string expressing constraint on allowed primary terms
	 * 
	 * @return null if unspecified
	 */
	public TerminologyID getTerminologyId() {
		return terminologyId;
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
	
	@Override
	public boolean validValue(CodePhrase value) {
		if(value == null) {
			return false;
		}
		if(terminologyId != null 
				&& !terminologyId.equals(value.getTerminologyId())) {
			return false;
		}
		if(codeList != null && !codeList.contains(value.getCodeString())) {
			return false;
		}
	
		return true;
	}

	@Override
	public CComplexObject standardEquivalent() {
		// TODO Auto-generated method stub
		return null;
	}
	
	private TerminologyID terminologyId;
	private List<String> codeList;
}

/*
 * ***** BEGIN LICENSE BLOCK ***** Version: MPL 1.1/GPL 2.0/LGPL 2.1
 * 
 * The contents of this file are subject to the Mozilla Public License Version
 * 1.1 (the 'License'); you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at http://www.mozilla.org/MPL/
 * 
 * Software distributed under the License is distributed on an 'AS IS' basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License for
 * the specific language governing rights and limitations under the License.
 * 
 * The Original Code is CCodePhrase.java
 * 
 * The Initial Developer of the Original Code is Rong Chen. Portions created by
 * the Initial Developer are Copyright (C) 2003-2006 the Initial Developer. All
 * Rights Reserved.
 * 
 * Contributor(s):
 * 
 * Software distributed under the License is distributed on an 'AS IS' basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License for
 * the specific language governing rights and limitations under the License.
 * 
 * ***** END LICENSE BLOCK *****
 */