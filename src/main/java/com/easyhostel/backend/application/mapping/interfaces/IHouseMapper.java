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
@Mapper(componentModel = "spring")
public interface IHouseMapper {

    //region Instance

    IHouseMapper MAPPER = Mappers.getMapper(IHouseMapper.class);

    //endregion

    //region General

    // Room need to ignore house to avoid circular dependency
    @Mapping(target = "rooms", qualifiedByName = "mapRoomsWithoutHouse")
    HouseDto mapHouseToHouseDto(House house);

    //endregion

    //region Map creation

    @Mapping(target = "rooms", qualifiedByName = "mapRoomCreationDtosToRooms")
    House mapHouseCreationDtoToHouse(HouseCreationDto houseCreationDto);

    @Named("mapRoomCreationDtosToRooms")
    Set<Room> mapRoomCreationDtosToRooms(Set<RoomCreationDto> roomCreationDto);

    //endregion

    //region Map update

    @Mapping(target = "rooms", qualifiedByName = "mapRoomUpdateDtosToRooms")
    House mapHouseUpdateDtoToHouse(HouseUpdateDto houseUpdateDto);

    @Named("mapRoomUpdateDtosToRooms")
    Set<Room> mapRoomUpdateDtosToRooms(Set<RoomUpdateDto> roomUpdateDtos);

    //endregion

    //region Map Room to RoomDto without house to avoid circular dependency

    @Named("mapRoomsWithoutHouse")
    @Mapping(target = "house", ignore = true)
    default Set<RoomDto> mapRoomsWithoutHouse(Set<Room> rooms) {
        return rooms.stream().map(room -> {
            RoomDto roomDto = mapRoomToRoomDtoWithoutHouse(room);
            return roomDto;
        }).collect(Collectors.toSet());
    }

    @Mapping(target = "house", ignore = true)
    RoomDto mapRoomToRoomDtoWithoutHouse(Room room);

    // endregion

    //region Set room references back to house after mapping from types (creation, update) to House

    @AfterMapping
    default void setHouseReferencesFromRooms(@MappingTarget House house) {
        // Ensure each room references the house
        if (house.getRooms() != null) {
            house.getRooms().forEach(room -> room.setHouse(house));
        }
    }

    //endregion

}
