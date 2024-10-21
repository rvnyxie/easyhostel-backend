package com.easyhostel.backend.domain.service.implementation.room;

import com.easyhostel.backend.application.service.interfaces.authentication.IAuthenticationService;
import com.easyhostel.backend.domain.entity.Manager;
import com.easyhostel.backend.domain.enums.ErrorCode;
import com.easyhostel.backend.domain.exception.EntityNotFoundException;
import com.easyhostel.backend.domain.exception.UnauthorizedAccessException;
import com.easyhostel.backend.domain.repository.interfaces.room.IRoomRepository;
import com.easyhostel.backend.domain.service.implementation.base.BaseBusinessValidator;
import com.easyhostel.backend.domain.service.interfaces.contract.IContractBusinessValidator;
import com.easyhostel.backend.domain.service.interfaces.room.IRoomBusinessValidator;
import com.easyhostel.backend.infrastructure.configuration.Translator;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

/**
 * Implementation for Room's business validator
 *
 * @author Nyx
 */
@Service
public class RoomBusinessValidator extends BaseBusinessValidator implements IRoomBusinessValidator {

    private final IAuthenticationService _authenticationService;
    private final IRoomRepository _roomRepository;
    private final IContractBusinessValidator _contractBusinessValidator;

    public RoomBusinessValidator(IAuthenticationService authenticationService,
                                 IRoomRepository roomRepository,
                                 IContractBusinessValidator contractBusinessValidator) {
        super(authenticationService);
        _authenticationService = authenticationService;
        _roomRepository = roomRepository;
        _contractBusinessValidator = contractBusinessValidator;
    }

    @Override
    public void checkIfRoomExistedById(String roomId) {
        var isRoomExisted =  _roomRepository.existsById(roomId);

        if (!isRoomExisted) {
            throw  new EntityNotFoundException(
                    Translator.toLocale("exception.room.notFound"),
                    ErrorCode.ENTITY_NOT_FOUND,
                    HttpStatus.NOT_FOUND
            );
        }
    }

    @Override
    public void checkIfContractBelongedToRoom(String roomId, String contractId) {
        checkIfRoomExistedById(roomId);
        _contractBusinessValidator.checkIfContractExistedByIdAsync(contractId).join();

        var room = _roomRepository.findById(roomId).orElseThrow();

        var isContractBelongedToRoom = room.getContracts().stream()
                .anyMatch(contract -> contract.getContractId().equals(contractId));

        if (!isContractBelongedToRoom) {
            throw new EntityNotFoundException(
                    Translator.toLocale("exception.contractFromRoom.notFound"),
                    ErrorCode.RESOURCE_NOT_FOUND,
                    HttpStatus.NOT_FOUND
            );
        }
    }

    @Override
    public void checkIfRoomSupervisedByAuthUser(String roomId) {
        var currentAuthUser = (Manager) _authenticationService.getAuthentication().getPrincipal();

        var room = _roomRepository.findById(roomId).orElseThrow();

        var houseWhichRoomBelongedTo = room.getHouse();

        var isRoomBelongedToHouseSupervisedByAuthUser = currentAuthUser.getManagerHouses()
                .stream()
                .anyMatch(managerHouse ->
                        managerHouse.getManagerHouseId().getHouseId().equals(houseWhichRoomBelongedTo.getHouseId()));

        if (!isRoomBelongedToHouseSupervisedByAuthUser) {
            throw new UnauthorizedAccessException(
                    Translator.toLocale("exception.roomNotSupervisedByManager"),
                    ErrorCode.FORBIDDEN_ACCESS,
                    HttpStatus.FORBIDDEN
            );
        }
    }
}
