package br.com.zilics.archetypes.models.rm.utils.path.parsed;


public abstract class Operator extends TreeNode {

	private static final long serialVersionUID = 185456563741333619L;

	public abstract String getOperatorName();
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		return getOperatorName().hashCode();
	}
}

