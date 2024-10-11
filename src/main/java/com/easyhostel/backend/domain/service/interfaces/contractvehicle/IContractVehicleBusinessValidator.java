package com.easyhostel.backend.domain.service.interfaces.contractvehicle;

/**
 * Interface for defining ContractVehicle's business validator
 *
 * @author Nyx
 */
public interface IContractVehicleBusinessValidator {

    /**
     * Check if Contract and Vehicle existed
     *
     * @param contractId Contract's ID
     * @param vehicleId Vehicle's ID
     * @exception com.easyhostel.backend.domain.exception.EntityNotFoundException If Contract or Vehicle not found
     * @author Nyx
     */
    void checkIfContractAndVehicleExisted(String contractId, String vehicleId);

    /**
     * Check if ContractVehicle existed
     *
     * @param contractId Contract's ID
     * @param vehicleId Vehicle's ID
     * @exception com.easyhostel.backend.domain.exception.EntityNotFoundException If ContractVehicle not found
     * @author Nyx
     */
    void checkIfContractVehicleExisted(String contractId, String vehicleId);

}
