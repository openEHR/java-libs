package br.com.zilics.archetypes.models.rm.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation for Map fields
 * @author Humberto Naves
 *
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface MapItem {
	/**
	 * Get the name of the key
	 * @return the name the key
	 */
	public String key() default "id";
	/**
	 * Is the key an attribute of the XML?
	 * @return true if the key is a XML attribute
	 */
	public boolean isXmlAttribute() default true;
}
