package br.com.zilics.archetypes.models.am.utils.validation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.com.zilics.archetypes.models.am.archetype.constraintmodel.CObject;

/**
 * Represents a collection of {@link SemanticValidationResultItem}
 * 
 * @author Humberto Naves
 *
 */
public final class SemanticValidationResult implements Serializable {

	private static final long serialVersionUID = 4988630778696092605L;
	
	private final List<SemanticValidationResultItem> items = new ArrayList<SemanticValidationResultItem>();
	

	/**
	 * Add a new item to the list
	 * @param item the new item to add {@link SemanticValidationResultItem}
	 */
	public void addItem(SemanticValidationResultItem item) {
		getItems().add(item);
	}
	
	/**
	 * Instantiate a new {@link SemanticValidationResultItem} and add it to the list
	 * @param constraint the parameter of the constructor
	 * @param value the parameter of the constructor
	 * @param message the parameter of the constructor
	 * @param cause the parameter of the constructor
	 */
	public void addItem(CObject constraint, Object value, String message, Throwable cause) {
		addItem(new SemanticValidationResultItem(constraint, value, message, cause));
	}

	/**
	 * Instantiate a new {@link SemanticValidationResultItem} and add it to the list
	 * @param constraint the parameter of the constructor
	 * @param value the parameter of the constructor
	 * @param message the parameter of the constructor
	 */
	public void addItem(CObject constraint, Object value, String message) {
		addItem(constraint, value, message, null);
	}

	/**
	 * Get the list of all items
	 * @return the list of all items
	 */
	public List<SemanticValidationResultItem> getItems() {
		return items;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == this) return true;
		if (!(obj instanceof SemanticValidationResult)) return false;
		SemanticValidationResult other = (SemanticValidationResult) obj;
		
		return other.items.equals(this.items);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		return items.hashCode();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(SemanticValidationResultItem item : items) {
			sb.append(item).append("\n");
		}
		return sb.toString();
	}

}
