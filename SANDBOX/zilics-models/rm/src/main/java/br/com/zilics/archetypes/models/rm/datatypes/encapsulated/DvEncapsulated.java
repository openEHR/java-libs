
package br.com.zilics.archetypes.models.rm.datatypes.encapsulated;

import br.com.zilics.archetypes.models.rm.datatypes.basic.DataValue;
import br.com.zilics.archetypes.models.rm.datatypes.text.CodePhrase;


/**
 * Abstract class defining the common meta-data of all types of
 * encapsulated data.
 *
 * @author Humberto
 */
public class DvEncapsulated extends DataValue {

	private static final long serialVersionUID = 4043632959522582015L;
	private CodePhrase charset;
    private CodePhrase language;
    private Integer size;

    /**
     * Get the charset
     * @return Name of character encoding scheme in which this value is encoded.
     * Coded from <I>open</I>EHR Code Set "character sets". Unicode is the
     * default assumption in <I>open</I>EHR, with UTF-8 being the assumed encoding.
     * This attribute allows for variations from these assumptions.
     */
    public CodePhrase getCharset() {
        return charset;
    }

    /**
     * Set the charset
     * @param charset Name of character encoding scheme in which this value is encoded.
     * Coded from <I>open</I>EHR Code Set "character sets". Unicode is the
     * default assumption in <I>open</I>EHR, with UTF-8 being the assumed encoding.
     * This attribute allows for variations from these assumptions.
     */
    public void setCharset(CodePhrase charset) {
		assertMutable();
        this.charset = charset;
    }

    /**
     * Get the language
     * @return Optional indicator of the localised language in which the data is written,
     * if relevant. Coded from <I>open</I>EHR code set "languages".
     */
    public CodePhrase getLanguage() {
        return language;
    }

    /**
     * Set the language
     * @param language Optional indicator of the localised language in which the data is written,
     * if relevant. Coded from <I>open</I>EHR code set "languages".
     */
    public void setLanguage(CodePhrase language) {
		assertMutable();
        this.language = language;
    }

    /**
     * Get the size
     * @return Original size in bytes of unencoded encapsulated data. I.e. encodings
     * such as base64, hexadecimal, etc do not change the value of this attribute.
     */
    public Integer getSize() {
        return size;
    }

    /**
     * Set the size
     * @param size Original size in bytes of unencoded encapsulated data. I.e. encodings
     * such as base64, hexadecimal, etc do not change the value of this attribute.
     */
    public void setSize(Integer size) {
		assertMutable();
        this.size = size;
    }

    /**
     * Customized toString() for the Composition Summary list format.
     * @return it's charset, language and size.
     */
    @Override
	public String toString(){
    	return this.charset.getCodeString() +" "+ this.language.getCodeString() + " "+ this.size;
    }

}
