package br.com.zilics.archetypes.models.rm.support.identification;

import br.com.zilics.archetypes.models.rm.annotation.Ignore;
import br.com.zilics.archetypes.models.rm.annotation.RmClass;
import br.com.zilics.archetypes.models.rm.utils.validation.ValidationResult;

/**
 * Globally unique identifier for one version of a versioned object. Lexical
 * form:<br /> object_id '::' creating_system-id '::' version_tree_id
 * 
 * @author Humberto
 */
@RmClass("OBJECT_VERSION_ID")
public class ObjectVersionID extends UIDBasedID {

	private static final long serialVersionUID = -3618299145241132267L;

	@Ignore
	private UID objectId;
	@Ignore
	private VersionTreeID versionTreeId;
	@Ignore
	private HierObjectID creatingSystemId;

	/**
	 * Unique identifier that identifies one version. It is normally the unique
	 * identifier of the version container containing the version referred to by
	 * this {@link ObjectVersionID} instance.
	 * 
	 * @return objectId
	 */
	public UID objectId() {
		return objectId;
	}

	/**
	 * Tree identifier of this version with respect to other versions in the
	 * same version tree, as either 1 or 3 part dot-separated numbers e.g. "1" ,
	 * "2.1.4"
	 * 
	 * @return versionTreeId
	 */
	public VersionTreeID versionTreeId() {
		return versionTreeId;
	}

	/**
	 * Identifier of the system that created the Version corresponding to this
	 * {@link ObjectVersionID}
	 * 
	 * @return creatingSystemId
	 */
	public HierObjectID creatingSystemId() {
		return creatingSystemId;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UID root() {
		return objectId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String extension() {
		return creatingSystemId.getValue() + "::" + versionTreeId.getValue();
	}
	
	/**
	 * True if this version identifier represents a branch.
	 * @return Is a branch?
	 */
	public boolean isBranch() {
		return creatingSystemId.hasExtension();
	}

	@Override
    protected void performValidation(ValidationResult result) {
    	super.performValidation(result);
		String value = getValue();
		objectId = null;
		creatingSystemId = null;
		versionTreeId = null;
		
		if (value == null) return;
		
		// Steps for value checking:
		// 1. Check if value contains any '::' or starts with '::'
		int doubleColons = value.indexOf("::");
		if (doubleColons <= 0) {
			result.addItem(this, "Bad format, missing objectId");
			return;
		}
		// 2. Check how many segments in the value
		String[] splits = value.split("::");
		int segments = splits.length;
		if (segments < 3) {
			result.addItem(this, "Bad format, missing creatingSystemId or versionTreeId");
			return;
		}
		if (segments > 4) {
			result.addItem(this, "Bad format, too many segments or '::'");
		}
		// 3. Construct objects for each segment
		// the patterns below are for sorting only, the correct syntax
		// checking is handled by the UID subclasses.
		if (splits[0].matches(UUID.PATTERN)) { // pattern for UUID
			objectId = new UUID();
			((UUID) objectId).setValue(value.substring(0, doubleColons));
		} else if (splits[0].matches(ISO_OID.PATTERN)) { // for ISO_OID
			objectId = new ISO_OID();
			((ISO_OID) objectId).setValue(value.substring(0, doubleColons));
		} else if (splits[0].matches(InternetID.PATTERN)) { // for InternetID,
			objectId = new InternetID();
			((InternetID) objectId).setValue(value.substring(0, doubleColons));
		} else {
			result.addItem(this, "Wrong format: " + splits[0]);
		}
		objectId.validate(result);

		creatingSystemId = new HierObjectID();
		versionTreeId = new VersionTreeID();
		if (segments == 4) {
			creatingSystemId.setValue(splits[1] + "::" + splits[2]);
			versionTreeId.setValue(splits[3]);
		} else {
			creatingSystemId.setValue(splits[1]);
			versionTreeId.setValue(splits[2]);
		}
		creatingSystemId.validate(result);
		versionTreeId.validate(result);

	}
}
