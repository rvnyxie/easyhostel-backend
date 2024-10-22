package com.easyhostel.backend.domain.service.interfaces.vehicle;

import com.easyhostel.backend.domain.exception.EntityNotFoundException;
import com.easyhostel.backend.domain.service.interfaces.base.IBaseBusinessValidator;

/**
 * Interface for defining Vehicle's business validator
 *
 * @author Nyx
 */
public interface IVehicleBusinessValidator extends IBaseBusinessValidator {

    /**
     * Check is Vehicle existed by ID
     *
     * @param vehicleId Vehicle's ID
     * @exception EntityNotFoundException If Vehicle not found
     * @author Nyx
     */
    void checkIfVehicleExistedById(String vehicleId);


}
