/*
 * component:   "openEHR Reference Implementation"
 * description: "Class ResourceDescription"
 * keywords:    "resource"
 *
 * author:      "Yin Su Lim <y.lim@chime.ucl.ac.uk>"
 * support:     "CHIME, UCL"
 * copyright:   "Copyright (c) 2006 UCL, UK"
 * license:     "See notice at bottom of class"
 *
 * file:        "$URL: http://svn.openehr.org/ref_impl_java/TRUNK/libraries/src/java/org/openehr/rm/common/resource/ResourceDescription.java $"
 * revision:    "$LastChangedRevision: 53 $"
 * last_change: "$LastChangedDate: 2006-08-11 13:20:08 +0100 (Fri, 11 Aug 2006) $"
 */
package org.openehr.rm.common.resource;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.openehr.rm.Attribute;

/**
 * Defines the descriptive meta-data of a resource.
 * 
 * @author Yin Su Lim
 * @version 1.0
 */
public class ResourceDescription implements Serializable{

	/**
     * 
     */
    private static final long serialVersionUID = 1L;
	/**
	 * Construct ResourceDescription
	 */
	public ResourceDescription(
			@Attribute(name = "originalAuthor", required = true) Map<String, String> originalAuthor,
			@Attribute(name = "otherContributors") List<String> otherContributors,
			@Attribute(name = "lifecycleState", required = true) String lifecycleState,
			@Attribute(name = "details", required = true) List<ResourceDescriptionItem> details,
			@Attribute(name = "resourcePackageUri") String resourcePackageUri,
			@Attribute(name = "otherDetails") Map<String, String> otherDetails,
			@Attribute(name = "parentResource") AuthoredResource parentResource ){
		if (originalAuthor == null || originalAuthor.size() == 0 ) {
			// throw new IllegalArgumentException("null or empty originalAuthor");
			System.out.println("Warning...parsing archetype with null or empty original author");
		}
		if (StringUtils.isEmpty(lifecycleState) ) {
			throw new IllegalArgumentException("null or empty lifecycleState");
		}
		if (details == null || details.size() == 0 ) {
			throw new IllegalArgumentException("null or empty details");
		}		
		this.originalAuthor = originalAuthor;
		this.otherContributors = otherContributors;
		this.lifecycleState = lifecycleState;
		this.details = details;
		this.resourcePackageUri = resourcePackageUri;
		this.otherDetails = otherDetails;
		setParentResource(parentResource);		
	}

	/**
	 * Details of all parts of resource description that are natural 
	 * language-dependent
	 * 
	 * @return details
	 */
	public List<ResourceDescriptionItem> getDetails() {
		return details;
	}

	/**
	 * Lifecycle state of the resource, typically including states 
	 * such as: initial, submitted, experimental...etc
	 * 
	 * @return lifecycleState
	 */
	public String getLifecycleState() {
		return lifecycleState;
	}

	/**
	 * Original author of this resource, with all relevant details, 
	 * including organisation.
	 * 
	 * @return originalAuthor
	 */
	public Map<String, String> getOriginalAuthor() {
		return originalAuthor;
	}

	/**
	 * Other contributors to the resource, probably listed 
	 * in "name<email>" form
	 * 
	 * @return otherContributors
	 */
	public List<String> getOtherContributors() {
		return otherContributors;
	}

	/**
	 * Additional non language-sensitive resource meta-data, 
	 * as a list of name/value pairs
	 * 
	 * @return otherDetails
	 */
	public Map<String, String> getOtherDetails() {
		return otherDetails;
	}

	/**
	 * Reference to owning resource
	 * 
	 * @return parentResource
	 */
	public AuthoredResource getParentResource() {
		return parentResource;
	}

	/**
	 * URI of package to which this resource belongs
	 * 
	 * @return resourcePackageUri
	 */
	public String getResourcePackageUri() {
		return resourcePackageUri;
	}

	//POJO start
	public ResourceDescription() {
	}
	
	void setDetails(List<ResourceDescriptionItem> details) {
		this.details = details;
	}

	void setLifecycleState(String lifecycleState) {
		this.lifecycleState = lifecycleState;
	}

	void setOriginalAuthor(Map<String, String> originalAuthor) {
		this.originalAuthor = originalAuthor;
	}

	void setOtherContributors(List<String> otherContributors) {
		this.otherContributors = otherContributors;
	}

	void setOtherDetails(Map<String, String> otherDetails) {
		this.otherDetails = otherDetails;
	}

	void setResourcePackageUri(String resourcePackageUri) {
		this.resourcePackageUri = resourcePackageUri;
	}

	void setParentResource(AuthoredResource parentResource) {
            
            boolean parentDesc = (parentResource != null) && 
                    parentResource.getDescription() != this;
            if(parentDesc) {
               languageValidCheck(parentResource, this.details);
            }
            this.parentResource = parentResource;
            if(parentDesc) {
                this.parentResource.setDescription(this);
            }
	}
        
        void languageValidCheck(AuthoredResource parent, List<ResourceDescriptionItem> details) {
            Set<String> languages = parent.languagesAvailable();
            for(ResourceDescriptionItem rdi : details) {
                if(!languages.contains(rdi.getLanguage().getCodeString())) {
                    throw new IllegalArgumentException("breach of language validity");
                }
            }
        }
        
	//POJO end
	
	/* fields */
	private Map<String, String> originalAuthor;
	private List<String> otherContributors;
	private String lifecycleState;
	private List<ResourceDescriptionItem> details;
	private String resourcePackageUri;
	private Map<String, String> otherDetails;
	private AuthoredResource parentResource;
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((details == null) ? 0 : details.hashCode());
		result = prime * result
				+ ((lifecycleState == null) ? 0 : lifecycleState.hashCode());
		result = prime * result
				+ ((originalAuthor == null) ? 0 : originalAuthor.hashCode());
		result = prime
				* result
				+ ((otherContributors == null) ? 0 : otherContributors
						.hashCode());
		result = prime * result
				+ ((otherDetails == null) ? 0 : otherDetails.hashCode());
		result = prime * result
				+ ((parentResource == null) ? 0 : parentResource.hashCode());
		result = prime
				* result
				+ ((resourcePackageUri == null) ? 0 : resourcePackageUri
						.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ResourceDescription other = (ResourceDescription) obj;
		if (details == null) {
			if (other.details != null)
				return false;
		} else if (!details.equals(other.details))
			return false;
		if (lifecycleState == null) {
			if (other.lifecycleState != null)
				return false;
		} else if (!lifecycleState.equals(other.lifecycleState))
			return false;
		if (originalAuthor == null) {
			if (other.originalAuthor != null)
				return false;
		} else if (!originalAuthor.equals(other.originalAuthor))
			return false;
		if (otherContributors == null) {
			if (other.otherContributors != null)
				return false;
		} else if (!otherContributors.equals(other.otherContributors))
			return false;
		if (otherDetails == null) {
			if (other.otherDetails != null)
				return false;
		} else if (!otherDetails.equals(other.otherDetails))
			return false;
		if (parentResource == null) {
			if (other.parentResource != null)
				return false;
		} else if (!parentResource.equals(other.parentResource))
			return false;
		if (resourcePackageUri == null) {
			if (other.resourcePackageUri != null)
				return false;
		} else if (!resourcePackageUri.equals(other.resourcePackageUri))
			return false;
		return true;
	}
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
 *  The Original Code is ResourceDescription.java
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