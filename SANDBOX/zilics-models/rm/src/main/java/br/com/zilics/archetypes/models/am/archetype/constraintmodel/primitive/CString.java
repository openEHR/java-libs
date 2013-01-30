
package br.com.zilics.archetypes.models.am.archetype.constraintmodel.primitive;

import java.util.List;

import br.com.zilics.archetypes.models.rm.annotation.EqualsField;
import br.com.zilics.archetypes.models.rm.utils.validation.ValidationResult;

/**
 * Constraint on instances of String
 *
 * @author Humberto
 */
public class CString extends CPrimitive {
	private static final long serialVersionUID = 7853299155542961944L;
	@EqualsField
	private List<String> list;
	@EqualsField
    private boolean listOpen;
	@EqualsField
    private String pattern;
	@EqualsField
    private String assumedValue;

	/**
	 * Default constructor
	 */
	public CString() {}
	
	/**
	 * Another constructor
	 * @param pattern regular expression defining this constraint
	 * @param list list of possible values
	 * @param assumedValue the assumed value
	 */
	public CString(String pattern, List<String> list, String assumedValue) {
		this.pattern = pattern;
		this.list = list;
		this.assumedValue = assumedValue;
	}
	
    /**
     * Get the listOpen
     *
     * @return True if the Set being used to specify the
     * constraint but is not considered exhaustive
     */
    public boolean isListOpen() {
        return listOpen;
    }

    /**
     * Set the listOpen
     *
     * @param listOpen True if the Set being used to specify the
     * constraint but is not considered exhaustive
     */
    public void setListOpen(boolean listOpen) {
		assertMutable();
        this.listOpen = listOpen;
    }


    /**
     * Get the pattern
     * @return Regular expression patter for proposed instances
     * of String to match
     */
    public String getPattern() {
        return pattern;
    }

    /**
     * Set the pattern
     * @param pattern Regular expression patter for proposed instances
     * of String to match
     */
    public void setPattern(String pattern) {
		assertMutable();
        this.pattern = pattern;
    }


    /**
     * Get the list
     * @return Set of Strings specifying constraint
     */
    public List<String> getList() {
        return getList(list);
    }

    /**
     * Set the list
     * @param list Set of Strings specifying constraint
     */
    public void setList(List<String> list) {
		assertMutable();
        this.list = list;
    }


    /**
     * Get the assumedValue
     * @return Value to be assumed if none sent in data
     */
    @Override
    public String getAssumedValue() {
        return assumedValue;
    }

    /**
     * Set the assumedValue
     * @param assumedValue Value to be assumed if none sent in data
     */
    public void setAssumedValue(String assumedValue) {
		assertMutable();
        this.assumedValue = assumedValue;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
	public final String getType() {
		return "STRING";
	}


    /**
     * {@inheritDoc}
     */
    @Override
    public String defaultValue() {
    	if (pattern != null) {
    		
    	} else if (list != null && list.size() > 0) {
    		return list.get(0);
    	}
    	return null;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isValidValue(Object value) {
    	if (!(value instanceof String)) return false;
    	String s = (String) value;
    	if (pattern != null) {
    		return s.matches(pattern);
    	} else if (list != null) {
    		return list.contains(s);
    	}
    	return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isValidType(Object value) {
    	return (value instanceof String);
    }
    

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
    	if (pattern != null)
    		return "{/" + pattern + "/}";
    	else
    		return "{/" + list + "/}";
    }

    
    /**
     * {@inheritDoc}
     */
    @Override
    protected void performValidation(ValidationResult result) {
    	super.performValidation(result);
    	if (!((pattern != null) ^ (list != null)))
    		result.addItem(this, "String list or (exclusive) pattern should be defined");
    }    
    
}
