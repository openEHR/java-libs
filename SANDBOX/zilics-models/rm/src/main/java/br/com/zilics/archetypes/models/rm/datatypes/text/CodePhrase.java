package br.com.zilics.archetypes.models.rm.datatypes.text;
import br.com.zilics.archetypes.models.rm.annotation.EqualsField;
import br.com.zilics.archetypes.models.rm.annotation.NotEmpty;
import br.com.zilics.archetypes.models.rm.annotation.NotNull;
import br.com.zilics.archetypes.models.rm.datatypes.basic.DataValue;
import br.com.zilics.archetypes.models.rm.support.identification.TerminologyID;

/**
 * A fully coordinated (all coordination has been performed) term from a
 * terminology service (as distinct from a particular {@link TerminologyID}).
 * @author Humberto
 */
public class CodePhrase extends DataValue {


	private static final long serialVersionUID = -3160712203460964423L;
	@NotNull
	@EqualsField
	private TerminologyID terminologyId;
	@NotEmpty
	@EqualsField
    private String codeString;

	/**
	 * Default constructor
	 */
	public CodePhrase() {}
	
	/**
	 * Another constructor
	 * @param terminologyId the terminology ID
	 * @param codeString the code string
	 */
	public CodePhrase(TerminologyID terminologyId, String codeString) {
		this.codeString = codeString;
		this.terminologyId = terminologyId;
	}

    /**
     * Get the terminologyId
     * @return Indentifier of the distinct terminology form which the codeString
     * (or its elements) was extracted.
     */
    public TerminologyID getTerminologyId() {
        return terminologyId;
    }

    /**
     * Set the terminologyId
     * @param terminologyId Indentifier of the distinct terminology form which the code_string
     * (or its elements) was extracted.
     */
    public void setTerminologyId(TerminologyID terminologyId) {
		assertMutable();
        this.terminologyId = terminologyId;
    }

    /**
     * Get the codeString
     * @return The key used by the terminology service to identify a concept or coordination of concepts.
     * This string is most likely parsable inside the terminology service, but nothing
     * can be assumed about its syntax outside that context.
     */
    public String getCodeString() {
        return codeString;
    }

    /**
     * Set the codeString
     * @param codeString The key used by the terminology service to identify a concept or coordination of concepts.
     * This string is most likely parsable inside the terminology service, but nothing
     * can be assumed about its syntax outside that context.
     */
    public void setCodeString(String codeString) {
		assertMutable();
        this.codeString = codeString;
    }

    /**
     * {@inheritDoc}
     */
    @Override
	public String toString(){
        return codeString;
    }
}