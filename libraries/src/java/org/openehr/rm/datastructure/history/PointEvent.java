/**
 * 
 */
package org.openehr.rm.datastructure.history;

import java.util.Set;

import org.openehr.rm.Attribute;
import org.openehr.rm.FullConstructor;
import org.openehr.rm.common.archetyped.Archetyped;
import org.openehr.rm.common.archetyped.FeederAudit;
import org.openehr.rm.common.archetyped.Link;
import org.openehr.rm.datastructure.itemstructure.ItemStructure;
import org.openehr.rm.datatypes.quantity.datetime.DvDateTime;
import org.openehr.rm.datatypes.text.DvText;
import org.openehr.rm.support.identification.ObjectID;

/**
 * Defines a single point event in a series
 *
 */
public final class PointEvent<T extends ItemStructure> extends Event<T> {

	/**
	 * Construct a PointEvent
	 * 
	 * @param uid
	 * @param archetypeNodeId
	 * @param name
	 * @param archetypeDetails
	 * @param feederAudit
	 * @param links
	 * @param time
	 * @param data
	 * @param state
	 */
    @FullConstructor
	public PointEvent(
			@Attribute(name = "uid") ObjectID uid,
            @Attribute(name = "archetypeNodeId", required = true) String archetypeNodeId,
            @Attribute(name = "name", required = true) DvText name,
            @Attribute(name = "archetypeDetails") Archetyped archetypeDetails,
            @Attribute(name = "feederAudit") FeederAudit feederAudit,
            @Attribute(name = "links") Set<Link> links,
            @Attribute(name = "parent") History<T> parent,
            @Attribute(name = "time", required = true) DvDateTime time,
            @Attribute(name = "data", required = true) T data,
            @Attribute(name = "state") ItemStructure state) {
		super(uid, archetypeNodeId, name, archetypeDetails, feederAudit, links,
				parent, time, data, state);
	}

	//POJO
	PointEvent() {
	}

}
