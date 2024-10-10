package com.easyhostel.backend.domain.service.interfaces.contractroomamenity;

import java.util.concurrent.CompletableFuture;

/**
 * Interface for defining ContractRoomAmenity's business validator
 *
 * @author Nyx
 */
public interface IContractRoomAmenityBusinessValidator {

    /**
     * Asynchronously check is Contract and RoomAmenity existed by IDs
     *
     * @param contractId Contract's ID
     * @param roomAmenityId RoomAmenity's ID
     * @return CompletableFuture Void object
     * @author Nyx
     */
    CompletableFuture<Void> checkIfContractAndRoomAmenityExistedByIdsAsync(String contractId, String roomAmenityId);

}
