package com.easyhostel.backend.domain.service.interfaces.manager;

import com.easyhostel.backend.domain.exception.DuplicatedDistinctRequiredValueException;
import com.easyhostel.backend.domain.exception.EntityNotFoundException;
import com.easyhostel.backend.domain.exception.UnauthorizedAccessException;
import com.easyhostel.backend.domain.service.interfaces.base.IBaseBusinessValidator;

/**
 * Interface for defining Manager's business validator
 *
 * @author Nyx
 */
public interface IManagerBusinessValidator extends IBaseBusinessValidator {

    /**
     * Check is Manager existed by ID
     *
     * @param managerId Manager's ID
     * @exception EntityNotFoundException If Manager not found
     * @author Nyx
     */
    void checkIfManagerExistedById(String managerId);

    /**
     * Check is Manager existed by username
     *
     * @param username Manager's username
     * @exception EntityNotFoundException If Manager not found
     * @author Nyx
     */
    void checkIfManagerUsernameExisted(String username);

    /**
     * Check is Manager's username existed
     *
     * @param username Manager's username
     * @exception DuplicatedDistinctRequiredValueException If username value already existed
     * @author Nyx
     */
    void checkIfUsernameExistedThenThrowException(String username);

    /**
     * Check is Manager's username existed
     *
     * @param email Manager's email
     * @exception DuplicatedDistinctRequiredValueException If email value already existed
     * @author Nyx
     */
    void checkIfEmailExistedThenThrowException(String email);

    /**
     * Check if Email is taken by provided Manager
     *
     * @param managerId Manager's ID
     * @param email Manager's email
     * @exception DuplicatedDistinctRequiredValueException If email value already taken by other Manager
     * @author Nyx
     */
    void checkIfEmailNotTakenByManagerThenThrowException(String managerId, String email);

    /**
     * Check if Manager being managed by authenticated user
     *
     * @param managerId Manager's ID
     * @exception UnauthorizedAccessException If Manager not manageable by authenticated user
     * @author Nyx
     */
    void checkIfManagerManagedByAuthUser(String managerId);

}
