package com.easyhostel.backend.domain.service.interfaces.permission;

import com.easyhostel.backend.domain.exception.DuplicatedDistinctRequiredValueException;
import com.easyhostel.backend.domain.exception.EntityNotFoundException;
import com.easyhostel.backend.domain.exception.UnauthorizedAccessException;
import com.easyhostel.backend.domain.service.interfaces.base.IBaseBusinessValidator;

/**
 * Interface for defining Permission's business validator
 *
 * @author Nyx
 */
public interface IPermissionBusinessValidator extends IBaseBusinessValidator {

    /**
     * Check is Permission existed by ID
     *
     * @param permissionId Permission's ID
     * @exception EntityNotFoundException If Permission or Interior not found
     * @author Nyx
     */
    void checkIfPermissionExistedById(Integer permissionId);

    /**
     * Check if Permission is accessible by authenticated user
     *
     * @param permissionId Permission's ID
     * @exception UnauthorizedAccessException Not accessible
     * @author Nyx
     */
    void checkIfPermissionAccessibleByAuthUser(Integer permissionId);

    /**
     * Check if new Permission has duplicated Permission's name
     *
     * @param permissionName Permission's name
     * @exception DuplicatedDistinctRequiredValueException Permission's name already existed
     * @author Nyx
     */
    void checkIfNewPermissionHasDuplicatedPermissionName(String permissionName);

    /**
     * Check if update Permission which change Permission's name to existed value
     *
     * @param permissionId Permission's ID
     * @param permissionName Permission's name
     * @exception DuplicatedDistinctRequiredValueException Permission's name already existed
     * @author Nyx
     */
    void checkIfUpdatePermissionHasDuplicatedPermissionName(Integer permissionId, String permissionName);

    /**
     * Check if Permission's name existed
     *
     * @param permissionName Permission's name
     * @exception DuplicatedDistinctRequiredValueException Permission's name already existed
     * @author Nyx
     */
    void checkIfPermissionNameExistedThrowException(String permissionName);

}
