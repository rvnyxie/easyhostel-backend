package com.easyhostel.backend.application.mapping.interfaces;

import com.easyhostel.backend.application.dto.roomamenity.RoomAmenityCreationDto;
import com.easyhostel.backend.application.dto.roomamenity.RoomAmenityDto;
import com.easyhostel.backend.application.dto.roomamenity.RoomAmenityUpdateDto;
import com.easyhostel.backend.domain.entity.RoomAmenity;
import org.mapstruct.Mapper;

/**
 * Mapper for RoomAmenity entity
 *
 * @author Nyx
 */
@Mapper(componentModel = "spring")
public interface IRoomAmenityMapper {

    //region General

    RoomAmenityDto mapRoomAmenityToRoomAmenityDto(RoomAmenity roomAmenity);

    //endregion

    //region Map creation

    RoomAmenity mapRoomAmenityCreationDtoToRoomAmenity(RoomAmenityCreationDto roomAmenityCreationDto);

    //endregion

    //region Map update

    RoomAmenity mapRoomAmenityUpdateDtoToRoomAmenity(RoomAmenityUpdateDto roomAmenityUpdateDto);

    //endregion

}
