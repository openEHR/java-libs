package br.com.zilics.archetypes.models.rm.utils.path.parsed;

import br.com.zilics.archetypes.models.rm.exception.PathEvaluationException;
import br.com.zilics.archetypes.models.rm.utils.path.context.PathEvaluationContext;
import br.com.zilics.archetypes.models.rm.utils.path.model.ListValue;

/**
 * Represents the ancestor-or-self test node
 * @author Humberto Naves
 *
 */
public final class AncestorOrSelf extends TestNode {

	private static final long serialVersionUID = -738718719609730162L;

	public AncestorOrSelf(String nodeTest) {
		super(nodeTest);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public ListValue evaluate(PathEvaluationContext context) throws PathEvaluationException {
		return Ancestor.getAncestor(context.getContextItem().getValue(), context.getEvaluator(), getNodeTest(), isAnyAllowed(), true);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "ancestor-or-self::" + super.toString();
	}
}

