
package br.com.zilics.archetypes.models.rm.common.generic;

import java.util.Set;

import br.com.zilics.archetypes.models.rm.annotation.EqualsField;
import br.com.zilics.archetypes.models.rm.annotation.NotNull;
import br.com.zilics.archetypes.models.rm.annotation.RmField;
import br.com.zilics.archetypes.models.rm.datatypes.encapsulated.DvMultimedia;
import br.com.zilics.archetypes.models.rm.datatypes.text.DvText;
import br.com.zilics.archetypes.models.rm.datatypes.uri.DvEHRURI;

/**
 * Record an attestation of a party (the committer) to item(s) of record content. The 
 * type of attestation is
 * @author Humberto
 */
public class Attestation extends AuditDetails {
	private static final long serialVersionUID = -4305736880320580606L;
	private DvMultimedia attestedView;
    private String proof;
    private Set<DvEHRURI> items;
    @NotNull
    @EqualsField
    private DvText reason;
    @RmField("is_pending")
    private boolean pending;

    /**
     * Get the attestedView
     * @return Optional visual representation of content 
     * attested e.g. screen image.
     */
    public DvMultimedia getAttestedView() {
        return attestedView;
    }

    /**
     * Set the attestedView
     * @param attestedView Optional visual representation of content 
     * attested e.g. screen image.
     */
    public void setAttestedView(DvMultimedia attestedView) {
    	assertMutable();
        this.attestedView = attestedView;
    }

    /**
     * Get the proof
     * @return Proof of attestation
     */
    public String getProof() {
        return proof;
    }

    /**
     * Set the proof
     * @param proof Proof of attestation
     */
    public void setProof(String proof) {
    	assertMutable();
        this.proof = proof;
    }

    /**
     * Get the items
     * @return Items attested, expressed as fully qualified 
     * runtime paths to the items in question.
     * Although not recommended, these may
     * include fine-grained items which have been
     * attested in some other system. Otherwise it is
     * assumed to be for the entire VERSION with
     * which it is associated.
     */
    public Set<DvEHRURI> getItems() {
        return getSet(items);
    }

    /**
     * Set the items 
     * @param items Items attested, expressed as fully qualified 
     * runtime paths to the items in question.
     * Although not recommended, these may
     * include fine-grained items which have been
     * attested in some other system. Otherwise it is
     * assumed to be for the entire VERSION with
     * which it is associated.
     */
    public void setItems(Set<DvEHRURI> items) {
    	assertMutable();
        this.items = items;
    }

    /**
     * Get the reason
     * @return  Reason of this attestation. Optionally coded
     * by the openEHR Terminology group "attestation reason";
     * includes values like "authorisation", "witness" etc.
     */
    public DvText getReason() {
        return reason;
    }

    /**
     * Set the reason
     * @param reason Reason of this attestation. Optionally coded
     * by the openEHR Terminology group "attestation reason";
     * includes values like "authorisation", "witness" etc.
     */
    public void setReason(DvText reason) {
    	assertMutable();
        this.reason = reason;
    }

    /**
     * Get the pending
     * @return True if this attestation is outstanding; False
     * means it has been completed.
     */
    public boolean isPending() {
        return pending;
    }

    /**
     * Set the pending
     * @param pending True if this attestation is outstanding; False
     * means it has been completed.
     */
    public void setPending(boolean pending) {
    	assertMutable();
        this.pending = pending;
    }
    
}
