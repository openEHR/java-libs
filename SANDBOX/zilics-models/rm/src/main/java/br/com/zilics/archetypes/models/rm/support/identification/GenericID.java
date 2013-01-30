
package br.com.zilics.archetypes.models.rm.support.identification;

import br.com.zilics.archetypes.models.rm.annotation.EqualsField;
import br.com.zilics.archetypes.models.rm.annotation.NotEmpty;
import br.com.zilics.archetypes.models.rm.annotation.RmClass;

/**
 * Generic identifier type for identifiers whose format is otherwise unknown to <I>open</I>EHR.
 * Includes an attribute for naming the identification scheme (which may well be local).
 *
 * @author Humberto
 */
@RmClass("GENERIC_ID")
public class GenericID extends ObjectID {

	private static final long serialVersionUID = 5609109190310626063L;
	@EqualsField
	@NotEmpty
	private String scheme;

    /**
     * Get the scheme
     * @return Name of the scheme to which this identifier conforms.
     * Ideally this name will be recognisable globally but realistically it
     * may be a local ad hoc scheme whose name is not controlled or
     * standardised in any way.
     */
    public String getScheme() {
        return scheme;
    }

    /**
     * Set the scheme
     * @param scheme Name of the scheme to which this identifier conforms.
     * Ideally this name will be recognisable globally but realistically it
     * may be a local ad hoc scheme whose name is not controlled or
     * standardised in any way.
     */
    public void setScheme(String scheme) {
		assertMutable();
        this.scheme = scheme;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
    	return this.getClass().getSimpleName() + "[" + getValue() + "|" + scheme + "]";
    }
    

}
