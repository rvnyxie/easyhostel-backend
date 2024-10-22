package com.easyhostel.backend.domain.service.implementation.contractinterior;

import com.easyhostel.backend.application.service.interfaces.authentication.IAuthenticationService;
import com.easyhostel.backend.domain.entity.Manager;
import com.easyhostel.backend.domain.entity.embedded.ContractInteriorId;
import com.easyhostel.backend.domain.enums.ErrorCode;
import com.easyhostel.backend.domain.exception.DuplicatedDistinctRequiredValueException;
import com.easyhostel.backend.domain.exception.EntityNotFoundException;
import com.easyhostel.backend.domain.exception.UnauthorizedAccessException;
import com.easyhostel.backend.domain.repository.interfaces.contract.IContractRepository;
import com.easyhostel.backend.domain.repository.interfaces.contractinterior.IContractInteriorRepository;
import com.easyhostel.backend.domain.repository.interfaces.interior.IInteriorRepository;
import com.easyhostel.backend.domain.service.implementation.base.BaseBusinessValidator;
import com.easyhostel.backend.domain.service.interfaces.contract.IContractBusinessValidator;
import com.easyhostel.backend.domain.service.interfaces.contractinterior.IContractInteriorBusinessValidator;
import com.easyhostel.backend.infrastructure.configuration.Translator;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

/**
 * Implementation for ContractInterior's business validator
 *
 * @author Nyx
 */
@Service
public class ContractInteriorBusinessValidator extends BaseBusinessValidator implements IContractInteriorBusinessValidator {

    private final IAuthenticationService _authenticationService;
    private final IContractInteriorRepository _contractInteriorRepository;
    private final IContractRepository _contractRepository;
    private final IInteriorRepository _interiorRepository;

    private final IContractBusinessValidator _contractBusinessValidator;

    public ContractInteriorBusinessValidator(IAuthenticationService authenticationService,
                                             IContractInteriorRepository contractInteriorRepository,
                                             IContractRepository contractRepository,
                                             IInteriorRepository interiorRepository,
                                             IContractBusinessValidator contractBusinessValidator) {
        super(authenticationService);
        _authenticationService = authenticationService;
        _contractInteriorRepository = contractInteriorRepository;
        _contractRepository = contractRepository;
        _interiorRepository = interiorRepository;
        _contractBusinessValidator = contractBusinessValidator;
    }

    @Override
    public void checkIfContractAndInteriorExistedByIds(String contractId, String interiorId) {
        var isContractExisted = _contractRepository.existsById(contractId);

        if (!isContractExisted) {
            throw new EntityNotFoundException(
                    Translator.toLocale("exception.contract.notFound"),
                    ErrorCode.ENTITY_NOT_FOUND,
                    HttpStatus.NOT_FOUND
            );
        }

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
    public void checkIfContractInteriorExistedByIds(ContractInteriorId contractInteriorId) {
        var isContractInteriorExisted = _contractInteriorRepository.existsById(contractInteriorId);

        if (!isContractInteriorExisted) {
            throw new EntityNotFoundException(
                    Translator.toLocale("exception.contractInterior.notFound"),
                    ErrorCode.ENTITY_NOT_FOUND,
                    HttpStatus.NOT_FOUND
            );
        }
    }

    @Override
    public void checkIfContractInteriorAccessibleByAuthUser(ContractInteriorId contractInteriorId) {
        var currentAuthUser = (Manager) _authenticationService.getAuthentication().getPrincipal();

        var contractInterior = _contractInteriorRepository.findById(contractInteriorId).orElseThrow();

        var isContractInteriorAccessibleByAuthUser = currentAuthUser.getManagerHouses()
                .stream()
                .anyMatch(managerHouse ->
                        managerHouse.getHouse().getHouseId().equals(
                                contractInterior.getContract().getRoom().getHouse().getHouseId()));

        if (!isContractInteriorAccessibleByAuthUser) {
            throw new UnauthorizedAccessException(
                    Translator.toLocale("exception.contractInteriorNotAccessibleByAuthUser"),
                    ErrorCode.FORBIDDEN_ACCESS,
                    HttpStatus.FORBIDDEN
            );
        }
    }

    @Override
    public void checkIfContractAndInteriorAccessibleByAuthUser(String contractId, String interiorId) {
        // Interior is accessible by any authenticated user
        // Check Contract accessibility
        _contractBusinessValidator.checkIfContractManageableByAuthUser(contractId);
    }

    @Override
    public void checkIfContractInteriorExistedThrowException(ContractInteriorId contractInteriorId) {
        var isContractInteriorExisted = _contractInteriorRepository.existsById(contractInteriorId);

        if (isContractInteriorExisted) {
            throw new DuplicatedDistinctRequiredValueException(
                    Translator.toLocale("exception.contractInterior.existed"),
                    ErrorCode.DUPLICATED_VALUE,
                    HttpStatus.BAD_REQUEST
            );
        }
    }

}
