package com.easyhostel.backend.domain.service.implementation.manager;

import com.easyhostel.backend.domain.enums.ErrorCode;
import com.easyhostel.backend.domain.exception.DuplicatedDistinctRequiredValueException;
import com.easyhostel.backend.domain.exception.EntityNotFoundException;
import com.easyhostel.backend.domain.repository.interfaces.manager.IManagerRepository;
import com.easyhostel.backend.domain.service.interfaces.manager.IManagerBusinessValidator;
import com.easyhostel.backend.infrastructure.configuration.Translator;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Implementation for Manager's business validator
 *
 * @author Nyx
 */
@Service
public class ManagerBusinessValidator implements IManagerBusinessValidator {

    private final IManagerRepository _managerRepository;

    public ManagerBusinessValidator(IManagerRepository managerRepository) {
        _managerRepository = managerRepository;
    }

    @Override
    public void checkIfManagerExistedById(String managerId) {
        var isManagerExisted = _managerRepository.existsById(managerId);

        if (!isManagerExisted) {
            throw new EntityNotFoundException(
                    Translator.toLocale("exception.manager.notFound"),
                    ErrorCode.ENTITY_NOT_FOUND,
                    HttpStatus.NOT_FOUND
            );
        }
    }

    @Override
    public void checkIfUsernameExistedThenThrowException(String username) {
        var isUsernameExisted = _managerRepository.existsByUsername(username);

        if (isUsernameExisted) {
            throw new DuplicatedDistinctRequiredValueException(
                    Translator.toLocale("exception.managerUsername.duplicated"),
                    ErrorCode.DUPLICATED_VALUE,
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    @Override
    public void checkIfEmailExistedThenThrowException(String email) {
        var isEmailExisted = _managerRepository.existsByEmail(email);

        if (isEmailExisted) {
            throw new DuplicatedDistinctRequiredValueException(
                    Translator.toLocale("exception.managerEmail.duplicated"),
                    ErrorCode.DUPLICATED_VALUE,
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    @Override
    public void checkIfEmailNotTakenByManagerThenThrowException(String managerId, String email) {
        var manager = _managerRepository.findById(managerId).orElseThrow();

        if (!manager.getEmail().equals(email)) {
            checkIfEmailExistedThenThrowException(email);
        }
    }
}
