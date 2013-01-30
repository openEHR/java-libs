
package br.com.zilics.archetypes.models.rm.common.changecontrol;

import br.com.zilics.archetypes.models.rm.RMObject;
import br.com.zilics.archetypes.models.rm.annotation.EqualsField;
import br.com.zilics.archetypes.models.rm.annotation.NotNull;
import br.com.zilics.archetypes.models.rm.common.generic.AuditDetails;
import br.com.zilics.archetypes.models.rm.datatypes.text.DvCodedText;
import br.com.zilics.archetypes.models.rm.support.identification.ObjectRef;
import br.com.zilics.archetypes.models.rm.support.identification.ObjectVersionID;

/**
 * Abstract model of one Version within a Version container, containing data,
 * commit audit trail, and the identifier of its {@link br.com.zilics.archetypes.models.rm.common.changecontrol.Contribution}.
 * @author Humberto
 * @param <T> Type of the versioned object 
 */
public abstract class Version<T extends RMObject> extends RMObject {
	private static final long serialVersionUID = -533781298208092832L;
	@NotNull
	@EqualsField
	private ObjectVersionID uid;
    private ObjectVersionID precedingVersionUid;
	@NotNull
    private T data;
	@NotNull
    private DvCodedText lifecycleState;

	@NotNull
    private AuditDetails commitAudit;
	@NotNull
    private ObjectRef contribution;
    private String signature;

    /**
     * Get the uid
     * @return Unique identifier of this version, containing owner_id, version_tree_id and creating_system_id.
     */
    public ObjectVersionID getUid() {
        return uid;
    }

    /**
     * Set the uid
     * @param uid Unique identifier of this version, containing owner_id, version_tree_id and creating_system_id.
     */
    public void setUid(ObjectVersionID uid) {
    	assertMutable();
        this.uid = uid;
    }

    /**
     * Get the precedingVersionUid
     * @return Unique identifier of the version of which this version is a modification; Void if this is the first version.
     */
    public ObjectVersionID getPrecedingVersionUid() {
        return precedingVersionUid;
    }

    /**
     * Set the precedingVersionUid
     * @param precedingVersionUid Unique identifier of the version of which this version is a modification; Void if this is the first version.
     */
    public void setPrecedingVersionUid(ObjectVersionID precedingVersionUid) {
    	assertMutable();
        this.precedingVersionUid = precedingVersionUid;
    }

    /**
     * Get the commitAudit
     * @return Audit trail corresponding to the committal of this version to the versioned object.
     */
    public AuditDetails getCommitAudit() {
        return commitAudit;
    }

    /**
     * Set the commitAudit
     * @param commitAudit Audit trail corresponding to the committal of this version to the versioned object
     */
    public void setCommitAudit(AuditDetails commitAudit) {
    	assertMutable();
        this.commitAudit = commitAudit;
    }

    /**
     * Get the contribution
     * @return Contribution in which this version was added.
     */
    public ObjectRef getContribution() {
        return contribution;
    }

    /**
     * Set the contribution
     * @param contribution Contribution in which this version was added.
     */
    public void setContribution(ObjectRef contribution) {
    	assertMutable();
        this.contribution = contribution;
    }

    /**
     * Get the signature
     * @return OpenPGP digital signature or digest of content committed in this Version.
     */
    public String getSignature() {
        return signature;
    }

    /**
     * Set the signature
     * @param signature OpenPGP digital signature or digest of content committed in this Version.
     */
    public void setSignature(String signature) {
    	assertMutable();
        this.signature = signature;
    }

    /**
     * Get the data
     * @return Original content of this Version.
     */
    public T getData() {
        return data;
    }

    /**
     * Set the data
     * @param data Original content of this Version.
     */
    public void setData(T data) {
    	assertMutable();
        this.data = data;
    }

    /**
     * Get the lifecycleState
     * @return Lifecycle state of this version; coded by openEHR
     * vocabulary "version lifecycle state".
     */
    public DvCodedText getLifecycleState() {
        return lifecycleState;
    }

    /**
     * Set the lifecycleState
     * @param lifecycleState Lifecycle state of this version; coded by openEHR
     * vocabulary "version lifecycle state".
     */
    public void setLifecycleState(DvCodedText lifecycleState) {
    	assertMutable();
        this.lifecycleState = lifecycleState;
    }
}
