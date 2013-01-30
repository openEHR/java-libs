package br.com.zilics.archetypes.models.rm.utils.path.parsed;

import br.com.zilics.archetypes.models.rm.exception.PathEvaluationException;
import br.com.zilics.archetypes.models.rm.utils.path.PathEvaluator;
import br.com.zilics.archetypes.models.rm.utils.path.context.PathEvaluationContext;
import br.com.zilics.archetypes.models.rm.utils.path.model.ListValue;
import br.com.zilics.archetypes.models.rm.utils.path.model.ObjectValue;

public final class Self extends TestNode {

	private static final long serialVersionUID = -9152322655022559722L;

	public Self(String nodeTest) {
		super(nodeTest);
	}
	
	@Override
	public ListValue evaluate(PathEvaluationContext context) throws PathEvaluationException {
		if (!isAnyAllowed()) {
			if (context.getContextItem().getValue().isPrimitive())
				throw new PathEvaluationException("Name test not allowed for primitive types");
			PathEvaluator evaluator = context.getEvaluator();
			ObjectValue current = (ObjectValue) context.getContextItem().getValue().getValue();
			if (!getNodeTest().equals(evaluator.getNodeName(current)))
				return ListValue.EMPTY;
		}
		
		return new ListValue(context.getContextItem().getValue());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "self::" + super.toString();
	}
}

