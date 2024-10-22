package com.easyhostel.backend.application.service.interfaces.contractinterior;

import com.easyhostel.backend.application.dto.contractinterior.ContractInteriorCreationDto;
import com.easyhostel.backend.application.dto.contractinterior.ContractInteriorDto;
import com.easyhostel.backend.application.dto.contractinterior.ContractInteriorUpdateDto;
import com.easyhostel.backend.application.service.interfaces.base.IBaseService;
import com.easyhostel.backend.domain.entity.embedded.ContractInteriorId;

import java.util.concurrent.CompletableFuture;

/**
 * Interface for ContractInterior service, extends base GET, INSERT, UPDATE, DELETE methods
 *
 * @author Nyx
 */
public interface IContractInteriorService extends IBaseService<ContractInteriorDto, ContractInteriorCreationDto, ContractInteriorUpdateDto, ContractInteriorId> {

    /**
     * Asynchronously delete ContractInterior by IDs
     *
     * @param contractInteriorId ContractInterior's ID
     * @return CompletableFuture Void object
     * @author Nyx
     */
    CompletableFuture<Void> deleteContractInteriorByIdsAsync(ContractInteriorId contractInteriorId);

}
