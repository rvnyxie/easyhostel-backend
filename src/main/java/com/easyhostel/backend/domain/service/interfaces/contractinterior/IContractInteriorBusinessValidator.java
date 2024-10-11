package com.easyhostel.backend.domain.service.interfaces.contractinterior;

/**
 * Interface for defining ContractInterior's business validator
 *
 * @author Nyx
 */
public interface IContractInteriorBusinessValidator {

    /**
     * Check if Contract and Interior existed by IDs
     *
     * @param contractId Contract's ID
     * @param interiorId Interior's ID
     * @exception com.easyhostel.backend.domain.exception.EntityNotFoundException If Contract or Interior not found
     * @author Nyx
     */
    void checkIfContractAndInteriorExistedByIds(String contractId, String interiorId);

    /**
     * Check if ContractInterior existed by IDs
     *
     * @param contractId Contract's ID
     * @param interiorId Interior's ID
     * @exception com.easyhostel.backend.domain.exception.EntityNotFoundException If ContractInterior not found
     * @author Nyx
     */
    void checkIfContractInteriorAlreadyExistedByIds(String contractId, String interiorId);

}
