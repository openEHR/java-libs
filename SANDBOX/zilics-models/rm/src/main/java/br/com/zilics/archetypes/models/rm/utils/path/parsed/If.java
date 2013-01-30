package br.com.zilics.archetypes.models.rm.utils.path.parsed;

import br.com.zilics.archetypes.models.rm.exception.PathEvaluationException;
import br.com.zilics.archetypes.models.rm.utils.path.context.PathEvaluationContext;
import br.com.zilics.archetypes.models.rm.utils.path.model.ListValue;

public final class If extends TreeNode {
	private static final long serialVersionUID = -1568988888686315889L;

	private final TreeNode condition;
	private final TreeNode ifTrueExpression;
	private final TreeNode ifFalseExpression;
	
	public If(TreeNode condition, TreeNode ifTrueExpression, TreeNode ifFalseExpression) {
		if (condition == null || ifTrueExpression == null || ifFalseExpression == null)
			throw new NullPointerException("Condition and expressions can't be null");
		this.condition = condition;
		this.ifFalseExpression = ifFalseExpression;
		this.ifTrueExpression = ifTrueExpression;
	}
	
	public TreeNode getCondition() {
		return condition;
	}

	public TreeNode getIfTrueExpression() {
		return ifTrueExpression;
	}

	public TreeNode getIfFalseExpression() {
		return ifFalseExpression;
	}

	@Override
	public ListValue evaluate(PathEvaluationContext context) throws PathEvaluationException {
		ListValue cond = condition.evaluate(context);
		if (cond.effectiveBooleanValue()) {
			return ifTrueExpression.evaluate(context);
		} else {
			return ifFalseExpression.evaluate(context);
		}
	}

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "if " + condition + " then " + ifTrueExpression + " else " + ifFalseExpression;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		if (!super.equals(obj)) return false;
		If other = (If) obj;
		
		return (condition.equals(other.condition)) &&
		(ifTrueExpression.equals(other.ifTrueExpression)) &&
		(ifFalseExpression.equals(other.ifFalseExpression));
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		final int PRIME = 31;
		int hash = condition.hashCode();
		hash = hash * PRIME + ifTrueExpression.hashCode();
		hash = hash * PRIME + ifFalseExpression.hashCode();
		return hash;
	}
}
