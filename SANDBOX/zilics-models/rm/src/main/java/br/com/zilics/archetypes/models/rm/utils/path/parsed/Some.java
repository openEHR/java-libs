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

public final class Some extends VariablesNode {

	private static final long serialVersionUID = 8834364835885537519L;

	public Some(Map<String, TreeNode> variables, TreeNode operand) {
		super(variables, operand);
	}
	
	private boolean explodeAll(List<Entry<String, ListValue>> list, int pos, PathEvaluationContext context) throws PathEvaluationException {
		boolean hasNext = (pos != (list.size() - 1));
		Entry<String, ListValue> entry = list.get(pos);
		for(SingleValue sv : entry.getValue().getValues()) {
			context.bindVariable(entry.getKey(), sv);
			if (hasNext) {
				if (explodeAll(list, pos + 1, context))
					return true;
			} else {
				if ((getOperand().evaluate(context).effectiveBooleanValue()))
					return true;
			}
		}
		return false;
	}	
	
	@Override
	public ListValue evaluate(PathEvaluationContext context) throws PathEvaluationException {
		Map<String, ListValue> values = new HashMap<String, ListValue>();
		boolean result = true;
		if (evaluateVariableRanges(values, context)) {
			List<Entry<String, ListValue>> list = new ArrayList<Entry<String, ListValue>>(values.entrySet());
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
		return "some " + super.toString() + " satisfies " + getOperand();
	}

}

