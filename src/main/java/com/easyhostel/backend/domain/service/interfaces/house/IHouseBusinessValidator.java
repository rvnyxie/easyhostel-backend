package com.easyhostel.backend.domain.service.interfaces.house;

import com.easyhostel.backend.domain.entity.House;
import com.easyhostel.backend.domain.exception.UnauthorizedAccessException;
import com.easyhostel.backend.domain.service.interfaces.base.IBaseBusinessValidator;

import java.util.concurrent.CompletableFuture;

/**
 * Interface for defining House's business validator
 *
 * @author Nyx
 */
public interface IHouseBusinessValidator extends IBaseBusinessValidator {

    /**
     * Asynchronously check is Room existed by ID
     *
     * @param houseId House's ID
     * @return CompletableFuture House object
     * @author Nyx
     */
    House checkIfHouseExistedFromId(String houseId);

    /**
     * Asynchronously check if Room belonged to House by ID
     *
     * @param houseId House's ID
     * @param roomId Room's ID
     * @return CompletableFuture Void object
     * @author Nyx
     */
    void checkIsRoomBelongedToHouse(String houseId, String roomId);

    /**
     * Check if requested House supervised by authenticated user
     *
     * @param houseId House's ID
     * @exception UnauthorizedAccessException Authenticated doesn't supervise House
     * @author Nyx
     */
    void checkIfHouseSupervisedByAuthUser(String houseId);
}
