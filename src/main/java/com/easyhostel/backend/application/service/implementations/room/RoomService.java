package com.easyhostel.backend.application.service.implementations.room;

import com.easyhostel.backend.application.dto.room.RoomCreationDto;
import com.easyhostel.backend.application.dto.room.RoomDto;
import com.easyhostel.backend.application.dto.room.RoomUpdateDto;
import com.easyhostel.backend.application.mapping.interfaces.IRoomMapper;
import com.easyhostel.backend.application.service.implementations.base.BaseService;
import com.easyhostel.backend.application.service.interfaces.room.IRoomService;
import com.easyhostel.backend.domain.entity.Room;
import com.easyhostel.backend.domain.repository.interfaces.room.IRoomRepository;
import com.easyhostel.backend.domain.service.interfaces.room.IRoomBusinessValidator;
import org.springframework.stereotype.Service;

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

    public RoomService(IRoomRepository roomRepository, IRoomBusinessValidator roomBusinessValidator, IRoomMapper roomMapper) {
        super(roomRepository);
        _roomRepository = roomRepository;
        _roomBusinessValidator = roomBusinessValidator;
        _roomMapper = roomMapper;
    }

    // TODO: Add logic to delete a room belonged to a house
    // TODO: Add logic to delete many rooms belonged to a house

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
