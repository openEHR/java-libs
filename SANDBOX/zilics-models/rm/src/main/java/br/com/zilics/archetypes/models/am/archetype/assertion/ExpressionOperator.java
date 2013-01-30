
package br.com.zilics.archetypes.models.am.archetype.assertion;

import br.com.zilics.archetypes.models.rm.annotation.EqualsField;
import br.com.zilics.archetypes.models.rm.annotation.NotNull;
import br.com.zilics.archetypes.models.rm.annotation.RmClass;

/**
 * Abstract parent of operator types
 *
 * @author Humberto
 */
@RmClass("EXPR_OPERATOR")
public abstract class ExpressionOperator extends ExpressionItem {
	private static final long serialVersionUID = -2829617307986124309L;
	@EqualsField
	@NotNull
	private OperatorKind operator;
	@EqualsField
    private boolean precedenceOverridden;

	/**
	 * Default constructor
	 */
	public ExpressionOperator() {}
	
	/**
	 * Another constructor
	 * @param type type of this expression
	 * @param operator the operator
	 * @param precedenceOverridden true if overrides precedence
	 */
	public ExpressionOperator(String type, OperatorKind operator, boolean precedenceOverridden) {
		super(type);
		this.operator = operator;
		this.precedenceOverridden = precedenceOverridden;
	}
	
    /**
     * Get the operator
     * @return Code of operator
     */
    public OperatorKind getOperator() {
        return operator;
    }

    /**
     * Set the operator
     * @param operator Code of operator
     */
    public void setOperator(OperatorKind operator) {
		assertMutable();
        this.operator = operator;
    }

    /**
     * Get the precedenceOverridden
     * @return True if the natural precedence of operators is overridden in the
     * expression represented by this node of the expression tree. If True,
     * parentheses should be introduced around the totality of the syntax expression
     * corresponding to this operator node and its operands.
     */
    public boolean isPrecedenceOverridden() {
        return precedenceOverridden;
    }

    /**
     * Set the precedenceOverridden
     * @param precedenceOverridden True if the natural precedence of operators is overridden in the
     * expression represented by this node of the expression tree. If True,
     * parentheses should be introduced around the totality of the syntax expression
     * corresponding to this operator node and its operands.
     */
    public void setPrecedenceOverridden(boolean precedenceOverridden) {
		assertMutable();
        this.precedenceOverridden = precedenceOverridden;
    }
}
