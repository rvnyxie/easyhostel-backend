package com.easyhostel.backend.domain.service.interfaces.rolepermission;

import com.easyhostel.backend.domain.entity.embedded.RolePermissionId;
import com.easyhostel.backend.domain.exception.DuplicatedDistinctRequiredValueException;
import com.easyhostel.backend.domain.exception.EntityNotFoundException;
import com.easyhostel.backend.domain.exception.UnauthorizedAccessException;
import com.easyhostel.backend.domain.service.interfaces.base.IBaseBusinessValidator;

/**
 * Interface for defining RolePermission's business validator
 *
 * @author Nyx
 */
public interface IRolePermissionBusinessValidator extends IBaseBusinessValidator {

    /**
     * Check if Role and Permission existed by IDs
     *
     * @param roleId Role's ID
     * @param permissionId Permission's ID
     * @exception EntityNotFoundException If Role or Permission not found
     * @author Nyx
     */
    void checkIfRoleAndPermissionExistedByIds(Integer roleId, Integer permissionId);

    /**
     * Check if RolePermission existed by ID
     *
     * @param rolePermissionId RolePermissionId Object
     * @exception EntityNotFoundException If RolePermission not found
     * @author Nyx
     */
    void checkIfRolePermissionExistedById(RolePermissionId rolePermissionId);

    /**
     * Check if RolePermission accessible by authenticated user
     *
     * @param rolePermissionId RolePermission's ID
     * @exception UnauthorizedAccessException If RolePermission not accessible by authenticated user
     * @author Nyx
     */
    void checkIfRolePermissionAccessibleByAuthUser(RolePermissionId rolePermissionId);

    /**
     * Check if RolePermission existed by ID
     *
     * @param rolePermissionId RolePermission's ID
     * @exception DuplicatedDistinctRequiredValueException If RolePermission already existed
     * @author Nyx
     */
    void checkIfRolePermissionExistedThrowException(RolePermissionId rolePermissionId);

}
