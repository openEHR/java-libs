package br.com.zilics.archetypes.models.rm.utils.path.parsed;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import br.com.zilics.archetypes.models.rm.exception.PathEvaluationException;
import br.com.zilics.archetypes.models.rm.utils.path.context.PathEvaluationContext;
import br.com.zilics.archetypes.models.rm.utils.path.model.ListValue;
import br.com.zilics.archetypes.models.rm.utils.path.model.SingleValue;

public final class For extends VariablesNode {

	private static final long serialVersionUID = 8644753824448892596L;

	public For(Map<String, TreeNode> variables, TreeNode operand) {
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
	private ListValue explodeAll(List<Entry<String, ListValue>> list, int pos, PathEvaluationContext context) throws PathEvaluationException {
		boolean hasNext = (pos != (list.size() - 1));
		Entry<String, ListValue> entry = list.get(pos);
		ListValue result = ListValue.EMPTY;
		for(SingleValue sv : entry.getValue().getValues()) {
			context.bindVariable(entry.getKey(), sv);
			if (hasNext) {
				result = result.union(explodeAll(list, pos + 1, context));
			} else {
				result = result.union(getOperand().evaluate(context));
			}
		}
		return result;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public ListValue evaluate(PathEvaluationContext context) throws PathEvaluationException {
		Map<String, ListValue> values = new LinkedHashMap<String, ListValue>();
		ListValue result = ListValue.EMPTY;
		if (evaluateVariableRanges(values, context)) {
			List<Entry<String, ListValue>> list = new ArrayList<Entry<String, ListValue>>(values.entrySet());
			result = explodeAll(list, 0, context);
		}
		removeVariables(context);
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "for " + super.toString() + " return " + getOperand();
	}

}

