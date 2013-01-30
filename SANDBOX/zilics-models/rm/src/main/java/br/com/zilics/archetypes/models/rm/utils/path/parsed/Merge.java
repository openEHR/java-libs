package br.com.zilics.archetypes.models.rm.utils.path.parsed;

import java.util.List;

import br.com.zilics.archetypes.models.rm.exception.PathEvaluationException;
import br.com.zilics.archetypes.models.rm.utils.path.context.PathEvaluationContext;
import br.com.zilics.archetypes.models.rm.utils.path.model.ListValue;

public final class Merge extends MultipleOperandsNode {

	private static final long serialVersionUID = -7265817552176911516L;

	public Merge(List<TreeNode> operands) {
		super(operands);
	}

	@Override
	public ListValue evaluate(PathEvaluationContext context) throws PathEvaluationException {
		ListValue result = ListValue.EMPTY;
		for(TreeNode operand : getOperands()) {
			result = result.union(operand.evaluate(context));
		}
		return result;
	}

}

