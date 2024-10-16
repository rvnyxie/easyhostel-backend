package com.easyhostel.backend.domain.service.implementation.manager;

import com.easyhostel.backend.domain.enums.ErrorCode;
import com.easyhostel.backend.domain.exception.EntityNotFoundException;
import com.easyhostel.backend.domain.repository.interfaces.manager.IManagerRepository;
import com.easyhostel.backend.domain.service.interfaces.manager.IManagerBusinessValidator;
import com.easyhostel.backend.infrastructure.configuration.Translator;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

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

}
