package com.easyhostel.backend.domain.service.implementation.permission;

import com.easyhostel.backend.application.service.interfaces.authentication.IAuthenticationService;
import com.easyhostel.backend.domain.entity.Manager;
import com.easyhostel.backend.domain.enums.ErrorCode;
import com.easyhostel.backend.domain.exception.DuplicatedDistinctRequiredValueException;
import com.easyhostel.backend.domain.exception.EntityNotFoundException;
import com.easyhostel.backend.domain.exception.UnauthorizedAccessException;
import com.easyhostel.backend.domain.repository.interfaces.permission.IPermissionRepository;
import com.easyhostel.backend.domain.service.implementation.base.BaseBusinessValidator;
import com.easyhostel.backend.domain.service.interfaces.permission.IPermissionBusinessValidator;
import com.easyhostel.backend.infrastructure.configuration.Translator;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

/**
 * Implementation for Permission's business validator
 *
 * @author Nyx
 */
@Service
public class PermissionBusinessValidator extends BaseBusinessValidator implements IPermissionBusinessValidator {

    private final IAuthenticationService _authenticationService;
    private final IPermissionRepository _permissionRepository;

    public PermissionBusinessValidator(IAuthenticationService authenticationService,
                                       IPermissionRepository permissionRepository) {
        super(authenticationService);
        _authenticationService = authenticationService;
        _permissionRepository = permissionRepository;
    }

    @Override
    public void checkIfPermissionExistedById(Integer permissionId) {
        var isPermissionExisted = _permissionRepository.existsById(permissionId);

        if (!isPermissionExisted) {
            throw new EntityNotFoundException(
                    Translator.toLocale("exception.permission.notFound"),
                    ErrorCode.ENTITY_NOT_FOUND,
                    HttpStatus.NOT_FOUND
            );
        }
    }

    @Override
    public void checkIfPermissionAccessibleByAuthUser(Integer permissionId) {
        var currentAuthUser = (Manager) _authenticationService.getAuthentication().getPrincipal();

        var isPermissionAccessibleByAuthUser = currentAuthUser.getRole().getRolePermissions()
                .stream()
                .anyMatch(rolePermission -> rolePermission.getPermission().getPermissionId().equals(permissionId));

        if (!isPermissionAccessibleByAuthUser) {
            throw new UnauthorizedAccessException(
                    Translator.toLocale("exception.permissionNotAccessibleByAuthUser"),
                    ErrorCode.FORBIDDEN_ACCESS,
                    HttpStatus.FORBIDDEN
            );
        }
    }

    @Override
    public void checkIfNewPermissionHasDuplicatedPermissionName(String permissionName) {
        checkIfPermissionNameExistedThrowException(permissionName);
    }

    @Override
    public void checkIfUpdatePermissionHasDuplicatedPermissionName(Integer permissionId, String updatePermissionName) {
        var permission = _permissionRepository.findById(permissionId).orElseThrow();

        if (!permission.getPermissionName().equals(updatePermissionName)) {
            checkIfPermissionNameExistedThrowException(updatePermissionName);
        }
    }

    @Override
    public void checkIfPermissionNameExistedThrowException(String permissionName) {
        var isPermissionNameExisted = _permissionRepository.existsByPermissionName(permissionName);

        if (isPermissionNameExisted) {
            throw new DuplicatedDistinctRequiredValueException(
                    Translator.toLocale("exception.permissionName.existed"),
                    ErrorCode.DUPLICATED_VALUE,
                    HttpStatus.BAD_REQUEST
            );
        }
    }

}
