package br.com.zilics.archetypes.models.rm.datatypes.text;

import br.com.zilics.archetypes.models.rm.annotation.EqualsField;
import br.com.zilics.archetypes.models.rm.annotation.NotNull;

/**
 * A text item whose value must be the rubric from a controlled terminology, the
 * key (the code) of which is the defining code attribute.
 * @author Humberto
 */
public class DvCodedText extends DvText {

	private static final long serialVersionUID = 7005652875430617523L;
	@NotNull
	@EqualsField
	private CodePhrase definingCode;

	/**
	 * Default constructor
	 */
	public DvCodedText() {}
	
	/**
	 * Another constructor
	 * @param definingCode the defining code
	 */
	public DvCodedText(String value, CodePhrase definingCode) {
		super(value);
		this.definingCode = definingCode;
	}
    /**
     * Return defining code
     * @return defining code
     */
    public CodePhrase getDefiningCode(){
        return definingCode;
    }

    /**
     * Set the defining code
     * @param definingCode The defining code
     */
    public void setDefiningCode(CodePhrase definingCode){
		assertMutable();
        this.definingCode = definingCode;
    }
    

    /**
     * {@inheritDoc}
     */
    @Override
	public String toString(){
    	return objectToString(definingCode);
    }
}