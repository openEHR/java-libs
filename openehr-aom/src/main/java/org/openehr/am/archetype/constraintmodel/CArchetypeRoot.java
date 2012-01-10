package org.openehr.am.archetype.constraintmodel;

import java.util.List;

import org.openehr.rm.support.basic.MultiplicityInterval;
import org.openehr.rm.support.identification.ArchetypeID;

public class CArchetypeRoot extends CComplexObject {

	public CArchetypeRoot(String path, String rmTypeName,
			MultiplicityInterval occurrences, String nodeID,
			List<CAttribute> attributes, CAttribute parent) {
		super(path, rmTypeName, occurrences, nodeID, attributes, parent);		
	}
	
	public String getSlotNodeId(){
		return slotNodeId;
	}
	
	public void setSlotNodeId(String slotNodeId){
		this.slotNodeId = slotNodeId;
	}
	
	public ArchetypeID getArchetypeID(){
		return archetypeID;
	}
	
	public void setArchetypeId(ArchetypeID archetypeID){
		this.archetypeID = archetypeID;
	}
	
	/* fields */
	private String slotNodeId;
	private ArchetypeID archetypeID;

}
