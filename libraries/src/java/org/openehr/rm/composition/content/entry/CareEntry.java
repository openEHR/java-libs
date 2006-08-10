/**
 * 
 */
package org.openehr.rm.composition.content.entry;

import java.util.List;
import java.util.Set;

import org.openehr.rm.common.archetyped.Archetyped;
import org.openehr.rm.common.archetyped.FeederAudit;
import org.openehr.rm.common.archetyped.Link;
import org.openehr.rm.common.archetyped.Locatable;
import org.openehr.rm.common.generic.Participation;
import org.openehr.rm.common.generic.PartyProxy;
import org.openehr.rm.datastructure.itemstructure.ItemStructure;
import org.openehr.rm.datatypes.text.CodePhrase;
import org.openehr.rm.datatypes.text.DvText;
import org.openehr.rm.support.identification.ObjectID;
import org.openehr.rm.support.identification.ObjectReference;
import org.openehr.rm.support.terminology.TerminologyService;

/**
 * The abstract parent of all clinical Entry subtypes. A CareEntry defines 
 * protocol and guideline attributes for all clinical Entry subtypes.
 *
 */
public abstract class CareEntry extends Entry {

    /**
     * Construct an Entry
     *
     * @param archetypeNodeId
     * @param name
     * @param subject
     * @param provider
     * @param protocol            null if unspecified
     * @param actID               null if unspecified
     * @param guidelineID         null if unspecified
     * @param otherParticipations null if unspecified
     * @throws IllegalArgumentException if archetypeNodeId or name null,
     *                                  or subject or provider null or invalid
     */
    protected CareEntry(ObjectID uid, String archetypeNodeId, DvText name,
                 Archetyped archetypeDetails, FeederAudit feederAudit,
                 Set<Link> links, Locatable parent, CodePhrase language,
                 CodePhrase charset, PartyProxy subject, 
                 PartyProxy provider, ObjectReference workflowID,
                 List<Participation> otherParticipations,
                 ItemStructure protocol, ObjectReference guidelineID, 
                 TerminologyService terminologyService) {

        super(uid, archetypeNodeId, name, archetypeDetails, feederAudit, links, parent, 
        		language, charset, subject, provider, workflowID, otherParticipations,
        		terminologyService);
        this.protocol = protocol;
        this.guidelineID = guidelineID;
    }

    public Object itemAtPath(String path) {

        Object item = super.itemAtPath(path);
        if(item != null) {
            return item;
        }
        String tmp = path;
        String attr = ROOT + PROTOCOL;
        if (tmp.equals(attr)) {
            return protocol;
        }
        if(tmp.startsWith(attr)) {
            return protocol.itemAtPath(tmp.substring(attr.length()));
        }
        return null;    // path needs to be further processed by sub-class
    }
    
    /**
     * Optional external identifier of guideline creating this action 
     * if relevant
     * 
     * @return guidelineID
     */
    public ObjectReference getGuidelineID() {
    		return guidelineID;
    }
    
    /**
     * Description of the method the information in this entry was arrived at.
     * 
     * @return protocol
     */
    public ItemStructure getProtocol() {
    		return protocol;
    }
    
    //POJO start
    CareEntry() {
    }
    
	void setGuidelineID(ObjectReference guidelineID) {
		this.guidelineID = guidelineID;
	}
	void setProtocol(ItemStructure protocol) {
		this.protocol = protocol;
	}
	//POJO end
	
    /* fields */
        
   private ItemStructure protocol;
   private ObjectReference guidelineID;

   /* static fields */
   public static final String PROTOCOL = "protocol";
   //public static final String ACTION = "action";
   //public static final String PROFILE = "profile";
}
