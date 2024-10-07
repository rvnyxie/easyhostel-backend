package com.easyhostel.backend.application.service.interfaces.house;

import com.easyhostel.backend.application.dto.house.HouseCreationDto;
import com.easyhostel.backend.application.dto.house.HouseDto;
import com.easyhostel.backend.application.dto.house.HouseUpdateDto;
import com.easyhostel.backend.application.service.interfaces.base.IBaseService;

import java.util.concurrent.CompletableFuture;

/**
 * Interface for House service, extends base GET, INSERT, UPDATE, DELETE methods
 *
 * @author Nyx
 */
public interface IHouseService extends IBaseService<HouseDto, HouseCreationDto, HouseUpdateDto, String> {

    /**
     * Asynchronously delete Room from House by ID
     *
     * @param houseId House's ID
     * @param roomId Room's ID
     * @return CompletableFuture Void object
     * @author Nyx
     */
    CompletableFuture<Void> deleteRoomFromHouseByIdAsync(String houseId, String roomId);

}
