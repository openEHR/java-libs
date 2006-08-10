/**
 * 
 */
package org.openehr.rm.composition.content.entry;

import java.util.List;
import java.util.Set;

import org.openehr.rm.Attribute;
import org.openehr.rm.FullConstructor;
import org.openehr.rm.common.archetyped.Archetyped;
import org.openehr.rm.common.archetyped.FeederAudit;
import org.openehr.rm.common.archetyped.Link;
import org.openehr.rm.common.generic.Participation;
import org.openehr.rm.common.generic.PartyProxy;
import org.openehr.rm.datastructure.itemstructure.ItemStructure;
import org.openehr.rm.datatypes.encapsulated.DvParsable;
import org.openehr.rm.datatypes.quantity.datetime.DvDateTime;
import org.openehr.rm.datatypes.text.CodePhrase;
import org.openehr.rm.datatypes.text.DvText;
import org.openehr.rm.support.identification.ObjectID;
import org.openehr.rm.support.identification.ObjectReference;
import org.openehr.rm.support.terminology.TerminologyService;

import org.openehr.rm.common.archetyped.Locatable;

/**
 * @author yinsulim
 *
 */
public final class Action extends CareEntry {

	/**
	 * @param uid
	 * @param archetypeNodeId
	 * @param name
	 * @param archetypeDetails
	 * @param feederAudit
	 * @param links
	 * @param parent
	 * @param language
	 * @param charset
	 * @param subject
	 * @param provider
	 * @param workflowID
	 * @param otherParticipations
	 * @param protocol
	 * @param guidelineID
	 * @param terminologyService
	 */
    @FullConstructor
	public Action(@Attribute(name = "uid") ObjectID uid,
            @Attribute(name = "archetypeNodeId", required = true) String archetypeNodeId,
            @Attribute(name = "name", required = true) DvText name,
            @Attribute(name = "archetypeDetails", required = true) Archetyped archetypeDetails,
            @Attribute(name = "feederAudit") FeederAudit feederAudit,
            @Attribute(name = "links") Set<Link> links,
            @Attribute(name = "parent") Locatable parent, 
            @Attribute(name = "language", required = true) CodePhrase language,
            @Attribute(name = "charset", required = true) CodePhrase charset, 
            @Attribute(name = "subject", system = true) PartyProxy subject,
            @Attribute(name = "provider", system = true) PartyProxy provider,
            @Attribute(name = "workflowID") ObjectReference workflowID,
            @Attribute(name = "otherParticipation") List<Participation> otherParticipations,
            @Attribute(name = "protocol") ItemStructure protocol,
            @Attribute(name = "guidelineID") ObjectReference guidelineID,
            @Attribute(name = "time", required = true) DvDateTime time,
            @Attribute(name = "description", required = true) ItemStructure description,
            @Attribute(name = "ismTransition", required = true) ISMTransition ismTransition,
            @Attribute(name = "instructionDetails") InstructionDetails instructionDetails,
            @Attribute(name = "terminologyService", system = true) TerminologyService terminologyService
            ){
		super(uid, archetypeNodeId, name, archetypeDetails, feederAudit, links, parent,
				language, charset, subject, provider, workflowID, otherParticipations,
				protocol, guidelineID, terminologyService);
		if (time == null) {
			throw new IllegalArgumentException("null time");
		}
		if (description == null) {
			throw new IllegalArgumentException("null description");
		}
		if (ismTransition == null) {
			throw new IllegalArgumentException("null ismTransition");
		}
		this.time = time;
		this.description = description;
		this.ismTransition = ismTransition;
		this.instructionDetails = instructionDetails;
	}

	/**
	 * Description of the activity to be performed, in the form of an 
	 * archetyped structure.
	 * 
	 * @return description
	 */
	public ItemStructure getDescription() {
		return description;
	}

	/**
	 * Details of the Instruction that caused this ACtion to be performed, 
	 * if there was one.
	 * 
	 * @return instructionDetails
	 */
	public InstructionDetails getInstructionDetails() {
		return instructionDetails;
	}

	/**
	 * Detials of transition in the Instruction state machine caused by 
	 * this Action.
	 * 
	 * @return ismTransition
	 */
	public ISMTransition getIsmTransition() {
		return ismTransition;
	}

	/**
	 * Point in time at which this action completed.
	 * 
	 * @return time
	 */
	public DvDateTime getTime() {
		return time;
	}

	/* (non-Javadoc)
	 * @see org.openehr.rm.common.archetyped.Locatable#pathOfItem(org.openehr.rm.common.archetyped.Locatable)
	 */
	@Override
	public String pathOfItem(Locatable item) {
		// TODO Auto-generated method stub
		return null;
	}

    /**
     * The item at a path that is relative to this item.
     *
     * @param path
     * @return the item or null if not found
     */
    public Object itemAtPath(String path) {
        
        Object item = super.itemAtPath(path);
        if (item != null) {
            return item;
        }
        String tmp = path;
        /*String[] attributeNames = {
            DESCRIPTION
        };
        Locatable [] attributes = {
            description
        };
        return locateAttribute(tmp, attributeNames, attributes);
         */
        Object ret = checkAttribute(tmp, "description", description);
        if( ret != null) {
            return ret;
        } else {
            throw new IllegalArgumentException("invalid path: " + path);
        }
    }
    
	//POJO start
	Action() {
	}

	void setDescription(ItemStructure description) {
		this.description = description;
	}

	void setInstructionDetails(InstructionDetails instructionDetails) {
		this.instructionDetails = instructionDetails;
	}

	void setIsmTransition(ISMTransition ismTransition) {
		this.ismTransition = ismTransition;
	}

	void setTime(DvDateTime time) {
		this.time = time;
	}
	//POJO end
	
	/* fields */
	private DvDateTime time;
	private ItemStructure description;
	private ISMTransition ismTransition;
	private InstructionDetails instructionDetails;
        
        /* static fields*/
        public static final String DESCRIPTION = "description";

}
