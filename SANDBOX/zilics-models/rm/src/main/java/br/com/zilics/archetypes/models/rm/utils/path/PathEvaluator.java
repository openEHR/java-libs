package br.com.zilics.archetypes.models.rm.utils.path;

import java.util.List;

import br.com.zilics.archetypes.models.rm.exception.PathEvaluationException;
import br.com.zilics.archetypes.models.rm.utils.path.model.ObjectValue;

/**
 * Represents an evaluator that is used by the {@link br.com.zilics.archetypes.models.rm.utils.path.context.PathEvaluationContext}
 * @author Humberto Naves
 */
public interface PathEvaluator {
	/**
	 * Get the root value of the path evaluation context
	 * @return the root value of the path evaluation context
	 */
	public ObjectValue getRoot();
	
	/**
	 * Get the name of an {@link ObjectValue} (for tests like child::name)
	 * @param current The {@link ObjectValue} to get the name
	 * @return the name of the object
	 * @throws PathEvaluationException possible exception raised
	 */
	public String getNodeName(ObjectValue current) throws PathEvaluationException;
	
	/**
	 * Get the parent of an {@link ObjectValue}
	 * @param current the value to get the parent
	 * @return the parent result
	 * @throws PathEvaluationException possible exception raised
	 */
	public ObjectValue getParent(ObjectValue current) throws PathEvaluationException;
	
	/**
	 * Get a metadata by its name
	 * @param current from who we will get the metadata
	 * @param metadataName the name of the metadata
	 * @return the metadata value
	 * @throws PathEvaluationException possible exception raised
	 */
	public Object getMetadata(ObjectValue current, String metadataName) throws PathEvaluationException;
	
	/**
	 * Check if a given {@link ObjectValue} is of the class className
	 * @param current the current value to check
	 * @param className the name of the class
	 * @return true if it an instance of
	 * @throws PathEvaluationException possible exception raised
	 */
	public boolean isInstanceOf(ObjectValue current, String className) throws PathEvaluationException;
	
	/**
	 * Get the children by name
	 * @param current the current {@link ObjectValue}
	 * @param childName the name of the children
	 * @return the result list of children
	 * @throws PathEvaluationException possible exception raised
	 */
	public List<?> getChildren(ObjectValue current, String childName) throws PathEvaluationException;
	
	/**
	 * Get the names of all children
	 * @param current the current {@link ObjectValue}
	 * @return the list of names
	 * @throws PathEvaluationException possible exception raised
	 */
	public List<String> getAllChildrenNames(ObjectValue current) throws PathEvaluationException;
}
