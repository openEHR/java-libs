/**
 * 
 */
package org.openehr.rm.composition.content.entry;

import java.util.Set;

import org.openehr.rm.Attribute;
import org.openehr.rm.common.archetyped.Archetyped;
import org.openehr.rm.common.archetyped.FeederAudit;
import org.openehr.rm.common.archetyped.Link;
import org.openehr.rm.common.archetyped.Locatable;
import org.openehr.rm.datatypes.text.DvText;
import org.openehr.rm.datatypes.text.DvCodedText;
import org.openehr.rm.support.identification.ObjectID;
import org.openehr.rm.support.terminology.TerminologyService;

/**
 * @author yinsulim
 *
 */
public class ISMTransition extends Locatable {

	/**
	 * @param uid
	 * @param archetypeNodeId
	 * @param name
	 * @param archetypeDetails
	 * @param feederAudit
	 * @param links
	 * @param parent
	 */
	public ISMTransition(@Attribute(name = "uid") ObjectID uid,
            @Attribute(name = "archetypeNodeId", required = true) String archetypeNodeId,
            @Attribute(name = "name", required = true) DvText name,
            @Attribute(name = "archetypeDetails") Archetyped archetypeDetails,
            @Attribute(name = "feederAudit") FeederAudit feederAudit,
            @Attribute(name = "links") Set<Link> links,
            @Attribute(name = "parent") Locatable parent, 
            @Attribute(name = "currentState", required = true) DvCodedText currentState,
            @Attribute(name = "transition") DvCodedText transition,
            @Attribute(name = "careflowStep") DvCodedText careflowStep,
            @Attribute(name = "terminologyService", system = true) TerminologyService terminologyService)  {
		super(uid, archetypeNodeId, name, archetypeDetails, feederAudit, links,
				parent);
		if (currentState == null) {
			throw new IllegalArgumentException("null currentState");
		}
		if (terminologyService == null) {
			throw new IllegalArgumentException("null terminologyService");
		}
		if (!terminologyService.terminology(TerminologyService.OPENEHR).
				hasCodeForGroupName(currentState.getDefiningCode(), "ISM states", "en")) {
			throw new IllegalArgumentException("unknown currentState:" + currentState);
		}
		if (transition != null && !terminologyService.terminology(TerminologyService.OPENEHR).
				hasCodeForGroupName(transition.getDefiningCode(), "ISM transitions", "en")) {
			throw new IllegalArgumentException("unknown transition:" + transition);
		}
	}

	/**
	 * Construct ISMTransition
	 * @param archetypeNodeId
	 * @param name
	 * @param currentState
	 * @param terminologyService
	 */
	public ISMTransition(String archetypeNodeId, DvText name, 
			DvCodedText currentState, TerminologyService terminologyService) {
		this(null, archetypeNodeId, name, null, null, null, null, currentState, 
				null, null, terminologyService);		
	}

	/**
	 * The step in the careflow process which occurred as part of generating 
	 * this action, e.g. "dispense", "start_administration". This attribute 
	 * represents the clinical label for the activity, as opposed to 
	 * currentState which represents the state machine computable form.
	 * 
	 * @return careflowStep
	 */
	public DvCodedText getCareflowStep() {
		return careflowStep;
	}

	/**
	 * The ISM current state. Coded by openEHR terminology group "ISM states"
	 * 
	 * @return currentState
	 */
	public DvCodedText getCurrentState() {
		return currentState;
	}

	/**
	 * The ISM transition which occurred to arrive in the currentState. Coded by 
	 * openEHR terminology group "ISM transitions"
	 * 
	 * @return transition
	 */
	public DvCodedText getTransition() {
		return transition;
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
	ISMTransition() {
	}
	
	void setCareflowStep(DvCodedText careflowStep) {
		this.careflowStep = careflowStep;
	}

	void setCurrentState(DvCodedText currentState) {
		this.currentState = currentState;
	}

	void setTransition(DvCodedText transition) {
		this.transition = transition;
	}
	//POJO end

	/* fields */
	private DvCodedText currentState;
	private DvCodedText transition;
	private DvCodedText careflowStep;

}
