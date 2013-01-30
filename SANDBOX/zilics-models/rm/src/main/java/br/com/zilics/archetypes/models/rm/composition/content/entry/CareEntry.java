
package br.com.zilics.archetypes.models.rm.composition.content.entry;

import br.com.zilics.archetypes.models.rm.datastructure.itemstructure.ItemStructure;
import br.com.zilics.archetypes.models.rm.support.identification.ObjectRef;

/**
 * The abstract parent of all clinical Entry subtypes. A CareEntry defines
 * protocol and guideline attributes for all clinical Entry subtypes.
 *
 * @author Humberto
 */
public abstract class CareEntry extends Entry {
	private static final long serialVersionUID = -439241784320377482L;
	private ItemStructure protocol;
    private ObjectRef guidelineId;

    /**
     * Get the protocol
     * @return the description of the method
     */
    public ItemStructure getProtocol() {
        return protocol;
    }

    /**
     * Set the protocol
     * @param protocol the description of the method
     */
    public void setProtocol(ItemStructure protocol) {
		assertMutable();
        this.protocol = protocol;
    }

    /**
     * Get the Guideline Id
     * @return the optional external identifier of guideline creating this action
     */
    public ObjectRef getGuidelineId() {
        return guidelineId;
    }

    /**
     * Set the Guideline Id
     * @param guidelineId the optional external identifier of guideline creating this action
     */
    public void setGuidelineId(ObjectRef guidelineId) {
		assertMutable();
        this.guidelineId = guidelineId;
    }
    
}
