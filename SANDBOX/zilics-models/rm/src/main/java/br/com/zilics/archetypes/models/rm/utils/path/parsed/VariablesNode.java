package br.com.zilics.archetypes.models.rm.utils.path.parsed;

import java.util.Map;

import br.com.zilics.archetypes.models.rm.exception.PathEvaluationException;
import br.com.zilics.archetypes.models.rm.utils.path.context.PathEvaluationContext;
import br.com.zilics.archetypes.models.rm.utils.path.model.ListValue;


public abstract class VariablesNode extends TreeNode {
	private static final long serialVersionUID = -526631777916120412L;

	private final Map<String, TreeNode> variables;
	private final TreeNode operand;
	
	public VariablesNode(Map<String, TreeNode> variables, TreeNode operand) {
		if (variables == null || operand == null)
			throw new NullPointerException("Variables and operand can't be null");
		this.variables = variables;
		this.operand = operand;
	}
	
	public Map<String, TreeNode> getVariables() {
		return variables;
	}
	
	public TreeNode getOperand() {
		return operand;
	}
	
	protected boolean evaluateVariableRanges(Map<String, ListValue> result, PathEvaluationContext context) throws PathEvaluationException {
		boolean isEmpty = false;
		boolean hasVariable = false;
		for(String varName : variables.keySet()) {
			hasVariable = true;
			context.assertVariableUnused(varName);
			ListValue val = variables.get(varName).evaluate(context);
			if (val.getValues().size() == 0) isEmpty = true;
			result.put(varName, val);
		}
		return !isEmpty && hasVariable;
	}
	
	protected void removeVariables(PathEvaluationContext context) {
		for(String varName : variables.keySet()) {
			context.clearVariable(varName);
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		boolean first = true;
		for(String varName : variables.keySet()) {
			if (!first) sb.append(", ");
			sb.append("$").append(varName).append(" in ").append(variables.get(varName));
			first = false;
		}
		return sb.toString();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		if (!super.equals(obj)) return false;
		VariablesNode other = (VariablesNode) obj;
		
		return (variables.equals(other.variables)) &&
		(operand.equals(other.operand));
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		final int PRIME = 31;
		int hash = variables.hashCode();
		hash = hash * PRIME + operand.hashCode();
		return hash;
	}
}

