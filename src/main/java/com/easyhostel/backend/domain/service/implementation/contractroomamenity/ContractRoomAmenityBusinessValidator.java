package com.easyhostel.backend.domain.service.implementation.contractroomamenity;

import com.easyhostel.backend.domain.service.interfaces.contract.IContractBusinessValidator;
import com.easyhostel.backend.domain.service.interfaces.contractroomamenity.IContractRoomAmenityBusinessValidator;
import com.easyhostel.backend.domain.service.interfaces.roomamenity.IRoomAmenityBusinessValidator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

/**
 * Implementation for ContractRoomAmenity's business validator
 *
 * @author Nyx
 */
@Service
public class ContractRoomAmenityBusinessValidator implements IContractRoomAmenityBusinessValidator {

    private final IContractBusinessValidator _contractBusinessValidator;
    private final IRoomAmenityBusinessValidator _roomAmenityBusinessValidator;

    public ContractRoomAmenityBusinessValidator(IContractBusinessValidator contractBusinessValidator, IRoomAmenityBusinessValidator roomAmenityBusinessValidator) {
        _contractBusinessValidator = contractBusinessValidator;
        _roomAmenityBusinessValidator = roomAmenityBusinessValidator;
    }

    @Override
    @Async
    public CompletableFuture<Void> checkIfContractAndRoomAmenityExistedByIdsAsync(String contractId, String roomAmenityId) {
        return CompletableFuture.allOf(
            _contractBusinessValidator.checkIfContractExistedByIdAsync(contractId),
            _roomAmenityBusinessValidator.checkIfRoomAmenityExistedByIdAsync(roomAmenityId)
        );
    }

}
