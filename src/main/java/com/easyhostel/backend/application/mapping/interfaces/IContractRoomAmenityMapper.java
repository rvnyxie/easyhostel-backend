package com.easyhostel.backend.application.mapping.interfaces;

import com.easyhostel.backend.application.dto.contractroomamenity.ContractRoomAmenityCreationDto;
import com.easyhostel.backend.application.dto.contractroomamenity.ContractRoomAmenityDto;
import com.easyhostel.backend.application.dto.contractroomamenity.ContractRoomAmenityUpdateDto;
import com.easyhostel.backend.domain.entity.ContractRoomAmenity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * Mapper for ContractRoomAmenity entity
 *
 * @author Nyx
 */
@Mapper(componentModel = "spring")
public interface IContractRoomAmenityMapper {

    //region General

    // Mapstruct does not automatically map embedded IDs, so we map them manually
    @Mapping(source = "contractRoomAmenity.contractRoomAmenityId.contractId", target = "contractId")
    @Mapping(source = "contractRoomAmenity.contractRoomAmenityId.roomAmenityId", target = "roomAmenityId")
    ContractRoomAmenityDto mapContractRoomAmenityToContractRoomAmenityDto(ContractRoomAmenity contractRoomAmenity);

    //endregion

    //region Map creation

    ContractRoomAmenity mapContractRoomAmenityCreationDtoToContractRoomAmenity(ContractRoomAmenityCreationDto contractRoomAmenityCreationDto);

    //endregion

    //region Map update

    ContractRoomAmenity mapContractRoomAmenityUpdateDtoToContractRoomAmenity(ContractRoomAmenityUpdateDto contractRoomAmenityUpdateDto);

    //endregion

}
