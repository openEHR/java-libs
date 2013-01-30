package br.com.zilics.archetypes.models.rm.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Utility class to deep copy an object
 * 
 * @author Humberto Naves
 *
 */
public final class CloneUtils {
	private CloneUtils() {}
	
	/**
	 * Deep copy an object by serializing and deserializing it (using Java serialization)
	 * 
	 * @param obj the object to copy
	 * @return the copy
	 */
	public static Serializable deepCloneSerializable(Serializable obj) {
		if (obj == null) throw new NullPointerException("Object to clone can't be null");
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(obj);
			ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
			ObjectInputStream ois = new ObjectInputStream(bais);
			return (Serializable) ois.readObject();
		} catch(Throwable t) {
			throw new RuntimeException("Can't deep clone object", t);
		}
	}
}
