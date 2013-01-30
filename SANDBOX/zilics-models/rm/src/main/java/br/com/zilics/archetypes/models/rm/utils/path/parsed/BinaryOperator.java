package br.com.zilics.archetypes.models.rm.utils.path.parsed;

/**
 * Abstract class of all binary operators
 * @author Humberto Naves
 *
 */
public abstract class BinaryOperator extends Operator {
	private static final long serialVersionUID = -8357337642748002638L;

	private final TreeNode leftOperand;
	private final TreeNode rightOperand;

	public BinaryOperator(TreeNode leftOperand, TreeNode rightOperand) {
		if (leftOperand == null || rightOperand == null)
			throw new NullPointerException("Null operands");
		this.leftOperand = leftOperand;
		this.rightOperand = rightOperand;
	}
	
	/**
	 * Get the left operand (first one)
	 * @return the left operand
	 */
	public TreeNode getLeftOperand() {
		return leftOperand;
	}
	
	/**
	 * Get the right operand (the second one)
	 * @return the right operand
	 */
	public TreeNode getRightOperand() {
		return rightOperand;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(leftOperand).append(" ").append(getOperatorName()).append(" ").append(rightOperand);
		return sb.toString();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		if (!super.equals(obj)) return false;
		BinaryOperator other = (BinaryOperator) obj;
		
		return (leftOperand.equals(other.leftOperand)) &&
			(rightOperand.equals(other.rightOperand));
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		final int PRIME = 31;
		int hash = super.hashCode();
		hash = hash * PRIME + leftOperand.hashCode();
		hash = hash * PRIME + rightOperand.hashCode();
		return hash;
	}
}

