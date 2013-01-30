
package br.com.zilics.archetypes.models.am.archetype.assertion;

import br.com.zilics.archetypes.models.rm.annotation.EqualsField;
import br.com.zilics.archetypes.models.rm.annotation.NotNull;
import br.com.zilics.archetypes.models.rm.annotation.RmClass;

/**
 * Expression tree leaf item
 * <p>This can represent one of:
 * <ul>
 *   <li>A manifest constant of any primitive type(Integer, Real, Boolean, String
 *  Character, Date, Time, Date_Time, Duration), or (in future) of any complex
 * reference model type, e. g. a DV_CODED_TEXT</li>
 *   <li>A path refering to a value in the archetype (paths with a leading slash
 * are in the definition section; paths with no leading slash are in the outer part
 * of the archetype, e.g. "archetype_id/value" refers to the String value of the
 * <i>archetype_id</i> attribute of the enclosing archetype</li>
 *   <li>A constraint, expressed in the form of concrete subtype of
 * {@link br.com.zilics.archetypes.models.am.archetype.constraintmodel.CObject}, most often this
 * will be a {@link br.com.zilics.archetypes.models.am.archetype.constraintmodel.CPrimitiveObject}</li>
 *
 *
 * @author Humberto
 */
@RmClass("EXPR_LEAF")
public class ExpressionLeaf extends ExpressionItem {
	private static final long serialVersionUID = -6134047255321020746L;
	@NotNull
	@EqualsField
	private Object item;
	@NotNull
	@EqualsField
    private ReferenceType referenceType;

	/**
	 * Default constructor
	 */
	public ExpressionLeaf() {}
	
	/**
	 * Another constructor
	 * @param type the type of this expression item
	 * @param item the item that this leaf refers
	 * @param referenceType what kind of reference
	 */
	public ExpressionLeaf(String type, Object item, ReferenceType referenceType) {
		super(type);
		this.item = item;
		this.referenceType = referenceType;
	}

    /**
     * Get the item
     * @return The value referred to; a manifest constant, an attribute-path
     * (in the form of a String), or for the right-hand side of a <i>matches</i> node,
     * a constraint
     */
    public Object getItem() {
        return item;
    }

    /**
     * Set the item
     * @param item The value referred to; a manifest constant, an attribute-path
     * (in the form of a String), or for the right-hand side of a <i>matches</i> node,
     * a constraint
     */
    public void setItem(Object item) {
		assertMutable();
        this.item = item;
    }

    /**
     * Get the referenceType
     * @return Type of reference: "constant", "attribute", "function", "constraint".
     * The first three are used to indicate the referencing mechanism for an operand.
     * The last is used to indicate a constraint operand, as happens in the case of
     * the right-hand operand of the <i>matches</i> operator
     */
    public ReferenceType getReferenceType() {
        return referenceType;
    }

    /**
     * Set the referenceType
     * @param referenceType Type of reference: "constant", "attribute", "function", "constraint".
     * The first three are used to indicate the referencing mechanism for an operand.
     * The last is used to indicate a constraint operand, as happens in the case of
     * the right-hand operand of the <i>matches</i> operator
     */
    public void setReferenceType(ReferenceType referenceType) {
		assertMutable();
        this.referenceType = referenceType;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
    	if (item != null)
    		return item.toString();
    	return null;
    }
    
    
    /**
     * Make an {@link ExpressionLeaf} based on a boolean value
     * @param value the boolean value
     * @return the resulting {@link ExpressionLeaf}
     */
    public static ExpressionLeaf booleanConstant(boolean value) {
    	ExpressionLeaf result = new ExpressionLeaf();
    	result.setItem(value);
    	result.setReferenceType(ReferenceType.CONSTANT);
    	result.setType(ExpressionItem.BOOLEAN);
    	return result;
    }
    
    /**
     * Make an {@link ExpressionLeaf} based on a double value
     * @param value the double value
     * @return the resulting {@link ExpressionLeaf}
     */
    public static ExpressionLeaf realConstant(double value) {
    	ExpressionLeaf result = new ExpressionLeaf();
    	result.setItem(value);
    	result.setReferenceType(ReferenceType.CONSTANT);
    	result.setType(ExpressionItem.REAL);
    	return result;
    }
    
    /**
     * Make an {@link ExpressionLeaf} based on a string value
     * @param value the string value
     * @return the resulting {@link ExpressionLeaf}
     */
    public static ExpressionLeaf stringConstant(String value) {
    	ExpressionLeaf result = new ExpressionLeaf();
    	result.setItem(value);
    	result.setReferenceType(ReferenceType.CONSTANT);
    	result.setType(ExpressionItem.STRING);
    	return result;
    }

    /**
     * Make an {@link ExpressionLeaf} based on a integer value
     * @param value the int value
     * @return the resulting {@link ExpressionLeaf}
     */
    public static ExpressionLeaf intConstant(int value) {
    	ExpressionLeaf result = new ExpressionLeaf();
    	result.setItem(value);
    	result.setReferenceType(ReferenceType.CONSTANT);
    	result.setType(ExpressionItem.INTEGER);
    	return result;
    }

    /**
     * Make an {@link ExpressionLeaf} based on a path
     * @param value the path
     * @return the resulting {@link ExpressionLeaf}
     */
    public static ExpressionLeaf pathConstant(String value) {
    	ExpressionLeaf result = new ExpressionLeaf();
    	result.setItem(value);
    	result.setReferenceType(ReferenceType.CONSTANT);
    	result.setType(ExpressionItem.PATH);
    	return result;
    }

}
