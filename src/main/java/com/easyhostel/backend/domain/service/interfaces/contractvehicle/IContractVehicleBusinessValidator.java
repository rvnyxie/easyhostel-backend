package com.easyhostel.backend.domain.service.interfaces.contractvehicle;

import com.easyhostel.backend.domain.entity.embedded.ContractVehicleId;
import com.easyhostel.backend.domain.exception.DuplicatedDistinctRequiredValueException;
import com.easyhostel.backend.domain.exception.EntityNotFoundException;
import com.easyhostel.backend.domain.exception.UnauthorizedAccessException;
import com.easyhostel.backend.domain.service.interfaces.base.IBaseBusinessValidator;

/**
 * Interface for defining ContractVehicle's business validator
 *
 * @author Nyx
 */
public interface IContractVehicleBusinessValidator extends IBaseBusinessValidator {

    /**
     * Check if Contract and Vehicle existed
     *
     * @param contractId Contract's ID
     * @param vehicleId Vehicle's ID
     * @exception EntityNotFoundException If Contract or Vehicle not found
     * @author Nyx
     */
    void checkIfContractAndVehicleExisted(String contractId, String vehicleId);

    /**
     * Check if ContractVehicle existed
     *
     * @param contractVehicleId ContractVehicle's ID
     * @exception EntityNotFoundException If ContractVehicle not found
     * @author Nyx
     */
    void checkIfContractVehicleExisted(ContractVehicleId contractVehicleId);

    /**
     * Check if ContractVehicle accessible by authenticated user
     *
     * @param contractVehicleId ContractVehicle's ID
     * @exception UnauthorizedAccessException If not accessible
     * @author Nyx
     */
    void checkIfContractVehicleAccessibleByAuthUser(ContractVehicleId contractVehicleId);

    /**
     * Check if ContractVehicle existed
     *
     * @param contractVehicleId ContractVehicle's ID
     * @exception DuplicatedDistinctRequiredValueException If ContractVehicle existed
     * @author Nyx
     */
    void checkIfContractVehicleExistedThrowException(ContractVehicleId contractVehicleId);

    /**
     * Check if Contract and Vehicle accessible by authenticated user
     *
     * @param contractId Contract's ID
     * @param vehicleId Vehicle's ID
     * @exception UnauthorizedAccessException If not accessible
     * @author Nyx
     */
    void checkIfContractAndVehicleAccessibleByAuthUser(String contractId, String vehicleId);

}
