/**
 * 
 */
package org.openehr.rm.common.resource;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.openehr.rm.Attribute;
import org.openehr.rm.support.identification.ObjectID;

/**
 * 
 * 
 * @author Yin Su Lim
 * @version 1.0
 */
public class ResourceDescription {

	/**
	 * Construct ResourceDescription
	 */
	public ResourceDescription(
			@Attribute(name = "originalAuthor", required = true) HashMap<String, String> originalAuthor,
			@Attribute(name = "otherContributors") List<String> otherContributors,
			@Attribute(name = "lifecycleState", required = true) String lifecycleState,
			@Attribute(name = "details", required = true) List<ResourceDescriptionItem> details,
			@Attribute(name = "resourcePackageUri") String resourcePackageUri,
			@Attribute(name = "otherDetails") HashMap<String, String> otherDetails,
			@Attribute(name = "parentResource") AuthoredResource parentResource ){
		if (originalAuthor == null || originalAuthor.size() == 0 ) {
			throw new IllegalArgumentException("null or empty originalAuthor");
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
	public HashMap<String, String> getOriginalAuthor() {
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
	public HashMap<String, String> getOtherDetails() {
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
	ResourceDescription() {
	}
	
	void setDetails(List<ResourceDescriptionItem> details) {
		this.details = details;
	}

	void setLifecycleState(String lifecycleState) {
		this.lifecycleState = lifecycleState;
	}

	void setOriginalAuthor(HashMap<String, String> originalAuthor) {
		this.originalAuthor = originalAuthor;
	}

	void setOtherContributors(List<String> otherContributors) {
		this.otherContributors = otherContributors;
	}

	void setOtherDetails(HashMap<String, String> otherDetails) {
		this.otherDetails = otherDetails;
	}

	void setResourcePackageUri(String resourcePackageUri) {
		this.resourcePackageUri = resourcePackageUri;
	}

	void setParentResource(AuthoredResource parentResource) {
		this.parentResource = parentResource;
		if (parentResource != null && (parentResource.getDescription() != this)) {
			parentResource.setDescription(this);
		}		
	}
	//POJO end
	
	/* fields */
	private HashMap<String, String> originalAuthor;
	private List<String> otherContributors;
	private String lifecycleState;
	private List<ResourceDescriptionItem> details;
	private String resourcePackageUri;
	private HashMap<String, String> otherDetails;
	private AuthoredResource parentResource;
}
