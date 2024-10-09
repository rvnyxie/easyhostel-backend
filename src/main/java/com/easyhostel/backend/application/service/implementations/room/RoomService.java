package com.easyhostel.backend.application.service.implementations.room;

import com.easyhostel.backend.application.dto.room.RoomCreationDto;
import com.easyhostel.backend.application.dto.room.RoomDto;
import com.easyhostel.backend.application.dto.room.RoomUpdateDto;
import com.easyhostel.backend.application.mapping.interfaces.IRoomMapper;
import com.easyhostel.backend.application.service.implementations.base.BaseService;
import com.easyhostel.backend.application.service.interfaces.room.IRoomService;
import com.easyhostel.backend.domain.entity.Room;
import com.easyhostel.backend.domain.enums.ErrorCode;
import com.easyhostel.backend.domain.exception.EntityNotFoundException;
import com.easyhostel.backend.domain.repository.interfaces.contract.IContractRepository;
import com.easyhostel.backend.domain.repository.interfaces.house.IHouseRepository;
import com.easyhostel.backend.domain.repository.interfaces.room.IRoomRepository;
import com.easyhostel.backend.domain.service.interfaces.room.IRoomBusinessValidator;
import com.easyhostel.backend.infrastructure.configuration.Translator;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Room modification service for implementing abstract methods and define dedicated methods
 *
 * @author Nyx
 */
@Service
public class RoomService extends BaseService<Room, RoomDto, RoomCreationDto, RoomUpdateDto, String> implements IRoomService {

    private final IRoomRepository _roomRepository;
    private final IRoomBusinessValidator _roomBusinessValidator;
    private final IRoomMapper _roomMapper;
    private final IContractRepository _contractRepository;

    public RoomService(IRoomRepository roomRepository, IRoomBusinessValidator roomBusinessValidator, IRoomMapper roomMapper, IContractRepository contractRepository) {
        super(roomRepository);
        _roomRepository = roomRepository;
        _roomBusinessValidator = roomBusinessValidator;
        _roomMapper = roomMapper;
        _contractRepository = contractRepository;
    }

    @Override
    public CompletableFuture<Void> deleteContractFromRoomByIdAsync(String roomId, String contractId) {
        return CompletableFuture.runAsync(() -> {
           _roomBusinessValidator.checkIsContractBelongedToRoom(roomId, contractId);

           var contract = _contractRepository.findById(contractId).orElseThrow(() -> new EntityNotFoundException(
                   Translator.toLocale("exception.contractFromRoom.notFound"),
                   ErrorCode.RESOURCE_NOT_FOUND,
                   HttpStatus.NOT_FOUND
           ));

           // Delete contract
           contract.setRoom(null);
           _contractRepository.save(contract);
           _contractRepository.delete(contract);
        });
    }

    @Override
    public Room mapCreationDtoToEntity(RoomCreationDto roomCreationDto) {
        var room = _roomMapper.mapRoomCreationDtoToRoom(roomCreationDto);

        return room;
    }

    @Override
    public Room mapUpdateDtoToEntity(RoomUpdateDto roomUpdateDto) {
        var room = _roomMapper.mapRoomUpdateDtoToRoom(roomUpdateDto);

        return room;
    }

    @Override
    public RoomDto mapEntityToDto(Room room) {
        var roomDto = _roomMapper.mapRoomToRoomDTO(room);

        return roomDto;
    }

    // TODO: Add business creation validation for Room
    @Override
    public CompletableFuture<Void> validateCreationBusiness(RoomCreationDto roomCreationDto) {
        return super.validateCreationBusiness(roomCreationDto);
    }

    // TODO: Add business update validation for Room
    @Override
    public CompletableFuture<Void> validateUpdateBusiness(RoomUpdateDto roomUpdateDto) {
        return super.validateUpdateBusiness(roomUpdateDto);
    }

}
