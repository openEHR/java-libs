package br.com.zilics.archetypes.models.rm.utils.path.parsed;

import java.io.Serializable;

import br.com.zilics.archetypes.models.rm.exception.PathEvaluationException;
import br.com.zilics.archetypes.models.rm.utils.path.context.PathEvaluationContext;
import br.com.zilics.archetypes.models.rm.utils.path.model.ListValue;

public abstract class TreeNode implements Serializable {

	private static final long serialVersionUID = -1787133791556333361L;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == this) return true;
		if (!(obj instanceof TreeNode)) return false;
		if (obj.getClass() != this.getClass()) return false;
		
		return true;
	}
	
	public abstract ListValue evaluate(PathEvaluationContext context) throws PathEvaluationException;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		return 0;
	}
}
