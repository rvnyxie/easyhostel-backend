package com.easyhostel.backend.domain.service.implementation.roomamenity;

import com.easyhostel.backend.domain.entity.RoomAmenity;
import com.easyhostel.backend.domain.enums.ErrorCode;
import com.easyhostel.backend.domain.exception.EntityNotFoundException;
import com.easyhostel.backend.domain.repository.interfaces.roomamenity.IRoomAmenityRepository;
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
public class RoomAmenityBusinessValidator implements IRoomAmenityBusinessValidator {

    private final IRoomAmenityRepository _roomAmenityRepository;

    public RoomAmenityBusinessValidator(IRoomAmenityRepository roomAmenityRepository) {
        _roomAmenityRepository = roomAmenityRepository;
    }

    @Override
    @Async
    public CompletableFuture<RoomAmenity> checkIfRoomAmenityExistedByIdAsync(String roomAmenityId) {
        return CompletableFuture.supplyAsync(() -> {
            var roomAmenity = _roomAmenityRepository.findById(roomAmenityId).orElseThrow(() -> new EntityNotFoundException(
                    Translator.toLocale("exception.roomAmenity.notFound"),
                    ErrorCode.RESOURCE_NOT_FOUND,
                    HttpStatus.NOT_FOUND
            ));

            return roomAmenity;
        });
    }

}
