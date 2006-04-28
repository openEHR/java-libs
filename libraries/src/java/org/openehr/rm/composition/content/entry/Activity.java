/**
 * 
 */
package org.openehr.rm.composition.content.entry;

import java.util.Set;

import org.openehr.rm.Attribute;
import org.openehr.rm.common.archetyped.*;
import org.openehr.rm.datatypes.text.DvText;
import org.openehr.rm.datatypes.encapsulated.DvParsable;
import org.openehr.rm.datastructure.itemstructure.ItemStructure;
import org.openehr.rm.support.identification.ObjectID;

/**
 * @author yinsulim
 *
 */
public class Activity extends Locatable {

	/**
	 * 
	 */
	public Activity(
		    @Attribute(name = "uid") ObjectID uid,
            @Attribute(name = "archetypeNodeId", required = true) String archetypeNodeId,
            @Attribute(name = "name", required = true) DvText name,
            @Attribute(name = "archetypeDetails") Archetyped archetypeDetails,
            @Attribute(name = "feederAudit") FeederAudit feederAudit,
            @Attribute(name = "links") Set<Link> links,
            @Attribute(name = "parent") Locatable parent,
            @Attribute(name = "description", required = true) ItemStructure description,
            @Attribute(name = "timing", required = true) DvParsable timing) {
		super(uid, archetypeNodeId, name, archetypeDetails, feederAudit, links, parent);
		if (description == null) {
			throw new IllegalArgumentException("null description");
		}
		if (timing == null) {
			throw new IllegalArgumentException("null timing");
		}
		this.description = description;
		this.timing = timing;
	}

	
	/**
	 * Description of the activity, in the form of an archetyped structure.
	 * 
	 * @return description
	 */
	public ItemStructure getDescription() {
		return description;
	}

	/**
	 * Timing of the activity, in the form of a parsable string, such as 
	 * GTS or iCal string
	 * 
	 * @return timing
	 */
	public DvParsable getTiming() {
		return timing;
	}

	@Override
	public String pathOfItem(Locatable item) {
		// TODO Auto-generated method stub
		return null;
	}

	//POJO start
	Activity() {};
	
	void setDescription(ItemStructure description) {
		this.description = description;
	}

	void setTiming(DvParsable timing) {
		this.timing = timing;
	}	
	//POJO end	
	
	/* fields */
	private ItemStructure description;
	private DvParsable timing;
}
