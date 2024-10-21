package com.easyhostel.backend.domain.service.implementation.base;

import com.easyhostel.backend.application.service.interfaces.authentication.IAuthenticationService;
import com.easyhostel.backend.domain.entity.Manager;
import com.easyhostel.backend.domain.enums.ErrorCode;
import com.easyhostel.backend.domain.enums.RoleType;
import com.easyhostel.backend.domain.exception.UnauthorizedAccessException;
import com.easyhostel.backend.domain.service.interfaces.base.IBaseBusinessValidator;
import com.easyhostel.backend.infrastructure.configuration.Translator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

/**
 * Implementation for base's business validator
 *
 * @author Nyx
 */
@Service
@RequiredArgsConstructor
public abstract class BaseBusinessValidator implements IBaseBusinessValidator {

    private final IAuthenticationService _authenticationService;

    @Override
    public void checkIfAuthenticatedUserNotSysadminThrowException() {
        var isAuthenticatedUserSysadmin = checkIsAuthenticatedUserSysadmin();

        if (!isAuthenticatedUserSysadmin) {
            throw new UnauthorizedAccessException(
            Translator.toLocale("exception.notEnoughPrivilege"),
            ErrorCode.FORBIDDEN_ACCESS,
            HttpStatus.FORBIDDEN);
        }
    }

    @Override
    public boolean checkIsAuthenticatedUserSysadmin() {
        var userDetail = (Manager) _authenticationService.getAuthentication().getPrincipal();

        return userDetail.getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_" + RoleType.SYSADMIN.name()));
    }

}
