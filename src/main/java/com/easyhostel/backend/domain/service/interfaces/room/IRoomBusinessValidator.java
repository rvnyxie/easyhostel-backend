package com.easyhostel.backend.domain.service.interfaces.room;

import com.easyhostel.backend.domain.exception.EntityNotFoundException;
import com.easyhostel.backend.domain.exception.UnauthorizedAccessException;
import com.easyhostel.backend.domain.service.interfaces.base.IBaseBusinessValidator;

/**
 * Interface for defining Room's business validator
 *
 * @author Nyx
 */
public interface IRoomBusinessValidator extends IBaseBusinessValidator {

    /**
     * Check if Room existed by ID
     *
     * @param roomId Room's ID
     * @exception EntityNotFoundException Room not found
     * @author Nyx
     */
    void checkIfRoomExistedById(String roomId);

    /**
     * Check if Contract belonged to Room by IDs
     *
     * @param roomId Room's ID
     * @param contractId Contract's ID
     * @exception EntityNotFoundException Contract from Room not found
     * @author Nyx
     */
    void checkIfContractBelongedToRoom(String roomId, String contractId);

    /**
     * Check if Room not belonged to House which is supervised by authenticated user
     *
     * @param roomId Room's ID
     * @exception UnauthorizedAccessException If Room not supervised by authenticated user
     * @author Nyx
     */
    void checkIfRoomSupervisedByAuthUser(String roomId);

}
