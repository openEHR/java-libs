/**
 * 
 */
package org.openehr.rm.ehr;

import java.util.Set;

import org.openehr.rm.Attribute;
import org.openehr.rm.FullConstructor;
import org.openehr.rm.common.archetyped.Archetyped;
import org.openehr.rm.common.archetyped.FeederAudit;
import org.openehr.rm.common.archetyped.Link;
import org.openehr.rm.common.archetyped.Locatable;
import org.openehr.rm.common.generic.PartySelf;
import org.openehr.rm.datastructure.itemstructure.ItemStructure;
import org.openehr.rm.datatypes.text.DvText;
import org.openehr.rm.support.identification.ObjectID;

/**
 * @author yinsulim
 *
 */
public class EHRStatus extends Locatable {

	@FullConstructor public EHRStatus(
			@Attribute(name = "uid", required = true) ObjectID uid,
            @Attribute(name = "archetypeNodeId", required = true) String archetypeNodeId,
            @Attribute(name = "name", required = true) DvText name,
            @Attribute(name = "archetypeDetails", required = true) Archetyped archetypeDetails,
            @Attribute(name = "feederAudit") FeederAudit feederAudit,
            @Attribute(name = "links") Set<Link> links,
            @Attribute(name = "parent") Locatable parent,
		    @Attribute(name = "subject", required = true) PartySelf subject,
            @Attribute(name = "isQueryable", required=true) boolean isQueryable,
            @Attribute(name = "isModifiable", required=true) boolean isModifiable,
            @Attribute(name = "otherDetails") ItemStructure otherDetails ) {
		super(uid, archetypeNodeId, name, archetypeDetails, feederAudit, links, parent);
		if (subject == null) {
			throw new IllegalArgumentException("null subject");
		}
		if (parent != null) {
			throw new IllegalArgumentException("parent must be null");
		}
		if (!isArchetypeRoot()) {
            throw new IllegalArgumentException("not archetype root");
        }
	}


	@Override
	public String pathOfItem(Locatable item) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Locatable itemAtPath(String path) {
		return null;
		//TODO implement
	}

	
	/* fields */
	private PartySelf subject;
	private boolean isQueryable;
	private boolean isModifiable;
	private ItemStructure otherDetails;

}
