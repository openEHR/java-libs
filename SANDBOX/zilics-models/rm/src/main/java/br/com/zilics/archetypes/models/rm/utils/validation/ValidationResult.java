package br.com.zilics.archetypes.models.rm.utils.validation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.com.zilics.archetypes.models.rm.RMObject;

/**
 * Represents a collection of {@link ValidatonResultItem}
 * 
 * @author Humberto Naves
 */
public final class ValidationResult implements Serializable {

	private static final long serialVersionUID = 2261415554396444111L;

	private final List<ValidationResultItem> items = new ArrayList<ValidationResultItem>();

	/**
	 * Add an item to the list 
	 * @param item the item to add
	 */
	public void addItem(ValidationResultItem item) {
		items.add(item);
	}
	
	/**
	 * Instantiate and add an item 
	 * @param problematicObject the constructor parameter
	 * @param message the constructor parameter
	 * @param cause the constructor parameter
	 */
	public void addItem(RMObject problematicObject, String message, Throwable cause) {
		addItem(new ValidationResultItem(problematicObject, message, cause));
	}
	
	/**
	 * Instantiate and add an item
	 * @param problematicObject the constructor parameter
	 * @param message the constructor parameter
	 */
	public void addItem(RMObject problematicObject, String message) {
		addItem(problematicObject, message, null);
	}
	
	/**
	 * Returns the list of all validation result items
	 * @return the list of all items
	 */
	public List<ValidationResultItem> getItems() {
		return items;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == this) return true;
		if (!(obj instanceof ValidationResult)) return false;
		ValidationResult other = (ValidationResult) obj;
		
		return other.items.equals(this.items);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		if (items == null) return 0;
		return items.hashCode();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(ValidationResultItem item : items) {
			sb.append(item).append("\n");
		}
		return sb.toString();
	}
	
	
	
}
