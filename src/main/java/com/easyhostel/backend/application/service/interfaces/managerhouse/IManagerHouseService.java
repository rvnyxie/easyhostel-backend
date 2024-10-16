package com.easyhostel.backend.application.service.interfaces.managerhouse;

import com.easyhostel.backend.application.dto.managerhouse.ManagerHouseCreationDto;
import com.easyhostel.backend.application.dto.managerhouse.ManagerHouseDto;
import com.easyhostel.backend.application.dto.managerhouse.ManagerHouseUpdateDto;
import com.easyhostel.backend.application.service.interfaces.base.IBaseService;
import com.easyhostel.backend.domain.entity.embedded.ManagerHouseId;

import java.util.concurrent.CompletableFuture;

public interface IManagerHouseService extends IBaseService<ManagerHouseDto, ManagerHouseCreationDto, ManagerHouseUpdateDto, ManagerHouseId> {

    /**
     * Asynchronously validate ManagerHouse for getting by ID
     *
     * @param managerHouseId ManagerHouseId object
     * @return CompletableFuture Void object
     * @author Nyx
     */
    CompletableFuture<Void> validateGettingBusinessAsync(ManagerHouseId managerHouseId);

    /**
     * Asynchronously validate ManagerHouse for deleting by ID
     *
     * @param managerHouseId ManagerHouseId object
     * @return CompletableFuture Void object
     * @author Nyx
     */
    CompletableFuture<Void> validateDeletionBusinessAsync(ManagerHouseId managerHouseId);

}
