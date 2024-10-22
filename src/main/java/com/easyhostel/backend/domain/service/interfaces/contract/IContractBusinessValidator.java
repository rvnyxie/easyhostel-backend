package com.easyhostel.backend.domain.service.interfaces.contract;

import com.easyhostel.backend.application.dto.contract.ContractCreationDto;
import com.easyhostel.backend.domain.exception.EntityNotFoundException;
import com.easyhostel.backend.domain.exception.MissingRequiredFieldsException;
import com.easyhostel.backend.domain.exception.UnauthorizedAccessException;
import com.easyhostel.backend.domain.service.interfaces.base.IBaseBusinessValidator;

/**
 * Interface for defining Contract's business validator
 *
 * @author Nyx
 */
public interface IContractBusinessValidator extends IBaseBusinessValidator {

    /**
     * Check if Contract existed by ID
     *
     * @param contractId Contract's ID
     * @exception EntityNotFoundException Contract not found
     * @author Nyx
     */
    void checkIfContractExistedById(String contractId);

    /**
     * Check if Contract is Manageable by authenticated user
     *
     * @param contractId Contract's ID
     * @exception UnauthorizedAccessException Not manageable by authenticated user
     * @author Nyx
     */
    void checkIfContractManageableByAuthUser(String contractId);

    /**
     * Check if ContractCreationDto contains Room's ID inside Room field
     *
     * @param contractCreationDto ContractCreationDto object
     * @exception EntityNotFoundException Room not found
     * @exception MissingRequiredFieldsException Room's ID not found
     * @author Nyx
     */
    void checkIfRoomIdProvidedInContractCreationDto(ContractCreationDto contractCreationDto);

}
