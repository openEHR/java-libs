package br.com.zilics.archetypes.models.rm.utils.path.parsed;

import br.com.zilics.archetypes.models.rm.exception.PathEvaluationException;
import br.com.zilics.archetypes.models.rm.utils.path.context.PathEvaluationContext;
import br.com.zilics.archetypes.models.rm.utils.path.model.ListValue;

public final class GreaterThanOrEqual extends BinaryOperator {


	private static final long serialVersionUID = -4475653142708471120L;

	public GreaterThanOrEqual(TreeNode leftOperand, TreeNode rightOperand) {
		super(leftOperand, rightOperand);
	}

	@Override
	public ListValue evaluate(PathEvaluationContext context) throws PathEvaluationException {
		ListValue left = getLeftOperand().evaluate(context);
		ListValue right = getRightOperand().evaluate(context);
		return left.isGreaterThanOrEqual(right);
	}

	@Override
	public String getOperatorName() {
		return ">=";
	}
		
}

