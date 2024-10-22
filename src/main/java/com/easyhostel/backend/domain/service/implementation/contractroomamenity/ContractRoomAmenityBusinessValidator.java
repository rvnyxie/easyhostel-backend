package com.easyhostel.backend.domain.service.implementation.contractroomamenity;

import com.easyhostel.backend.application.service.interfaces.authentication.IAuthenticationService;
import com.easyhostel.backend.domain.entity.Manager;
import com.easyhostel.backend.domain.entity.embedded.ContractRoomAmenityId;
import com.easyhostel.backend.domain.enums.ErrorCode;
import com.easyhostel.backend.domain.exception.DuplicatedDistinctRequiredValueException;
import com.easyhostel.backend.domain.exception.EntityNotFoundException;
import com.easyhostel.backend.domain.exception.UnauthorizedAccessException;
import com.easyhostel.backend.domain.repository.interfaces.contract.IContractRepository;
import com.easyhostel.backend.domain.repository.interfaces.contractroomamenity.IContractRoomAmenityRepository;
import com.easyhostel.backend.domain.service.implementation.base.BaseBusinessValidator;
import com.easyhostel.backend.domain.service.interfaces.contract.IContractBusinessValidator;
import com.easyhostel.backend.domain.service.interfaces.contractroomamenity.IContractRoomAmenityBusinessValidator;
import com.easyhostel.backend.domain.service.interfaces.roomamenity.IRoomAmenityBusinessValidator;
import com.easyhostel.backend.infrastructure.configuration.Translator;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

/**
 * Implementation for ContractRoomAmenity's business validator
 *
 * @author Nyx
 */
@Service
public class ContractRoomAmenityBusinessValidator extends BaseBusinessValidator implements IContractRoomAmenityBusinessValidator {

    private final IAuthenticationService _authenticationService;
    private final IContractBusinessValidator _contractBusinessValidator;
    private final IRoomAmenityBusinessValidator _roomAmenityBusinessValidator;
    private final IContractRoomAmenityRepository _contractRoomAmenityRepository;
    private final IContractRepository _contractRepository;

    public ContractRoomAmenityBusinessValidator(IAuthenticationService authenticationService,
                                                IContractBusinessValidator contractBusinessValidator,
                                                IRoomAmenityBusinessValidator roomAmenityBusinessValidator,
                                                IContractRoomAmenityRepository contractRoomAmenityRepository,
                                                IContractRepository contractRepository) {
        super(authenticationService);
        _authenticationService = authenticationService;
        _contractBusinessValidator = contractBusinessValidator;
        _roomAmenityBusinessValidator = roomAmenityBusinessValidator;
        _contractRoomAmenityRepository = contractRoomAmenityRepository;
        _contractRepository = contractRepository;
    }

    @Override
    @Async
    public void checkIfContractAndRoomAmenityExistedByIds(String contractId, String roomAmenityId) {
        _contractBusinessValidator.checkIfContractExistedById(contractId);
        _roomAmenityBusinessValidator.checkIfRoomAmenityExistedById(roomAmenityId);
    }

    @Override
    public void checkIfContractRoomAmenityExistedById(ContractRoomAmenityId contractRoomAmenityId) {
        var isContractRoomAmenityExisted = _contractRoomAmenityRepository.existsById(contractRoomAmenityId);

        if (!isContractRoomAmenityExisted) {
            throw  new EntityNotFoundException(
                    Translator.toLocale("exception.contractRoomAmenity.notFound"),
                    ErrorCode.RESOURCE_NOT_FOUND,
                    HttpStatus.NOT_FOUND
            );
        }
    }

    @Override
    public void checkIfContractRoomAmenityAccessibleByAuthUser(ContractRoomAmenityId contractRoomAmenityId) {
        var currenAuthUser = (Manager) _authenticationService.getAuthentication().getPrincipal();

        // RoomAmenity is accessible for any authenticated user
        var contract = _contractRepository.findById(contractRoomAmenityId.getContractId()).orElseThrow();

        var isContractRoomAmenityAccessibleByAuthUser = currenAuthUser.getManagerHouses()
                .stream()
                .anyMatch(managerHouse -> managerHouse.getHouse().getHouseId().equals(
                        contract.getRoom().getHouse().getHouseId()
                ));

        if (!isContractRoomAmenityAccessibleByAuthUser) {
            throw new UnauthorizedAccessException(
                    Translator.toLocale("exception.contractRoomAmenityNotAccessibleByAuthUser"),
                    ErrorCode.FORBIDDEN_ACCESS,
                    HttpStatus.FORBIDDEN
            );
        }
    }

    @Override
    public void checkIfContractRoomAmenityExistedThrowException(ContractRoomAmenityId contractRoomAmenityId) {
        var isContractRoomAmenityExisted = _contractRoomAmenityRepository.existsById(contractRoomAmenityId);

        if (isContractRoomAmenityExisted) {
            throw new DuplicatedDistinctRequiredValueException(
                    Translator.toLocale("exception.contractRoomAmenity.existed"),
                    ErrorCode.DUPLICATED_VALUE,
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    @Override
    public void checkIfContractAndRoomAmenityAccessibleByAuthUser(String contractId, String roomAmenityId) {
        // RoomAmenity is accessible for any authenticated user
        _contractBusinessValidator.checkIfContractManageableByAuthUser(contractId);
    }

}
