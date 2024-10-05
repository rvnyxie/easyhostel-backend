package com.easyhostel.backend.application.service.implementations.room;

import com.easyhostel.backend.application.dto.room.RoomDto;
import com.easyhostel.backend.application.mapping.interfaces.IRoomMapper;
import com.easyhostel.backend.application.service.implementations.base.BaseReadonlyService;
import com.easyhostel.backend.application.service.interfaces.room.IRoomReadonlyService;
import com.easyhostel.backend.domain.entity.Room;
import com.easyhostel.backend.domain.repository.interfaces.base.IBaseReadonlyRepository;
import com.easyhostel.backend.domain.repository.interfaces.room.IRoomReadonlyRepository;
import com.easyhostel.backend.domain.service.interfaces.room.IRoomBusinessValidator;
import org.springframework.stereotype.Service;

/**
 * Room readonly service for implementing abstract methods and define dedicated methods
 *
 * @author Nyx
 */
@Service
public class RoomReadonlyService extends BaseReadonlyService<Room, RoomDto, String> implements IRoomReadonlyService {

    private final IRoomReadonlyRepository _roomReadonlyRepository;
    private final IRoomBusinessValidator _roomBusinessValidator;
    private final IRoomMapper _roomMapper;

    public RoomReadonlyService(IRoomReadonlyRepository roomReadonlyRepository, IRoomBusinessValidator roomBusinessValidator, IRoomMapper roomMapper) {
        super(roomReadonlyRepository);
        _roomReadonlyRepository = roomReadonlyRepository;
        _roomBusinessValidator = roomBusinessValidator;
        _roomMapper = roomMapper;
    }

    @Override
    public RoomDto mapEntityToDto(Room room) {
        var roomDto = _roomMapper.mapRoomToRoomDTO(room);

        return roomDto;
    }

}