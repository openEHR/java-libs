package org.openehr.am.validation;

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


    public ValidationError(ErrorType type) {
        this(type, null);
    }

    public ValidationError(ErrorType type, String text) {
        this.type = type;
        this.text = text;
    }

    public ErrorType getType() {
        return type;
    }

    public String getText() {
        return text;
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


        return ResourceBundle.getBundle("validations", locale).getString(this.type.toString());
    }   


    /**
     * String representation of this error
     */
    @Override
    public String toString() {
        return type + ", " + text + ", "+ getDescription();
    }

    /**
     * String representation of this error
     */

    public String toString(Locale locale) {
        return type + ", " + text + ", "+ getDescription(locale);
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
        .append(text, ve.text)
     //   .append(getDescription(), ve.getDescription())
        .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(11, 47).
                append(type).
                append(text).
       //         append(getDescription()).
                toHashCode();
    }



    // fields
    private ErrorType type;
    private String text;
}
