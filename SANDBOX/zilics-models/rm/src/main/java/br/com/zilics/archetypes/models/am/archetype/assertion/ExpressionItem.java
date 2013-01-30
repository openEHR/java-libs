
package br.com.zilics.archetypes.models.am.archetype.assertion;

import br.com.zilics.archetypes.models.am.AMObject;
import br.com.zilics.archetypes.models.rm.annotation.EqualsField;
import br.com.zilics.archetypes.models.rm.annotation.NotEmpty;
import br.com.zilics.archetypes.models.rm.annotation.RmClass;


/**
 * Abstract parent of all expression tree items
 *
 * @author Humberto
 */
@RmClass("EXPR_ITEM")
public abstract class ExpressionItem extends AMObject {
	private static final long serialVersionUID = -1150173284865312978L;

	@EqualsField
	@NotEmpty
	private String type;

	/**
	 * Default constructor
	 */
	public ExpressionItem() {}
	
	/**
	 * Another constructor
	 * @param type type of this expression
	 */
	public ExpressionItem(String type) {
		this.type = type;
	}
    /**
     * Boolean type
     */
    public final static String BOOLEAN = "BOOLEAN";
    /**
     * Real type
     */
    public final static String REAL = "REAL";
    /**
     * Integer type
     */
    public final static String INTEGER = "INTEGER";
    /**
     * String type
     */
    public final static String STRING = "STRING";
    /**
     * Path type
     */
    public final static String PATH = "PATH";

    /**
     * Get the type
     * @return Type ame of this item in the mathematical sense. For leaf nodes,
     * must be the name of a primitive type, or else a reference model type.
     * The type for any relational or boolean operator will be "BOOLEAN",
     * while the type for any arithmetic operator, will be "REAL" or "INTEGER"
     */
    public String getType() {
        return type;
    }

    /**
     * Set the type
     * @param type Type ame of this item in the mathematical sense. For leaf nodes,
     * must be the name of a primitive type, or else a reference model type.
     * The type for any relational or boolean operator will be "BOOLEAN",
     * while the type for any arithmetic operator, will be "REAL" or "INTEGER"
     */
    public void setType(String type) {
		assertMutable();
        this.type = type;
    }
}
