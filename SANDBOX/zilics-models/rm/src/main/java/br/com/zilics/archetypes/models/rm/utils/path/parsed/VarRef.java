package br.com.zilics.archetypes.models.rm.utils.path.parsed;

import br.com.zilics.archetypes.models.rm.exception.PathEvaluationException;
import br.com.zilics.archetypes.models.rm.utils.path.context.PathEvaluationContext;
import br.com.zilics.archetypes.models.rm.utils.path.model.ListValue;

public final class VarRef extends TreeNode {
	private static final long serialVersionUID = -8667887161560962150L;

	private final String varName;
	
	public VarRef(String varName) {
		if (varName == null)
			throw new NullPointerException("Null varName");
		this.varName = varName;
	}
	
	public String getVarName() {
		return varName;
	}

	@Override
	public ListValue evaluate(PathEvaluationContext context) throws PathEvaluationException {
		return new ListValue(context.resolveVariable(varName));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "$" + varName;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		if (!super.equals(obj)) return false;
		VarRef other = (VarRef) obj;
		
		return (varName.equals(other.varName));
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		return varName.hashCode();
	}
}

