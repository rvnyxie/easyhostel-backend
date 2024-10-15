package com.easyhostel.backend.domain.service.interfaces.role;

/**
 * Interface for defining Role's business validator
 *
 * @author Nyx
 */
public interface IRoleBusinessValidator {

    /**
     * Check is Role existed by ID
     *
     * @param roleId Role's ID
     * @exception com.easyhostel.backend.domain.exception.EntityNotFoundException If Role or Interior not found
     * @author Nyx
     */
    void checkIfRoleExistedById(Integer roleId);

}
