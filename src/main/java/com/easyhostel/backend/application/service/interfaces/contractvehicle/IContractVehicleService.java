package com.easyhostel.backend.application.service.interfaces.contractvehicle;

import com.easyhostel.backend.application.dto.contractvehicle.ContractVehicleCreationDto;
import com.easyhostel.backend.application.dto.contractvehicle.ContractVehicleDto;
import com.easyhostel.backend.application.dto.contractvehicle.ContractVehicleUpdateDto;
import com.easyhostel.backend.application.service.interfaces.base.IBaseService;
import com.easyhostel.backend.domain.entity.embedded.ContractVehicleId;

import java.util.concurrent.CompletableFuture;

/**
 * Interface for ContractVehicle service, extends base GET, INSERT, UPDATE, DELETE methods
 *
 * @author Nyx
 */
public interface IContractVehicleService extends IBaseService<ContractVehicleDto, ContractVehicleCreationDto, ContractVehicleUpdateDto, ContractVehicleId> {

    /**
     * Asynchronously delete ContractVehicle by IDs
     *
     * @param contractVehicleId ContractVehicle's ID
     * @return CompletableFuture Void object
     * @author Nyx
     */
    CompletableFuture<Void> deleteContractVehicleByIdsAsync(ContractVehicleId contractVehicleId);

}
