
package br.com.zilics.archetypes.models.am.archetype.assertion;

/**
 * Enumeration type for operator types in assertion expressions.
 * <p>Use as the type of operators in the {@link br.com.zilics.archetypes.models.am.archetype.assertion}
 * package, or for related uses.</p>
 *
 * @author Humberto
 * @see br.com.zilics.archetypes.models.am.archetype.assertion.ExpressionOperator
 */
public enum OperatorKind {
    /**
     * Equals operator ("=" or "==")
     */
    OP_EQ(2001, "="),

    /**
     * Not equals operator ("!=" or "/=" or "<>")
     */
    OP_NE(2002, "!="),

    /**
     * Less-than or equals operator ("<=")
     */
    OP_LE(2003, "<="),

    /**
     * Less-than operator ("<")
     */
    OP_LT(2004, "<"),

    /**
     * Grater-than or equals operator (">=")
     */
    OP_GE(2005, ">="),

    /**
     * Grater-than operator (">")
     */
    OP_GT(2006, ">"),

    /**
     * Matches operator ("matches" or "is_in")
     */
    OP_MATCHES(2007, "matches"),

    /**
     * Not logical operator
     */
    OP_NOT(2010, "not"),

    /**
     * And logical operator
     */
    OP_AND(2011, "and"),

    /**
     * Or logical operator
     */
    OP_OR(2012, "or"),

    /**
     * Xor logical operator
     */
    OP_XOR(2013, "xor"),

    /**
     * Implies logical operator
     */
    OP_IMPLIES(2014, "implies"),

    /**
     * For-all quantifier operator
     */
    OP_FOR_ALL(2015, "for all"),

    /**
     * Exists quantifier operator
     */
    OP_EXISTS(2016, "exists"),

    /**
     * Plus operator ("+")
     */
    OP_PLUS(2020, "+"),

    /**
     * Minus operator ("-")
     */
    OP_MINUS(2021, "-"),

    /**
     * Multiply operator ("*")
     */
    OP_MULTIPLY(2022, "*"),

    /**
     * Divide operator ("/")
     */
    OP_DIVIDE(2023, "/"),

    /**
     * Exponent operator ("^")
     */
    OP_EXP(2024, "^");

    private final int value;
    private final String operatorForm;

    OperatorKind(int value, String operatorForm) {
        this.value = value;
        this.operatorForm = operatorForm;
    }

    /**
     * Get the value of this {@link OperatorKind}
     * @return the value of the constant
     */
    public int getValue() {
        return value;
    }
    

    /**
     * Get the operator form
     * @return the operator form
     */
    public String getOperatorForm() {
    	return operatorForm;
    }

    /**
     * Get an enum constant by its name
     * This method is used by {@link br.com.zilics.archetypes.models.rm.utils.xml.XmlParser}
     * @param val the name of the constant
     * @return the enum constant
     */
    public static OperatorKind byValue(String val) {
    	int value = Integer.parseInt(val);
    	for(OperatorKind operatorKind : OperatorKind.values()) {
    		if (operatorKind.getValue() == value) return operatorKind;
    	}
    	return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
    	return Integer.toString(this.value);
    }
    
}
