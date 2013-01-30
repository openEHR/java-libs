package br.com.zilics.archetypes.models.rm.utils.path.parsed;

import br.com.zilics.archetypes.models.rm.exception.PathEvaluationException;
import br.com.zilics.archetypes.models.rm.utils.path.context.PathEvaluationContext;
import br.com.zilics.archetypes.models.rm.utils.path.model.ListValue;

public final class Or extends BinaryOperator {

	private static final long serialVersionUID = 6566379002787226697L;

	public Or(TreeNode leftOperand, TreeNode rightOperand) {
		super(leftOperand, rightOperand);
	}

	@Override
	public ListValue evaluate(PathEvaluationContext context) throws PathEvaluationException {
		ListValue left = getLeftOperand().evaluate(context);
		if (left.effectiveBooleanValue()) return ListValue.TRUE;
		ListValue right = getRightOperand().evaluate(context);
		if (right.effectiveBooleanValue()) return ListValue.TRUE;
		return ListValue.FALSE;
	}

	@Override
	public String getOperatorName() {
		return "or";
	}
	
}
