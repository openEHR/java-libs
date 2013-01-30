
package br.com.zilics.archetypes.models.rm.common.generic;

import br.com.zilics.archetypes.models.rm.RMObject;
import br.com.zilics.archetypes.models.rm.annotation.EqualsField;
import br.com.zilics.archetypes.models.rm.annotation.NotEmpty;
import br.com.zilics.archetypes.models.rm.annotation.NotNull;
import br.com.zilics.archetypes.models.rm.datatypes.quantity.datetime.DvDateTime;
import br.com.zilics.archetypes.models.rm.datatypes.text.DvCodedText;
import br.com.zilics.archetypes.models.rm.datatypes.text.DvText;

/**
 * The set of attributes required to document the committal of an information item to
 * a repository.
 * @author Humberto
 */
public class AuditDetails extends RMObject {
	private static final long serialVersionUID = -3405323051130347734L;
	@NotEmpty
	@EqualsField
	private String systemId;
	@NotNull
	@EqualsField
    private PartyProxy committer;
	@NotNull
	@EqualsField
    private DvDateTime timeCommitted;
	@NotNull
    private DvCodedText changeType;
    private DvText description;

    /**
     * Get the systemId
     * @return Identity of the system where the change was
     * committed. Ideally this is a machine- and
     * human-processable identifier, but it may not
     * be.
     */
    public String getSystemId() {
        return systemId;
    }

    /**
     * Set the systemId
     * @param systemId Identity of the system where the change was
     * committed. Ideally this is a machine- and
     * human-processable identifier, but it may not
     * be.
     */
    public void setSystemId(String systemId) {
    	assertMutable();
        this.systemId = systemId;
    }

    /**
     * Get the committer
     * @return Identity and optional reference into identity
     * management service, of user who committed
     * the item.
     */
    public PartyProxy getCommitter() {
        return committer;
    }

    /**
     * Set the committer
     * @param committer Identity and optional reference into identity
     * management service, of user who committed
     * the item.
     */
    public void setCommitter(PartyProxy committer) {
    	assertMutable();
        this.committer = committer;
    }

    /**
     * Get the timeCommitted
     * @return Time of committal of the item.
     */
    public DvDateTime getTimeCommitted() {
        return timeCommitted;
    }

    /**
     * Set the timeCommitted
     * @param timeCommitted Time of committal of the item.
     */
    public void setTimeCommitted(DvDateTime timeCommitted) {
    	assertMutable();
        this.timeCommitted = timeCommitted;
    }

    /**
     * Get the changeType
     * @return type of change
     */
    public DvCodedText getChangeType() {
        return changeType;
    }

    /**
     * Set the changeType
     * @param changeType type of change
     */
    public void setChangeType(DvCodedText changeType) {
    	assertMutable();
        this.changeType = changeType;
    }

    /**
     * Get the description
     * @return reason for committal
     */
    public DvText getDescription() {
        return description;
    }

    /**
     * Set the description
     * @param description reason for committal
     */
    public void setDescription(DvText description) {
    	assertMutable();
        this.description = description;
    }
}
