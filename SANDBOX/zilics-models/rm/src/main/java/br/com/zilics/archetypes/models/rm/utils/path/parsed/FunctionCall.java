package br.com.zilics.archetypes.models.rm.utils.path.parsed;

import java.util.List;

import br.com.zilics.archetypes.models.rm.exception.PathEvaluationException;
import br.com.zilics.archetypes.models.rm.utils.path.PathFunction;
import br.com.zilics.archetypes.models.rm.utils.path.context.PathEvaluationContext;
import br.com.zilics.archetypes.models.rm.utils.path.model.ListValue;

/**
 * 
 * @author hnaves
 *
 */
public final class FunctionCall extends MultipleOperandsNode {

	private static final long serialVersionUID = -6479490819423169542L;

	private final String functionName;

	public FunctionCall(String functionName, List<TreeNode> operands) {
		super(operands);
		if (functionName == null)
			throw new NullPointerException("Function name is null");
		this.functionName = functionName;
	}
	
	public String getFunctionName() {
		return functionName;
	}
	
	@Override
	public ListValue evaluate(PathEvaluationContext context) throws PathEvaluationException {
		PathFunction function = context.getFunctionByName(functionName);
		if (function == null) throw new PathEvaluationException("Unknown function " + functionName);
		return function.evaluate(getOperands(), context);
	}

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return functionName + super.toString();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		if (!super.equals(obj)) return false;
		FunctionCall other = (FunctionCall) obj;
		
		return (functionName.equals(other.functionName));
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		final int PRIME = 31;
		int hash = super.hashCode();
		hash = hash * PRIME + functionName.hashCode();
		return hash;
	}
}

