package com.easyhostel.backend.domain.service.implementation.permission;

import com.easyhostel.backend.domain.enums.ErrorCode;
import com.easyhostel.backend.domain.exception.EntityNotFoundException;
import com.easyhostel.backend.domain.repository.interfaces.permission.IPermissionRepository;
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
public class PermissionBusinessValidator implements IPermissionBusinessValidator {

    private final IPermissionRepository _permissionRepository;

    public PermissionBusinessValidator(IPermissionRepository permissionRepository) {
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

}
