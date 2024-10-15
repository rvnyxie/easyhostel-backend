package com.easyhostel.backend.application.mapping.interfaces;

import com.easyhostel.backend.application.dto.role.RoleCreationDto;
import com.easyhostel.backend.application.dto.role.RoleDto;
import com.easyhostel.backend.application.dto.role.RoleUpdateDto;
import com.easyhostel.backend.domain.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Mapper for Role entity
 *
 * @author Nyx
 */
@Mapper(componentModel = "spring")
public interface IRoleMapper {

    //region Instance

    IRoleMapper MAPPER = Mappers.getMapper(IRoleMapper.class);

    //endregion

    //region General

    RoleDto mapRoleToRoleDto(Role role);

    //endregion

    //region Map creation

    Role mapRoleCreationDtoToRole(RoleCreationDto roleCreationDto);

    //endregion

    //region Map update

    Role mapRoleUpdateDtoToRole(RoleUpdateDto roleUpdateDto);

    //endregion
}
