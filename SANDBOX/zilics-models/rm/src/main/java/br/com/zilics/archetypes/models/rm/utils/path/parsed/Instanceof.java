package br.com.zilics.archetypes.models.rm.utils.path.parsed;

import br.com.zilics.archetypes.models.rm.exception.PathEvaluationException;
import br.com.zilics.archetypes.models.rm.utils.path.context.PathEvaluationContext;
import br.com.zilics.archetypes.models.rm.utils.path.model.ListValue;
import br.com.zilics.archetypes.models.rm.utils.path.model.ObjectValue;
import br.com.zilics.archetypes.models.rm.utils.path.model.SingleValue;

public final class Instanceof extends TreeNode {
	private static final long serialVersionUID = 4625793536441829237L;
	private final TreeNode operand;
	private final String className;
	
	public Instanceof(TreeNode operand, String className) {
		if (operand == null || className == null)
			throw new NullPointerException("Operand and class name can't be null");
		this.operand = operand;
		this.className = className;
	}
	
	@Override
	public ListValue evaluate(PathEvaluationContext context) throws PathEvaluationException {
		ListValue base = operand.evaluate(context);
		if (!base.isSingleton()) throw new PathEvaluationException("Instanceof should be applied to a singleton");
		SingleValue sv = base.getValues().get(0);
		if (!(sv.getValue() instanceof ObjectValue))
			throw new PathEvaluationException("Instanceof should be applied to a RMObject");
		if (context.getEvaluator().isInstanceOf((ObjectValue) sv.getValue(), className))
			return ListValue.TRUE;
		return ListValue.FALSE;
	}

	public TreeNode getOperand() {
		return operand;
	}
	
	public String getClassName() {
		return className;
	}
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return operand + " instance of " + className;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		if (!super.equals(obj)) return false;
		Instanceof other = (Instanceof) obj;
		
		return (operand.equals(other.operand)) &&
		       (className.equals(other.className));
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		final int PRIME = 31;
		int hash = operand.hashCode();
		hash = hash * PRIME + className.hashCode();
		return hash;
	}
}

