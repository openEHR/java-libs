package br.com.zilics.archetypes.models.rm.utils.path.parsed;

import br.com.zilics.archetypes.models.rm.exception.PathEvaluationException;
import br.com.zilics.archetypes.models.rm.utils.path.context.PathEvaluationContext;
import br.com.zilics.archetypes.models.rm.utils.path.model.ListValue;

/**
 * Returns the set-difference between to {@link ListValue}s 
 * @author Humberto Naves
 *
 */
public final class Except extends BinaryOperator {


	private static final long serialVersionUID = -3505349983540798760L;

	public Except(TreeNode leftOperand, TreeNode rightOperand) {
		super(leftOperand, rightOperand);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ListValue evaluate(PathEvaluationContext context) throws PathEvaluationException {
		ListValue left = getLeftOperand().evaluate(context);
		ListValue right = getRightOperand().evaluate(context);
		return left.except(right);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getOperatorName() {
		return "except";
	}
		
}

