package com.easyhostel.backend.application.mapping.interfaces;

import com.easyhostel.backend.application.dto.contract.ContractCreationDto;
import com.easyhostel.backend.application.dto.contract.ContractDto;
import com.easyhostel.backend.application.dto.contract.ContractUpdateDto;
import com.easyhostel.backend.application.dto.house.HouseDto;
import com.easyhostel.backend.application.dto.room.RoomDto;
import com.easyhostel.backend.domain.entity.Contract;
import com.easyhostel.backend.domain.entity.House;
import com.easyhostel.backend.domain.entity.Room;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

/**
 * Mapper for Contract entity
 *
 * @author Nyx
 */
@Mapper(componentModel = "spring")
public interface IContractMapper {

    //region General

    @Mapping(target = "room", qualifiedByName = "mapRoomToRoomDtoWithoutContractsAndHouseToHouseDtoWithoutRoom")
    ContractDto mapContractToContractDto(Contract contract);

    //endregion

    //region Map Room to RoomDto without Contracts to avoid circular dependency

    @Named("mapRoomToRoomDtoWithoutContractsAndHouseToHouseDtoWithoutRoom")
    @Mapping(target = "contracts", ignore = true)
    @Mapping(target = "house", qualifiedByName = "mapHouseToHouseDtoWithoutRoom")
    RoomDto mapRoomToRoomDtoWithoutContracts(Room room);

    //endregion

    //region Map House to HouseDto without Room

    @Named("mapHouseToHouseDtoWithoutRoom")
    @Mapping(target = "rooms", ignore = true)
    HouseDto mapHouseToHouseDtoWithoutRoom(House house);

    //endregion

    //region Map creation

    @Named("mapContractCreationDtoToContract")
    Contract mapContractCreationDtoToContract(ContractCreationDto creationDto);

    //endregion

    //region Map update

    @Named("mapContractUpdateDtoToContract")
    Contract mapContractUpdateDtoToContract(ContractUpdateDto updateDto);

    //endregion

    //region Map Contract to ContractDto without Room to avoid circular dependency

    @Named("mapContractToContractDtoWithoutRoom")
    @Mapping(target = "room", ignore = true)
    ContractDto mapContractToContractDtoWithoutRoom(Contract contract);

    //endregion

}
