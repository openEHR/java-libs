
package br.com.zilics.archetypes.models.rm.datatypes.uri;

import br.com.zilics.archetypes.models.rm.annotation.RmClass;

/**
 * A EHRURI is a URI which has the scheme name "ehr", and which can
 * only reference elements in EHRs.
 *
 * @author Humberto
 */
@RmClass("DV_EHR_URI")
public class DvEHRURI extends DvURI {

	private static final long serialVersionUID = -8305080555163910724L;

}
