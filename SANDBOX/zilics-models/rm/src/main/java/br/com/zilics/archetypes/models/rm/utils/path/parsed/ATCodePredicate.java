package br.com.zilics.archetypes.models.rm.utils.path.parsed;

import br.com.zilics.archetypes.models.rm.exception.PathEvaluationException;
import br.com.zilics.archetypes.models.rm.utils.path.PathEvaluator;
import br.com.zilics.archetypes.models.rm.utils.path.context.PathEvaluationContext;
import br.com.zilics.archetypes.models.rm.utils.path.model.ObjectValue;

/**
 * Represents an AT code predicate like "[at0001]"
 * @author Humberto Naves
 *
 */
public final class ATCodePredicate extends Predicate {

	private static final long serialVersionUID = -1882133646812244845L;
	private final String atTest;

	public ATCodePredicate(TreeNode node, String atTest) {
		super(node);
		this.atTest = atTest;
	}
	
	/**
	 * get the predicate test
	 * @return the predicate test
	 */
	public String getAtTest() {
		return atTest;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected boolean testContextItem(PathEvaluationContext context) throws PathEvaluationException {
		ObjectValue current = (ObjectValue) context.getContextItem().getValue().getValue();
		PathEvaluator evaluator = context.getEvaluator();
		Object result = evaluator.getMetadata(current, "node_id");
		return atTest.equals(result);
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return super.toString() + "[" + atTest + "]";
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		if (!super.equals(obj)) return false;
		ATCodePredicate other = (ATCodePredicate) obj;
		
		return (atTest.equals(other.atTest));
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		final int PRIME = 31;
		int hash = super.hashCode();
		hash = hash * PRIME + atTest.hashCode();
		return hash;
	}

}

