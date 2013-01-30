
package br.com.zilics.archetypes.models.rm.common.generic;

import br.com.zilics.archetypes.models.rm.RMObject;
import br.com.zilics.archetypes.models.rm.annotation.EqualsField;
import br.com.zilics.archetypes.models.rm.annotation.NotNull;
import br.com.zilics.archetypes.models.rm.datatypes.quantity.DvInterval;
import br.com.zilics.archetypes.models.rm.datatypes.quantity.datetime.DvDateTime;
import br.com.zilics.archetypes.models.rm.datatypes.text.DvCodedText;
import br.com.zilics.archetypes.models.rm.datatypes.text.DvText;

/**
 * Model of participation of a Party (any Actor or Role) in activity.
 * @author Humberto
 */
public class Participation extends RMObject {
	private static final long serialVersionUID = -4370242901459668706L;
	@NotNull
	@EqualsField
	private PartyProxy performer;
	@NotNull
	@EqualsField
    private DvText function;
	@NotNull
	@EqualsField
    private DvCodedText mode;
    private DvInterval<DvDateTime> time;

    /**
     * Get the performer
     * @return The id and possibly demographic system link of
     * the party participating in the activity.
     */
    public PartyProxy getPerformer() {
        return performer;
    }

    /**
     * Set the performer
     * @param performer The id and possibly demographic system link of
     * the party participating in the activity.
     */
    public void setPerformer(PartyProxy performer) {
    	assertMutable();
        this.performer = performer;
    }

    /**
     * Get the function
     * @return The function of the Party in this participation (note
     * that a given party might participate in more than
     * one way in a particular activity). This attribute
     * should be coded, but cannot be limited to the
     * HL7v3:ParticipationFunction vocabulary, since it
     * is too limited and hospital-oriented.
     */ 
    public DvText getFunction() {
        return function;
    }

    /**
     * Set the function
     * @param function The function of the Party in this participation (note
     * that a given party might participate in more than
     * one way in a particular activity). This attribute
     * should be coded, but cannot be limited to the
     * HL7v3:ParticipationFunction vocabulary, since it
     * is too limited and hospital-oriented.
     */
    public void setFunction(DvText function) {
    	assertMutable();
        this.function = function;
    }

    /**
     * Get the mode
     * @return The mode of the performer / activity interaction,
     *  e.g. present, by telephone, by email etc.
     */
    public DvCodedText getMode() {
        return mode;
    }

    /**
     * Set the mode
     * @param mode The mode of the performer / activity interaction,
     *  e.g. present, by telephone, by email etc.
     */
    public void setMode(DvCodedText mode) {
    	assertMutable();
        this.mode = mode;
    }

    /**
     * Get the time
     * @return The time interval during which the participation
     * took place, if it is used in an observational context
     * (i.e. recording facts about the past); or the intended
     * time interval of the participation when used in
     * future contexts, such as EHR Instructions.
     */
    public DvInterval<DvDateTime> getTime() {
        return time;
    }

    /**
     * Set the time
     * @param time The time interval during which the participation
     * took place, if it is used in an observational context
     * (i.e. recording facts about the past); or the intended
     * time interval of the participation when used in
     * future contexts, such as EHR Instructions.
     */
    public void setTime(DvInterval<DvDateTime> time) {
    	assertMutable();
        this.time = time;
    }
}
