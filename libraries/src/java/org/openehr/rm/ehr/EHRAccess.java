/*
 * EHRAccess.java
 *
 * Created on August 7, 2006, 1:33 PM
 *
 */

package org.openehr.rm.ehr;

import java.util.Set;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.openehr.rm.Attribute;
import org.openehr.rm.common.archetyped.Archetyped;
import org.openehr.rm.common.archetyped.FeederAudit;
import org.openehr.rm.common.archetyped.Link;
import org.openehr.rm.common.archetyped.Locatable;
import org.openehr.rm.datatypes.text.DvCodedText;
import org.openehr.rm.datatypes.text.DvText;
import org.openehr.rm.security.AccessControlSettings;
import org.openehr.rm.support.identification.ObjectID;

/**
 *
 * @author yinsulim
 */
public class EHRAccess extends Locatable {
    
    /** Creates a new instance of EHRAccess */
    public EHRAccess(
            @Attribute(name = "uid") ObjectID uid,
            @Attribute(name = "archetypeNodeId", required = true) String archetypeNodeId,
            @Attribute(name = "name", required = true) DvText name,
            @Attribute(name = "archetypeDetails") Archetyped archetypeDetails,
            @Attribute(name = "feederAudit") FeederAudit feederAudit,
            @Attribute(name = "links") Set<Link> links,
            @Attribute(name = "parent") Locatable parent,
            @Attribute(name = "settings") AccessControlSettings settings) {
        
        super(uid, archetypeNodeId, name, archetypeDetails, feederAudit, links, parent);
        if(settings == null) {
            throw new IllegalArgumentException("null settings");
        }
        this.settings = settings;
    }

    /**
     * The name of the access control scheme in use; corresponds to the concrete instance
     * of the settings attribute.
     */
    public String scheme() {
        return ""; //TODO: implement here or in AccessContolSettings
    }
    
    /**
     * Access control settings for the EHR. Instance is a subtype of the type 
     * AccessControlSettings, allowing for the use of different access control schemes.
     */
    public AccessControlSettings getSettings() {
        return settings;
    }
    
    public String pathOfItem(Locatable item) {
        return "";
    }
 
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!( o instanceof EHRAccess)) return false;
        if (!super.equals(o)) return false;

        final EHRAccess ehrA = (EHRAccess) o;
        return new EqualsBuilder()
                .append(settings, ehrA.settings)
                .isEquals();
    }
    
        /**
     * Return a hash code of this actor
     *
     * @return hash code
     */
    public int hashCode() {
        return new HashCodeBuilder(19, 83)
                .appendSuper(super.hashCode())
                .append(settings)
                .toHashCode();
    }
    
    //POJO
    EHRAccess() {}
    
    void setSettings(AccessControlSettings settings) {
        this.settings = settings;
    }
    
    /* private fields */
    private AccessControlSettings settings;
}
