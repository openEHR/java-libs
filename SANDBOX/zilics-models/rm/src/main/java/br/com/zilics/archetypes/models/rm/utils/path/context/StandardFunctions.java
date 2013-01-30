package br.com.zilics.archetypes.models.rm.utils.path.context;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.zilics.archetypes.models.am.template.openehrprofile.TemplateConstraint;
import br.com.zilics.archetypes.models.rm.RMObject;
import br.com.zilics.archetypes.models.rm.exception.PathEvaluationException;
import br.com.zilics.archetypes.models.rm.exception.XmlSerializerException;
import br.com.zilics.archetypes.models.rm.utils.path.PathEagerFunction;
import br.com.zilics.archetypes.models.rm.utils.path.PathFunction;
import br.com.zilics.archetypes.models.rm.utils.path.model.ListValue;
import br.com.zilics.archetypes.models.rm.utils.path.model.ObjectValue;
import br.com.zilics.archetypes.models.rm.utils.path.model.SingleValue;
import br.com.zilics.archetypes.models.rm.utils.path.parsed.TreeNode;
import br.com.zilics.archetypes.models.rm.utils.xml.XmlSerializer;

/**
 * 
 * @author Humberto Naves
 *
 */
class StandardFunctions {
	static final Map<String, PathFunction> FUNCTIONS;
	
	static {
		FUNCTIONS = new HashMap<String, PathFunction>();
		FUNCTIONS.put("position", new PositionFunction());
		FUNCTIONS.put("last", new LastFunction());
		FUNCTIONS.put("toXml", new ToXmlFunction());
		FUNCTIONS.put("length", new LengthFunction());
		FUNCTIONS.put("define", new DefineFunction());
		FUNCTIONS.put("new", new NewFunction());
	}

}

/**
 * 
 * @author Humberto Naves
 *
 */
class PositionFunction extends PathEagerFunction {
	public ListValue evaluate(PathEvaluationContext context, List<ListValue> params) throws PathEvaluationException {
		if (params.size() != 0) throw new PathEvaluationException("Function 'position' takes no parameters");
		return new ListValue(new SingleValue(context.getContextItem().getPosition()));
	}	
}

/**
 * 
 * @author Humberto Naves
 *
 */
class LastFunction extends PathEagerFunction {
	public ListValue evaluate(PathEvaluationContext context, List<ListValue> params) throws PathEvaluationException {
		if (params.size() != 0) throw new PathEvaluationException("Function 'last' takes no parameters");
		return new ListValue(new SingleValue(context.getContextItem().getTotal()));
	}
}

/**
 * 
 * @author Humberto Naves
 *
 */
class LengthFunction extends PathEagerFunction {
	public ListValue evaluate(PathEvaluationContext context, List<ListValue> params) throws PathEvaluationException {
		int size = 0;
		for(int i = 0; i < params.size(); i++) {
			size += params.get(i).getValues().size();
		}
		return new ListValue(new SingleValue(size));
	}	
}

/**
 * 
 * @author Humberto Naves
 *
 */
class ToXmlFunction extends PathEagerFunction {
	public ListValue evaluate(PathEvaluationContext context, List<ListValue> params) throws PathEvaluationException {
		if (params.size() != 0) throw new PathEvaluationException("Function 'toXml' takes no parameters");
		Object instance = context.getContextItem().getValue().getValue();
		RMObject obj;
		if (instance instanceof TemplateConstraint) {
			obj = ((TemplateConstraint) instance).getArchetypeConstraint();
		} else if (!(instance instanceof RMObject))
			throw new PathEvaluationException("Function 'toXml' only applies to RMObject or TemplateConstraint");
		else
			obj = (RMObject) context.getContextItem().getValue().getValue();
		try {
			return new ListValue(new SingleValue(XmlSerializer.serializeXml(obj, null)));
		} catch (XmlSerializerException e) {
			throw new PathEvaluationException("Problem while serializing", e);
		}
	}	
}

/**
 * 
 * @author Humberto Naves
 *
 */
