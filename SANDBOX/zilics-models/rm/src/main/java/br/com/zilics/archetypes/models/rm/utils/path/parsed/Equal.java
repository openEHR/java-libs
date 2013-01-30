package br.com.zilics.archetypes.models.rm.utils.path.parsed;

import br.com.zilics.archetypes.models.rm.exception.PathEvaluationException;
import br.com.zilics.archetypes.models.rm.utils.path.context.PathEvaluationContext;
import br.com.zilics.archetypes.models.rm.utils.path.model.ListValue;

/**
 * Test for equality binary operator
 * @author Humberto Naves
 *
 */
public final class Equal extends BinaryOperator {

	private static final long serialVersionUID = -2448260787210333559L;

	public Equal(TreeNode leftOperand, TreeNode rightOperand) {
		super(leftOperand, rightOperand);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ListValue evaluate(PathEvaluationContext context) throws PathEvaluationException {
		ListValue left = getLeftOperand().evaluate(context);
		ListValue right = getRightOperand().evaluate(context);
		return left.isEqual(right);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getOperatorName() {
		return "=";
	}
		
}

