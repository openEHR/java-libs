package br.com.zilics.archetypes.models.rm.utils.path.parsed;

import br.com.zilics.archetypes.models.rm.exception.PathEvaluationException;
import br.com.zilics.archetypes.models.rm.utils.path.context.PathEvaluationContext;
import br.com.zilics.archetypes.models.rm.utils.path.model.ListValue;

public final class Remainder extends BinaryOperator {

	private static final long serialVersionUID = -478100494126581767L;

	public Remainder(TreeNode leftOperand, TreeNode rightOperand) {
		super(leftOperand, rightOperand);
	}

	@Override
	public ListValue evaluate(PathEvaluationContext context) throws PathEvaluationException {
		ListValue left = getLeftOperand().evaluate(context);
		ListValue right = getRightOperand().evaluate(context);
		return left.remainder(right);
	}

	@Override
	public String getOperatorName() {
		return "mod";
	}
	
}

