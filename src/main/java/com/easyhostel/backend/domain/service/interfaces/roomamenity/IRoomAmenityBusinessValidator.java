package com.easyhostel.backend.domain.service.interfaces.roomamenity;

import com.easyhostel.backend.domain.exception.DuplicatedDistinctRequiredValueException;
import com.easyhostel.backend.domain.exception.EntityNotFoundException;
import com.easyhostel.backend.domain.service.interfaces.base.IBaseBusinessValidator;

/**
 * Interface for defining RoomService's business validator
 *
 * @author Nyx
 */
public interface IRoomAmenityBusinessValidator extends IBaseBusinessValidator {

    /**
     * Check is RoomAmenity existed by ID
     *
     * @param roomAmenityId RoomAmenity's ID
     * @exception EntityNotFoundException If RoomAmenity not found
     * @author Nyx
     */
    void checkIfRoomAmenityExistedById(String roomAmenityId);

    /**
     * Check if new RoomAmenity has duplicated RoomAmenity's name
     *
     * @param roomAmenityName RoomAmenity's name
     * @exception DuplicatedDistinctRequiredValueException RoomAmenity's name already existed
     * @author Nyx
     */
    void checkIfNewRoomAmenityHasDuplicatedName(String roomAmenityName);

    /**
     * Check if update RoomAmenity which change RoomAmenity's name to existed value
     *
     * @param roomAmenityId RoomAmenity's ID
     * @param roomAmenityName RoomAmenity's name
     * @exception DuplicatedDistinctRequiredValueException RoomAmenity's name already existed
     * @author Nyx
     */
    void checkIfUpdateRoomAmenityHasDuplicatedName(String roomAmenityId, String roomAmenityName);

    /**
     * Check if RoomAmenity's name existed
     *
     * @param roomAmenityName Interior's name
     * @exception DuplicatedDistinctRequiredValueException RoomAmenity's name already existed
     * @author Nyx
     */
    void checkIfRoomAmenityNameExistedThrowException(String roomAmenityName);

}
