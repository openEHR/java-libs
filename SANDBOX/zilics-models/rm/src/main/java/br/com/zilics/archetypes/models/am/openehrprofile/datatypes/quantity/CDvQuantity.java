
package br.com.zilics.archetypes.models.am.openehrprofile.datatypes.quantity;

import java.util.ArrayList;
import java.util.List;

import br.com.zilics.archetypes.models.am.archetype.constraintmodel.CDomainType;
import br.com.zilics.archetypes.models.rm.annotation.EqualsField;
import br.com.zilics.archetypes.models.rm.datatypes.quantity.DvQuantity;
import br.com.zilics.archetypes.models.rm.datatypes.text.CodePhrase;
import br.com.zilics.archetypes.models.rm.support.basic.Interval;
import br.com.zilics.archetypes.models.rm.utils.validation.ValidationResult;

/**
 * Constraint instances of {@link br.com.zilics.archetypes.models.rm.datatypes.quantity.DvQuantity}
 * @author Humberto
 */
public class CDvQuantity extends CDomainType {

	private static final long serialVersionUID = -8607421840075668552L;
	@EqualsField
	private List<CQuantityItem> list;
	@EqualsField
    private CodePhrase property;

	/**
	 * DefaultConstructor
	 */
	public CDvQuantity() {
	}
	
    /**
     * Another constructor
     * @param occurrences how many times it occurs
     * @param nodeId the node id
     * @param assumedValue the assumed value
     * @param list the list of possible values
     * @param property the property we are measuring
     */
    public CDvQuantity(Interval<Integer> occurrences, String nodeId, DvQuantity assumedValue, List<CQuantityItem> list, CodePhrase property) {
    	super(occurrences, nodeId, assumedValue);
    	this.list = list;
    	this.property = property;
    	if (list == null && property == null)
    		setAnyAllowed(true);
    }

	/**
     * Get the list
     * @return List of value/units pairs
     */
    public List<CQuantityItem> getList() {
        return getList(list);
    }

    /**
     * Set the list
     * @param list List of value/units pairs
     */
    public void setList(List<CQuantityItem> list) {
		assertMutable();
        this.list = list;
    }

    /**
     * Get the property
     * @return Optional constraint on units property
     */
    public CodePhrase getProperty() {
        return property;
    }

    /**
     * Set the property
     * @param property Optional constraint on units property
     */
    public void setProperty(CodePhrase property) {
		assertMutable();
        this.property = property;
    }
    
    /**
     * {@inheritDoc}
     */
	@Override
    public String getRmTypeName() {
        return "DV_QUANTITY";
    }

	/**
     * {@inheritDoc}
     */
    @Override
    public CDvQuantity makeSimpleCopy() {
    	CDvQuantity result = new CDvQuantity();
    	this.copyProperties(result);
    	result.setList(this.getList());
    	result.setProperty(this.getProperty());
    	return result;
    }	


    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
    	return "C_DV_QUANTITY[" + property + ", " + list + "]";
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected void performValidation(ValidationResult result) {
    	super.performValidation(result);
    	if (!((getAnyAllowed() != null && getAnyAllowed()) ^ (list != null || property != null)))
   			result.addItem(this, "Problem with any allowed");
    }

    /**
     * returns a list with the units of the CQuantityItem's
     */
	public List<String> getUnitsList() {
		List<String> result = new ArrayList<String>();
		if(list == null) return result;
		for(CQuantityItem cqItem : list){
			result.add(cqItem.getUnits());
		}
		return result;
	}
    
}
