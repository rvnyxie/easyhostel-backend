package com.easyhostel.backend.application.mapping.interfaces;

import com.easyhostel.backend.application.dto.roomservice.RoomAmenityCreationDto;
import com.easyhostel.backend.application.dto.roomservice.RoomAmenityDto;
import com.easyhostel.backend.application.dto.roomservice.RoomAmenityUpdateDto;
import com.easyhostel.backend.domain.entity.RoomAmenity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Mapper for RoomAmenity entity
 *
 * @author Nyx
 */
@Mapper(componentModel = "spring")
public interface IRoomAmenityMapper {

    IRoomAmenityMapper MAPPER = Mappers.getMapper(IRoomAmenityMapper.class);

    RoomAmenityDto mapRoomAmenityToRoomAmenityDto(RoomAmenity roomAmenity);

    RoomAmenity mapRoomAmenityCreationDtoToRoomAmenity(RoomAmenityCreationDto roomAmenityCreationDto);

    RoomAmenity mapRoomAmenityUpdateDtoToRoomAmenity(RoomAmenityUpdateDto roomAmenityUpdateDto);

}