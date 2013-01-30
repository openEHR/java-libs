
package br.com.zilics.archetypes.models.rm.common.changecontrol;

import java.util.Set;

import br.com.zilics.archetypes.models.rm.RMObject;
import br.com.zilics.archetypes.models.rm.annotation.EqualsField;
import br.com.zilics.archetypes.models.rm.annotation.NotEmpty;
import br.com.zilics.archetypes.models.rm.annotation.NotNull;
import br.com.zilics.archetypes.models.rm.common.generic.AuditDetails;
import br.com.zilics.archetypes.models.rm.support.identification.HierObjectID;
import br.com.zilics.archetypes.models.rm.support.identification.ObjectRef;

/**
 * Documents a contribution of one or more versions added to a change-controlled
 * repository.
 * @author Humberto
 */
public class Contribution extends RMObject {
	private static final long serialVersionUID = -3686558521283354964L;
	@NotNull
	@EqualsField
	private HierObjectID uid;
	@NotEmpty
    private Set<ObjectRef> versions;
	@NotNull
    private AuditDetails audit;

    /**
     * Get the uid
     * @return Unique identifier for this contribution.
     */
    public HierObjectID getUid() {
        return uid;
    }

    /**
     * Set the uid
     * @param uid Unique identifier for this contribution.
     */
    public void setUid(HierObjectID uid) {
    	assertMutable();
        this.uid = uid;
    }

    /**
     * Get the versions
     * @return Set of references to versions causing changes to
     * this EHR. Each contribution contains a list of versions, which may include paths pointing to any
     * number of versionable items
     */
    public Set<ObjectRef> getVersions() {
        return getSet(versions);
    }

    /**
     * Set the versions
     * @param versions Set of references to versions causing changes to
     * this EHR. Each contribution contains a list of versions, which may include paths pointing to any
     * number of versionable items
     */
    public void setVersions(Set<ObjectRef> versions) {
    	assertMutable();
        this.versions = versions;
    }

    /**
     * Get the audit
     * @return Audit trail corresponding to the committal of this
     * Contribution.
     */
    public AuditDetails getAudit() {
        return audit;
    }

    /**
     * Set the audit
     * @param audit Audit trail corresponding to the committal of this
     * Contribution.
     */
    public void setAudit(AuditDetails audit) {
    	assertMutable();
        this.audit = audit;
    }
}
