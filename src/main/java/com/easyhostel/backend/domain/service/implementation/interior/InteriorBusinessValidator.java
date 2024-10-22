package com.easyhostel.backend.domain.service.implementation.interior;

import com.easyhostel.backend.application.service.interfaces.authentication.IAuthenticationService;
import com.easyhostel.backend.domain.enums.ErrorCode;
import com.easyhostel.backend.domain.exception.DuplicatedDistinctRequiredValueException;
import com.easyhostel.backend.domain.exception.EntityNotFoundException;
import com.easyhostel.backend.domain.repository.interfaces.interior.IInteriorRepository;
import com.easyhostel.backend.domain.service.implementation.base.BaseBusinessValidator;
import com.easyhostel.backend.domain.service.interfaces.interior.IInteriorBusinessValidator;
import com.easyhostel.backend.infrastructure.configuration.Translator;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

/**
 * Implementation for Interior's business validator
 *
 * @author Nyx
 */
@Service
public class InteriorBusinessValidator extends BaseBusinessValidator implements IInteriorBusinessValidator {

    private final IInteriorRepository _interiorRepository;

    public InteriorBusinessValidator(IAuthenticationService _authenticationService,
                                     IInteriorRepository interiorRepository) {
        super(_authenticationService);
        _interiorRepository = interiorRepository;
    }

    @Override
    public void checkIfInteriorExistedById(String interiorId) {
        var isInteriorExisted = _interiorRepository.existsById(interiorId);

        if (!isInteriorExisted) {
            throw new EntityNotFoundException(
                    Translator.toLocale("exception.interior.notFound"),
                    ErrorCode.ENTITY_NOT_FOUND,
                    HttpStatus.NOT_FOUND
            );
        }
    }

    @Override
    public void checkIfNewInteriorHasDuplicatedName(String interiorName) {
        checkIfInteriorNameExistedThrowException(interiorName);
    }

    @Override
    public void checkIfUpdateInteriorHasDuplicatedName(String interiorId, String updateInteriorName) {
        var interior = _interiorRepository.findById(interiorId).orElseThrow();

        if (!interior.getInteriorName().equals(updateInteriorName)) {
            checkIfInteriorNameExistedThrowException(updateInteriorName);
        }
    }

    @Override
    public void checkIfInteriorNameExistedThrowException(String interiorName) {
        var isInteriorNameExisted = _interiorRepository.existsByInteriorName(interiorName);

        if (isInteriorNameExisted) {
            throw new DuplicatedDistinctRequiredValueException(
                    Translator.toLocale("exception.interiorName.existed"),
                    ErrorCode.DUPLICATED_VALUE,
                    HttpStatus.BAD_REQUEST
            );
        }
    }

}
