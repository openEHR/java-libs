
package br.com.zilics.archetypes.models.am.archetype.assertion;

import br.com.zilics.archetypes.models.rm.annotation.EqualsField;
import br.com.zilics.archetypes.models.rm.annotation.NotNull;
import br.com.zilics.archetypes.models.rm.annotation.RmClass;

/**
 * Unary operator expression node
 *
 * @author Humberto
 */
@RmClass("EXPR_UNARY_OPERATOR")
public class ExpressionUnaryOperator extends ExpressionOperator {
	private static final long serialVersionUID = 1591694100908364479L;
	@NotNull
	@EqualsField
	private ExpressionItem operand;

	/**
	 * Default constructor
	 */
	public ExpressionUnaryOperator() {}
	
	/**
	 * Another constructor
	 * @param type type of this expression
	 * @param operator the operator
	 * @param precedenceOverridden true if overrides precedence
	 * @param operand the operand
	 */
	public ExpressionUnaryOperator(String type, OperatorKind operator, boolean precedenceOverridden, ExpressionItem operand) {
		super(type, operator, precedenceOverridden);
		this.operand = operand;
	}

	/**
     * Get the operand
     * @return Operand node
     */
    public ExpressionItem getOperand() {
        return operand;
    }

    /**
     * Set the operand
     * @param operand Operand node
     */
    public void setOperand(ExpressionItem operand) {
		assertMutable();
        this.operand = operand;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
    	if (getOperator() != null)
        	return "" + getOperator().getOperatorForm() + " " + getOperand();
    	return null;
    }

}
