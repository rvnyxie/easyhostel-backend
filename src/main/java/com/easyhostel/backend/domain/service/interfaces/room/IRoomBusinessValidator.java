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

}
