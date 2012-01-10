package org.openehr.am.archetype.constraintmodel;

public class SiblingOrder {
	
	

	public SiblingOrder(){		
	}
	
	public boolean isBefore(){
		return isBefore;
	}
	
	public void setIsBefore(boolean isBefore){
		this.isBefore = isBefore;
	}
	
	public String getSiblingNodeId(){
		return siblingNodeId;
	}
	
	public void setSiblingNodeId(String siblingNodeId){
		this.siblingNodeId = siblingNodeId; 
	}
	
	/* fields */
	private String siblingNodeId;	
	private boolean isBefore;
}
