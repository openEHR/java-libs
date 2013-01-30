
package br.com.zilics.archetypes.models.rm.common.generic;

import br.com.zilics.archetypes.models.rm.RMObject;
import br.com.zilics.archetypes.models.rm.support.identification.PartyRef;

/**
 * Abstract concept of a proxy description of a party, including an optional link
 * to data for this party in a demographic or other identity management system.
 * @author Humberto
 */
public abstract class PartyProxy extends RMObject {
	private static final long serialVersionUID = -2545738233144723981L;
	private PartyRef externalRef;

	/**
	 * Get the external reference
	 * @return Optional reference to more detailed demographic or identification
	 * information for this party, in an external system.
	 */
    public PartyRef getExternalRef() {
        return externalRef;
    }

    /**
     * Set the external reference
     * @param externalRef Optional reference to more detailed demographic or identification
	 * information for this party, in an external system.
     */
    public void setExternalRef(PartyRef externalRef) {
    	assertMutable();
        this.externalRef = externalRef;
    }
}
