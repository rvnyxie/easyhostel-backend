package com.easyhostel.backend.application.mapping.interfaces;

import com.easyhostel.backend.application.dto.manager.ManagerCreationDto;
import com.easyhostel.backend.application.dto.manager.ManagerDto;
import com.easyhostel.backend.application.dto.manager.ManagerUpdateDto;
import com.easyhostel.backend.domain.entity.Manager;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

/**
 * Mapper for Manager entity
 *
 * @author Nyx
 */
@Mapper(componentModel = "spring")
public interface IManagerMapper {

    //region Instance

    IManagerMapper MAPPER = Mappers.getMapper(IManagerMapper.class);

    //endregion

    //region General

    ManagerDto mapManagerToManagerDto(Manager manager);

    Manager mapManagerDtoToManager(ManagerDto managerDto);

    //endregion

    //region Map creation

    Manager mapManagerCreationDtoToManager(ManagerCreationDto managerCreationDto);

    //endregion

    //region Map update

    Manager mapManagerUpdateDtoToManager(ManagerUpdateDto managerUpdateDto);

    //endregion

    //region Map Manager to ManagerDto without Role

    @Named("mapManagerToManagerDtoWithoutRole")
    @Mapping(target = "role", ignore = true)
    ManagerDto mapManagerToManagerDtoWithoutRole(Manager manager);

    //endregion

}
