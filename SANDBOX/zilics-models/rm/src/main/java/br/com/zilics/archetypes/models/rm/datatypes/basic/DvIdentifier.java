
package br.com.zilics.archetypes.models.rm.datatypes.basic;

import br.com.zilics.archetypes.models.rm.annotation.EqualsField;
import br.com.zilics.archetypes.models.rm.annotation.NotEmpty;

/**
 * Type for representing identifiers of real-world entities. Typical identifiers
 * include drivers license number, social security number, vertans affairs number,
 * prescription id, order id, and so on.
 * @author Humberto
 */
public class DvIdentifier extends DataValue {

	private static final long serialVersionUID = -711887910408727839L;
	@NotEmpty
	@EqualsField
	private String issuer;
	@NotEmpty
	@EqualsField
    private String assigner;
	@NotEmpty
	@EqualsField
    private String id;
	@NotEmpty
	@EqualsField
    private String type;

    /**
     * Get the issuer
     * @return Authority which issues the kind of id used in the <I>id</I> field
     * of this object.
     */
    public String getIssuer() {
        return issuer;
    }

    /**
     * Set the issuer
     * @param issuer Authority which issues the kind of id used in the <I>id</I> field
     * of this object.
     */
    public void setIssuer(String issuer) {
		assertMutable();
        this.issuer = issuer;
    }

    /**
     * Get the assigner
     * @return Organization that assigned the id to the item being identified.
     */
    public String getAssigner() {
        return assigner;
    }

    /**
     * Set the assigner
     * @param assigner Organization that assigned the id to the item being identified.
     */
    public void setAssigner(String assigner) {
		assertMutable();
        this.assigner = assigner;
    }

    /**
     * Get the id
     * @return The identifier value. Often structured, according to the definition
     * of the issuing authority's rules.
     */
    public String getId() {
        return id;
    }

    /**
     * Set the id
     * @param id The identifier value. Often structured, according to the definition
     * of the issuing authority's rules.
     */
    public void setId(String id) {
		assertMutable();
        this.id = id;
    }

    /**
     * Get the type
     * @return The identifier type, such as "prescription", or "SSN".
     */
    public String getType() {
        return type;
    }

    /**
     * Set the type
     * @param type The identifier type, such as "prescription", or "SSN".
     */
    public void setType(String type) {
		assertMutable();
        this.type = type;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
	public String toString(){
    	return objectToString(id);
    }    

}
