package com.easyhostel.backend.application.mapping.interfaces;

import com.easyhostel.backend.application.dto.role.RoleCreationDto;
import com.easyhostel.backend.application.dto.role.RoleDto;
import com.easyhostel.backend.application.dto.role.RoleUpdateDto;
import com.easyhostel.backend.domain.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for Role entity
 *
 * @author Nyx
 */
@Mapper(componentModel = "spring", uses = { IManagerMapper.class })
public interface IRoleMapper {

    //region General

    @Mapping(target = "managers", qualifiedByName = "mapManagerToManagerDtoWithoutRole")
    RoleDto mapRoleToRoleDto(Role role);

    //endregion

    //region Map creation

    Role mapRoleCreationDtoToRole(RoleCreationDto roleCreationDto);

    //endregion

    //region Map update

    Role mapRoleUpdateDtoToRole(RoleUpdateDto roleUpdateDto);

    //endregion
}
