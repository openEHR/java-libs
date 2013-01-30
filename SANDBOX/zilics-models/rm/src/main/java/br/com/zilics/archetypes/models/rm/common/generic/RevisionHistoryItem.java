
package br.com.zilics.archetypes.models.rm.common.generic;

import java.util.List;

import br.com.zilics.archetypes.models.rm.RMObject;
import br.com.zilics.archetypes.models.rm.annotation.EqualsField;
import br.com.zilics.archetypes.models.rm.annotation.NotEmpty;
import br.com.zilics.archetypes.models.rm.annotation.NotNull;
import br.com.zilics.archetypes.models.rm.support.identification.ObjectVersionID;

/**
 * An entry in a revision history, corresponding to a version from a versioned container.
 * Consists of AUDIT_DETAILS instances with revision identifier of the revision to which
 * the AUDIT_DETAILS intance belongs.
 * @author Humberto
 */
public class RevisionHistoryItem extends RMObject {
	private static final long serialVersionUID = 3984990290583030212L;
	@NotEmpty
	@EqualsField
	private List<AuditDetails> audits;
	@NotNull
	@EqualsField
    private ObjectVersionID versionId;

	/**
	 * Get the audits
	 * @return The audits for this revision; there will always
     * be at least one commit audit (which may itself
     * be an ATTESTATION), there may also be further attestations.
	 */
    public List<AuditDetails> getAudits() {
        return getList(audits);
    }

    /**
     * Set the audits
     * @param audits The audits for this revision; there will always
     * be at least one commit audit (which may itself
     * be an ATTESTATION), there may also be further attestations.
     */
    public void setAudits(List<AuditDetails> audits) {
    	assertMutable();
        this.audits = audits;
    }

    /**
     * Get the versionId
     * @return Version identifier for this revision.
     */
    public ObjectVersionID getVersionId() {
        return versionId;
    }

    /**
     * Set the versionId
     * @param versionId Version identifier for this revision.
     */
    public void setVersionId(ObjectVersionID versionId) {
    	assertMutable();
        this.versionId = versionId;
    }

}
