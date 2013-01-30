package br.com.zilics.archetypes.models.rm.utils.path.parsed;

import br.com.zilics.archetypes.models.rm.exception.PathEvaluationException;
import br.com.zilics.archetypes.models.rm.utils.path.PathEvaluator;
import br.com.zilics.archetypes.models.rm.utils.path.context.PathEvaluationContext;
import br.com.zilics.archetypes.models.rm.utils.path.model.ListValue;
import br.com.zilics.archetypes.models.rm.utils.path.model.ObjectValue;
import br.com.zilics.archetypes.models.rm.utils.path.model.SingleValue;

public final class Parent extends TestNode {

	private static final long serialVersionUID = 982517083328957552L;

	public Parent(String nodeTest) {
		super(nodeTest);
	}
	
	@Override
	public ListValue evaluate(PathEvaluationContext context) throws PathEvaluationException {
		if (context.getContextItem().getValue().isPrimitive())
			throw new PathEvaluationException("Child is not allowed for primitive types");
		PathEvaluator evaluator = context.getEvaluator();
		ObjectValue current = (ObjectValue) context.getContextItem().getValue().getValue();
		ObjectValue parent = evaluator.getParent(current); 
		if (parent == null) return ListValue.EMPTY;
		if (!isAnyAllowed()) {
			if (!getNodeTest().equals(context.getEvaluator().getNodeName(parent)))
				return ListValue.EMPTY;
		}
		return new ListValue(new SingleValue(parent));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "parent::" + super.toString();
	}
}

