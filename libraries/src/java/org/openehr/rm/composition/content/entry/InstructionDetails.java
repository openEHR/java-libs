/**
 * 
 */
package org.openehr.rm.composition.content.entry;

import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.openehr.rm.Attribute;
import org.openehr.rm.common.archetyped.Archetyped;
import org.openehr.rm.common.archetyped.FeederAudit;
import org.openehr.rm.common.archetyped.Link;
import org.openehr.rm.common.archetyped.Locatable;
import org.openehr.rm.common.generic.Participation;
import org.openehr.rm.common.generic.PartyProxy;
import org.openehr.rm.datastructure.itemstructure.ItemStructure;
import org.openehr.rm.datatypes.quantity.datetime.DvDateTime;
import org.openehr.rm.datatypes.text.CodePhrase;
import org.openehr.rm.datatypes.text.DvText;
import org.openehr.rm.support.identification.ObjectID;
import org.openehr.rm.support.identification.ObjectVersionID;
import org.openehr.rm.support.identification.ObjectReference;
import org.openehr.rm.support.terminology.TerminologyService;

/**
 * @author yinsulim
 *
 */
public class InstructionDetails extends Locatable {

	/**
	 * @param uid
	 * @param archetypeNodeId
	 * @param name
	 * @param archetypeDetails
	 * @param feederAudit
	 * @param links
	 * @param parent
	 */
	public InstructionDetails(@Attribute(name = "uid") ObjectID uid,
            @Attribute(name = "archetypeNodeId", required = true) String archetypeNodeId,
            @Attribute(name = "name", required = true) DvText name,
            @Attribute(name = "archetypeDetails") Archetyped archetypeDetails,
            @Attribute(name = "feederAudit") FeederAudit feederAudit,
            @Attribute(name = "links") Set<Link> links,
            @Attribute(name = "parent") Locatable parent, 
            @Attribute(name = "instructionID", required = true) ObjectVersionID instructionID,
            @Attribute(name = "activityID", required = true) String activityID,
            @Attribute(name = "wfDetails") ItemStructure wfDetails) {
		super(uid, archetypeNodeId, name, archetypeDetails, feederAudit, links,
				parent);
		if (instructionID == null) {
			throw new IllegalArgumentException("null instructionID");
		}
		if (StringUtils.isEmpty(activityID)) {
			throw new IllegalArgumentException("null or empty activityID");
		}
		this.instructionID =instructionID;
		this.activityID = activityID;
		this.wfDetails = wfDetails;
	}

	/**
	 * Identifier of Activity within Instruction, in the form of its archetype 
	 * path.
	 * 
	 * @return activityID
	 */
	public String getActivityID() {
		return activityID;
	}

	/**
	 * Id of causing Instruction
	 * 
	 * @return instructionID
	 */
	public ObjectVersionID getInstructionID() {
		return instructionID;
	}

	/**
	 * Various workflow engine state details, potentially including such things as:
	 * -condition that fired to cause this Action to be done
	 * -list of notifications which actually occured
	 * -other workflow engine state
	 * 
	 * @return wfDetails
	 */
	public ItemStructure getWfDetails() {
		return wfDetails;
	}


	/**
	 * @param archetypeNodeId
	 * @param name
	 */
	public InstructionDetails(String archetypeNodeId, DvText name) {
		super(archetypeNodeId, name);
		// TODO Auto-generated constructor stub
	}



	/* (non-Javadoc)
	 * @see org.openehr.rm.common.archetyped.Locatable#pathOfItem(org.openehr.rm.common.archetyped.Locatable)
	 */
	@Override
	public String pathOfItem(Locatable item) {
		// TODO Auto-generated method stub
		return null;
	}

	//POJO start
	InstructionDetails() {
	}

	void setActivityID(String activityID) {
		this.activityID = activityID;
	}

	void setInstructionID(ObjectVersionID instructionID) {
		this.instructionID = instructionID;
	}

	void setWfDetails(ItemStructure wfDetails) {
		this.wfDetails = wfDetails;
	}
	//POJO end
	
	/* fields */
	private ObjectVersionID instructionID;
	private String activityID;
	private ItemStructure wfDetails;

}
