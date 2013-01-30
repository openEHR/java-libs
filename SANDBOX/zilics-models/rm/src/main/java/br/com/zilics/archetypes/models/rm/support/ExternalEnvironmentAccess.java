
package br.com.zilics.archetypes.models.rm.support;

import br.com.zilics.archetypes.models.rm.support.measurement.MeasurementService;
import br.com.zilics.archetypes.models.rm.support.terminology.TerminologyService;

/**
 * A mixin class providing access to services in the external environment
 * @author Humberto
 */
public interface ExternalEnvironmentAccess {
    /**
     * Get a terminology interface
     * @return An interface to the terminology service.
     */
    public TerminologyService getTerminologyService();
    /**
     * Get a measurement interface
     * @return An interface to the terminology service.
     */
    public MeasurementService getMeasurementService();
}
