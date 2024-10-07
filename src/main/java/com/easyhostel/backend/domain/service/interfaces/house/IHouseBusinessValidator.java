package com.easyhostel.backend.domain.service.interfaces.house;

import com.easyhostel.backend.domain.entity.House;
import com.easyhostel.backend.domain.entity.Room;

import java.util.concurrent.CompletableFuture;

/**
 * Interface for defining House's business validator
 *
 * @author Nyx
 */
public interface IHouseBusinessValidator {

    /**
     * Asynchronously check is Room existed by ID
     *
     * @param houseId House's ID
     * @return CompletableFuture House object
     * @author Nyx
     */
    CompletableFuture<House> checkIfHouseExistedFromId(String houseId);

    /**
     * Asynchronously check if Room belonged to House by ID
     *
     * @param houseId House's ID
     * @param roomId Room's ID
     * @return CompletableFuture Void object
     * @author Nyx
     */
    CompletableFuture<Void> checkIsRoomBelongedToHouse(String houseId, String roomId);

}
