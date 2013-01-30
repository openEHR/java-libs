
package br.com.zilics.archetypes.models.rm.composition.content.entry;

import br.com.zilics.archetypes.models.rm.common.archetyped.Locatable;
import br.com.zilics.archetypes.models.rm.datastructure.itemstructure.ItemStructure;
import br.com.zilics.archetypes.models.rm.support.identification.LocatableRef;

/**
 * Used to record details of the
 * {@link br.com.zilics.archetypes.models.rm.composition.content.entry.Instruction}
 * causing an
 * {@link br.com.zilics.archetypes.models.rm.composition.content.entry.Action}.
 *
 * @author Humberto
 */
public class InstructionDetails extends Locatable {
	private static final long serialVersionUID = 8076212029878751047L;
	private LocatableRef instructionId;
    private String activityId;
    private ItemStructure wfDetails;

    /**
     * Get the instruction Id
     * @return reference to causing Instruction
     */
    public LocatableRef getInstructionId() {
        return instructionId;
    }

    /**
     * Set the instruction Id
     * @param instructionId reference to causing Instruction
     */
    public void setInstructionId(LocatableRef instructionId) {
		assertMutable();
        this.instructionId = instructionId;
    }

    /**
     * Get the Activity Id
     * @return identifier of Activity within Instruction, ih the form of its archetype path
     */
    public String getActivityId() {
        return activityId;
    }

    /**
     * Set the Activity Id
     * @param activityId identifier of Activity within Instruction, ih the form of its archetype path
     */
    public void setActivityId(String activityId) {
		assertMutable();
        this.activityId = activityId;
    }

    /**
     * Get the workflow engine state details
     * @return Various workflow engine state details, potentially including such thing as:
     * - Condition that fired to cause this Action to be done;
     * - list of notifications which actually occurred;
     * - other workflow engine state.
     */
    public ItemStructure getWfDetails() {
        return wfDetails;
    }

    /**
     * Set the workflow engine state details
     * @param wfDetails Various workflow engine state details, potentially including such thing as:
     * - Condition that fired to cause this Action to be done;
     * - list of notifications which actually occurred;
     * - other workflow engine state.
     */
    public void setWfDetails(ItemStructure wfDetails) {
		assertMutable();
        this.wfDetails = wfDetails;
    }
}
