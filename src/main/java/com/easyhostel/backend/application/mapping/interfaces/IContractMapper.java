package com.easyhostel.backend.application.mapping.interfaces;

import com.easyhostel.backend.application.dto.contract.ContractCreationDto;
import com.easyhostel.backend.application.dto.contract.ContractDto;
import com.easyhostel.backend.application.dto.contract.ContractUpdateDto;
import com.easyhostel.backend.application.dto.room.RoomDto;
import com.easyhostel.backend.domain.entity.Contract;
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

    //region Instance

    IContractMapper MAPPER = Mappers.getMapper(IContractMapper.class);

    //endregion

    //region General

    @Mapping(target = "room", qualifiedByName = "mapRoomToRoomDtoWithoutContracts")
    ContractDto mapContractToContractDto(Contract contract);

    //endregion

    //region Map Room to RoomDto without Contracts to avoid circular dependency

    @Named("mapRoomToRoomDtoWithoutContracts")
    default RoomDto mapRoomToRoomDtoWithoutContracts(Room room) {
        var roomDto = IRoomMapper.MAPPER.mapRoomToRoomDtoWithoutContracts(room);
        return roomDto;
    }

    //endregion

    //region Map creation

    Contract mapContractCreationDtoToContract(ContractCreationDto creationDto);

    //endregion

    //region Map update

    Contract mapContractUpdateDtoToContract(ContractUpdateDto updateDto);

    //endregion

    //region Map Contract to ContractDto without Room to avoid circular dependency

    @Mapping(target = "room", ignore = true)
    ContractDto mapContractToContractDtoWithoutRoom(Contract contract);

    //endregion

}
