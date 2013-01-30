
package br.com.zilics.archetypes.models.am.openehrprofile.datatypes.text;

import java.util.List;

import br.com.zilics.archetypes.models.am.archetype.constraintmodel.CDomainType;
import br.com.zilics.archetypes.models.rm.annotation.EqualsField;
import br.com.zilics.archetypes.models.rm.support.basic.Interval;
import br.com.zilics.archetypes.models.rm.support.identification.TerminologyID;
import br.com.zilics.archetypes.models.rm.utils.validation.ValidationResult;

/**
 * Express constraints on instances of
 * {@link br.com.zilics.archetypes.models.rm.datatypes.text.CodePhrase}. The terminologyId
 * attribute may be specified on its own to indicate any term from a specified
 * terminology; the codeList attribute may be used to limit the codes to
 * a specific list.
 *
 * @author Humberto
 */
public class CCodePhrase extends CDomainType {

	private static final long serialVersionUID = 7598105769691450610L;

	@EqualsField
	private TerminologyID terminologyId;
	@EqualsField
    private List<String> codeList;

	/**
	 * Default constructor
	 */
	public CCodePhrase() {
	}
	
    /**
     * Another constructor
     * @param occurrences how many times it occurs
     * @param nodeId the node id
     * @param assumedValue the assumed value
     */
    public CCodePhrase(Interval<Integer> occurrences, String nodeId, Object assumedValue, TerminologyID terminologyId, List<String> codeList) {
    	super(occurrences, nodeId, assumedValue);
    	this.terminologyId = terminologyId;
    	this.codeList = codeList;
    }

	/**
     * Get the terminologyId
     * @return  Syntax string expressing constraint on allowed primary terms
     */
    public TerminologyID getTerminologyId() {
        return terminologyId;
    }

    /**
     * Set the terminologyId
     * @param terminologyId  Syntax string expressing constraint on allowed primary terms
     */
    public void setTerminologyId(TerminologyID terminologyId) {
		assertMutable();
        this.terminologyId = terminologyId;
    }

    /**
     * Get the codeList
     * @return List of allowed codes; may be empty, meaning any code
     * in the terminology may be used.
     */
    public List<String> getCodeList() {
        return getList(codeList);
    }

    /**
     * Set the codeList
     * @param codeList List of allowed codes; may be empty, meaning any code
     * in the terminology may be used.
     */
    public void setCodeList(List<String> codeList) {
		assertMutable();
        this.codeList = codeList;
    }

    /**
     * {@inheritDoc}
     */
	@Override
    public String getRmTypeName() {
        return "CODE_PHRASE";
    }
	
	/**
     * {@inheritDoc}
     */
    @Override
    public CCodePhrase makeSimpleCopy() {
    	CCodePhrase result = new CCodePhrase();
    	this.copyProperties(result);
    	result.setCodeList(this.getCodeList());
    	result.setTerminologyId(this.getTerminologyId());
    	return result;
    }		

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
    	return "C_CODE_PHRASE[" + terminologyId + ", " + codeList + "]: " + getCanonicalPath();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected void performValidation(ValidationResult result) {
    	super.performValidation(result);
    	if (!((getAnyAllowed() != null && getAnyAllowed()) ^ (terminologyId != null)))
    		result.addItem(this, "Problem with any allowed");
   		if (codeList != null) {
   			if (terminologyId == null)
   				result.addItem(this, "Code list needs terminology id");
   		}
    }
}
