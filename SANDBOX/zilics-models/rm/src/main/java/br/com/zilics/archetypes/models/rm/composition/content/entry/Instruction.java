
package br.com.zilics.archetypes.models.rm.composition.content.entry;

import java.util.List;

import br.com.zilics.archetypes.models.rm.datatypes.encapsulated.DvParsable;
import br.com.zilics.archetypes.models.rm.datatypes.quantity.datetime.DvDateTime;
import br.com.zilics.archetypes.models.rm.datatypes.text.DvText;

/**
 * The root of an action specification.
 *
 * @author Humberto
 */
public class Instruction extends CareEntry {
	private static final long serialVersionUID = -7674825467496266164L;
	private DvText narrative;
    private DvDateTime expiryTime;
    private DvParsable wfDefinition;
    private List<Activity> activities;

    /**
     * Get the narrative
     * @return the mandatory human-readable version of what the instruction is about
     */
    public DvText getNarrative() {
        return narrative;
    }

    /**
     * Set the narrative
     * @param narrative the mandatory human-readable version of what the instruction is about
     */
    public void setNarrative(DvText narrative) {
		assertMutable();
        this.narrative = narrative;
    }

    /**
     * Get the expiry time
     * @return the optional expire date/time to assist determination of when an Instruction can be assume to have expired
     */
    public DvDateTime getExpiryTime() {
        return expiryTime;
    }

    /**
     * Set the expiry time
     * @param expiryTime the optional expire date/time to assist determination of when an Instruction can be assume to have expired
     */
    public void setExpiryTime(DvDateTime expiryTime) {
		assertMutable();
        this.expiryTime = expiryTime;
    }

    /**
     * Get the work flow definition
     * @return the optional workflow engine executable expression of the Instruction
     */
    public DvParsable getWfDefinition() {
        return wfDefinition;
    }

    /**
     * Set the work flow definition
     * @param wfDefinition the optional workflow engine executable expression of the Instruction
     */
    public void setWfDefinition(DvParsable wfDefinition) {
		assertMutable();
        this.wfDefinition = wfDefinition;
    }

    /**
     * Get the activities
     * @return the list of all activities in Instruction
     */
    public List<Activity> getActivities() {
        return getList(activities);
    }

    /**
     * Set the activities
     * @param activities the list of all activities in Instruction
     */
    public void setActivities(List<Activity> activities) {
		assertMutable();
        this.activities = activities;
    }

}
