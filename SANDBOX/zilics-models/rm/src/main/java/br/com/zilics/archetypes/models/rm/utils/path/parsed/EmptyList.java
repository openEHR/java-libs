package br.com.zilics.archetypes.models.rm.utils.path.parsed;

import br.com.zilics.archetypes.models.rm.exception.PathEvaluationException;
import br.com.zilics.archetypes.models.rm.utils.path.context.PathEvaluationContext;
import br.com.zilics.archetypes.models.rm.utils.path.model.ListValue;

/**
 * Represents an empty list
 * @author Humberto Naves
 *
 */
public final class EmptyList extends TreeNode {

	private static final long serialVersionUID = -2304890458402678012L;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public ListValue evaluate(PathEvaluationContext context) throws PathEvaluationException {
		return ListValue.EMPTY;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "()";
	}
	
}

