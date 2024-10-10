package com.easyhostel.backend.domain.service.interfaces.roomamenity;

import com.easyhostel.backend.domain.entity.RoomAmenity;

import java.util.concurrent.CompletableFuture;

/**
 * Interface for defining RoomService's business validator
 *
 * @author Nyx
 */
public interface IRoomAmenityBusinessValidator {

    /**
     * Asynchronously check is RoomAmenity existed by ID
     *
     * @param roomAmenityId RoomAmenity's ID
     * @return CompletableFuture Contract object
     * @author Nyx
     */
    CompletableFuture<RoomAmenity> checkIfRoomAmenityExistedByIdAsync(String roomAmenityId);

}
