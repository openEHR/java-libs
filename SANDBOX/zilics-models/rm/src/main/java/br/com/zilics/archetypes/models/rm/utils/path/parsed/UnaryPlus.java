package br.com.zilics.archetypes.models.rm.utils.path.parsed;

import br.com.zilics.archetypes.models.rm.exception.PathEvaluationException;
import br.com.zilics.archetypes.models.rm.utils.path.context.PathEvaluationContext;
import br.com.zilics.archetypes.models.rm.utils.path.model.ListValue;

public final class UnaryPlus extends UnaryOperator {

	private static final long serialVersionUID = 8970162776756935120L;

	public UnaryPlus(TreeNode operand) {
		super(operand);
	}

	@Override
	public ListValue evaluate(PathEvaluationContext context) throws PathEvaluationException {
		ListValue operand = getOperand().evaluate(context);
		return operand;
	}

	@Override
	public String getOperatorName() {
		return "+";
	}
	 
}

