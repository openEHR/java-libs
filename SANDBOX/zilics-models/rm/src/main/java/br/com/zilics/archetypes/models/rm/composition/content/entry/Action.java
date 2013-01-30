
package br.com.zilics.archetypes.models.rm.composition.content.entry;

import br.com.zilics.archetypes.models.rm.datastructure.itemstructure.ItemStructure;
import br.com.zilics.archetypes.models.rm.datatypes.quantity.datetime.DvDateTime;


/**
 * Used to record a clinical action that has been performed, which may have been ad hoc,
 * or due to the execution of an
 * {@link br.com.zilics.archetypes.models.rm.composition.content.entry.Activity} in
 * an {@link br.com.zilics.archetypes.models.rm.composition.content.entry.Instruction} workflow.
 * Every {@link Action}
 * corresponds to a careflow step of some kind or another.
 *
 * @author Humberto
 */
public class Action extends CareEntry {
	private static final long serialVersionUID = -3164437036471556783L;
	private DvDateTime time;
    private ItemStructure description;
    private ISMTransition ismTransition;
    private InstructionDetails instructionDetails;

    /**
     * Get the time
     * @return the time at which this action completed
     */
    public DvDateTime getTime() {
        return time;
    }

    /**
     * Set the time
     * @param time the time at which this action completed
     */
    public void setTime(DvDateTime time) {
		assertMutable();
        this.time = time;
    }

    /**
     * Get the description
     * @return the description of the activity to be performed
     */
    public ItemStructure getDescription() {
        return description;
    }

    /**
     * Set the description
     * @param description the description of the activity to be performed
     */
    public void setDescription(ItemStructure description) {
		assertMutable();
        this.description = description;
    }

    /**
     * Get details of a transition
     * @return the details of transition in the Instruction state machine caused by this Action
     */
    public ISMTransition getIsmTransition() {
        return ismTransition;
    }

    /**
     * Set details of a transition
     * @param ismTransition the details of transition in the Instruction state machine caused by this Action
     */
    public void setIsmTransition(ISMTransition ismTransition) {
		assertMutable();
        this.ismTransition = ismTransition;
    }

    /**
     * Get details of an instruction
     * @return the details of the Instruction that caused this Action to be performed
     */
    public InstructionDetails getInstructionDetails() {
        return instructionDetails;
    }

    /**
     * Set details of an instruction
     * @param instructionDetails the details of the Instruction that caused this Action to be performed
     */
    public void setInstructionDetails(InstructionDetails instructionDetails) {
		assertMutable();
        this.instructionDetails = instructionDetails;
    }
    
}
