
package br.com.zilics.archetypes.models.am.archetype.assertion;

import br.com.zilics.archetypes.models.rm.annotation.EqualsField;
import br.com.zilics.archetypes.models.rm.annotation.NotNull;
import br.com.zilics.archetypes.models.rm.annotation.RmClass;

/**
 * Binary operator expression node
 *
 * @author Humberto
 */
@RmClass("EXPR_BINARY_OPERATOR")
public class ExpressionBinaryOperator extends ExpressionOperator {
	private static final long serialVersionUID = 5877762770258324L;
	@EqualsField
	@NotNull
	private ExpressionItem leftOperand;
	@EqualsField
	@NotNull
    private ExpressionItem rightOperand;

	/**
	 * Default constructor
	 */
	public ExpressionBinaryOperator() {}
	
	/**
	 * Another constructor
	 * @param type type of this expression
	 * @param operator the operator
	 * @param precedenceOverridden true if overrides precedence
	 * @param leftOperand the left operand
	 * @param rightOperand the right operand
	 */
	public ExpressionBinaryOperator(String type, OperatorKind operator, boolean precedenceOverridden, ExpressionItem leftOperand, ExpressionItem rightOperand) {
		super(type, operator, precedenceOverridden);
		this.leftOperand = leftOperand;
		this.rightOperand = rightOperand;
	}
	
	/**
	 * Get the {@link ExpressionItem} that is the left operand
	 * @return the left operand
	 */
    public ExpressionItem getLeftOperand() {
        return leftOperand;
    }

	/**
	 * Set the {@link ExpressionItem} that is the left operand
	 * @param leftOperand the left operand
	 */
    public void setLeftOperand(ExpressionItem leftOperand) {
		assertMutable();
        this.leftOperand = leftOperand;
    }

	/**
	 * Get the {@link ExpressionItem} that is the right operand
	 * @return the right operand
	 */
    public ExpressionItem getRightOperand() {
        return rightOperand;
    }

	/**
	 * Set the {@link ExpressionItem} that is the right operand
	 * @param rightOperand the right operand
	 */
    public void setRightOperand(ExpressionItem rightOperand) {
		assertMutable();
        this.rightOperand = rightOperand;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
    	if (getOperator() != null)
    		return "" + getLeftOperand() + " " + getOperator().getOperatorForm() + " " + getRightOperand();
    	return null;
    }    

}
