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
    @Mapping(target = "rooms", qualifiedByName = "mapRoomsToRoomDtosWithoutHouse")
    HouseDto mapHouseToHouseDto(House house);

    //endregion

    //region Map Room to RoomDto without house to avoid circular dependency

    @Named("mapRoomsToRoomDtosWithoutHouse")
    @Mapping(target = "house", ignore = true)
    default Set<RoomDto> mapRoomsToRoomDtosWithoutHouse(Set<Room> rooms) {
        return rooms.stream().map(room -> {
            RoomDto roomDto = IRoomMapper.MAPPER.mapRoomToRoomDtoWithoutHouse(room);
            return roomDto;
        }).collect(Collectors.toSet());
    }

    // endregion

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

    //region Map House to HouseDto without Rooms to avoid circular dependency

    @Mapping(target = "rooms", ignore = true)
    HouseDto mapHouseToHouseDtoWithoutRooms(House house);

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
