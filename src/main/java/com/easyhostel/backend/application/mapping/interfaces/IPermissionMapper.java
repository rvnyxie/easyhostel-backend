package com.easyhostel.backend.application.mapping.interfaces;

import com.easyhostel.backend.application.dto.permission.PermissionCreationDto;
import com.easyhostel.backend.application.dto.permission.PermissionDto;
import com.easyhostel.backend.application.dto.permission.PermissionUpdateDto;
import com.easyhostel.backend.application.dto.role.RoleCreationDto;
import com.easyhostel.backend.application.dto.role.RoleDto;
import com.easyhostel.backend.application.dto.role.RoleUpdateDto;
import com.easyhostel.backend.domain.entity.Permission;
import com.easyhostel.backend.domain.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Mapper for Permission entity
 *
 * @author Nyx
 */
@Mapper(componentModel = "spring")
public interface IPermissionMapper {

    //region Instance

    IPermissionMapper MAPPER = Mappers.getMapper(IPermissionMapper.class);

    //endregion

    //region General

    PermissionDto mapPermissionToPermissionDto(Permission permission);

    //endregion

    //region Map creation

    Permission mapPermissionCreationDtoToPermission(PermissionCreationDto permissionCreationDto);

    //endregion

    //region Map update

    Permission mapPermissionUpdateDtoToPermission(PermissionUpdateDto permissionUpdateDto);

    //endregion

}
