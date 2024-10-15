package com.easyhostel.backend.domain.service.implementation.rolepermission;

import com.easyhostel.backend.domain.entity.embedded.RolePermissionId;
import com.easyhostel.backend.domain.enums.ErrorCode;
import com.easyhostel.backend.domain.exception.EntityNotFoundException;
import com.easyhostel.backend.domain.repository.interfaces.rolepermission.IRolePermissionRepository;
import com.easyhostel.backend.domain.service.interfaces.permission.IPermissionBusinessValidator;
import com.easyhostel.backend.domain.service.interfaces.role.IRoleBusinessValidator;
import com.easyhostel.backend.domain.service.interfaces.rolepermission.IRolePermissionBusinessValidator;
import com.easyhostel.backend.infrastructure.configuration.Translator;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

/**
 * Implementation for RolePermission's business validator
 *
 * @author Nyx
 */
@Service
public class RolePermissionBusinessValidator implements IRolePermissionBusinessValidator {

    private final IRolePermissionRepository _rolePermissionRepository;
    private final IRoleBusinessValidator _roleBusinessValidator;
    private final IPermissionBusinessValidator _permissionBusinessValidator;

    public RolePermissionBusinessValidator(IRolePermissionRepository rolePermissionRepository,
                                           IRoleBusinessValidator roleBusinessValidator,
                                           IPermissionBusinessValidator permissionBusinessValidator) {
        _rolePermissionRepository = rolePermissionRepository;
        _roleBusinessValidator = roleBusinessValidator;
        _permissionBusinessValidator = permissionBusinessValidator;
    }

    @Override
    public void checkIfRoleAndPermissionExistedByIds(Integer roleId, Integer permissionId) {
        _roleBusinessValidator.checkIfRoleExistedById(roleId);
        _permissionBusinessValidator.checkIfPermissionExistedById(permissionId);
    }

    @Override
    public void checkIfRolePermissionExistedById(RolePermissionId rolePermissionId) {
        var isRolePermissionExisted = _rolePermissionRepository.existsById(rolePermissionId);

        if (!isRolePermissionExisted) {
            throw new EntityNotFoundException(
                    Translator.toLocale("exception.rolePermission.notFound"),
                    ErrorCode.ENTITY_NOT_FOUND,
                    HttpStatus.NOT_FOUND
            );
        }
    }
}
