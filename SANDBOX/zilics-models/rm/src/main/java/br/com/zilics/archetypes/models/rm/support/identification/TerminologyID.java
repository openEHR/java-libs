package br.com.zilics.archetypes.models.rm.support.identification;

import br.com.zilics.archetypes.models.rm.annotation.Ignore;
import br.com.zilics.archetypes.models.rm.annotation.RmClass;
import br.com.zilics.archetypes.models.rm.utils.validation.ValidationResult;


/**
 * Identifier for terminologies such as accessed via a terminology query service. In this class,
 * the value attribute identifies the Terminology in the terminology service, e.g. "SNOMED-CT".
 * A terminology is assumed to be in a particular language, which must be explicitly specified.
 * <br>
 * The value if the id attribute is the precise terminology id identifier, including actual release
 * (i.e. actual "version"), local modifications etc; e.g. "ICPC2".
 * Lexical form:
 * <br />name [ '(' version ')' ]
 *
 * @author Humberto
 */
@RmClass("TERMINOLOGY_ID")
public class TerminologyID extends ObjectID {

	private static final long serialVersionUID = 6818904046086680605L;
	
	@Ignore
	private String name;
	@Ignore
    private String versionId;

	/**
	 * Default constructor
	 */
	public TerminologyID() {}
	
	/**
	 * Another constructor
	 * @param value value of this terminology ID in the Lexical form:
	 * <br />name [ '(' version ')' ]
	 */
	public TerminologyID(String value) {
		super(value);
	}
	
	/**
	 * Builds a TerminologyID object from given name and versionId
	 * @param name Terminology name
	 * @param versionId Terminology version Id
	 */
	public TerminologyID(String name, String versionId){
		super(name + (versionId == null ? "" : '('+versionId+')'));
	}
	
    /**
     * Name of this terminology ID
     *
     * @return name
     */
    public String name() {
        return name;
    }

    /**
     * Return version of information pointed to by this ID,
     * if versioning is supported.
     *
     * @return versionId null if not present
     */
    public String versionId() {
        return versionId;
    }
	

	@Override
    protected void performValidation(ValidationResult result) {
    	super.performValidation(result);
		String value = getValue();
		if (value == null) return;
        int leftBrace = value.indexOf("(");
        int rightBrace = value.lastIndexOf(")");
        if (leftBrace > 1 && rightBrace == value.length() - 1) {
            name = value.substring(0, leftBrace);
            versionId = value.substring(leftBrace + 1, rightBrace);
        } else {
            name = value;
            versionId = null;
        }
	}
}
