package com.easyhostel.backend.domain.service.interfaces.rolepermission;

import com.easyhostel.backend.domain.entity.embedded.RolePermissionId;

/**
 * Interface for defining RolePermission's business validator
 *
 * @author Nyx
 */
public interface IRolePermissionBusinessValidator {

    /**
     * Check if Role and Permission existed by IDs
     *
     * @param roleId Role's ID
     * @param permissionId Permission's ID
     * @exception com.easyhostel.backend.domain.exception.EntityNotFoundException If Role or Permission not found
     * @author Nyx
     */
    void checkIfRoleAndPermissionExistedByIds(Integer roleId, Integer permissionId);

    /**
     * Check if RolePermission existed by ID
     *
     * @param rolePermissionId RolePermissionId Object
     * @exception com.easyhostel.backend.domain.exception.EntityNotFoundException If Role or Permission not found
     * @author Nyx
     */
    void checkIfRolePermissionExistedById(RolePermissionId rolePermissionId);

}
