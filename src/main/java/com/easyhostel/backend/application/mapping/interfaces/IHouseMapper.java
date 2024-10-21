package com.easyhostel.backend.application.mapping.interfaces;

import com.easyhostel.backend.application.dto.house.HouseCreationDto;
import com.easyhostel.backend.application.dto.house.HouseDto;
import com.easyhostel.backend.application.dto.house.HouseUpdateDto;
import com.easyhostel.backend.application.dto.room.RoomCreationDto;
import com.easyhostel.backend.application.dto.room.RoomDto;
import com.easyhostel.backend.application.dto.room.RoomUpdateDto;
import com.easyhostel.backend.domain.entity.House;
import com.easyhostel.backend.domain.entity.Room;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Mapper for House entity
 *
 * @author Nyx
 */
@Mapper(componentModel = "spring", uses = { IRoomMapper.class })
public interface IHouseMapper {

    //region General

    @Mapping(target = "rooms", qualifiedByName = "mapRoomToRoomDtoWithoutHouse")
    HouseDto mapHouseToHouseDto(House house);

    //endregion

    //region Map creation

    @Mapping(target = "rooms", qualifiedByName = "mapRoomCreationDtoToRoom")
    House mapHouseCreationDtoToHouse(HouseCreationDto houseCreationDto);

    //endregion

    //region Map update

    @Mapping(target = "rooms", qualifiedByName = "mapRoomUpdateDtoToRoom")
    House mapHouseUpdateDtoToHouse(HouseUpdateDto houseUpdateDto);

    //endregion

    //region Set Room references back to House after mapping from types (creation, update)

    @AfterMapping
    default void setHouseReferencesFromRooms(@MappingTarget House house) {
        // Ensure each room references the house
        if (house.getRooms() != null) {
            house.getRooms().forEach(room -> room.setHouse(house));
        }
    }

    //endregion

}
