package br.com.zilics.archetypes.models.rm.utils.path.parsed;


public abstract class UnaryOperator extends Operator {

	private static final long serialVersionUID = 5465327131524376418L;
	private final TreeNode operand;

	public UnaryOperator(TreeNode operand) {
		if (operand == null)
			throw new NullPointerException("Null operand");
		this.operand = operand;
	}
	
	public TreeNode getOperand() {
		return operand;
	}
	

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getOperatorName()).append("(").append(operand).append(")");
		return sb.toString();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		if (!super.equals(obj)) return false;
		UnaryOperator other = (UnaryOperator) obj;
		
		return (operand.equals(other.operand));
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		final int PRIME = 31;
		int hash = super.hashCode();
		hash = hash * PRIME + operand.hashCode();
		return hash;
	}
}

