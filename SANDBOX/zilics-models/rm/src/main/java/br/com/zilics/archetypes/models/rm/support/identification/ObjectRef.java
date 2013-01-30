
package br.com.zilics.archetypes.models.rm.support.identification;

import br.com.zilics.archetypes.models.rm.RMObject;
import br.com.zilics.archetypes.models.rm.annotation.EqualsField;
import br.com.zilics.archetypes.models.rm.annotation.NotEmpty;
import br.com.zilics.archetypes.models.rm.annotation.NotNull;

/**
 * Class describing a reference to another object, which may exist locally or be maintained outside the current
 * namespace, e.g. in another service. Services are usually external, e.g. available in a LAN(including
 * on the same host) or the Internet via Corba, SOAP, or some other distributed protocol.
 * However, in small systems they may be part of the same executable as the data containing the Id.
 *
 * @author Humberto
 */
public class ObjectRef extends RMObject {

	private static final long serialVersionUID = -7573967533464340831L;
	@NotNull
	@EqualsField
	private ObjectID id;
	@NotEmpty
	@EqualsField
    private String namespace;
	@NotEmpty
	@EqualsField
    private String type;


    /**
     * Get the id
     * @return Globally unique id of an object, regardless of where it is sorted.
     */
    public ObjectID getId() {
        return id;
    }

    /**
     * Set the id
     * @param id Globally unique id of an object, regardless of where it is sorted.
     */
    public void setId(ObjectID id) {
		assertMutable();
        this.id = id;
    }

    /**
     * Get the namespace
     * @return Namespace to which this identifier belongs in the local system context (and possibly
     * in any other <I>open</I>EHR compliant environment) e.g. "terminology", "demographic".
     * These names are not yet standardized. Legal values for the namespace are:
     * <br>"local" | "unknown" | "[a-zA-Z][a-zA-Z0-9_-:/&+?]*"
     */
    public String getNamespace() {
        return namespace;
    }

    /**
     * Set the namespace
     * @param namespace Namespace to which this identifier belongs in the local system context (and possibly
     * in any other <I>open</I>EHR compliant environment) e.g. "terminology", "demographic".
     * These names are not yet standardized. Legal values for the namespace are:
     * <br>"local" | "unknown" | "[a-zA-Z][a-zA-Z0-9_-:/&+?]*"
     */
    public void setNamespace(String namespace) {
		assertMutable();
        this.namespace = namespace;
    }

    /**
     * Get the type
     * @return Name of the class (concrete or abstract) of object to which this identifier type refers,
     * e.g. "PARTY", "PERSON", "GUIDELINE" etc. These class names are from the relevant reference
     * model. The type name "ANY" can be used to indicate that any type is accepted (e.g.
     * if type is unknown).
     */
    public String getType() {
        return type;
    }

    /**
     * Set the type
     * @param type Name of the class (concrete or abstract) of object to which this identifier type refers,
     * e.g. "PARTY", "PERSON", "GUIDELINE" etc. These class names are from the relevant reference
     * model. The type name "ANY" can be used to indicate that any type is accepted (e.g.
     * if type is unknown).
     */
    public void setType(String type) {
		assertMutable();
        this.type = type;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
    	return this.getClass().getSimpleName() + "[id: " + id + ", namespace:" + namespace + ", type:" + type + "]";
    }    

}
