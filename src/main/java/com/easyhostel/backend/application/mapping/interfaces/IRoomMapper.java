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
@Mapper(componentModel = "spring")
public interface IRoomMapper {

    //region Instance

    IRoomMapper MAPPER = Mappers.getMapper(IRoomMapper.class);

    //endregion

    //region General

    // House need to ignore rooms to avoid circular dependency
    @Mapping(target = "house", qualifiedByName = "mapHouseToHouseDtoWithoutRooms")
    // Contracts need to ignore Room to avoid circular dependency
    @Mapping(target = "contracts", qualifiedByName = "mapContractsToContractDtosWithoutRoom")
    RoomDto mapRoomToRoomDTO(Room room);

    //endregion

    //region Map House to HouseDto without rooms to avoid circular dependency

    @Named("mapHouseToHouseDtoWithoutRooms")
    default HouseDto mapHouseToHouseDtoWithoutRooms(House house) {
        HouseDto houseDto = IHouseMapper.MAPPER.mapHouseToHouseDtoWithoutRooms(house);
        return houseDto;
    }

    //endregion

    //region Map Contract to ContractDto without Room to avoid circular dependency

    @Named("mapContractsToContractDtosWithoutRoom")
    default Set<ContractDto> mapContractsToContractDtosWithoutRoom(Set<Contract> contracts) {
        return contracts.stream().map(contract -> {
            ContractDto contractDto = IContractMapper.MAPPER.mapContractToContractDtoWithoutRoom(contract);
            return contractDto;
        }).collect(Collectors.toSet());
    }

    //endregion

    //region Map creation

    @Mapping(target = "contracts", qualifiedByName = "mapContractCreationDtosToContracts")
    Room mapRoomCreationDtoToRoom(RoomCreationDto roomCreationDto);

    @Named("mapContractCreationDtosToContracts")
    Set<Contract> mapContractCreationDtosToContracts(Set<ContractCreationDto> contractCreationDtos);

    //endregion

    //region Map update

    @Mapping(target = "contracts", qualifiedByName = "mapContractUpdateDtosToContracts")
    Room mapRoomUpdateDtoToRoom(RoomUpdateDto roomUpdateDto);

    @Named("mapContractUpdateDtosToContracts")
    Set<Contract> mapContractUpdateDtosToContracts(Set<ContractUpdateDto> contractUpdateDtos);

    //endregion

    //region Map Room to RoomDto without house to avoid circular dependency

    @Named("mapRoomToRoomDtoWithoutHouse")
    @Mapping(target = "house", ignore = true)
    RoomDto mapRoomToRoomDtoWithoutHouse(Room room);

    //endregion

    //region Map Room to RoomDto without Contracts to avoid circular dependency

    @Named("mapRoomToRoomDtoWithoutContracts")
    @Mapping(target = "contracts", ignore = true)
    RoomDto mapRoomToRoomDtoWithoutContracts(Room room);

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
