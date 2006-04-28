/**
 * 
 */
package org.openehr.rm.composition.content.entry;

import java.util.List;
import java.util.Set;

import org.openehr.rm.common.archetyped.Archetyped;
import org.openehr.rm.common.archetyped.FeederAudit;
import org.openehr.rm.common.archetyped.Link;
import org.openehr.rm.common.generic.Participation;
import org.openehr.rm.common.generic.PartyProxy;
import org.openehr.rm.datastructure.itemstructure.ItemStructure;
import org.openehr.rm.datatypes.text.CodePhrase;
import org.openehr.rm.datatypes.text.DvText;
import org.openehr.rm.support.identification.ObjectID;
import org.openehr.rm.support.identification.ObjectReference;
import org.openehr.rm.support.terminology.TerminologyService;

import org.openehr.rm.common.archetyped.Locatable;

/**
 * Entry subtype for administrative information, i.e. information about setting up 
 * the clinical process, but not itself clinically relevant. Archetypes will define 
 * contained information
 * 
 * @author yinsulim
 *
 */
public class AdminEntry extends Entry {

	/**
	 * @param uid
	 * @param archetypeNodeId
	 * @param name
	 * @param archetypeDetails
	 * @param feederAudit
	 * @param links
	 * @param parent
	 * @param language
	 * @param encoding
	 * @param subject
	 * @param provider
	 * @param workflowID
	 * @param otherParticipations
	 * @param terminologyService
	 */
	public AdminEntry(ObjectID uid, String archetypeNodeId, DvText name, 
			Archetyped archetypeDetails, FeederAudit feederAudit, Set<Link> links, 
			Locatable parent, CodePhrase language, CodePhrase encoding, 
			PartyProxy subject, PartyProxy provider, ObjectReference workflowID, 
			List<Participation> otherParticipations, ItemStructure data,
			TerminologyService terminologyService) {
		super(uid, archetypeNodeId, name, archetypeDetails, feederAudit, links, parent,
				language, encoding, subject, provider, workflowID, otherParticipations,
				terminologyService);
		if (data == null) {
			throw new IllegalArgumentException("null data");
		}
		this.data = data;
	}

	public ItemStructure getData() {
		return data;
	}

	@Override
	public String pathOfItem(Locatable item) {
		// TODO Auto-generated method stub
		return null;
	}
	
	//POJO start
	AdminEntry() {
	}
	
	void setData(ItemStructure data) {
		this.data = data;
	}
	//POJO end
	
	/* field */
	private ItemStructure data;
	
}
