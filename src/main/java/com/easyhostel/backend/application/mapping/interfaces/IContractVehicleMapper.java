package com.easyhostel.backend.application.mapping.interfaces;

import com.easyhostel.backend.application.dto.contractvehicle.ContractVehicleCreationDto;
import com.easyhostel.backend.application.dto.contractvehicle.ContractVehicleDto;
import com.easyhostel.backend.application.dto.contractvehicle.ContractVehicleUpdateDto;
import com.easyhostel.backend.domain.entity.ContractVehicle;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface IContractVehicleMapper {

    //region Instance

    IContractVehicleMapper MAPPER = Mappers.getMapper(IContractVehicleMapper.class);

    //endregion

    //region General

    // Mapstruct does not automatically map embedded IDs, so we map them manually
    @Mapping(source = "contractVehicle.contractVehicleId.contractId", target = "contractId")
    @Mapping(source = "contractVehicle.contractVehicleId.vehicleId", target = "vehicleId")
    ContractVehicleDto mapContractVehicleToContractVehicleDto(ContractVehicle contractVehicle);

    //endregion

    //region Map creation

    ContractVehicle mapContractVehicleCreationDtoToContractVehicle(ContractVehicleCreationDto contractVehicleCreationDto);

    //endregion

    //region Map update

    ContractVehicle mapContractVehicleUpdateDtoToContractVehicle(ContractVehicleUpdateDto contractVehicleUpdateDto);

    //endregion

}
