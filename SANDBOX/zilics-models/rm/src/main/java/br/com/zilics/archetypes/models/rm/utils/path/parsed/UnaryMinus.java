package br.com.zilics.archetypes.models.rm.utils.path.parsed;

import br.com.zilics.archetypes.models.rm.exception.PathEvaluationException;
import br.com.zilics.archetypes.models.rm.utils.path.context.PathEvaluationContext;
import br.com.zilics.archetypes.models.rm.utils.path.model.ListValue;

public final class UnaryMinus extends UnaryOperator {

	private static final long serialVersionUID = 7148920612837128192L;

	public UnaryMinus(TreeNode operand) {
		super(operand);
	}

	@Override
	public ListValue evaluate(PathEvaluationContext context) throws PathEvaluationException {
		ListValue operand = getOperand().evaluate(context);
		return operand.negate();
	}

	@Override
	public String getOperatorName() {
		return "-";
	}
		 
}

