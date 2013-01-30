package br.com.zilics.archetypes.models.rm.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Gives an official name to the class
 * @author Humberto Naves
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface RmClass {
	/**
	 * Official name of the class
	 * @return the official name
	 */
	public String value();
}