class DefineFunction implements PathFunction {
	public ListValue evaluate(List<TreeNode> nodes, PathEvaluationContext context) throws PathEvaluationException {
		if (nodes.size() != 2) throw new PathEvaluationException("Function 'define' takes 2 parameters: (function, args), definition");
		ListValue functionParam = nodes.get(0).evaluate(context);
		if (functionParam.isEmpty())
			throw new PathEvaluationException("Missing defined function name");
		int pos = 0;
		String functionName = null;
		List<String> args = new ArrayList<String>();
		for(SingleValue sv : functionParam.getValues()) {
			if (sv.getType() != SingleValue.TYPE_STRING)
				throw new PathEvaluationException("Invalid defined function declaration");

			if (pos++ == 0) {
				functionName = (String) sv.getValue();
			} else {
				args.add((String) sv.getValue());
			}
		}
		DefinedFunction function = new DefinedFunction(functionName, args, nodes.get(1));
		context.defineFunction(functionName, function);
		return ListValue.EMPTY;
	}
}

/**
 * 
 * @author Humberto Naves
 *
 */
class DefinedFunction extends PathEagerFunction implements Serializable {

	private static final long serialVersionUID = 5205726520729354216L;

	private final String functionName;
	private final List<String> arguments;
	private final TreeNode definition;
	
	public DefinedFunction(String functionName, List<String> arguments, TreeNode definition) {
		if (functionName == null || arguments == null)
			throw new NullPointerException("Invalid function definition");
		this.functionName = functionName;
		this.arguments = arguments;
		this.definition = definition;
	}
	
	
	@Override
	public ListValue evaluate(PathEvaluationContext context, List<ListValue> params) throws PathEvaluationException {
		List<SingleValue> merge = new ArrayList<SingleValue>();
		for(ListValue part : params) {
			merge.addAll(part.getValues());
		}
		if (merge.size() != arguments.size())
			throw new PathEvaluationException("Invalid number of arguments for defined function " + functionName);
		
		List<SingleValue> oldValues = new ArrayList<SingleValue>(arguments.size());
		for(int i = 0; i < arguments.size(); i++) {
			String varName = arguments.get(i);
			SingleValue value = merge.get(i);
			oldValues.add(context.variableValues.get(varName));
			context.bindVariable(varName, value);
		}
		
		ListValue result = definition.evaluate(context);
		
		
		for(int i = 0; i < arguments.size(); i++) {
			String varName = arguments.get(i);
			context.bindVariable(varName, oldValues.get(i));
		}
		
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == this) return true;
		if (!(obj instanceof DefinedFunction)) return false;
		DefinedFunction other = (DefinedFunction) obj;
		return (functionName.equals(other.functionName)) &&
			(arguments.equals(other.arguments)) &&
			(definition.equals(other.definition));
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		final int PRIME = 31;
		int hash = functionName.hashCode();
		hash = hash * PRIME + arguments.hashCode();
		hash = hash * PRIME + definition.hashCode();
		return hash;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return functionName + arguments;
	}


}

class NewFunction extends PathEagerFunction {

	@Override
	public ListValue evaluate(PathEvaluationContext context, List<ListValue> params) throws PathEvaluationException {
		if (params.size() == 0) throw new PathEvaluationException("Function new needs an class name as an argument");
		ListValue className = params.get(0);
		for(ListValue param : params) {
			if (!param.isSingleton()) throw new PathEvaluationException("Function new only accepts singletons as arguments");
		}
		Object cn = className.getValues().get(0).getValue(); 
		if (!(cn instanceof String))
			throw new PathEvaluationException("Class name should be a string");
		Class<?> clazz;
		try {
			clazz = Class.forName((String) cn);
		} catch (ClassNotFoundException e) {
			throw new PathEvaluationException("Class "+cn+" not found", e);
		}
		if (!ObjectValue.class.isAssignableFrom(clazz)) {
			throw new PathEvaluationException("Class "+cn+" must implements ObjectValue");
		}
		Object args[] = new Object[params.size() - 1];
		for(int i = 1; i < params.size(); i++) {
			args[i-1] = params.get(i).getValues().get(0).getValue();
		}
		for(Constructor<?> constructor : clazz.getConstructors()) {
			if (constructor.getParameterTypes().length != args.length) continue;
			try {
				return new ListValue(new SingleValue((ObjectValue) constructor.newInstance(args)));
			} catch (Throwable t) {
				throw new PathEvaluationException("Exception while creating new instance", t);
			}
		}
		throw new PathEvaluationException("Constructor of "+cn+" not found");
	}
}
