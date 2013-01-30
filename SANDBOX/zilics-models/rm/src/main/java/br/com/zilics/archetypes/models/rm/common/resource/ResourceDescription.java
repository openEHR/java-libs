
package br.com.zilics.archetypes.models.rm.common.resource;

import java.util.List;
import java.util.Map;


import br.com.zilics.archetypes.models.rm.RMObject;
import br.com.zilics.archetypes.models.rm.annotation.EqualsField;
import br.com.zilics.archetypes.models.rm.annotation.MapItem;
import br.com.zilics.archetypes.models.rm.annotation.NotEmpty;

/**
 * Defines the descriptive meta-data of a resource
 * @author Humberto
 */
public class ResourceDescription extends RMObject {
	private static final long serialVersionUID = 4905592759540628314L;
	@MapItem(key="id")
	@NotEmpty
    @EqualsField
	private Map<String, String> originalAuthor;
    private List<String> otherContributors;
    @NotEmpty
    @EqualsField
    private String lifecycleState;
    @NotEmpty
    @EqualsField
    private List<ResourceDescriptionItem> details;
    private String resourcePackageUri;
    @MapItem
    private Map<String,String> otherDetails;
    private AuthoredResource parentResource;

    /**
     * Default constructor
     */
    public ResourceDescription() {}
    
    /**
     * Another constructor
     * @param originalAuthor the original author
     * @param otherContributors who has contributed too
     * @param lifecycleState the lifecycle state
     * @param details some details
     * @param resourcePackageUri the resource identifier of the package
     * @param otherDetails some other details
     * @param parentResource the parent resource
     */
    public ResourceDescription(Map<String, String> originalAuthor, List<String> otherContributors, String lifecycleState, List<ResourceDescriptionItem> details, String resourcePackageUri, Map<String, String> otherDetails, AuthoredResource parentResource) {
    	this.originalAuthor = originalAuthor;
    	this.otherContributors = otherContributors;
    	this.lifecycleState = lifecycleState;
    	this.details = details;
    	this.resourcePackageUri = resourcePackageUri;
    	this.otherDetails = otherDetails;
    	this.parentResource = parentResource;
    }
    
    /**
     * Get the originalAuthor
     * @return Original author of this resource, with all relevant details, including organisation.
     */
    public Map<String, String> getOriginalAuthor() {
        return getMap(originalAuthor);
    }

    /**
     * Set the originalAuthor
     * @param originalAuthor Original author of this resource, with all relevant details, including organisation.
     */
    public void setOriginalAuthor(Map<String, String> originalAuthor) {
    	assertMutable();
        this.originalAuthor = originalAuthor;
    }

    /**
     * Get the otherContributors
     * @return Other contributors to the resource, probably
     * listed in "name email" form.
     */
    public List<String> getOtherContributors() {
        return getList(otherContributors);
    }

    /**
     * Set the otherContributors
     * @param otherContributors Other contributors to the resource, probably
     * listed in "name email" form.
     */
    public void setOtherContributors(List<String> otherContributors) {
    	assertMutable();
        this.otherContributors = otherContributors;
    }

    /**
     * Get the lifecycleState
     * @return Lifecycle state of the resource, typically
     * including states such as: initial, submitted, experimental,
     * awaiting_approval, approved, superseded, obsolete.
     */
    public String getLifecycleState() {
        return lifecycleState;
    }

    /**
     * Set the lifecycleState
     * @param lifecycleState Lifecycle state of the resource, typically
     * including states such as: initial, submitted, experimental,
     * awaiting_approval, approved, superseded, obsolete.
     */
    public void setLifecycleState(String lifecycleState) {
    	assertMutable();
        this.lifecycleState = lifecycleState;
    }

    /**
     * Get the details
     * @return Details of all parts of resource description
     * that are natural language-dependent, keyed
     * by language code.
     */
    public List<ResourceDescriptionItem> getDetails() {
        return getList(details);
    }

    /**
     * Set the details
     * @param details Details of all parts of resource description
     * that are natural language-dependent, keyed
     * by language code.
     */
    public void setDetails(List<ResourceDescriptionItem> details) {
    	assertMutable();
        this.details = details;
    }

    /**
     * Get the resourcePackageUri
     * @return URI of package to which this resource
     * belongs.
     */
    public String getResourcePackageUri() {
        return resourcePackageUri;
    }

    /**
     * Set the resourcePackageUri
     * @param resourcePackageUri URI of package to which this resource
     * belongs.
     */
    public void setResourcePackageUri(String resourcePackageUri) {
    	assertMutable();
        this.resourcePackageUri = resourcePackageUri;
    }

    /**
     * Get the otherDetails
     * @return Additional non language-senstive resource
     * meta-data, as a list of name/value pairs.
     */
    public Map<String, String> getOtherDetails() {
        return getMap(otherDetails);
    }

    /**
     * Set the otherDetails
     * @param otherDetails Additional non language-senstive resource
     * meta-data, as a list of name/value pairs.
     */
    public void setOtherDetails(Map<String, String> otherDetails) {
    	assertMutable();
        this.otherDetails = otherDetails;
    }

    /**
     * Get the parentResource
     * @return Reference to owning resource.
     */
    public AuthoredResource getParentResource() {
        return parentResource;
    }

    /**
     * Set the parentResource
     * @param parentResource Reference to owning resource.
     */
    public void setParentResource(AuthoredResource parentResource) {
    	assertMutable();
        this.parentResource = parentResource;
    }

}
