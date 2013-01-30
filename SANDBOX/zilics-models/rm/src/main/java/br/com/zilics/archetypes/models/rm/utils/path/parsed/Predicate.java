package br.com.zilics.archetypes.models.rm.utils.path.parsed;

import java.util.ArrayList;
import java.util.List;

import br.com.zilics.archetypes.models.rm.exception.PathEvaluationException;
import br.com.zilics.archetypes.models.rm.utils.path.context.PathEvaluationContext;
import br.com.zilics.archetypes.models.rm.utils.path.model.ListValue;
import br.com.zilics.archetypes.models.rm.utils.path.model.SingleValue;


public abstract class Predicate extends TreeNode {

	private static final long serialVersionUID = -1354267062725147394L;
	private final TreeNode node;
	
	public Predicate(TreeNode node) {
		if (node == null)
			throw new NullPointerException("Node can't be null");
		this.node = node;
	}
	
	public TreeNode getNode() {
		return node;
	}
	
	@Override
	public final ListValue evaluate(PathEvaluationContext context) throws PathEvaluationException {
		ListValue base = getNode().evaluate(context);
		PathEvaluationContext.ContextItem oldContextItem = context.getContextItem();
		PathEvaluationContext.ContextItem contextItem = new PathEvaluationContext.ContextItem();
		context.setContextItem(contextItem);
		
		int position = 1;
		contextItem.setTotal(base.getValues().size());
		List<SingleValue> filtered = new ArrayList<SingleValue>();
		for(SingleValue sv : base.getValues()) {
			contextItem.setPosition(position);
			contextItem.setValue(sv);
			if (testContextItem(context)) {
				filtered.add(sv);
			}
			position++;
		}
		
		context.setContextItem(oldContextItem);
		return ListValue.getInstanceFromList(filtered);
	}
	
	protected abstract boolean testContextItem(PathEvaluationContext context) throws PathEvaluationException;
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return node.toString();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		if (!super.equals(obj)) return false;
		Predicate other = (Predicate) obj;
		
		return (node.equals(other.node));
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		return node.hashCode();
	}
}

