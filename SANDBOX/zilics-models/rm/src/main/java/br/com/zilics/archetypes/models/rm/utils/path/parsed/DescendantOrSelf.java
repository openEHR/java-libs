package br.com.zilics.archetypes.models.rm.utils.path.parsed;

import br.com.zilics.archetypes.models.rm.exception.PathEvaluationException;
import br.com.zilics.archetypes.models.rm.utils.path.context.PathEvaluationContext;
import br.com.zilics.archetypes.models.rm.utils.path.model.ListValue;

/**
 * 
 * Represents a "descendant-or-self::"
 * @see Child
 * @author Humberto Naves
 *
 */
public final class DescendantOrSelf extends TestNode {

	private static final long serialVersionUID = -1714262513987346876L;

	public DescendantOrSelf(String nodeTest) {
		super(nodeTest);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public ListValue evaluate(PathEvaluationContext context) throws PathEvaluationException {
		if (context.getContextItem().getValue().isPrimitive())
			throw new PathEvaluationException("DescendantOrSelf is not allowed for primitive types");
		ListValue descendants = Descendant.getDescendant(context.getContextItem().getValue(), context.getEvaluator(), true);
		if (!isAnyAllowed()) {
			descendants = Descendant.filterByName(descendants, context.getEvaluator(), getNodeTest());
		}
		return descendants;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "descendant-or-self::" + super.toString();
	}
}

