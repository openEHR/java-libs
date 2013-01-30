
package br.com.zilics.archetypes.models.rm.datatypes.text;

import java.util.List;

import br.com.zilics.archetypes.models.rm.annotation.EqualsField;
import br.com.zilics.archetypes.models.rm.annotation.NotEmpty;
import br.com.zilics.archetypes.models.rm.datatypes.basic.DataValue;
import br.com.zilics.archetypes.models.rm.datatypes.uri.DvURI;

/**
 * A text item, which may contain any amount of legal characters arranged as e.g. words
 * sentences etc(i.e. one DvText may be more tan one word). Visual formating and
 * hyperlinks may be included.
 * @author Humberto
 */
public class DvText extends DataValue {

	private static final long serialVersionUID = 2962032028609151375L;
	@NotEmpty
	@EqualsField
	private String value;
    private DvURI hyperlink;
    private String formatting;
    private List<TermMapping> mappings;
	@EqualsField
    private CodePhrase language;
	@EqualsField
    private CodePhrase encoding;

	/**
	 * Default constructor
	 */
	public DvText() {}
	
	/**
	 * Another constructor
	 * @param value the value of this data value
	 */
	public DvText(String value) {
		this.value = value;
	}

    /**
     * Get the value
     * @return Displayable rendition of the item, regardless of its underlying structure.
     */
    public String getValue() {
        return value;
    }

    /**
     * Set the value
     * @param value Displayable rendition of the item, regardless of its underlying structure.
     */
    public void setValue(String value) {
		assertMutable();
        this.value = value;
    }

    /**
     * Get the hyperlink
     * @return Optional link sitting behind a section of plain text or coded term item.
     */
    public DvURI getHyperlink() {
        return hyperlink;
    }

    /**
     * Set the hyperlink
     * @param hyperlink Optional link sitting behind a section of plain text or coded term item.
     */
    public void setHyperlink(DvURI hyperlink) {
		assertMutable();
        this.hyperlink = hyperlink;
    }

    /**
     * Get the formatting
     * @return A format string of the form "name : value; name : value...", e.g.
     * "font-weight : bold; font-family : Arial; font-size : 12pt".
     */
    public String getFormatting() {
        return formatting;
    }

    /**
     * Set the formatting
     * @param formatting A format string of the form "name : value; name : value...", e.g.
     * "font-weight : bold; font-family : Arial; font-size : 12pt".
     */
    public void setFormatting(String formatting) {
		assertMutable();
        this.formatting = formatting;
    }

    /**
     * Get the mappings
     * @return Terms from other terminologies most closely matching this term,
     * typically used where the originator(e.g. pathology lab) of information
     * uses a local terminology but also supplies one or more equivalents from
     * wellknown terminologies.
     */
    public List<TermMapping> getMappings() {
        return getList(mappings);
    }

    /**
     * Set the mappings
     * @param mappings Terms from other terminologies most closely matching this term,
     * typically used where the originator(e.g. pathology lab) of information
     * uses a local terminology but also supplies one or more equivalents from
     * wellknown terminologies.
     */
    public void setMappings(List<TermMapping> mappings) {
		assertMutable();
        this.mappings = mappings;
    }

    /**
     * Get the language
     * @return Optional indicator of the localised language in which the value is written.
     */
    public CodePhrase getLanguage() {
        return language;
    }

    /**
     * Set the language
     * @param language Optional indicator of the localised language in which the value is written.
     */
    public void setLanguage(CodePhrase language) {
		assertMutable();
        this.language = language;
    }

    /**
     * Get the encoding
     * @return Name of character scheme in which this value is encoded.
     */
    public CodePhrase getEncoding() {
        return encoding;
    }

    /**
     * Set the encoding
     * @param encoding Name of character scheme in which this value is encoded.
     */
    public void setEncoding(CodePhrase encoding) {
		assertMutable();
        this.encoding = encoding;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
	public String toString(){
        return value;
    }
}
