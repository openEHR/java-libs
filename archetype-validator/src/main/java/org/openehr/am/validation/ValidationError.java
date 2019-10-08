package org.openehr.am.validation;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
/**
 * Archetype validation error with error type, short text
 * and a longer more specific explanation of the error
 *
 * @author rong.chen
 * @author sebastian.garde
 */
public class ValidationError {


    public ValidationError(ErrorType type, String subType, Object... params) {
        this.type = type;
        this.subType = subType;
        this.params = params;
    }

    public ErrorType getType() {
        return type;
    }


    /** Gets the error text in the default locale
     *
     * @return
     */
    public String getText() {
        return getText(Locale.getDefault());
    }

    /** Gets the description in an explicitly specified locale
     *
     * @param locale
     * @return
     */
    public String getText(Locale locale) {
        if (locale == null) {
            locale = Locale.getDefault();
        }

        String textKey = getTextKey();
        ResourceBundle rb = ResourceBundle.getBundle("validations", locale, UTF8Control.getInstance());

        // If the resource bundle doen't know the textkey, we try without the subtype
        if (!rb.containsKey(textKey)) {
            textKey = getTextKey(true);
        }
        // If for some reason the bundle doesn't contain the textKey, we don't fail completely but return the textkey at least.
        String errorText = rb.containsKey(textKey) ? rb.getString(textKey) : textKey;
        if (params != null) {
            errorText= MessageFormat.format(errorText, params);
        }
        return errorText;
    }

    protected String getTextKey() {
        return getTextKey(false);
    }

    protected String getTextKey(boolean ignoreSubtype) {
        if (ignoreSubtype || subType == null) {
            return this.type.toString()+"_TEXT";
        } else {
            return this.type.toString() + "_" + subType + "_TEXT";
        }
    }

    /** Gets the description in the default locale
     *
     * @return
     */
    public String getDescription() {
        return getDescription(Locale.getDefault());
    }

    /** Gets the description in an explicitly specified locale
     *
     * @param locale
     * @return
     */
    public String getDescription(Locale locale) {
        if (locale == null) {
            locale = Locale.getDefault();
        }


        return ResourceBundle.getBundle("validations", locale, UTF8Control.getInstance()).getString(this.type.toString());
    }


    /**
     * String representation of this error
     */
    @Override
    public String toString() {
        return type + ", " + getText() + ", "+ getDescription();
    }

    /**
     * String representation of this error
     */

    public String toString(Locale locale) {
        return type + ", " + getText(locale) + ", "+ getDescription(locale);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) { return false; }
        if (obj == this) { return true; }
        if (obj.getClass() != getClass()) {
            return false;
        }
        ValidationError ve = (ValidationError) obj;
        return new EqualsBuilder()
                .append(type, ve.type)
                .append(subType, ve.subType)
                .append(params, ve.params)
                //   .append(getDescription(), ve.getDescription())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(11, 47).
                append(type).
                append(subType).
                append(params).
                //         append(getDescription()).
                toHashCode();
    }



    // fields
    private ErrorType type;
    private String subType;
    private Object[] params;
}
