package com.easyhostel.backend.domain.service.interfaces.base;

import com.easyhostel.backend.domain.exception.UnauthorizedAccessException;

import java.util.concurrent.CompletableFuture;

/**
 * Interface for defining base's business validator
 *
 * @author Nyx
 */
public interface IBaseBusinessValidator {

    /**
     * Check if authenticated user not SYSADMIN
     *
     * @exception UnauthorizedAccessException Not SYSADMIN
     * @author Nyx
     */

    void checkIfAuthenticatedUserNotSysadminThrowException();

    /**
     * Check is authenticated user SYSADMIN
     *
     * @return true if SYSADMIN, false if not
     * @author Nyx
     */
    boolean checkIsAuthenticatedUserSysadmin();

    /**
     * Check if authenticated user is ADMIN
     *
     * @exception UnauthorizedAccessException Not ADMIN
     * @author Nyx
     */
    void checkIfAuthenticatedUserNotAdminThrowException();

    /**
     * Check is authenticated user ADMIN
     *
     * @return true if ADMIN, false if not
     * @author Nyx
     */
    boolean checkIsAuthenticatedUserAdmin();

}
