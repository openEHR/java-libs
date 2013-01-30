package br.com.zilics.archetypes.models.rm.support.identification;

import br.com.zilics.archetypes.models.rm.annotation.RmClass;

/**
 * Abstract model of UID-based identifiers consisting of a root part and an
 * optional extension; lexical form: root "::" extension
 *
 * @author Humberto
 */
@RmClass("UID_BASED_ID")
public abstract class UIDBasedID extends ObjectID {
	private static final long serialVersionUID = -2052537805998829174L;

	/**
	 * The identifier of the conceptual namespace in which the object exists,
	 * within the identification scheme. 
	 * 
	 * @return the part to the left of the first ‘::’ separator, if any, 
	 * or else the whole string
	 */
	public abstract UID root(); 
	
	/**
	 * Optional local identifier of the object within the context of the root
	 * identifier. 
	 * 
	 * @return the part to the right of the first ‘::’ separator if any, 
	 * or else any empty String
	 */
	public abstract String extension();
	
	/**
	 * True if extension is not empty
	 * 
	 * @return true if extension not empty
	 */
	public boolean hasExtension() {
		return !isEmptyString(extension());
	}	
}