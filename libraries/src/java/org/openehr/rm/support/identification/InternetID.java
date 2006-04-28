/**
 * 
 */
package org.openehr.rm.support.identification;

//import java.net.MalformedURLException;
//import java.net.URL;


/**
 * @author yinsulim
 *
 */
public class InternetID extends UID {

	/**
	 * @param value
	 */
	public InternetID(String value) {
		super(value);
		if (!value.matches(PATTERN)) {
			throw new IllegalArgumentException("wrong format");
		}
		/* or checking using java.net.URL ?
		try {
			URL url = new URL("http", value, 0, "");
		} catch (MalformedURLException e) {
			throw new IllegalArgumentException("wrong format");
		}
		 * but this won't check the format of host or domain.
		 */
	}

	private static String PATTERN = "[a-zA-Z]([a-zA-Z0-9-]*[a-zA-Z0-9])?(\\.[a-zA-Z]([a-zA-Z0-9-]*[a-zA-Z0-9])?)*";
}
