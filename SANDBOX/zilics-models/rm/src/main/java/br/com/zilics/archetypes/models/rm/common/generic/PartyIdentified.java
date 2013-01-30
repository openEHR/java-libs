
package br.com.zilics.archetypes.models.rm.common.generic;

import java.util.List;

import br.com.zilics.archetypes.models.rm.annotation.EqualsField;
import br.com.zilics.archetypes.models.rm.datatypes.basic.DvIdentifier;
import br.com.zilics.archetypes.models.rm.utils.validation.ValidationResult;

/**
 * Proxy data for an identified party other than the subject of the record,
 * minimally consisting of human-readable identifier(s), such as name, formal
 * (and possibly computable) identifiers such as NHS numbers, and an optional
 * link to external data. There must be at least one of <I>name</I>,
 * <I>identifier</I> or <I>external_ref</I> present.
 * @author Humberto
 */
public class PartyIdentified extends PartyProxy {
	private static final long serialVersionUID = 8006300223379081613L;
	@EqualsField
	private String name;
	@EqualsField
    private List<DvIdentifier> identifiers;

    /**
     * Get the name
     * @return Optional human-readable name (in String form).
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name
     * @param name Optional human-readable name (in String form).
     */
    public void setName(String name) {
    	assertMutable();
        this.name = name;
    }

    /**
     * Get the identifiers
     * @return One or more formal identifiers (possibly computable).
     */
    public List<DvIdentifier> getIdentifiers() {
        return getList(identifiers);
    }

    /**
     * Set the identifiers
     * @param identifiers One or more formal identifiers (possibly computable).
     */
    public void setIdentifiers(List<DvIdentifier> identifiers) {
    	assertMutable();
        this.identifiers = identifiers;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected void performValidation(ValidationResult result) {
    	super.performValidation(result);
		if (name == null && identifiers == null && getExternalRef() == null)
			result.addItem(this, "Invalid party identified");
	}    

}
