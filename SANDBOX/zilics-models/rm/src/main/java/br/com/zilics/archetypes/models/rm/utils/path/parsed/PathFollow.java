package br.com.zilics.archetypes.models.rm.utils.path.parsed;

import br.com.zilics.archetypes.models.rm.exception.PathEvaluationException;
import br.com.zilics.archetypes.models.rm.utils.path.context.PathEvaluationContext;
import br.com.zilics.archetypes.models.rm.utils.path.model.ListValue;
import br.com.zilics.archetypes.models.rm.utils.path.model.SingleValue;

public final class PathFollow extends TreeNode {

	private static final long serialVersionUID = -2207245810662237118L;
	
	private final TreeNode node;
	private final TreeNode followingNode;
	
	public PathFollow(TreeNode node, TreeNode followingNode) {
		if (node == null || followingNode == null)
			throw new NullPointerException("Node and following node can't be null");
		this.node = node;
		this.followingNode = followingNode;
	}
	
	public TreeNode getNode() {
		return node;
	}
	
	public TreeNode getFollowingNode() {
		return followingNode;
	}
	
	@Override
	public ListValue evaluate(PathEvaluationContext context) throws PathEvaluationException {
		ListValue base = node.evaluate(context);
		PathEvaluationContext.ContextItem oldContextItem = context.getContextItem();
		PathEvaluationContext.ContextItem contextItem = new PathEvaluationContext.ContextItem();
		context.setContextItem(contextItem);
		
		int position = 1;
		contextItem.setTotal(base.getValues().size());
		ListValue result = ListValue.EMPTY;
		for(SingleValue sv : base.getValues()) {
			contextItem.setPosition(position);
			contextItem.setValue(sv);
			result = result.union(followingNode.evaluate(context));
			position++;
		}
		
		context.setContextItem(oldContextItem);
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(node);
		if (!(node instanceof Root)) sb.append("/");
		sb.append(followingNode);
		return sb.toString();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		if (!super.equals(obj)) return false;
		PathFollow other = (PathFollow) obj;
		
		return (node.equals(other.node)) &&
		followingNode.equals(other.followingNode);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		final int PRIME = 31;
		int hash = node.hashCode();
		hash = hash * PRIME + followingNode.hashCode();
		return hash;
	}
}

