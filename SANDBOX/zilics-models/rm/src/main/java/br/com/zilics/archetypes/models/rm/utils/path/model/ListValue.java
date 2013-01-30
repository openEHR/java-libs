package br.com.zilics.archetypes.models.rm.utils.path.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

import br.com.zilics.archetypes.models.rm.exception.PathEvaluationException;


public class ListValue implements Serializable {
	private static final long serialVersionUID = -7608171738415867068L;
	private final List<SingleValue> values = new ArrayList<SingleValue>();
	
	public static final ListValue EMPTY = new ListValue();
	public static final ListValue NAN = new ListValue();
	public static final ListValue FALSE = new ListValue(SingleValue.FALSE);
	public static final ListValue TRUE = new ListValue(SingleValue.TRUE);
	
	static {
		NAN.values.add(new SingleValue(Double.NaN));
	}
	
	private ListValue() {
		
	}

	public ListValue(ListValue copy) {
		this();
		values.addAll(copy.values);
	}
	
	public ListValue(SingleValue single) {
		this();
		values.add(single);
	}
	
	public static ListValue getInstanceFromList(List<SingleValue> values) throws PathEvaluationException {
		ListValue result = new ListValue();
		int primitiveCount = 0;
		int objectCount = 0;
		for(SingleValue sv : values) {
			if (sv.getType() == SingleValue.TYPE_OBJECT)
				objectCount++;
			else
				primitiveCount++;
		}
		if (objectCount > 0 && primitiveCount > 0)
			throw new PathEvaluationException("Invalid list of objects");
		result.values.addAll(values);
		return result;
	}
	
	public List<SingleValue> getValues() {
		return values;
	}
	
	public boolean isEmpty() {
		return values.size() == 0;
	}
	
	public boolean isSingleton() {
		return values.size() == 1;
	}
	
	public boolean isPrimitiveList() {
		if (isEmpty()) return true;
		return values.get(0).isPrimitive();
	}
	
	public boolean effectiveBooleanValue() {
		if (isEmpty()) return false;
		if (!isPrimitiveList()) return true;
		if (isSingleton()) {
			return values.get(0).effectiveBooleanValue();
		}
		return false;
	}
	
	
	public ListValue add(ListValue other) throws PathEvaluationException {
		if (this.isEmpty() || other.isEmpty()) return NAN;
		return new ListValue(
					this.values.get(0).add(other.values.get(0))
				);
	}
	
	public ListValue subtract(ListValue other) throws PathEvaluationException {
		if (this.isEmpty() || other.isEmpty()) return NAN;
		return new ListValue(
					this.values.get(0).subtract(other.values.get(0))
				);
	}

	public ListValue multiply(ListValue other) throws PathEvaluationException {
		if (this.isEmpty() || other.isEmpty()) return NAN;
		return new ListValue(
					this.values.get(0).multiply(other.values.get(0))
				);
	}

	public ListValue divide(ListValue other) throws PathEvaluationException {
		if (this.isEmpty() || other.isEmpty()) return NAN;
		return new ListValue(
					this.values.get(0).divide(other.values.get(0))
				);
	}

	public ListValue remainder(ListValue other) throws PathEvaluationException {
		if (this.isEmpty() || other.isEmpty()) return NAN;
		return new ListValue(
					this.values.get(0).remainder(other.values.get(0))
				);
	}

	public ListValue negate() throws PathEvaluationException {
		if (this.isEmpty()) return NAN;
		return new ListValue(
					this.values.get(0).negate()
				);
	}
	
	private int compare(ListValue other, boolean allowsObjects) throws PathEvaluationException {
		if (this.isEmpty() || other.isEmpty())
			return -2;
		return this.values.get(0).compare(other.values.get(0), allowsObjects);
	}
	
	public ListValue isEqual(ListValue other) throws PathEvaluationException {
		int cmp = compare(other, true);
		if (cmp == 0) return ListValue.TRUE;
		return ListValue.FALSE;
	}
	
	public ListValue isNotEqual(ListValue other) throws PathEvaluationException {
		int cmp = compare(other, true);
		if (cmp != -2 && cmp != 0) return ListValue.TRUE;
		return ListValue.FALSE;
	}

	public ListValue isGreaterThan(ListValue other) throws PathEvaluationException {
		int cmp = compare(other, false);
		if  (cmp == 1) return ListValue.TRUE;
		return ListValue.FALSE;
	}

	public ListValue isGreaterThanOrEqual(ListValue other) throws PathEvaluationException {
		int cmp = compare(other, false);
		if (cmp == 1 || cmp == 0) return ListValue.TRUE;
		return ListValue.FALSE;
	}

	public ListValue isLessThan(ListValue other) throws PathEvaluationException {
		int cmp = compare(other, false);
		if (cmp == -1) return ListValue.TRUE;
		return ListValue.FALSE;
	}

	public ListValue isLessThanOrEqual(ListValue other) throws PathEvaluationException {
		int cmp = compare(other, false);
		if (cmp == -1 || cmp == 0) return ListValue.TRUE;
		return ListValue.FALSE;
	}
	
	public ListValue rangeTo(ListValue other) throws PathEvaluationException {
		if (isEmpty() || other.isEmpty())
			throw new PathEvaluationException("Empty values in range");
		if (values.get(0).getType() != SingleValue.TYPE_INT ||
			other.values.get(0).getType() != SingleValue.TYPE_INT) {
			throw new PathEvaluationException("Range only allows integer types");
		}
		int start = (Integer) values.get(0).getValue();
		int end = (Integer) other.values.get(0).getValue();
		ListValue result = new ListValue();
		while (start <= end) {
			result.values.add(new SingleValue(start));
			start++;
		}
		return result;
	}
	
	public ListValue union(ListValue other) throws PathEvaluationException {
		if (!isEmpty() && !other.isEmpty() && (isPrimitiveList() ^ other.isPrimitiveList()))
			throw new PathEvaluationException("Can't perform a union between different types of list");
		if (!isPrimitiveList()) {
			LinkedHashSet<SingleValue> set = new LinkedHashSet<SingleValue>();
			set.addAll(this.values);
			set.addAll(other.values);
			ListValue result = new ListValue();
			result.values.addAll(set);
			return result;
		} else {
			ListValue result = new ListValue(this);
			result.values.addAll(other.values);
			return result;
		}		
	}

	public ListValue intersect(ListValue other) {
		LinkedHashSet<SingleValue> set = new LinkedHashSet<SingleValue>();
		set.addAll(this.values);
		set.retainAll(other.values);
		ListValue result = new ListValue();
		result.values.addAll(set);
		return result;
		
	}

	public ListValue except(ListValue other) {
		LinkedHashSet<SingleValue> set = new LinkedHashSet<SingleValue>();
		set.addAll(this.values);
		set.removeAll(other.values);
		ListValue result = new ListValue();
		result.values.addAll(set);
		return result;		
	}
		
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		return this.values.hashCode();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == this) return true;
		if (! ( obj instanceof ListValue)) return false;
		ListValue other = (ListValue) obj;

		return values.equals(other.values);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < values.size(); i++) {
			sb.append("  ").append(i+1).append(" --> ").append(values.get(i)).append("\n");
		}
		return sb.toString();
	}
	

}
