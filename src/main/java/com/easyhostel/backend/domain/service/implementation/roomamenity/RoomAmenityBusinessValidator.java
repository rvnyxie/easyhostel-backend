package com.easyhostel.backend.domain.service.implementation.roomamenity;

import com.easyhostel.backend.application.service.interfaces.authentication.IAuthenticationService;
import com.easyhostel.backend.domain.entity.RoomAmenity;
import com.easyhostel.backend.domain.enums.ErrorCode;
import com.easyhostel.backend.domain.exception.DuplicatedDistinctRequiredValueException;
import com.easyhostel.backend.domain.exception.EntityNotFoundException;
import com.easyhostel.backend.domain.repository.interfaces.roomamenity.IRoomAmenityRepository;
import com.easyhostel.backend.domain.service.implementation.base.BaseBusinessValidator;
import com.easyhostel.backend.domain.service.interfaces.roomamenity.IRoomAmenityBusinessValidator;
import com.easyhostel.backend.infrastructure.configuration.Translator;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

/**
 * Implementation for RoomService's business validator
 *
 * @author Nyx
 */
@Service
public class RoomAmenityBusinessValidator extends BaseBusinessValidator implements IRoomAmenityBusinessValidator {

    private final IRoomAmenityRepository _roomAmenityRepository;

    public RoomAmenityBusinessValidator(IAuthenticationService authenticationService,
                                        IRoomAmenityRepository roomAmenityRepository) {
        super(authenticationService);
        _roomAmenityRepository = roomAmenityRepository;
    }

    @Override
    @Async
    public void checkIfRoomAmenityExistedById(String roomAmenityId) {
        var isRoomAmenityExisted = _roomAmenityRepository.existsById(roomAmenityId);

        if (!isRoomAmenityExisted) {
            throw new EntityNotFoundException(
                    Translator.toLocale("exception.roomAmenity.notFound"),
                    ErrorCode.RESOURCE_NOT_FOUND,
                    HttpStatus.NOT_FOUND
            );
        }
    }

    @Override
    public void checkIfNewRoomAmenityHasDuplicatedName(String roomAmenityName) {
        checkIfRoomAmenityNameExistedThrowException(roomAmenityName);
    }

    @Override
    public void checkIfUpdateRoomAmenityHasDuplicatedName(String roomAmenityId, String updateRoomAmenityName) {
        var roomAmenity = _roomAmenityRepository.findById(roomAmenityId).orElseThrow();

        if (!roomAmenity.getRoomAmenityName().equals(updateRoomAmenityName)) {
            checkIfRoomAmenityNameExistedThrowException(updateRoomAmenityName);
        }
    }

    @Override
    public void checkIfRoomAmenityNameExistedThrowException(String roomAmenityName) {
        var isRoomAmenityNameExisted = _roomAmenityRepository.existsByRoomAmenityName(roomAmenityName);

        if (isRoomAmenityNameExisted) {
            throw new DuplicatedDistinctRequiredValueException(
                    Translator.toLocale("exception.roomAmenityName.existed"),
                    ErrorCode.DUPLICATED_VALUE,
                    HttpStatus.BAD_REQUEST
            );
        }
    }


}
