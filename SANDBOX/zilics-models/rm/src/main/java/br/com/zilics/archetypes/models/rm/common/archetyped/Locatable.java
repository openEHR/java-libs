
package br.com.zilics.archetypes.models.rm.common.archetyped;

import java.util.Set;

import br.com.zilics.archetypes.models.am.template.openehrprofile.Template;
import br.com.zilics.archetypes.models.rm.annotation.EqualsField;
import br.com.zilics.archetypes.models.rm.annotation.Ignore;
import br.com.zilics.archetypes.models.rm.annotation.MapItem;
import br.com.zilics.archetypes.models.rm.annotation.NotEmpty;
import br.com.zilics.archetypes.models.rm.annotation.NotNull;
import br.com.zilics.archetypes.models.rm.datatypes.text.DvText;
import br.com.zilics.archetypes.models.rm.support.identification.UIDBasedID;
import br.com.zilics.archetypes.models.rm.utils.path.LocatablePathEvaluator;
import br.com.zilics.archetypes.models.rm.utils.path.context.PathEvaluationContext;

/**
 * Root structural class of all information models.
 *
 * @author Humberto
 */
public abstract class Locatable extends Pathable {
	private static final long serialVersionUID = -7950958085710609453L;
	private UIDBasedID uid;
	@NotEmpty
	@EqualsField
	@MapItem
    private String archetypeNodeId;
    @NotNull
    private DvText name;
    @EqualsField
    private Archetyped archetypeDetails;
    private FeederAudit feederAudit;
    private Set<Link> links;

	@Ignore
	private Locatable parent;
	@Ignore
	private String ownerAttributeName;

    /**
     * Get the Uid
     * @return the optional globally unique object identifier for root points of archetyped structures
     */
    public UIDBasedID getUid() {
        return uid;
    }

    /**
     * Set the Uid
     * @param uid the optional globally unique object identifier for root points of archetyped structures
     */
    public void setUid(UIDBasedID uid) {
    	assertMutable();
        this.uid = uid;
    }

    /**
     * Get the Archetype node Id
     * @return the design-time archetype id for this node taken from its generating archetype
     */
    public String getArchetypeNodeId() {
        return archetypeNodeId;
    }

    /**
     * Set the Archetype node Id
     * @param archetypeNodeId the design-time archetype id for this node taken from its generating archetype
     */
    public void setArchetypeNodeId(String archetypeNodeId) {
    	assertMutable();
        this.archetypeNodeId = archetypeNodeId;
    }

    /**
     * Get the runtime name
     * @return the runtime name of this fragment, used to build runtime paths
     */
    public DvText getName() {
        return name;
    }

    /**
     * Set the runtime name
     * @param name the runtime name of this fragment, used to build runtime paths
     */
    public void setName(DvText name) {
    	assertMutable();
        this.name = name;
    }

    /**
     * Get details of the archetype
     * @return details of archetyping used on this node
     */
    public Archetyped getArchetypeDetails() {
        return archetypeDetails;
    }

    /**
     * Set details of the archetype
     * @param archetypeDetails details of archetyping used on this node
     */
    public void setArchetypeDetails(Archetyped archetypeDetails) {
    	assertMutable();
        this.archetypeDetails = archetypeDetails;
    }

    /**
     * Get Feed
     * @return audit trail from non-openEHR system of original commit of information forming the content of this node
     */
    public FeederAudit getFeederAudit() {
        return feederAudit;
    }

    /**
     * Set Feed
     * @param feederAudit audit trail from non-openEHR system of original commit of information forming the content of this node
     */
    public void setFeederAudit(FeederAudit feederAudit) {
    	assertMutable();
        this.feederAudit = feederAudit;
    }

    /**
     * Get links
     * @return links to other archetyped structures(data whose root object inherits from {@link br.com.zilics.archetypes.models.rm.common.archetyped.Locatable}, such as {@link br.com.zilics.archetypes.models.rm.composition.content.entry.Entry},{@link br.com.zilics.archetypes.models.rm.composition.content.navigation.Section} and so on)
     */
    public Set<Link> getLinks() {
        return getSet(links);
    }

    /**
     * Set links
     * @param links links to other archetyped structures(data whose root object inherits from {@link br.com.zilics.archetypes.models.rm.common.archetyped.Locatable}, such as {@link br.com.zilics.archetypes.models.rm.composition.content.entry.Entry},{@link br.com.zilics.archetypes.models.rm.composition.content.navigation.Section} and so on)
     */
    public void setLinks(Set<Link> links) {
    	assertMutable();
        this.links = links;
    }
        
    /**
     * True if this node is the root of an archetyped structure
     * @return true if it is root
     */
    public boolean isArchetypeRoot() {
    	return (archetypeDetails != null);
    }
    
    /**
     * Get the parent
     * @return the parent of this node in compositional hierarchy
     */
    public Locatable getParent() {
        return parent;
    }

    /**
     * Set the parent
     * @param parent the parent of this node in compositional hierarchy
     */
    public void setParent(Locatable parent) {
    	assertMutable();
        this.parent = parent;
    }
 
    /**
     * Get the owner attribute name 
     * @return the owner attribute name
     */
    public String getOwnerAttributeName() {
    	return ownerAttributeName;
    }
    
    /**
     * Set the owner attribute name
     * @param ownerAttributeName the owner attribute name
     */
    public void setOwnerAttributeName(String ownerAttributeName) {
    	assertMutable();
    	this.ownerAttributeName = ownerAttributeName;
    }
    

    /**
     * Build up the full canonical path of this locatable
     * @return the full canonical path
     */
    public String getTemplatePath() {
    	StringBuilder sb = new StringBuilder();
    	getTemplatePath(sb);
    	if (sb.length() == 0) return "/";
    	return sb.toString();
    }
    
    private void getTemplatePath(StringBuilder sb) {
    	if (parent != null) {
    		parent.getTemplatePath(sb);
    		sb.append("/").append(ownerAttributeName);
    		if (archetypeDetails != null) {
    			if (archetypeDetails.getArchetypeId() != null)
    				sb.append("[").append(archetypeDetails.getArchetypeId().getValue()).append("]");
    		}
    		sb.append("[").append(archetypeNodeId).append("]");
    	}
    }
    
    /**
     * Set up a new {@link PathEvaluationContext} for A-path queries 
     * @param template the template to use in conjunction with the locatable
     * @return the context
     */
    public PathEvaluationContext getPathEvaluatorContext(Template template) {
    	return new PathEvaluationContext(new LocatablePathEvaluator(this, template));
    }
    
    /**
     * {@inheritDoc} 
     */
    @Override
    public String toString() {
    	return this.getClass().getSimpleName() + ": " + this.getTemplatePath();
    }
}
