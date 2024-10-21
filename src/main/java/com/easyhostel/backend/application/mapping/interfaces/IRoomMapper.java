package com.easyhostel.backend.application.mapping.interfaces;

import com.easyhostel.backend.application.dto.contract.ContractCreationDto;
import com.easyhostel.backend.application.dto.contract.ContractDto;
import com.easyhostel.backend.application.dto.contract.ContractUpdateDto;
import com.easyhostel.backend.application.dto.house.HouseDto;
import com.easyhostel.backend.application.dto.room.RoomCreationDto;
import com.easyhostel.backend.application.dto.room.RoomDto;
import com.easyhostel.backend.application.dto.room.RoomUpdateDto;
import com.easyhostel.backend.domain.entity.Contract;
import com.easyhostel.backend.domain.entity.House;
import com.easyhostel.backend.domain.entity.Room;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Mapper for Room entity
 *
 * @author Nyx
 */
@Mapper(componentModel = "spring", uses = { IContractMapper.class })
public interface IRoomMapper {

    //region General

    @Mapping(target = "house", qualifiedByName = "mapHouseToHouseDtoWithoutRooms")
    @Mapping(target = "contracts", qualifiedByName = "mapContractToContractDtoWithoutRoom")
    RoomDto mapRoomToRoomDTO(Room room);

    //region Map House to HouseDto without Rooms to avoid circular dependency

    @Named("mapHouseToHouseDtoWithoutRooms")
    @Mapping(target = "rooms", ignore = true)
    HouseDto mapHouseToHouseDtoWithoutRooms(House house);

    //endregion

    //endregion

    //region Map creation

    @Named("mapRoomCreationDtoToRoom")
    @Mapping(target = "contracts", qualifiedByName = "mapContractCreationDtoToContract")
    Room mapRoomCreationDtoToRoom(RoomCreationDto roomCreationDto);

    //endregion

    //region Map update

    @Named("mapRoomUpdateDtoToRoom")
    @Mapping(target = "contracts", qualifiedByName = "mapContractUpdateDtoToContract")
    Room mapRoomUpdateDtoToRoom(RoomUpdateDto roomUpdateDto);

    //endregion

    //region Map Room to RoomDto without house to avoid circular dependency

    @Named("mapRoomToRoomDtoWithoutHouse")
    @Mapping(target = "house", ignore = true)
    RoomDto mapRoomToRoomDtoWithoutHouse(Room room);

    //endregion

    //region Set Contracts references back to Room after mapping from types (creation, update)

    @AfterMapping
    default void setContractsReferencesToRoom(@MappingTarget Room room) {
        if (room.getContracts() != null) {
            room.getContracts().forEach(contract -> contract.setRoom(room));
        }
    }

    //endregion

}
