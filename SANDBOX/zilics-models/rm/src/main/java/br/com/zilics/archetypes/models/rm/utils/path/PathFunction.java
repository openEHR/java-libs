package br.com.zilics.archetypes.models.rm.utils.path;

import java.util.List;

import br.com.zilics.archetypes.models.rm.exception.PathEvaluationException;
import br.com.zilics.archetypes.models.rm.utils.path.context.PathEvaluationContext;
import br.com.zilics.archetypes.models.rm.utils.path.model.ListValue;
import br.com.zilics.archetypes.models.rm.utils.path.parsed.TreeNode;

/**
 * Represents a custom defined function of the A-path (with lazy evaluated arguments)
 * 
 * @author Humberto Naves
 *
 */
public interface PathFunction {
	
	/**
	 * Evaluate the function passing parameters and returning a {@link ListValue}
	 * @param nodes the list will all the arguments to be passed to the function
	 * @param context the context that will we use for the evaluation
	 * @return the result of the evaluation
	 * @throws PathEvaluationException Any exception raised while evaluating
	 */
	public ListValue evaluate(List<TreeNode> nodes, PathEvaluationContext context) throws PathEvaluationException;
}
