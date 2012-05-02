/*
 * component:   "openEHR Reference Implementation"
 * description: "Class SimpleTerminologyService"
 * keywords:    "terminology"
 *
 * author:      "Rong Chen <rong.acode@gmail.com>"
 * copyright:   "Copyright (c) 2007 Rong Chen"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL$"
 * revision:    "$LastChangedRevision$"
 * last_change: "$LastChangedDate$"
 */
package org.openehr.terminology;

import java.util.*;
import org.apache.log4j.Logger;
import org.openehr.rm.support.terminology.*;

/**
 * A simple implementation of terminology service that provides
 * complete openEHR terminology and necessary code sets for the 
 * kernel to work properly
 * 
 * TODO: load complete external codesets 
 * 
 * @author rong.chen
 */
public class SimpleTerminologyService implements TerminologyService {

	/**
	 * Gets an instance of terminology service
	 * 
	 * @return terminology service
	 * @throws Exception
	 */
	public static TerminologyService getInstance() throws Exception {
		return soleInstance;
	}
	
	public TerminologyAccess terminology(String name) {
		return terminologies.get(name);
	}

	public CodeSetAccess codeSet(String name) {
		return codeSets.get(name);
	}

	public CodeSetAccess codeSetForId(OpenEHRCodeSetIdentifiers id) {
		String name = codeSetInternalIdToExternalName.get(id.toString());
		if(name == null) {
			return null;
		}
		return codeSets.get(name);
	}

	public boolean hasTerminology(String name) {
		return terminologies.containsKey(name);
	}

	public boolean hasCodeSet(String name) {
		return codeSetInternalIdToExternalName.containsKey(name);
	}

	public List<String> terminologyIdentifiers() {
		return new ArrayList<String>(terminologies.keySet());
	}

	public List<String> codeSetIdentifiers() {
		return new ArrayList<String>(codeSets.keySet());
	}

	public Map<String, String> openehrCodeSets() {
		return Collections.unmodifiableMap(codeSetInternalIdToExternalName);
	}
	
	/*
	 * Creates a simpleTerminologyService
	 */
	private SimpleTerminologyService() {
		
		terminologies = new HashMap<String, TerminologyAccess>();
		codeSets = new HashMap<String, CodeSetAccess>();
		codeSetInternalIdToExternalName = new HashMap<String, String>();
		
		try {			
			TerminologySource terminologySource = 
				TerminologySourceFactory.getOpenEHRTerminology();
			
			loadTerminologies(terminologySource);
			loadCodeSets(terminologySource);
			
			terminologySource = 
			TerminologySourceFactory.getExternalTerminologies();
				
			loadTerminologies(terminologySource);
			loadCodeSets(terminologySource);			
			
		} catch(Exception e) {
			log.error("failed to initialize terminology service..", e);
			throw new RuntimeException(e);
		}
	}
	
	private void loadTerminologies(TerminologySource source) {	
		
		SimpleTerminologyAccess terminology = (SimpleTerminologyAccess)
				terminologies.get(TerminologyService.OPENEHR);
		if(terminology == null) {
			terminology = new SimpleTerminologyAccess(TerminologyService.OPENEHR);
		}
		
		List<Group> groups = source.getConceptGroups();
		for(Group group : groups) {
			Set<String> codes = new HashSet<String>();
			Map<String, String> names = new HashMap<String, String>();
			names.put("en", group.name);
			for(Concept concept : group.concepts) {
				codes.add(concept.id);
				terminology.addRubric("en", concept.id, concept.rubric);
			}
			// English name as group id
			terminology.addGroup(group.name, codes, names);
		}		
		terminologies.put(TerminologyService.OPENEHR, terminology);
	}
	
	private void loadCodeSets(TerminologySource source) {		
		
		for(CodeSet codeset : source.getCodeSets()) {
			SimpleCodeSetAccess codeSetAccess = new SimpleCodeSetAccess(
					codeset.externalId, new HashSet<String>(codeset.codes));
			codeSets.put(codeset.externalId, codeSetAccess);
			codeSetInternalIdToExternalName.put(codeset.openehrId, 
					codeset.externalId);
		}
	}
	
	/* static final field */
	private static final Logger log = 
		Logger.getLogger(SimpleTerminologyService.class);
	
	/* code sets indexed by external codeset name */
	private Map<String, CodeSetAccess> codeSets;
	
	/* terminology indexed by name */
	private Map<String, TerminologyAccess> terminologies;
	
	/* mapping between external name and openEHR codeset id */ 
	private Map<String, String> codeSetInternalIdToExternalName;
	
	/* static instance */
	private static TerminologyService soleInstance = new SimpleTerminologyService();
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
 *  The Original Code is SimpleTerminologyService.java
 *
 *  The Initial Developer of the Original Code is Rong Chen.
 *  Portions created by the Initial Developer are Copyright (C) 2007
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