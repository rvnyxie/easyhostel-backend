package com.easyhostel.backend.domain.service.implementation.role;

import com.easyhostel.backend.domain.entity.Role;
import com.easyhostel.backend.domain.enums.ErrorCode;
import com.easyhostel.backend.domain.exception.EntityNotFoundException;
import com.easyhostel.backend.domain.repository.interfaces.role.IRoleRepository;
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
public class RoleBusinessValidator implements IRoleBusinessValidator {

    private final IRoleRepository _roleRepository;

    public RoleBusinessValidator(IRoleRepository roleRepository) {
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

}
