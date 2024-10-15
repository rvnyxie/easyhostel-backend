package com.easyhostel.backend.application.mapping.interfaces;

import com.easyhostel.backend.application.dto.rolepermission.RolePermissionCreationDto;
import com.easyhostel.backend.application.dto.rolepermission.RolePermissionDto;
import com.easyhostel.backend.application.dto.rolepermission.RolePermissionUpdateDto;
import com.easyhostel.backend.domain.entity.RolePermission;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * Mapper for RolePermission entity
 *
 * @author Nyx
 */
@Mapper(componentModel = "spring")
public interface IRolePermissionMapper {

    //region Instance

    IRolePermissionMapper MAPPER = Mappers.getMapper(IRolePermissionMapper.class);

    //endregion

    //region General

    @Mapping(source = "rolePermission.rolePermissionId.roleId", target = "roleId")
    @Mapping(source = "rolePermission.rolePermissionId.permissionId", target = "permissionId")
    RolePermissionDto mapRolePermissionToRolePermissionDto(RolePermission rolePermission);

    //endregion

    //region Map creation

    RolePermission mapRolePermissionCreationDtoToRolePermission(RolePermissionCreationDto rolePermissionCreationDto);

    //endregion

    //region Map update

    RolePermission mapRolePermissionUpdateDtoToRolePermission(RolePermissionUpdateDto rolePermissionUpdateDto);

    //endregion

}
