
package br.com.zilics.archetypes.models.rm.common.generic;

import br.com.zilics.archetypes.models.rm.annotation.EqualsField;
import br.com.zilics.archetypes.models.rm.annotation.NotNull;
import br.com.zilics.archetypes.models.rm.datatypes.text.DvCodedText;

/**
 * Proxy type for identifying a party and its relationship to the subject of the record.
 * @author Humberto
 */
public class PartyRelated extends PartyIdentified {
    private static final long serialVersionUID = 5481008338352438775L;
    @NotNull
    @EqualsField
	private DvCodedText relationship;

	/**
	 * Get the relationship
	 * @return Relationship of subject of this ENTRY
     * to the subject of the record. May be coded. If it is the patient, coded as
     * "self".
	 */
    public DvCodedText getRelationship() {
        return relationship;
    }

    /**
     * Set the relationship
     * @param relationship Relationship of subject of this ENTRY
     * to the subject of the record. May be coded. If it is the patient, coded as
     * "self".
     */
    public void setRelationship(DvCodedText relationship) {
    	assertMutable();
        this.relationship = relationship;
    }
    
}
