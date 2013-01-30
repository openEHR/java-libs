package br.com.zilics.archetypes.models.rm.utils.path;

import java.util.ArrayList;
import java.util.List;

import br.com.zilics.archetypes.models.rm.exception.PathEvaluationException;
import br.com.zilics.archetypes.models.rm.utils.path.context.PathEvaluationContext;
import br.com.zilics.archetypes.models.rm.utils.path.model.ListValue;
import br.com.zilics.archetypes.models.rm.utils.path.parsed.TreeNode;

/**
 * A simple abstract class that implements {@link PathFunction}, but
 * instead of lazy evaluated arguments, this class uses eager ones.
 * 
 * @see PathFunction
 * @author Humberto Naves
 *
 */
public abstract class PathEagerFunction implements PathFunction {
	/**
	 * The abstract method similar to {@link #evaluate(List, PathEvaluationContext)}, but
	 * instead of passing a list of {@link TreeNode}s as arguments we pass a list of {@link ListValue}s
	 * with the arguments ({@link TreeNode}) already evaluated.
	 * @param context the path context
	 * @param params the list of parameters (arguments)
	 * @return the result of the evaluation
	 * @throws PathEvaluationException Any exception raised while evaluating
	 */
	public abstract ListValue evaluate(PathEvaluationContext context, List<ListValue> params) throws PathEvaluationException;

	/**
	 * {@inheritDoc}
	 */
	public final ListValue evaluate(List<TreeNode> nodes, PathEvaluationContext context) throws PathEvaluationException {
		List<ListValue> params = new ArrayList<ListValue>();
		for(TreeNode node : nodes) {
			// Evaluate the arguments
			params.add(node.evaluate(context));
		}
		return evaluate(context, params);
	}
}
