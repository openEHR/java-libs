
package br.com.zilics.archetypes.models.am.openehrprofile.datatypes.quantity;

import java.util.Set;

import br.com.zilics.archetypes.models.am.archetype.constraintmodel.CDomainType;
import br.com.zilics.archetypes.models.rm.annotation.EqualsField;
import br.com.zilics.archetypes.models.rm.datatypes.quantity.DvOrdinal;
import br.com.zilics.archetypes.models.rm.support.basic.Interval;
import br.com.zilics.archetypes.models.rm.utils.validation.ValidationResult;

/**
 * Class specifying constraints on instances of
 * {@link DvOrdinal}. Custom constrainer type for
 * instances of {@link DvOrdinal}
 * @author Humberto
 */
public class CDvOrdinal extends CDomainType {

	private static final long serialVersionUID = 2910056186425943964L;

	@EqualsField
	private Set<DvOrdinal> list;

	/**
	 * DefaultConstructor
	 */
	public CDvOrdinal() {
	}
	
    /**
     * Another constructor
     * @param occurrences how many times it occurs
     * @param nodeId the node id
     * @param assumedValue the assumed value
     * @param list the list of possible values
     */
    public CDvOrdinal(Interval<Integer> occurrences, String nodeId, DvOrdinal assumedValue, Set<DvOrdinal> list) {
    	super(occurrences, nodeId, assumedValue);
    	this.list = list;
    }
	
    /**
     * Get the list
     * @return Set of allowed {@link DvOrdinal} values
     */
    public Set<DvOrdinal> getList() {
        return getSet(list);
    }

    /**
     * Set the list
     * @param list Set of allowed {@link DvOrdinal} values
     */
    public void setList(Set<DvOrdinal> list) {
		assertMutable();
        this.list = list;
    }
    

    /**
     * {@inheritDoc}
     */
	@Override
    public String getRmTypeName() {
        return "DV_ORDINAL";
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public CDvOrdinal makeSimpleCopy() {
    	CDvOrdinal result = new CDvOrdinal();
    	this.copyProperties(result);
    	result.setList(this.getList());
    	return result;
    }	

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
    	return "C_DV_ORDINAL[" + list + "]";
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected void performValidation(ValidationResult result) {
    	super.performValidation(result);
    	if (!((getAnyAllowed() != null && getAnyAllowed()) ^ (list != null)))
   			result.addItem(this, "Problem with any allowed");
    }

    /**
     * Returns the DvOrdinal in this object's list that has the given value
     * @param value value to search for
     * @return the DvOrdinal if found, null otherwise
     */
	public DvOrdinal getOrdinalByValue(int value) {
		if(list == null) return null;
		for(DvOrdinal dvOrdinal : list){
			if(dvOrdinal.getValue() == value)
				return dvOrdinal;
		}
		return null;
	}
    
    

}
