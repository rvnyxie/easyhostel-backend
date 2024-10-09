package com.easyhostel.backend.domain.service.interfaces.room;

import com.easyhostel.backend.domain.entity.Room;

import java.util.concurrent.CompletableFuture;

/**
 * Interface for defining Room's business validator
 *
 * @author Nyx
 */
public interface IRoomBusinessValidator {

    /**
     * Asynchronously check if Room existed by ID
     *
     * @param roomId Room's ID
     * @return CompletableFuture Room object
     * @author Nyx
     */
    CompletableFuture<Room> checkIfRoomExistedFromId(String roomId);

    /**
     * Asynchronously check if Contract belonged to Room by ID
     *
     * @param roomId Room's ID
     * @param contractId Contract's ID
     * @return CompletableFuture Void object
     * @author Nyx
     */
    CompletableFuture<Void> checkIsContractBelongedToRoom(String roomId, String contractId);

}
