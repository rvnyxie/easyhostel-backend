package com.easyhostel.backend.domain.service.interfaces.permission;

/**
 * Interface for defining Permission's business validator
 *
 * @author Nyx
 */
public interface IPermissionBusinessValidator {

    /**
     * Check is Permission existed by ID
     *
     * @param permissionId Permission's ID
     * @exception com.easyhostel.backend.domain.exception.EntityNotFoundException If Permission or Interior not found
     * @author Nyx
     */
    void checkIfPermissionExistedById(Integer permissionId);

}
