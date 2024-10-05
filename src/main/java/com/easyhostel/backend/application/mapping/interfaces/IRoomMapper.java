package com.easyhostel.backend.application.mapping.interfaces;

import com.easyhostel.backend.application.dto.room.RoomCreationDto;
import com.easyhostel.backend.application.dto.room.RoomDto;
import com.easyhostel.backend.application.dto.room.RoomUpdateDto;
import com.easyhostel.backend.domain.entity.Room;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Mapper for Room entity
 *
 * @author Nyx
 */
@Mapper(componentModel = "spring")
public interface IRoomMapper {

    IRoomMapper MAPPER = Mappers.getMapper(IRoomMapper.class);

    RoomDto mapRoomToRoomDTO(Room room);

    Room mapRoomCreationDtoToRoom(RoomCreationDto roomCreationDto);

    Room mapRoomUpdateDtoToRoom(RoomUpdateDto roomUpdateDto);

}
