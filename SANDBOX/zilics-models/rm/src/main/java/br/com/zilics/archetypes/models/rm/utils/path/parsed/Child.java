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
 * Represents a "child::" test node
 * @author Humberto Naves
 *
 */
public final class Child extends TestNode {

	private static final long serialVersionUID = -408022496505334664L;

	public Child(String nodeTest) {
		super(nodeTest);
	}
	
	/**
	 * Auxiliary method for getting all children
	 * @param sv the single value to get the children from
	 * @param evaluator the evaluator
	 * @param nodeTest the node test
	 * @param anyAllowed is any allowed?
	 * @return the list of children
	 * @throws PathEvaluationException any problem occurred will throw an exception here
	 */
	static ListValue getChildren(SingleValue sv, PathEvaluator evaluator, String nodeTest, boolean anyAllowed) throws PathEvaluationException {
		if (!(sv.getValue() instanceof ObjectValue))
			throw new PathEvaluationException("Child is not allowed for primitive types");
		List<?> children;
		ObjectValue current = (ObjectValue) sv.getValue();
		if (anyAllowed) {
			List<String> childrenNames = evaluator.getAllChildrenNames(current);
			childrenNames = evaluator.getAllChildrenNames(current);
			ListValue result = ListValue.EMPTY;
			for(String childName : childrenNames) {
				List<SingleValue> values = new ArrayList<SingleValue>();
				children = evaluator.getChildren(current, childName);
				if (!children.isEmpty() && !(children.get(0) instanceof ObjectValue))
					continue;
				for(Object obj : children)
					values.add(SingleValue.getInstanceFromObject(obj));
				result = result.union(ListValue.getInstanceFromList(values));
			}
			return result;
		} else {
			children = evaluator.getChildren(current, nodeTest);
			List<SingleValue> values = new ArrayList<SingleValue>();
			for(Object obj : children)
				values.add(SingleValue.getInstanceFromObject(obj));
			return ListValue.getInstanceFromList(values);
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public ListValue evaluate(PathEvaluationContext context) throws PathEvaluationException {
		if (context.getContextItem() == null || context.getContextItem().getValue() == null)
			throw new PathEvaluationException("Context item is null");
		return getChildren(context.getContextItem().getValue(), context.getEvaluator(), getNodeTest(), isAnyAllowed());
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "child::" + super.toString();
	}
}

