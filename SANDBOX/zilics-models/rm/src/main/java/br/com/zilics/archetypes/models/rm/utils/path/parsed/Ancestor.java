package br.com.zilics.archetypes.models.rm.utils.path.parsed;

import java.util.ArrayList;
import java.util.List;

import br.com.zilics.archetypes.models.rm.exception.PathEvaluationException;
import br.com.zilics.archetypes.models.rm.utils.path.PathEvaluator;
import br.com.zilics.archetypes.models.rm.utils.path.context.PathEvaluationContext;
import br.com.zilics.archetypes.models.rm.utils.path.model.ListValue;
import br.com.zilics.archetypes.models.rm.utils.path.model.ObjectValue;
import br.com.zilics.archetypes.models.rm.utils.path.model.SingleValue;

/**
 * Represents the ancestor test node
 * @author Humberto Naves
 *
 */
public final class Ancestor extends TestNode {

	private static final long serialVersionUID = -301595143837414519L;

	public Ancestor(String nodeTest) {
		super(nodeTest);
	}
	
	static ListValue getAncestor(SingleValue sv, PathEvaluator evaluator, String nodeTest, boolean anyAllowed, boolean includeSelf) throws PathEvaluationException {
		if (!(sv.getValue() instanceof ObjectValue))
			throw new PathEvaluationException("Ancestor is not allowed for primitive types");
		ObjectValue current = (ObjectValue) sv.getValue();
		
		List<SingleValue> result = new ArrayList<SingleValue>();
		if (includeSelf) result.add(sv);
		while(true) {
			current = evaluator.getParent(current); 			
			if (current == null) break;
			if (!anyAllowed) {
				if (nodeTest.equals(evaluator.getNodeName(current)))
					result.add(new SingleValue(current));
			} else {
				result.add(new SingleValue(current));
			}
		}
		
		return ListValue.getInstanceFromList(result);
		
	}	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public ListValue evaluate(PathEvaluationContext context) throws PathEvaluationException {
		return getAncestor(context.getContextItem().getValue(), context.getEvaluator(), getNodeTest(), isAnyAllowed(), false);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "ancestor::" + super.toString();
	}
}

