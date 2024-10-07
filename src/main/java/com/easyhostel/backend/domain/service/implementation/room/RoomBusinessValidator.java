package com.easyhostel.backend.domain.service.implementation.room;

import com.easyhostel.backend.domain.entity.Room;
import com.easyhostel.backend.domain.enums.ErrorCode;
import com.easyhostel.backend.domain.exception.EntityNotFoundException;
import com.easyhostel.backend.domain.repository.interfaces.room.IRoomRepository;
import com.easyhostel.backend.domain.service.interfaces.room.IRoomBusinessValidator;
import com.easyhostel.backend.infrastructure.configuration.Translator;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

/**
 * Implementation for Room's business validator
 *
 * @author Nyx
 */
@Service
public class RoomBusinessValidator implements IRoomBusinessValidator {

    private final IRoomRepository _roomRepository;

    public RoomBusinessValidator(IRoomRepository roomRepository) {
        _roomRepository = roomRepository;
    }

    @Override
    @Async
    public CompletableFuture<Room> checkIfRoomExistedFromId(String roomId) {
        return CompletableFuture.supplyAsync(() -> {
            var room = _roomRepository.findById(roomId).orElseThrow(() -> new EntityNotFoundException(
                    Translator.toLocale("exception.room.notFound"),
                    ErrorCode.ENTITY_NOT_FOUND,
                    HttpStatus.NOT_FOUND
            ));
            return room;
        });
    }

}
