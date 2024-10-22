package com.easyhostel.backend.domain.service.implementation.role;

import com.easyhostel.backend.application.service.interfaces.authentication.IAuthenticationService;
import com.easyhostel.backend.domain.entity.Manager;
import com.easyhostel.backend.domain.entity.Role;
import com.easyhostel.backend.domain.enums.ErrorCode;
import com.easyhostel.backend.domain.exception.DuplicatedDistinctRequiredValueException;
import com.easyhostel.backend.domain.exception.EntityNotFoundException;
import com.easyhostel.backend.domain.exception.UnauthorizedAccessException;
import com.easyhostel.backend.domain.repository.interfaces.role.IRoleRepository;
import com.easyhostel.backend.domain.service.implementation.base.BaseBusinessValidator;
import com.easyhostel.backend.domain.service.interfaces.role.IRoleBusinessValidator;
import com.easyhostel.backend.infrastructure.configuration.Translator;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

/**
 * Implementation for Role's business validator
 *
 * @author Nyx
 */
@Service
public class RoleBusinessValidator extends BaseBusinessValidator implements IRoleBusinessValidator {

    private final IAuthenticationService _authenticationService;
    private final IRoleRepository _roleRepository;

    public RoleBusinessValidator(IAuthenticationService authenticationService,
                                 IRoleRepository roleRepository) {
        super(authenticationService);
        _authenticationService = authenticationService;
        _roleRepository = roleRepository;
    }

    @Override
    public void checkIfRoleExistedById(Integer roleId) {

        var isRoleExisted = _roleRepository.existsById(roleId);

        if (!isRoleExisted) {
            throw new EntityNotFoundException(
                    Translator.toLocale("exception.role.notFound"),
                    ErrorCode.ENTITY_NOT_FOUND,
                    HttpStatus.NOT_FOUND
            );
        }
    }

    @Override
    public void checkIfRoleNameExistedThenThrowException(String roleName) {
        var isRoleNameExisted = _roleRepository.existsByRoleName(roleName);

        if (isRoleNameExisted) {
            throw new DuplicatedDistinctRequiredValueException(
                    Translator.toLocale("exception.roleName.duplicated"),
                    ErrorCode.DUPLICATED_VALUE,
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    @Override
    public void checkIfRoleNameUnchangedOrChangedToNonExistedValue(Integer roleId, String roleName) {
        var role = _roleRepository.findById(roleId).orElseThrow();

        if (!role.getRoleName().equals(roleName)) {
            checkIfRoleNameExistedThenThrowException(roleName);
        }
    }

    @Override
    public void checkIfRoleAccessibleByAuthUser(Integer roleId) {
        var currentAuthUser = (Manager) _authenticationService.getAuthentication().getPrincipal();

        var isRoleAccessibleByAuthUser = currentAuthUser.getRole().getRoleId().equals(roleId);

        if (!isRoleAccessibleByAuthUser) {
            throw new UnauthorizedAccessException(
                    Translator.toLocale("exception.roleNotAccessibleByManager"),
                    ErrorCode.FORBIDDEN_ACCESS,
                    HttpStatus.FORBIDDEN
            );
        }
    }
}
