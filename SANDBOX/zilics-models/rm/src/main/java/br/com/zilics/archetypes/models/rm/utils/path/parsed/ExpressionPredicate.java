package br.com.zilics.archetypes.models.rm.utils.path.parsed;

import br.com.zilics.archetypes.models.rm.exception.PathEvaluationException;
import br.com.zilics.archetypes.models.rm.utils.path.context.PathEvaluationContext;
import br.com.zilics.archetypes.models.rm.utils.path.model.ListValue;
import br.com.zilics.archetypes.models.rm.utils.path.model.SingleValue;

/**
 * Represents an expression predicate like "[. mod 2 = 1]"
 * @author Humberto Naves
 */
public final class ExpressionPredicate extends Predicate {

	private static final long serialVersionUID = -3994909018301348865L;

	private final TreeNode predicateExpression;
	
	public ExpressionPredicate(TreeNode node, TreeNode predicateExpression) {
		super(node);
		if (predicateExpression == null)
			throw new NullPointerException("Predicate expression can't be null");
		this.predicateExpression = predicateExpression;
	}
	
	/**
	 * 
	 * @return
	 */
	public TreeNode getPredicateExpression() {
		return predicateExpression;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected boolean testContextItem(PathEvaluationContext context) throws PathEvaluationException {
		ListValue result = predicateExpression.evaluate(context);
		if (result.isSingleton() && result.getValues().get(0).getType() == SingleValue.TYPE_INT) {
			if (context.getContextItem().getPosition() == ((Integer) result.getValues().get(0).getValue()).intValue())
				return true;
		} else if (result.effectiveBooleanValue())
			return true;

		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return super.toString() + "[" + predicateExpression + "]";
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		if (!super.equals(obj)) return false;
		ExpressionPredicate other = (ExpressionPredicate) obj;
		
		return (predicateExpression.equals(other.predicateExpression));
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		final int PRIME = 31;
		int hash = super.hashCode();
		hash = hash * PRIME + predicateExpression.hashCode();
		return hash;
	}

}
