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
 * 
 * Represents a "descendant::"
 * @see Child
 * @author Humberto Naves
 *
 */
public final class Descendant extends TestNode {

	private static final long serialVersionUID = 3115204849834875248L;

	public Descendant(String nodeTest) {
		super(nodeTest);
	}
	
	/**
	 * Statically invoke {@link Child#getChildren(SingleValue, PathEvaluator, String, boolean)} and
	 * get all the descendants (without filtering)
	 * @param sv the single value to get the descendants from
	 * @param evaluator the evaluator
	 * @param nodeTest the node test
	 * @param anyAllowed is any allowed?
	 * @return the list of descendants
	 * @throws PathEvaluationException any problem occurred will throw an exception here
	 */
	static ListValue getDescendant(SingleValue sv, PathEvaluator evaluator, boolean includeSelf) throws PathEvaluationException {
		if (!(sv.getValue() instanceof ObjectValue))
			return null;
		ListValue children = Child.getChildren(sv, evaluator, null, true);
		for(SingleValue child : children.getValues()) {
			ListValue grandChildren = getDescendant(child, evaluator, false);
			if (grandChildren != null) {
				children = children.union(grandChildren);
			}
		}
		if (includeSelf)
			children = new ListValue(sv).union(children);
		return children;
	}

	/**
	 * Filtering is performed after getting all descendants
	 * @param descendants the list of descendants
	 * @param evaluator the evaluator
	 * @param filterTest the name to test
	 * @return the filtered list
	 * @throws PathEvaluationException any problem occurred will throw an exception here
	 */
	static ListValue filterByName(ListValue descendants, PathEvaluator evaluator, String filterTest)  throws PathEvaluationException {
		List<SingleValue> result = new ArrayList<SingleValue>();
		for(SingleValue sv : descendants.getValues()) {
			if (!(sv.getValue() instanceof ObjectValue)) continue;
			if (filterTest.equals(evaluator.getNodeName((ObjectValue) sv.getValue()))) {
				result.add(sv);
			}
		}
		return ListValue.getInstanceFromList(result);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public ListValue evaluate(PathEvaluationContext context) throws PathEvaluationException {
		if (context.getContextItem().getValue().isPrimitive())
			throw new PathEvaluationException("Descendant is not allowed for primitive types");
		ListValue descendants = getDescendant(context.getContextItem().getValue(), context.getEvaluator(), false);
		if (!isAnyAllowed()) {
			descendants = filterByName(descendants, context.getEvaluator(), getNodeTest());
		}
		return descendants;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "descendant::" + super.toString();
	}
}

