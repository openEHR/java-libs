
package br.com.zilics.archetypes.models.rm.support.identification;

import br.com.zilics.archetypes.models.rm.annotation.EqualsField;
import br.com.zilics.archetypes.models.rm.annotation.NotEmpty;

/**
 * Reference to a {@link br.com.zilics.archetypes.models.rm.common.archetyped.Locatable}
 * instance inside the top-level content structure inside a
 * {@link br.com.zilics.archetypes.models.rm.common.changecontrol.Version}; the path
 * attribute is applied to the object that
 * {@link br.com.zilics.archetypes.models.rm.common.changecontrol.Version} data points to.
 *
 * @author Humberto
 */
public class LocatableRef extends ObjectRef {

	private static final long serialVersionUID = 5560319038690023753L;
	@NotEmpty
	@EqualsField
	private String path;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
		assertMutable();
        this.path = path;
    }

    /**
     * A URI form of the reference, created by concatenating the
     * following:
     * "ehr://" + id.value + "/" + path
     */
    public String asURI() {
        return "ehr://" + getId().getValue() + "/" + path;
    }
    
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
    	return this.getClass().getSimpleName() + "[id: " + getId() + ", namespace:" + getNamespace() + ", type:" + getType() + ", path" + path + "]";
    }    
    

}
