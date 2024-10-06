package com.easyhostel.backend.application.mapping.interfaces;

import com.easyhostel.backend.application.dto.house.HouseDto;
import com.easyhostel.backend.application.dto.room.RoomCreationDto;
import com.easyhostel.backend.application.dto.room.RoomDto;
import com.easyhostel.backend.application.dto.room.RoomUpdateDto;
import com.easyhostel.backend.domain.entity.House;
import com.easyhostel.backend.domain.entity.Room;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

/**
 * Mapper for Room entity
 *
 * @author Nyx
 */
@Mapper(componentModel = "spring")
public interface IRoomMapper {

    //region Instance

    IRoomMapper MAPPER = Mappers.getMapper(IRoomMapper.class);

    //endregion

    //region General

    // House need to ignore rooms to avoid circular dependency
    @Mapping(target = "house", qualifiedByName = "mapHouseWithoutRooms")
    RoomDto mapRoomToRoomDTO(Room room);

    //endregion

    //region Map creation, update

    Room mapRoomCreationDtoToRoom(RoomCreationDto roomCreationDto);

    Room mapRoomUpdateDtoToRoom(RoomUpdateDto roomUpdateDto);

    //endregion

    //region Map House to HouseDto without rooms to avoid circular dependency

    @Named("mapHouseWithoutRooms")
    default HouseDto mapHouseWithoutRooms(House house) {
        HouseDto houseDto = mapHouseToHouseDtoWithoutRooms(house);
        return houseDto;
    }

    @Mapping(target = "rooms", ignore = true)
    HouseDto mapHouseToHouseDtoWithoutRooms(House house);

    //endregion to

}
