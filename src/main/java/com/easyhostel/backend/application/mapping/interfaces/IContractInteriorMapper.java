package com.easyhostel.backend.application.mapping.interfaces;

import com.easyhostel.backend.application.dto.contractinterior.ContractInteriorCreationDto;
import com.easyhostel.backend.application.dto.contractinterior.ContractInteriorDto;
import com.easyhostel.backend.application.dto.contractinterior.ContractInteriorUpdateDto;
import com.easyhostel.backend.domain.entity.ContractInterior;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * Mapper for ContractInterior entity
 *
 * @author Nyx
 */
@Mapper(componentModel = "spring")
public interface IContractInteriorMapper {

    //region Instance

    IContractInteriorMapper MAPPER = Mappers.getMapper(IContractInteriorMapper.class);

    //endregion

    //region General

    @Mapping(source = "contractInterior.contractInteriorId.contractId", target = "contractId")
    @Mapping(source = "contractInterior.contractInteriorId.interiorId", target = "interiorId")
    ContractInteriorDto mapContractInteriorToContractInteriorDto(ContractInterior contractInterior);

    //endregion

    //region Map creation

    ContractInterior mapContractInteriorCreationDtoToContractInterior(ContractInteriorCreationDto contractInteriorCreationDto);

    //endregion

    //region Map update

    ContractInterior mapContractInteriorUpdateDtoToContractInterior(ContractInteriorUpdateDto contractInteriorUpdateDto);

    //endregion

}
