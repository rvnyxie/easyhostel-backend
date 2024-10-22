package com.easyhostel.backend.domain.service.interfaces.contractinterior;

import com.easyhostel.backend.domain.entity.embedded.ContractInteriorId;
import com.easyhostel.backend.domain.exception.DuplicatedDistinctRequiredValueException;
import com.easyhostel.backend.domain.exception.EntityNotFoundException;
import com.easyhostel.backend.domain.exception.UnauthorizedAccessException;
import com.easyhostel.backend.domain.service.interfaces.base.IBaseBusinessValidator;

/**
 * Interface for defining ContractInterior's business validator
 *
 * @author Nyx
 */
public interface IContractInteriorBusinessValidator extends IBaseBusinessValidator {

    /**
     * Check if Contract and Interior existed by IDs
     *
     * @param contractId Contract's ID
     * @param interiorId Interior's ID
     * @exception EntityNotFoundException If Contract or Interior not found
     * @author Nyx
     */
    void checkIfContractAndInteriorExistedByIds(String contractId, String interiorId);

    /**
     * Check if ContractInterior existed by IDs
     *
     * @param contractInteriorId ContractInterior's ID
     * @exception EntityNotFoundException If ContractInterior not found
     * @author Nyx
     */
    void checkIfContractInteriorExistedByIds(ContractInteriorId contractInteriorId);

    /**
     * Check if ContractInterior accessible by authenticated user
     *
     * @param contractInteriorId ContractInterior's ID
     * @exception UnauthorizedAccessException If not accessible
     * @author Nyx
     */
    void checkIfContractInteriorAccessibleByAuthUser(ContractInteriorId contractInteriorId);

    /**
     * Check if Contract and Interior accessible by authenticated user
     *
     * @param contractId Contract's ID
     * @param interiorId Interior's ID
     * @exception UnauthorizedAccessException If not accessible
     * @author Nyx
     */
    void checkIfContractAndInteriorAccessibleByAuthUser(String contractId, String interiorId);

    /**
     * Check if ContractInterior existed
     *
     * @param contractInteriorId ContractInterior's ID
     * @exception DuplicatedDistinctRequiredValueException If ContractInterior existed
     * @author Nyx
     */
    void checkIfContractInteriorExistedThrowException(ContractInteriorId contractInteriorId);

}
