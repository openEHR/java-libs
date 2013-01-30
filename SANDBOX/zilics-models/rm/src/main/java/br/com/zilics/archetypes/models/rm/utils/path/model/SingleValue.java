package br.com.zilics.archetypes.models.rm.utils.path.model;

import java.io.Serializable;

import br.com.zilics.archetypes.models.rm.exception.PathEvaluationException;

public class SingleValue implements Serializable {

	private static final long serialVersionUID = -2489541464340034386L;

	public static final SingleValue FALSE = new SingleValue(false);
	public static final SingleValue TRUE = new SingleValue(true);
	public static final SingleValue NAN = new SingleValue(Double.NaN);
	
	public static final int TYPE_INT = 0;
	public static final int TYPE_BOOLEAN = 1;
	public static final int TYPE_DOUBLE = 2;
	public static final int TYPE_STRING = 3;
	public static final int TYPE_OBJECT = 4;
	
	private final int type;
	private final int intValue;
	private final boolean booleanValue;
	private final double doubleValue;
	private final String stringValue;
	private final ObjectValue objectValue;
	
	public SingleValue(int intValue) {
		this.intValue = intValue;
		this.type = TYPE_INT;
		this.booleanValue = false;
		this.doubleValue = 0.0;
		this.stringValue = null;
		this.objectValue = null;
	}
	
	public SingleValue(boolean booleanValue) {
		this.booleanValue = booleanValue;
		this.type = TYPE_BOOLEAN;
		this.intValue = 0;
		this.doubleValue = 0.0;
		this.stringValue = null;
		this.objectValue = null;
	}

	public SingleValue(double doubleValue) {
		this.doubleValue = doubleValue;
		this.type = TYPE_DOUBLE;
		this.intValue = 0;
		this.booleanValue = false;
		this.stringValue = null;
		this.objectValue = null;
	}
	
	public SingleValue(String stringValue) {
		this.stringValue = stringValue;
		this.type = TYPE_STRING;
		this.intValue = 0;
		this.booleanValue = false;
		this.doubleValue = 0.0;
		this.objectValue = null;
	}
	
	public SingleValue(ObjectValue objectValue) {
		this.objectValue = objectValue;
		this.type = TYPE_OBJECT;
		this.intValue = 0;
		this.booleanValue = false;
		this.doubleValue = 0.0;
		this.stringValue = null;
	}
	
	public static SingleValue getInstanceFromObject(Object obj) throws PathEvaluationException {
		SingleValue result;
		if (obj instanceof Integer) {
			result = new SingleValue(((Integer) obj).intValue());
		} else if (obj instanceof Double) {
			result = new SingleValue(((Double) obj).doubleValue());
		} else if (obj instanceof Boolean) {
			result = new SingleValue(((Boolean) obj).booleanValue());
		} else if (obj instanceof String) {
			result = new SingleValue((String) obj);
		} else if (obj instanceof ObjectValue) {
			result = new SingleValue((ObjectValue) obj);
		} else
			throw new PathEvaluationException("Obj is of type unknown");
		return result;
	}

	public int getType() {
		return type;
	}
	
	public Object getValue() {
		switch(type) {
		case TYPE_INT: return intValue;
		case TYPE_BOOLEAN: return booleanValue;
		case TYPE_DOUBLE: return doubleValue;
		case TYPE_STRING: return stringValue;
		default:
			return objectValue;
		}	
	}
	
	public boolean effectiveBooleanValue() {
		switch(type) {
		case TYPE_INT: return (intValue != 0);
		case TYPE_BOOLEAN: return booleanValue;
		case TYPE_DOUBLE: return (doubleValue != 0 && !Double.isNaN(doubleValue));
		case TYPE_STRING: return (stringValue != null && stringValue.length() != 0);
		default:
			return true;
		}
	}
	
	public boolean isPrimitive() {
		return (type != TYPE_OBJECT);
	}
	
	public SingleValue add(SingleValue other) throws PathEvaluationException {
		Number n1, n2;
		n1 = this.effectiveNumberValue();
		n2 = other.effectiveNumberValue();
		if (n1 instanceof Integer && n2 instanceof Integer) {
			return new SingleValue(n1.intValue() + n2.intValue());
		} else {
			return new SingleValue(n1.doubleValue() + n2.doubleValue());
		}
	}
	
	public SingleValue subtract(SingleValue other) throws PathEvaluationException {
		Number n1, n2;
		n1 = this.effectiveNumberValue();
		n2 = other.effectiveNumberValue();
		if (n1 instanceof Integer && n2 instanceof Integer) {
			return new SingleValue(n1.intValue() - n2.intValue());
		} else {
			return new SingleValue(n1.doubleValue() - n2.doubleValue());
		}
	}

