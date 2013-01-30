package br.com.zilics.archetypes.models.rm.utils.path.parsed;

import br.com.zilics.archetypes.models.rm.exception.PathEvaluationException;
import br.com.zilics.archetypes.models.rm.utils.path.context.PathEvaluationContext;
import br.com.zilics.archetypes.models.rm.utils.path.model.ListValue;
import br.com.zilics.archetypes.models.rm.utils.path.model.SingleValue;

public final class Root extends TreeNode {

	private static final long serialVersionUID = 220887004707122416L;

	@Override
	public ListValue evaluate(PathEvaluationContext context) throws PathEvaluationException {
		return new ListValue(new SingleValue(context.getEvaluator().getRoot()));
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "/";
	}

}

