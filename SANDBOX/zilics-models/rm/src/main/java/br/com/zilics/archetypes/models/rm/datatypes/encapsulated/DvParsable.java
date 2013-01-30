
package br.com.zilics.archetypes.models.rm.datatypes.encapsulated;

/**
 * Encapsulated data expressed as a parsable string.
 * The internal model of the data item is not described in the
 * openEHR model in common with other encapsulated types, but in
 * this case, the form of the data is assumed to be plaintext,
 * rather than compressed or other types of large binary data.
 *
 * @author Humberto
 */
public class DvParsable extends DvEncapsulated {

	private static final long serialVersionUID = 4714863714232504281L;
	private String value;
    private String formalism;

    /**
     * Get the value
     * @return The string which may validly be empty in some syntaxes
     */
    public String getValue() {
        return value;
    }

    /**
     * Set the value
     * @param value The string which may validly be empty in some syntaxes
     */
    public void setValue(String value) {
		assertMutable();
        this.value = value;
    }

    /**
     * Get the formalism
     * @return Name of the formalism, e.g. "GLIF 1.0", "proforma", etc.
     */
    public String getFormalism() {
        return formalism;
    }

    /**
     * Set the formalism
     * @param formalism Name of the formalism, e.g. "GLIF 1.0", "proforma", etc.
     */
    public void setFormalism(String formalism) {
		assertMutable();
        this.formalism = formalism;
    }

    /**
     * Customized toString() for the Composition Summary list format.
     * @return it's formalism and its value
     */
    @Override
	public String toString(){
	return this.formalism + " "+this.value;
}

}
