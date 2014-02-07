/*
 * component:   "openEHR Reference Implementation"
 * description: "Class AuthoredResource"
 * keywords:    "resource"
 *
 * author:      "Yin Su Lim <y.lim@chime.ucl.ac.uk>"
 * support:     "CHIME, UCL"
 * copyright:   "Copyright (c) 2006 UCL, UK"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/TRUNK/libraries/src/java/org/openehr/rm/common/resource/AuthoredResource.java $"
 * revision:    "$LastChangedRevision: 53 $"
 * last_change: "$LastChangedDate: 2006-08-11 13:20:08 +0100 (Fri, 11 Aug 2006) $"
 */
package org.openehr.rm.common.resource;

import java.util.*;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.openehr.rm.RMObject;
import org.openehr.rm.common.generic.RevisionHistory;
import org.openehr.rm.datatypes.text.CodePhrase;
import org.openehr.rm.support.terminology.OpenEHRCodeSetIdentifiers;
import org.openehr.rm.support.terminology.TerminologyService;

/**
 * Abstract idea of an online resource created by a human author
 * 
 * @author Yin Su Lim
 * @version 1.0
 */
public abstract class AuthoredResource extends RMObject {

	/**
	 * Construct an AuthoredResource
	 * 
	 * @param originalLanguage
	 * @param translations
	 * @param description
	 * @param revisionHistory
	 * @param isControlled
	 */
	public AuthoredResource(CodePhrase originalLanguage,
			Map<String, TranslationDetails> translations,
			ResourceDescription description, RevisionHistory revisionHistory,
			boolean isControlled, TerminologyService terminologyService) {
		if (originalLanguage == null) {
			throw new IllegalArgumentException("null originalLanguage");
		}
		if (translations != null) {
			if (translations.isEmpty()) {
				throw new IllegalArgumentException("empty translations");
			}
			if (translations.containsKey(originalLanguage.getCodeString())) {
				throw new IllegalArgumentException(
						"original language in translations");
			}
		}
		 
		// TODO
		// if (terminologyService == null) {
		// 	throw new IllegalArgumentException("null terminology service");
		// }
		if (terminologyService != null 
				&& !terminologyService.codeSetForId(
						OpenEHRCodeSetIdentifiers.LANGUAGES).hasCode(originalLanguage)) {
			throw new IllegalArgumentException("unknown original language " + 
					originalLanguage);
		}
		if (isControlled == (revisionHistory == null)) {
			throw new IllegalArgumentException(
					"breach of revision history validity");
		}
		this.originalLanguage = originalLanguage;
		this.translations = translations;
		setDescription(description);
		this.revisionHistory = revisionHistory;
		this.isControlled = isControlled;
	}

	/**
	 * Description and lifecycle information of the resource
	 * 
	 * @return description
	 */
	public ResourceDescription getDescription() {
		return description;
	}

	/**
	 * True if this resource is under any kind of change control, 
	 * in which case revision history is created
	 * 
	 * @return true if under change control
	 */
	public boolean isControlled() {
		return isControlled;
	}

	/**
	 * Language in which this resource was initially authored.
	 * 
	 * @return originalLanguage
	 */
	public CodePhrase getOriginalLanguage() {
		return originalLanguage;
	}

	/**
	 * The revision history of the resource. Only required 
	 * if isControlled is true.
	 * 
	 * @return revisionHistory
	 */
	public RevisionHistory getRevisionHistory() {
		return revisionHistory;
	}

	/**
	 * List of details for each natural translation made of this 
	 * resource, keyed by language.
	 * 
	 * @return translations
	 */
	public Map<String, TranslationDetails> getTranslations() {
		return translations;
	}

	/**
	 * Current revision....
	 *TODO check the spec...
	 * @return 
	 */
	public String currentRevision() {

		if (isControlled) {
			return revisionHistory.mostRecentVersionId();
		} else {
			return "uncontrolled";
		}
	}

	/**
	 * Total list of languages available in  this resource, 
	 * derived from originalLanguage and translations
	 *
	 *@return set of languages available
	 */
	public Set<String> languagesAvailable() {
		Set<String> languages = null;
		if (translations != null) { //TODO: && translations.size != 0?
			languages = new HashSet<String>(translations.keySet());
		} else {
			languages = new HashSet<String>();
		}
		languages.add(originalLanguage.getCodeString());
		return languages;
	}

	/**
	 * Equals if two authored resource have same values
	 *
	 * @param o
	 * @return equals if true
	 */
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof AuthoredResource))
			return false;

		final AuthoredResource ar = (AuthoredResource) o;
		return new EqualsBuilder().append(description, ar.description).append(
				originalLanguage, ar.originalLanguage).append(translations,
				ar.translations).append(revisionHistory, ar.revisionHistory)
				.append(isControlled, ar.isControlled).isEquals();
	}

	/**
	 * Return a hash code of this authored resource
	 *
	 * @return hash code
	 */
	public int hashCode() {
		return new HashCodeBuilder(19, 43).append(description).append(
				originalLanguage).append(translations).append(revisionHistory)
				.append(isControlled).toHashCode();
	}

	//POJO start
	AuthoredResource() {
	}

	void setDescription(ResourceDescription description)
			throws IllegalArgumentException {
		if (description != null && translations != null) {
			//if (!description.equals(this.description)) {
			//if(this.description != description) {
			if (description.getParentResource() != this) {
				for (ResourceDescriptionItem rdi : description.getDetails()) {
					if (!translations.containsKey(rdi.getLanguage()
							.getCodeString())
							&& !originalLanguage.getCodeString().equals(
									rdi.getLanguage().getCodeString())) {
						throw new IllegalArgumentException(
								"the language of description details not in translations");
					}
				}
			}
		}
		this.description = description;
		if (description != null && description.getParentResource() != this) {
			description.setParentResource(this);
		}
	}

	void setControlled(boolean isControlled) {
		this.isControlled = isControlled;
	}

	void setOriginalLanguage(CodePhrase originalLanguage) {
		this.originalLanguage = originalLanguage;
	}

	void setRevisionHistory(RevisionHistory revisionHistory) {
		this.revisionHistory = revisionHistory;
	}

	void setTranslations(Map<String, TranslationDetails> translations) {
		this.translations = translations;
	}

	//POJO end

	/* fields */
	private CodePhrase originalLanguage;

	private Map<String, TranslationDetails> translations;

	private ResourceDescription description;

	private RevisionHistory revisionHistory;

	private boolean isControlled;

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
 *  The Original Code is AuthoredResource.java
 *
 *  The Initial Developer of the Original Code is Yin Su Lim.
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