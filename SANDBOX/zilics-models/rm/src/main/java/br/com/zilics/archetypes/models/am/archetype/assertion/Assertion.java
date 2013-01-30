
package br.com.zilics.archetypes.models.am.archetype.assertion;

import java.util.List;

import br.com.zilics.archetypes.models.am.AMObject;
import br.com.zilics.archetypes.models.rm.annotation.EqualsField;
import br.com.zilics.archetypes.models.rm.annotation.NotNull;

/**
 * Structural model of a typed first order predicate logic assertion,
 * in the form of an expression tree, including optional variable definitions
 *
 * @author Humberto
 */
public class Assertion extends AMObject {
	private static final long serialVersionUID = -2442044778691912822L;
	
	@EqualsField
	private String tag;
	@NotNull
	@EqualsField
    private ExpressionItem expression;
	@EqualsField
    private List<AssertionVariable> variables;

	/**
	 * Default constructor
	 */
	public Assertion() {}
	
	/**
	 * Another constructor
	 * @param tag the tag
	 * @param expression the expression that must be true
	 * @param variables variables used in expression
	 */
	public Assertion(String tag, ExpressionItem expression, List<AssertionVariable> variables) {
		this.tag = tag;
		this.expression = expression;
		this.variables = variables;
	}
	
    /**
     * Return the tag
     * @return Expression tag, used for differentiating multiple assertions
     */
    public String getTag() {
        return tag;
    }

    /**
     * Set the tag
     * @param tag Expression tag, used for differentiating multiple assertions
     */
    public void setTag(String tag) {
		assertMutable();
        this.tag = tag;
    }

    /**
     * Get the expression
     * @return Root of expression tree
     */
    public ExpressionItem getExpression() {
        return expression;
    }

    /**
     * Set the expression
     * @param expression Root of expression tree
     */
    public void setExpression(ExpressionItem expression) {
		assertMutable();
        this.expression = expression;
    }

    /**
     * Get the variables
     * @return Definitions of variables used in the assertion expression.
     */
    public List<AssertionVariable> getVariables() {
        return getList(variables);
    }

    /**
     * Set the variables
     * @param variables Definitions of variables used in the assertion expression.
     */
    public void setVariables(List<AssertionVariable> variables) {
		assertMutable();
        this.variables = variables;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
    	return "" + getExpression();
    }

}
