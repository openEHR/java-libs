package br.com.zilics.archetypes.models.rm.utils.path.parsed;

import java.util.List;

public abstract class MultipleOperandsNode extends TreeNode {
	private static final long serialVersionUID = 3391957663638820149L;
	private final List<TreeNode> operands;
	
	public MultipleOperandsNode(List<TreeNode> operands) {
		if (operands == null)
			throw new NullPointerException("Operands can't be null");
		this.operands = operands;
	}
	
	public List<TreeNode> getOperands() {
		return operands;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("(");
		boolean first = true;
		for(TreeNode operand : operands) {
			if (!first) sb.append(" ,");
			sb.append(operand);
			first = false;
		}
		sb.append(")");
		return sb.toString();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		if (!super.equals(obj)) return false;
		MultipleOperandsNode other = (MultipleOperandsNode) obj;
		
		return (operands.equals(other.operands));
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		return operands.hashCode();
	}
}

