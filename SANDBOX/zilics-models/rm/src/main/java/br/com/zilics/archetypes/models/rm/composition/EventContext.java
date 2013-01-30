
package br.com.zilics.archetypes.models.rm.composition;

import java.util.List;

import br.com.zilics.archetypes.models.rm.common.archetyped.Locatable;
import br.com.zilics.archetypes.models.rm.common.generic.Participation;
import br.com.zilics.archetypes.models.rm.common.generic.PartyIdentified;
import br.com.zilics.archetypes.models.rm.datastructure.itemstructure.ItemStructure;
import br.com.zilics.archetypes.models.rm.datatypes.quantity.datetime.DvDateTime;
import br.com.zilics.archetypes.models.rm.datatypes.text.DvCodedText;

/**
 * Documents the clinical context of the clinical session (or encounter). The
 * context information recorded here are independent of the attributes recorded in
 * the version audit, which document the  system interaction  context, ie the
 * context of a user interacting with the health record system. Clinical sessions
 * include patient contacts, and any other business activity, such as pathology
 * investigations which take place on behalf of the patient.
 * @author Humberto
 */
public class EventContext extends Locatable {

	private static final long serialVersionUID = 3518618611101120309L;
	private DvDateTime startTime;
    private DvDateTime endTime;
    private String location;
    private DvCodedText setting;

    private ItemStructure otherContext;

    private PartyIdentified healthCareFacility;
    private List<Participation> participations;

    /**
     * Get the start time
     * @return the start time of the clinical session or other kind of event during which a provider performs a serivice of any kind for the patient
     */
    public DvDateTime getStartTime() {
        return startTime;
    }

    /**
     * Set the start time
     * @param startTime the start time of the clinical session or other kind of event during which a provider performs a serivice of any kind for the patient
     */
    public void setStartTime(DvDateTime startTime) {
		assertMutable();
        this.startTime = startTime;
    }

    /**
     * Get the end time
     * @return the end time of the clinical session
     */
    public DvDateTime getEndTime() {
        return endTime;
    }

    /**
     * Set the end time
     * @param endTime the end time of the clinical session
     */
    public void setEndTime(DvDateTime endTime) {
		assertMutable();
        this.endTime = endTime;
    }

    /**
     * Get the location
     * @return the actual location where session occurred
     */
    public String getLocation() {
        return location;
    }

    /**
     * Set the location
     * @param location the actual location where session occurred
     */
    public void setLocation(String location) {
		assertMutable();
        this.location = location;
    }

    /**
     * Get the setting
     * @return the setting in which the clinical session took place
     */
    public DvCodedText getSetting() {
        return setting;
    }

    /**
     * Set the setting
     * @param setting the setting in which the clinical session took place
     */
    public void setSetting(DvCodedText setting) {
		assertMutable();
        this.setting = setting;
    }

    /**
     * Get the other optional context
     * @return other optional context of this Composition
     */
    public ItemStructure getOtherContext() {
        return otherContext;
    }

    /**
     * Set the other optional context
     * @param otherContext other optional context which will be archetyped
     */
    public void setOtherContext(ItemStructure otherContext) {
		assertMutable();
        this.otherContext = otherContext;
    }

    /**
     * Get the health care facility
     * @return the health care facility under whose care the event took place
     */
    public PartyIdentified getHealthCareFacility() {
        return healthCareFacility;
    }

    /**
     * Set the health care facility
     * @param healthCareFacility the health care facility under whose care the event took place
     */
    public void setHealthCareFacility(PartyIdentified healthCareFacility) {
		assertMutable();
        this.healthCareFacility = healthCareFacility;
    }

    /**
     * Get the Participations
     * @return parties involved in the healhcare event
     */
    public List<Participation> getParticipations() {
        return getList(participations);
    }

    /**
     * Set the Participations
     * @param participations parties involved in the healhcare event
     */
    public void setParticipations(List<Participation> participations) {
		assertMutable();
        this.participations = participations;
    }
    

}
