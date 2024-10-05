package com.easyhostel.backend.application.mapping.interfaces;

import com.easyhostel.backend.application.dto.contract.ContractCreationDto;
import com.easyhostel.backend.application.dto.contract.ContractDto;
import com.easyhostel.backend.application.dto.contract.ContractUpdateDto;
import com.easyhostel.backend.domain.entity.Contract;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Mapper for Contract entity
 *
 * @author Nyx
 */
@Mapper(componentModel = "spring")
public interface IContractMapper {

    IContractMapper MAPPER = Mappers.getMapper(IContractMapper.class);

    ContractDto mapContractToContractDto(Contract contract);

    Contract mapContractCreationDtoToContract(ContractCreationDto creationDto);

    Contract mapContractUpdateDtoToContract(ContractUpdateDto updateDto);

}
