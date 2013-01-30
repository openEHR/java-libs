
package br.com.zilics.archetypes.models.rm.common.changecontrol;

import java.util.List;
import java.util.Set;

import br.com.zilics.archetypes.models.rm.RMObject;
import br.com.zilics.archetypes.models.rm.common.generic.Attestation;
import br.com.zilics.archetypes.models.rm.support.identification.ObjectVersionID;

/**
 * A Version containing locally created content and optional attestations.
 * @author Humberto
 * @param <T> Type of the versioned object
 * 
 */
public class OriginalVersion<T extends RMObject> extends Version<T> {
	private static final long serialVersionUID = -3425626623321271098L;
	private Set<ObjectVersionID> otherInputVersionUids;
    private List<Attestation> attestations;


    /**
     * Get the inputVersionUids
     * @return Identifiers of other versions whose content was
     * merged into this version, if any.
     */
    public Set<ObjectVersionID> getOtherInputVersionUids() {
        return getSet(otherInputVersionUids);
    }

    /**
     * Set the inputVersionUids
     * @param otherInputVersionUids Identifiers of other versions whose content was
     * merged into this version, if any.
     */
    public void setOtherInputVersionUids(Set<ObjectVersionID> otherInputVersionUids) {
    	assertMutable();
        this.otherInputVersionUids = otherInputVersionUids;
    }

    /**
     * Get the attestations
     * @return Set of attestations relating to this version.
     */
    public List<Attestation> getAttestations() {
        return getList(attestations);
    }

    /**
     * Set the attestations
     * @param attestations Set of attestations relating to this version.
     */
    public void setAttestations(List<Attestation> attestations) {
    	assertMutable();
        this.attestations = attestations;
    }
    
}
