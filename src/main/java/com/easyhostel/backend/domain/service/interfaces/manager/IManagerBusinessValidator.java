package com.easyhostel.backend.domain.service.interfaces.manager;

/**
 * Interface for defining Manager's business validator
 *
 * @author Nyx
 */
public interface IManagerBusinessValidator {

    /**
     * Check is Manager existed by ID
     *
     * @param managerId Manager's ID
     * @exception com.easyhostel.backend.domain.exception.EntityNotFoundException If Role or Interior not found
     * @author Nyx
     */
    void checkIfManagerExistedById(String managerId);

}
