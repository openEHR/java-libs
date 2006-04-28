package org.openehr.rm.common.generic;

import org.openehr.rm.support.identification.PartyReference;

public final class PartySelf extends PartyProxy {

	/**
	 * Constructs a PartySelf
	 * 
	 *@param externalRef
	 *@param name
	 *@param identifiers
	 *@throws IllegalArgumentException if identifiers is empty
	 */
	public PartySelf(PartyReference externalRef) {
		super(externalRef);
	}
	
	
	PartySelf() {
	}

}
