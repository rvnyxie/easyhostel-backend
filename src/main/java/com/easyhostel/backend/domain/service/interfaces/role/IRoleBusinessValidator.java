package com.easyhostel.backend.domain.service.interfaces.role;

import com.easyhostel.backend.domain.exception.DuplicatedDistinctRequiredValueException;
import com.easyhostel.backend.domain.exception.EntityNotFoundException;
import com.easyhostel.backend.domain.exception.UnauthorizedAccessException;
import com.easyhostel.backend.domain.service.interfaces.base.IBaseBusinessValidator;

/**
 * Interface for defining Role's business validator
 *
 * @author Nyx
 */
public interface IRoleBusinessValidator extends IBaseBusinessValidator {

    /**
     * Check is Role existed by ID
     *
     * @param roleId Role's ID
     * @exception EntityNotFoundException If Role or Interior not found
     * @author Nyx
     */
    void checkIfRoleExistedById(Integer roleId);

    /**
     * Check if Role's name already existed
     * @param roleName Role's name value
     * @exception DuplicatedDistinctRequiredValueException If Role's name already existed
     * @author Nyx
     */
    void checkIfRoleNameExistedThenThrowException(String roleName);

    /**
     * Check if Role's name unchanged or changed to non-existed value
     *
     * @param roleId Role's ID
     * @param roleName Role's name
     * @exception DuplicatedDistinctRequiredValueException If new Role's name already existed
     * @author Nyx
     */
    void checkIfRoleNameUnchangedOrChangedToNonExistedValue(Integer roleId, String roleName);

    /**
     * Check if authenticated user can access Role
     *
     * @param roleId Role's ID
     * @exception UnauthorizedAccessException Not accessible by authenticated user
     * @author Nyx
     */
    void checkIfRoleAccessibleByAuthUser(Integer roleId);

}
