package com.easyhostel.backend.application.mapping.interfaces;

import com.easyhostel.backend.application.dto.manager.ManagerCreationDto;
import com.easyhostel.backend.application.dto.manager.ManagerDto;
import com.easyhostel.backend.application.dto.manager.ManagerUpdateDto;
import com.easyhostel.backend.domain.entity.Manager;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Mapper for Manager entity
 *
 * @author Nyx
 */
@Mapper(componentModel = "spring")
public interface IManagerMapper {

    IManagerMapper MAPPER = Mappers.getMapper(IManagerMapper.class);

    // TODO: for mapping, need to consider the case of manager-role

    ManagerDto mapManagerToManagerDto(Manager manager);

    Manager mapManagerCreationDtoToManager(ManagerCreationDto managerCreationDto);

    Manager mapManagerUpdateDtoToManager(ManagerUpdateDto managerUpdateDto);

}
