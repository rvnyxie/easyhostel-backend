package com.easyhostel.backend.application.service.interfaces.room;

import com.easyhostel.backend.application.dto.room.RoomCreationDto;
import com.easyhostel.backend.application.dto.room.RoomDto;
import com.easyhostel.backend.application.dto.room.RoomUpdateDto;
import com.easyhostel.backend.application.service.interfaces.base.IBaseService;

import java.util.concurrent.CompletableFuture;

/**
 * Interface for Room service, extends base GET, INSERT, UPDATE, DELETE methods
 *
 * @author Nyx
 */
public interface IRoomService extends IBaseService<RoomDto, RoomCreationDto, RoomUpdateDto, String> {

    /**
     * Asynchronously delete Contract from Room by ID
     *
     * @param roomId Room's ID
     * @param contractId Contract's ID
     * @return CompletableFuture Void object
     * @author Nyx
     */
    CompletableFuture<Void> deleteContractFromRoomByIdAsync(String roomId, String contractId);

}
