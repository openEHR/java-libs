package br.com.zilics.archetypes.models.rm.utils.path.parsed;

import br.com.zilics.archetypes.models.rm.exception.PathEvaluationException;
import br.com.zilics.archetypes.models.rm.utils.path.PathEvaluator;
import br.com.zilics.archetypes.models.rm.utils.path.context.PathEvaluationContext;
import br.com.zilics.archetypes.models.rm.utils.path.model.ListValue;
import br.com.zilics.archetypes.models.rm.utils.path.model.ObjectValue;
import br.com.zilics.archetypes.models.rm.utils.path.model.SingleValue;

public final class Metadata extends TestNode {

	private static final long serialVersionUID = -5652319134263603598L;

	public Metadata(String nodeTest) {
		super(nodeTest);
	}
	
	@Override
	public ListValue evaluate(PathEvaluationContext context) throws PathEvaluationException {
		if (context.getContextItem().getValue().isPrimitive())
			throw new PathEvaluationException("Metadata is not allowed for primitive types");
		ObjectValue current = (ObjectValue) context.getContextItem().getValue().getValue();
		PathEvaluator evaluator = context.getEvaluator();
		Object result = evaluator.getMetadata(current, getNodeTest());
		if (result == null) return ListValue.EMPTY;
		if (result instanceof String) return new ListValue(new SingleValue((String) result));
		if (result instanceof Integer) return new ListValue(new SingleValue((Integer) result));
		if (result instanceof Boolean) return new ListValue(new SingleValue((Boolean) result));
		if (result instanceof Double) return new ListValue(new SingleValue((Double) result));
		return new ListValue(new SingleValue((ObjectValue) result));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "metadata::" + super.toString();
	}
}

