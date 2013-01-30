
package br.com.zilics.archetypes.models.rm.common.archetyped;

import br.com.zilics.archetypes.models.rm.RMObject;
import br.com.zilics.archetypes.models.rm.annotation.EqualsField;
import br.com.zilics.archetypes.models.rm.annotation.NotEmpty;
import br.com.zilics.archetypes.models.rm.common.generic.PartyIdentified;
import br.com.zilics.archetypes.models.rm.common.generic.PartyProxy;
import br.com.zilics.archetypes.models.rm.datatypes.quantity.datetime.DvDateTime;

/**
 * Audit details for a feeder system.
 *
 * @author Humberto
 */
public class FeederAuditDetails extends RMObject {
	private static final long serialVersionUID = -7829799577381649615L;
	@NotEmpty
	@EqualsField
	private String systemId;
    private PartyIdentified provider;
    private PartyIdentified location;
    private DvDateTime time;
	@EqualsField
    private PartyProxy subject;
	@EqualsField
    private String versionId;

    /**
     * Get the systemId
     * @return Identifier of the system which handled the information item.
     */
    public String getSystemId() {
        return systemId;
    }

    /**
     * Set the systemId
     * @param systemId Identifier of the system which handled the information item.
     */
    public void setSystemId(String systemId) {
    	assertMutable();
        this.systemId = systemId;
    }

    /**
     * Get the provider
     * @return Optional provider(s) who created, committed, forwarded or otherwise
     * handled the item.
     */
    public PartyIdentified getProvider() {
        return provider;
    }

    /**
     * Set the provider
     * @param provider Optional provider(s) who created, committed, forwarded or otherwise
     * handled the item.
     */
    public void setProvider(PartyIdentified provider) {
    	assertMutable();
        this.provider = provider;
    }

    /**
     * Get the location
     * @return Identifier of the particular site/facility within an organisation
     * which handled the item. For computability, this identifier needs
     * to be e.g. a PKI identifier which can be included in the
     * <I>identifier</I> list of the {@link br.com.zilics.archetypes.models.rm.common.generic.PartyIdentified} object.
     */
    public PartyIdentified getLocation() {
        return location;
    }

    /**
     * Set the location
     * @param location Identifier of the particular site/facility within an organisation
     * which handled the item. For computability, this identifier needs
     * to be e.g. a PKI identifier which can be included in the
     * <I>identifier</I> list of the {@link br.com.zilics.archetypes.models.rm.common.generic.PartyIdentified} object.
     */
    public void setLocation(PartyIdentified location) {
    	assertMutable();
        this.location = location;
    }

    /**
     * Get the time
     * @return Time of handling the item.
     * For an originating system, this will be time of creation, for an
     * intermediate feeder system, this will be a time of accession or
     * other time of handling, where available.
     */
    public DvDateTime getTime() {
        return time;
    }

    /**
     * Set the time
     * @param time Time of handling the item.
     * For an originating system, this will be time of creation, for an
     * intermediate feeder system, this will be a time of accession or
     * other time of handling, where available.
     */
    public void setTime(DvDateTime time) {
    	assertMutable();
        this.time = time;
    }

    /**
     * Get the subject
     * @return Identifiers for subject of the received information item.
     */
    public PartyProxy getSubject() {
        return subject;
    }

    /**
     * Set the subject
     * @param subject Identifiers for subject of the received information item.
     */
    public void setSubject(PartyProxy subject) {
    	assertMutable();
        this.subject = subject;
    }

    /**
     * Get the versionId
     * @return Any identifier used in the system such as "interim", "final",
     * or numeric versions if available.
     */
    public String getVersionId() {
        return versionId;
    }

    /**
     * Set the versionId
     * @param versionId Any identifier used in the system such as "interim", "final",
     * or numeric versions if available.
     */
    public void setVersionId(String versionId) {
    	assertMutable();
        this.versionId = versionId;
    }
}
