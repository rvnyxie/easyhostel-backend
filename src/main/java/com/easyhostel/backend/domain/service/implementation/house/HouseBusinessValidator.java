package com.easyhostel.backend.domain.service.implementation.house;

import com.easyhostel.backend.domain.entity.House;
import com.easyhostel.backend.domain.enums.ErrorCode;
import com.easyhostel.backend.domain.exception.EntityNotFoundException;
import com.easyhostel.backend.domain.repository.interfaces.house.IHouseRepository;
import com.easyhostel.backend.domain.repository.interfaces.room.IRoomRepository;
import com.easyhostel.backend.domain.service.interfaces.house.IHouseBusinessValidator;
import com.easyhostel.backend.infrastructure.configuration.Translator;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

/**
 * Implementation for House's business validator
 *
 * @author Nyx
 */
@Service
@Async
public class HouseBusinessValidator implements IHouseBusinessValidator {

    private final IHouseRepository _houseRepository;

    public HouseBusinessValidator(IHouseRepository houseRepository) {
        _houseRepository = houseRepository;
    }

    @Override
    @Async
    public CompletableFuture<House> checkIfHouseExistedFromId(String houseId) {
        return CompletableFuture.supplyAsync(() -> {
            var house = _houseRepository.findById(houseId).orElseThrow(() -> new EntityNotFoundException(
                    Translator.toLocale("exception.house.notFound"),
                    ErrorCode.RESOURCE_NOT_FOUND,
                    HttpStatus.NOT_FOUND
            ));
            return house;
        });
    }

    @Override
    @Async
    public CompletableFuture<Void> checkIsRoomBelongedToHouse(String houseId, String roomId) {
        return CompletableFuture.runAsync(() -> {
            var house = checkIfHouseExistedFromId(houseId).join();

            var isRoomBelongedToHouse = house.getRooms().stream()
                    .anyMatch(room -> room.getRoomId().equals(roomId));

            if (!isRoomBelongedToHouse) {
                throw new EntityNotFoundException(
                        Translator.toLocale("exception.roomBelongedToHouse.notFound"),
                        ErrorCode.RESOURCE_NOT_FOUND,
                        HttpStatus.NOT_FOUND
                );
            }
        });
    }

}
