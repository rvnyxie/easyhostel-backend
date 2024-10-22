package com.easyhostel.backend.domain.service.interfaces.contractroomamenity;

import com.easyhostel.backend.domain.entity.embedded.ContractRoomAmenityId;
import com.easyhostel.backend.domain.exception.DuplicatedDistinctRequiredValueException;
import com.easyhostel.backend.domain.exception.EntityNotFoundException;
import com.easyhostel.backend.domain.exception.UnauthorizedAccessException;
import com.easyhostel.backend.domain.service.interfaces.base.IBaseBusinessValidator;

/**
 * Interface for defining ContractRoomAmenity's business validator
 *
 * @author Nyx
 */
public interface IContractRoomAmenityBusinessValidator extends IBaseBusinessValidator {

    /**
     * Asynchronously check is Contract and RoomAmenity existed by IDs
     *
     * @param contractId Contract's ID
     * @param roomAmenityId RoomAmenity's ID
     * @author Nyx
     */
    void checkIfContractAndRoomAmenityExistedByIds(String contractId, String roomAmenityId);

    /**
     * Check if ContractRoomAmenity existed by IDs
     *
     * @param contractRoomAmenityId ContractRoomAmenity's ID
     * @exception EntityNotFoundException If ContractRoomAmenity not found
     * @author Nyx
     */
    void checkIfContractRoomAmenityExistedById(ContractRoomAmenityId contractRoomAmenityId);

    /**
     * Check if ContractRoomAmenity accessible by authenticated user
     *
     * @param contractRoomAmenityId ContractRoomAmenity's ID
     * @exception UnauthorizedAccessException If not accessible
     * @author Nyx
     */
    void checkIfContractRoomAmenityAccessibleByAuthUser(ContractRoomAmenityId contractRoomAmenityId);

    /**
     * Check if ContractRoomAmenity existed
     *
     * @param contractRoomAmenityId ContractRoomAmenity's ID
     * @exception DuplicatedDistinctRequiredValueException If ContractRoomAmenity existed
     * @author Nyx
     */
    void checkIfContractRoomAmenityExistedThrowException(ContractRoomAmenityId contractRoomAmenityId);

    /**
     * Check if Contract and RoomAmenity accessible by authenticated user
     *
     * @param contractId Contract's ID
     * @param roomAmenityId RoomAmenity's ID
     * @exception UnauthorizedAccessException If not accessible
     * @author Nyx
     */
    void checkIfContractAndRoomAmenityAccessibleByAuthUser(String contractId, String roomAmenityId);

}