	public SingleValue multiply(SingleValue other) throws PathEvaluationException {
		Number n1, n2;
		n1 = this.effectiveNumberValue();
		n2 = other.effectiveNumberValue();
		if (n1 instanceof Integer && n2 instanceof Integer) {
			return new SingleValue(n1.intValue() * n2.intValue());
		} else {
			return new SingleValue(n1.doubleValue() * n2.doubleValue());
		}
	}

	public SingleValue divide(SingleValue other) throws PathEvaluationException {
		Number n1, n2;
		n1 = this.effectiveNumberValue();
		n2 = other.effectiveNumberValue();
		if (n1 instanceof Integer && n2 instanceof Integer) {
			return new SingleValue(n1.intValue() / n2.intValue());
		} else {
			return new SingleValue(n1.doubleValue() / n2.doubleValue());
		}
	}

	public SingleValue remainder(SingleValue other)  throws PathEvaluationException {
		Number n1, n2;
		n1 = this.effectiveNumberValue();
		n2 = other.effectiveNumberValue();
		if (n1 instanceof Integer && n2 instanceof Integer) {
			return new SingleValue(n1.intValue() % n2.intValue());
		} else {
			return new SingleValue(n1.doubleValue() % n2.doubleValue());
		}
	}

	public SingleValue negate() throws PathEvaluationException {
		Number n1;
		n1 = this.effectiveNumberValue();
		if (n1 instanceof Integer) {
			return new SingleValue(-n1.intValue());
		} else {
			return new SingleValue(-n1.doubleValue());
		}
	}

	public int compare(SingleValue other, boolean allowsObjects) throws PathEvaluationException{
		if (this.type == TYPE_OBJECT && other.type == TYPE_OBJECT) {
			if (!allowsObjects)
				throw new PathEvaluationException("Can't compare objects");
			if (this.objectValue == other.objectValue)
				return 0;
			else
				return 2;
		} else if (this.type == TYPE_STRING && other.type == TYPE_STRING) {
			if (!allowsObjects)
				throw new PathEvaluationException("Can't compare strings");			
			if (this.stringValue.equals(other.stringValue))
				return 0;
			else
				return 2;
		}
		double d1 = this.effectiveNumberValue().doubleValue();
		double d2 = other.effectiveNumberValue().doubleValue();
		if (Double.isNaN(d1) || Double.isNaN(d2)) return -2;
		return Double.compare(d1, d2);
	}
	
	public Number effectiveNumberValue() throws PathEvaluationException {
		switch(type) {
		case TYPE_INT:
			return intValue;
		case TYPE_BOOLEAN:
			throw new PathEvaluationException("Can't convert boolean to a numeric value");
		case TYPE_DOUBLE:
			return doubleValue;
		case TYPE_STRING:
			throw new PathEvaluationException("Can't convert string to a numeric value");
		default:
			throw new PathEvaluationException("Can't convert an object to a numeric value");
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		switch(type) {
		case TYPE_INT: return intValue;
		case TYPE_BOOLEAN: return (booleanValue ? 1 : 0);
		case TYPE_DOUBLE: return Double.valueOf(doubleValue).hashCode();
		case TYPE_STRING: return (stringValue != null ? stringValue.hashCode() : 0);
		default:
			return System.identityHashCode(objectValue);
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == this) return true;
		if (! ( obj instanceof SingleValue)) return false;
		SingleValue other = (SingleValue) obj;
		if (type != other.type) return false;

		switch(type) {
		case TYPE_INT: return intValue == other.intValue;
		case TYPE_BOOLEAN: return booleanValue == other.booleanValue;
		case TYPE_DOUBLE: return doubleValue == other.doubleValue;
		case TYPE_STRING:
			if (stringValue == other.stringValue) return true;
			if (stringValue == null || other.stringValue == null) return false;
			return stringValue.equals(other.stringValue);
		default:
			return objectValue == other.objectValue;
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		switch(type) {
		case TYPE_INT: return Integer.toString(intValue);
		case TYPE_BOOLEAN: return Boolean.toString(booleanValue);
		case TYPE_DOUBLE: return Double.toString(doubleValue);
		case TYPE_STRING:
			if (stringValue == null) return "null";
			else return "\"" + stringValue + "\"";
		default:
			return (objectValue == null ? "null" : objectValue.toString());
		}
	}

}
