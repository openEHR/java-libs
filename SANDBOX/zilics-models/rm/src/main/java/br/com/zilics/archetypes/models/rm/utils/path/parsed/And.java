package br.com.zilics.archetypes.models.rm.utils.path.parsed;

import br.com.zilics.archetypes.models.rm.exception.PathEvaluationException;
import br.com.zilics.archetypes.models.rm.utils.path.context.PathEvaluationContext;
import br.com.zilics.archetypes.models.rm.utils.path.model.ListValue;

/**
 * Represents the logical and operator
 * @author Humberto Naves
 *
 */
public final class And extends BinaryOperator {

	private static final long serialVersionUID = -7434031181310460160L;


	public And(TreeNode leftOperand, TreeNode rightOperand) {
		super(leftOperand, rightOperand);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ListValue evaluate(PathEvaluationContext context) throws PathEvaluationException {
		ListValue left = getLeftOperand().evaluate(context);
		if (left.effectiveBooleanValue() == false) return ListValue.FALSE;
		ListValue right = getRightOperand().evaluate(context);
		if (right.effectiveBooleanValue()) return ListValue.TRUE;
		return ListValue.FALSE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getOperatorName() {
		return "and";
	}
	
}

