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
	 * @param charset
	 * @param subject
	 * @param provider
	 * @param workflowID
	 * @param otherParticipations
	 * @param terminologyService
	 */
    @FullConstructor
	public AdminEntry(@Attribute(name = "uid") ObjectID uid,
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
                       @Attribute(name = "otherParticipations") List<Participation> otherParticipations, 
                       @Attribute(name = "data", required = true) ItemStructure data,
                       @Attribute(name = "terminologyService", system = true) TerminologyService terminologyService) {
		super(uid, archetypeNodeId, name, archetypeDetails, feederAudit, links, parent,
				language, charset, subject, provider, workflowID, otherParticipations,
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
        Object ret = checkAttribute(tmp, "data", data);
        if (ret == null) {
            throw new IllegalArgumentException("invalid path: " + path);
        }
        return ret;
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
