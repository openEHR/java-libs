package br.com.zilics.archetypes.models.rm.utils.path.parsed;

import br.com.zilics.archetypes.models.rm.exception.PathEvaluationException;
import br.com.zilics.archetypes.models.rm.utils.path.context.PathEvaluationContext;
import br.com.zilics.archetypes.models.rm.utils.path.model.ListValue;
import br.com.zilics.archetypes.models.rm.utils.path.model.SingleValue;

public final class Literal extends TreeNode {
	private static final long serialVersionUID = -4450236804000924123L;
	private final SingleValue value;
	private final ListValue listValue;
	
	public Literal(int intValue) {
		value = new SingleValue(intValue);
		listValue = new ListValue(value);
	}

	public Literal(String stringValue) {
		value = new SingleValue(stringValue);
		listValue = new ListValue(value);
	}
	
	public Literal(double doubleValue) {
		value = new SingleValue(doubleValue);
		listValue = new ListValue(value);
	}
	
	public SingleValue getValue() {
		return value;
	}
	
	@Override
	public ListValue evaluate(PathEvaluationContext context) throws PathEvaluationException {
		return listValue;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return value.toString();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		if (!super.equals(obj)) return false;
		Literal other = (Literal) obj;
		
		return (value.equals(other.value));
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		return value.hashCode();
	}
}

