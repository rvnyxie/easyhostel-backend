package com.easyhostel.backend.application.service.implementations.house;

import com.easyhostel.backend.application.dto.house.HouseCreationDto;
import com.easyhostel.backend.application.dto.house.HouseDto;
import com.easyhostel.backend.application.dto.house.HouseUpdateDto;
import com.easyhostel.backend.application.mapping.interfaces.IHouseMapper;
import com.easyhostel.backend.application.service.implementations.base.BaseService;
import com.easyhostel.backend.application.service.interfaces.house.IHouseService;
import com.easyhostel.backend.domain.entity.House;
import com.easyhostel.backend.domain.enums.ErrorCode;
import com.easyhostel.backend.domain.exception.EntityNotFoundException;
import com.easyhostel.backend.domain.repository.interfaces.house.IHouseRepository;
import com.easyhostel.backend.domain.repository.interfaces.room.IRoomRepository;
import com.easyhostel.backend.domain.service.interfaces.house.IHouseBusinessValidator;
import com.easyhostel.backend.infrastructure.configuration.Translator;
import jakarta.transaction.Transactional;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

/**
 * House service for implementing abstract methods and define dedicated methods
 *
 * @author Nyx
 */
@Service
@Primary
public class HouseService extends BaseService<House, HouseDto, HouseCreationDto, HouseUpdateDto, String> implements IHouseService {

    private final IHouseRepository _houseRepository;
    private final IHouseBusinessValidator _houseBusinessValidator;
    private final IHouseMapper _houseMapper;
    private final IRoomRepository _roomRepository;

    public HouseService(
            IHouseRepository houseRepository,
            IHouseBusinessValidator businessValidator,
            IHouseMapper houseMapper,
            IRoomRepository roomRepository) {
        super(houseRepository);
        _houseRepository = houseRepository;
        _houseBusinessValidator = businessValidator;
        _houseMapper = houseMapper;
        _roomRepository = roomRepository;
    }

    @Override
    public CompletableFuture<HouseDto> insertAsync(HouseCreationDto houseCreationDto) {
        CompletableFuture.runAsync(() -> validateCreationBusiness(houseCreationDto));

        return CompletableFuture.supplyAsync(() -> {
            var house = mapCreationDtoToEntity(houseCreationDto);

            var savedHouse = _houseRepository.save(house);
            var houseDto = mapEntityToDto(savedHouse);

            return houseDto;
        });
    }

    @Override
    @Transactional
    @Async
    public CompletableFuture<Void> deleteRoomFromHouseByIdAsync(String houseId, String roomId) {
        return CompletableFuture.runAsync(() -> {
            _houseBusinessValidator.checkIsRoomBelongedToHouse(houseId, roomId).join();

            var room = _roomRepository.findById(roomId).orElseThrow(() -> new EntityNotFoundException(
                    Translator.toLocale("exception.room.notFound"),
                    ErrorCode.RESOURCE_NOT_FOUND,
                    HttpStatus.NOT_FOUND
            ));

            // To delete room, we don't need to save house
            room.setHouse(null);
            // Save room first, because we changed house reference to null
            _roomRepository.save(room);
            _roomRepository.delete(room);
        });

    }

    @Override
    public House mapCreationDtoToEntity(HouseCreationDto houseCreationDto) {
        var house = _houseMapper.MAPPER.mapHouseCreationDtoToHouse(houseCreationDto);

        return house;
    }

    @Override
    public House mapUpdateDtoToEntity(HouseUpdateDto houseUpdateDto) {
        var house = _houseMapper.MAPPER.mapHouseUpdateDtoToHouse(houseUpdateDto);

        return house;
    }

    @Override
    public HouseDto mapEntityToDto(House house) {
        var houseDto = _houseMapper.MAPPER.mapHouseToHouseDto(house);

        return houseDto;
    }

    @Override
    public CompletableFuture<Void> validateCreationBusiness(HouseCreationDto houseCreationDto) {
        // TODO: Add business validation when creating house
        return super.validateCreationBusiness(houseCreationDto);
    }

    @Override
    public CompletableFuture<Void> validateUpdateBusiness(HouseUpdateDto houseUpdateDto) {
        // TODO: Add business validation when updating house (e.g.houseId not existed)
        return super.validateUpdateBusiness(houseUpdateDto);
    }
}
