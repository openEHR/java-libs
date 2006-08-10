/**
 * 
 */
package org.openehr.rm.ehr;

import java.util.Set;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import org.openehr.rm.Attribute;
import org.openehr.rm.FullConstructor;
import org.openehr.rm.common.archetyped.Archetyped;
import org.openehr.rm.common.archetyped.FeederAudit;
import org.openehr.rm.common.archetyped.Link;
import org.openehr.rm.common.archetyped.Locatable;
import org.openehr.rm.common.generic.PartySelf;
import org.openehr.rm.datastructure.itemstructure.ItemStructure;
import org.openehr.rm.datatypes.text.DvText;
import org.openehr.rm.support.identification.ObjectID;

/**
 * @author yinsulim
 *
 */
public class EHRStatus extends Locatable {

    @FullConstructor public EHRStatus(
            @Attribute(name = "uid", required = true) ObjectID uid,
            @Attribute(name = "archetypeNodeId", required = true) String archetypeNodeId,
            @Attribute(name = "name", required = true) DvText name,
            @Attribute(name = "archetypeDetails", required = true) Archetyped archetypeDetails,
            @Attribute(name = "feederAudit") FeederAudit feederAudit,
            @Attribute(name = "links") Set<Link> links,
            @Attribute(name = "parent") Locatable parent,
            @Attribute(name = "subject", system = true) PartySelf subject,
            @Attribute(name = "isQueryable", required = true) boolean isQueryable,
            @Attribute(name = "isModifiable", required = true) boolean isModifiable,
            @Attribute(name = "otherDetails") ItemStructure otherDetails ) {
        super(uid, archetypeNodeId, name, archetypeDetails, feederAudit, links, parent);
        if (subject == null) {
            throw new IllegalArgumentException("null subject");
        }
        if (parent != null) {
            throw new IllegalArgumentException("parent must be null");
        }
        if (!isArchetypeRoot()) {
            throw new IllegalArgumentException("not archetype root");
        }
        this.subject = subject;
        this.isQueryable = isQueryable;
        this.isModifiable = isModifiable;
        this.otherDetails = otherDetails;
    }
    
    /**
     * The subject of this EHR. The externalRef attribute can be used to contain a direct
     * reference to the subject in a demographic or identity service. Alternatively, the
     * association between patients and their records may be done elsewhere for security
     * reasons.
     */
    public PartySelf getSubject() {
        return subject;
    }
    
    /**
     * True if this EHR should be included in population queries, i.e. if this EHR
     * is considered active in the population.
     */
    public boolean getIsQueryable() {
        return isQueryable;
    }
    
    /**
     * True if this EHR is allowed to be written to.
     */
    public boolean getIsModifiable() {
        return isModifiable;
    }
    
    /**
     * Any other details of the EHR summary object, in the form of an archetyped itemStructure.
     */
    public ItemStructure getOtherDetails() {
        return otherDetails;
    }
    
    
    @Override
    public String pathOfItem(Locatable item) {
        // TODO Auto-generated method stub
        return null;
    }

    public boolean validPath(String path) {
        try {
            itemAtPath(path);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    } 
    
    public Object itemAtPath(String path) {
        Object item = super.itemAtPath(path);
        if(item != null) {
            return item;
        }
        String tmp = path;
        item = checkAttribute(tmp, "otherDetails", otherDetails);
        if(item != null) {
            return item;
        } else {
            throw new IllegalArgumentException("invalid path: " + path);
        }
        
    }
    
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!( o instanceof EHRStatus )) return false;
        if (!super.equals(o)) return false;
        
        final EHRStatus ehrS = (EHRStatus) o;
        
        return new EqualsBuilder()
        .append(subject, ehrS.subject)
        .append(isQueryable, ehrS.isQueryable)
        .append(isModifiable, ehrS.isModifiable)
        .append(otherDetails, ehrS.otherDetails)
        .isEquals();
    }
    
    public int hashCode() {
        return new HashCodeBuilder(79, 19)
        .appendSuper(super.hashCode())
        .append(subject)
        .append(isQueryable)
        .append(isModifiable)
        .append(otherDetails)
        .toHashCode();
    }
    
    //POJO
    void setSubject(PartySelf subject) {
        this.subject = subject;
    }
    
    void setIsQueryable(boolean isQueryable) {
        this.isQueryable = isQueryable;
    }
    
    void setIsModifiable(boolean isModifiable) {
        this.isModifiable = isModifiable;
    }
    
    void setOtherDetails(ItemStructure otherDetails) {
        this.otherDetails = otherDetails;
    }
    
    EHRStatus() {}
    
    /* fields */
    private PartySelf subject;
    private boolean isQueryable;
    private boolean isModifiable;
    private ItemStructure otherDetails;

}
