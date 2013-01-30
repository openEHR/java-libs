package br.com.zilics.archetypes.models.rm.utils.path.context;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import br.com.zilics.archetypes.models.rm.exception.PathEvaluationException;
import br.com.zilics.archetypes.models.rm.exception.PathParseException;
import br.com.zilics.archetypes.models.rm.utils.path.PathEvaluator;
import br.com.zilics.archetypes.models.rm.utils.path.PathFunction;
import br.com.zilics.archetypes.models.rm.utils.path.PathUtils;
import br.com.zilics.archetypes.models.rm.utils.path.model.ListValue;
import br.com.zilics.archetypes.models.rm.utils.path.model.SingleValue;
import br.com.zilics.archetypes.models.rm.utils.path.parsed.TreeNode;

/**
 * This class is the base of all evaluations
 * @author Humberto Naves
 *
 */
public final class PathEvaluationContext implements Serializable {
	private static final long serialVersionUID = 2706926987004363339L;

	private ContextItem contextItem;
	
	Map<String, SingleValue> variableValues = new HashMap<String, SingleValue>();
	private Map<String, PathFunction> definedFunctions = new HashMap<String, PathFunction>();
	private PathEvaluator evaluator;
	
	public PathEvaluationContext(PathEvaluator evaluator) {
		if (evaluator == null)
			throw new NullPointerException("Null evaluator");
		this.evaluator = evaluator;
		this.contextItem = new ContextItem();
		this.contextItem.setPosition(1);
		this.contextItem.setTotal(1);
		this.contextItem.setValue(new SingleValue(evaluator.getRoot()));
	}
	
	public PathEvaluator getEvaluator() {
		return evaluator;
	}
	
	public void setContextItem(ContextItem contextItem) {
		this.contextItem = contextItem;
	}
	
	public ContextItem getContextItem() {
		return contextItem;
	}
	
	public ListValue parseAndEvaluate(String expression) throws PathParseException, PathEvaluationException {
		TreeNode parsed = PathUtils.parseArchPath(expression);
		return parsed.evaluate(this);		
	}
	
	public SingleValue resolveVariable(String varName) throws PathEvaluationException {
		SingleValue result = variableValues.get(varName);
		if (result == null) throw new PathEvaluationException("Var " + varName + " is not defined");
		return result;
	}
	
	public void assertVariableUnused(String varName) throws PathEvaluationException {
		if (variableValues.containsKey(varName))
			throw new PathEvaluationException("Variable " + varName + " already exists");
	}
	
	public void bindVariable(String varName, SingleValue value) {
		variableValues.put(varName, value);
	}
	
	public void clearVariable(String varName) {
		variableValues.remove(varName);
	}
	
	public void clearVariables() {
		variableValues.clear();
	}
	
	public PathFunction getFunctionByName(String functionName) {
		if (StandardFunctions.FUNCTIONS.containsKey(functionName))
			return StandardFunctions.FUNCTIONS.get(functionName);
		return definedFunctions.get(functionName);
	}
	
	public void defineFunction(String functionName, PathFunction function) throws PathEvaluationException {
		if (definedFunctions.containsKey(functionName) || StandardFunctions.FUNCTIONS.containsKey(functionName))
			throw new PathEvaluationException("Function " + functionName + " already defined");
		definedFunctions.put(functionName, function);
	}
	
	public void clearDefinedFunctions() {
		definedFunctions.clear();
	}
	
	/**
	 * Represents the context item
	 * 
	 * @author Humberto Naves
	 *
	 */
	public static class ContextItem implements Serializable {

		private static final long serialVersionUID = 5205726520729354216L;
		
		private int position;
		private int total;
		private SingleValue value;

		public int getPosition() {
			return position;
		}
		
		public void setPosition(int position) {
			this.position = position;
		}
		
		public int getTotal() {
			return total;
		}
		
		public void setTotal(int total) {
			this.total = total;
		}

		public SingleValue getValue() {
			return value;
		}
		
		public void setValue(SingleValue value) {
			this.value = value;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean equals(Object obj) {
			if (obj == this) return true;
			if (!(obj instanceof ContextItem)) return false;
			ContextItem other = (ContextItem) obj;
			return (position == other.position) &&
				(total == other.total) &&
				(value.equals(other.value));
		}
		
		/**
		 * {@inheritDoc}
		 */
		@Override
		public int hashCode() {
			final int PRIME = 31;
			int hash = value.hashCode();
			hash = hash * PRIME + position;
			hash = hash * PRIME + total;
			return hash;
		}
		
		/**
		 * {@inheritDoc}
		 */
		@Override
		public String toString() {
			return "[" + value + ", " + position + ", " + total + "]";
		}
		
		
	}
	

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == this) return true;
		if (!(obj instanceof PathEvaluationContext)) return false;
		PathEvaluationContext other = (PathEvaluationContext) obj;
		return (evaluator.equals(other.evaluator));
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		int hash = evaluator.hashCode();
		return hash;
	}
	

}


