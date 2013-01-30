package br.com.zilics.archetypes.models.rm.utils.path.parsed;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import br.com.zilics.archetypes.models.rm.exception.PathEvaluationException;
import br.com.zilics.archetypes.models.rm.utils.path.context.PathEvaluationContext;
import br.com.zilics.archetypes.models.rm.utils.path.model.ListValue;
import br.com.zilics.archetypes.models.rm.utils.path.model.SingleValue;

/**
 * The "every" construction
 * @author Humberto Naves
 *
 */
public final class Every extends VariablesNode {

	private static final long serialVersionUID = 3984707440393912639L;

	public Every(Map<String, TreeNode> variables, TreeNode operand) {
		super(variables, operand);
	}
	
	/**
	 * This method explodes all possible combinations of values of variables (each combination
	 * appears only one time)
	 * @param list The list of of the ranges of the variables
	 * @param pos the position in the list (marking the already configured variables)
	 * @param context the path evaluation context
	 * @return true if operand is true for every combinations of values 
	 * @throws PathEvaluationException any exception raised during calculation
	 */
	private boolean explodeAll(List<Entry<String, ListValue>> list, int pos, PathEvaluationContext context) throws PathEvaluationException {
		boolean hasNext = (pos != (list.size() - 1));
		Entry<String, ListValue> entry = list.get(pos);
		for(SingleValue sv : entry.getValue().getValues()) {
			context.bindVariable(entry.getKey(), sv);
			if (hasNext) {
				if (!explodeAll(list, pos + 1, context))
					return false;
			} else {
				if (!(getOperand().evaluate(context).effectiveBooleanValue()))
					return false;
			}
		}
		return true;
	}	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public ListValue evaluate(PathEvaluationContext context) throws PathEvaluationException {
		Map<String, ListValue> values = new HashMap<String, ListValue>();
		boolean result = true;
		if (evaluateVariableRanges(values, context)) {
			List<Entry<String, ListValue>> list = new ArrayList<Entry<String, ListValue>>(values.entrySet());
			// explode all combinations of values
			result = explodeAll(list, 0, context);
		}
		removeVariables(context);
		if (result) return ListValue.TRUE;
		return ListValue.FALSE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "every " + super.toString() + " satisfies " + getOperand();
	}

}

