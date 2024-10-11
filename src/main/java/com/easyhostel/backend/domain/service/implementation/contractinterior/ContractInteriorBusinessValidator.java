package com.easyhostel.backend.domain.service.implementation.contractinterior;

import com.easyhostel.backend.domain.entity.embedded.ContractInteriorId;
import com.easyhostel.backend.domain.enums.ErrorCode;
import com.easyhostel.backend.domain.exception.EntityNotFoundException;
import com.easyhostel.backend.domain.repository.interfaces.contract.IContractRepository;
import com.easyhostel.backend.domain.repository.interfaces.contractinterior.IContractInteriorRepository;
import com.easyhostel.backend.domain.repository.interfaces.interior.IInteriorRepository;
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
public class ContractInteriorBusinessValidator implements IContractInteriorBusinessValidator {

    private final IContractInteriorRepository _contractInteriorRepository;
    private final IContractRepository _contractRepository;
    private final IInteriorRepository _interiorRepository;

    public ContractInteriorBusinessValidator(IContractInteriorRepository contractInteriorRepository,
                                             IContractRepository contractRepository,
                                             IInteriorRepository interiorRepository) {
        _contractInteriorRepository = contractInteriorRepository;
        _contractRepository = contractRepository;
        _interiorRepository = interiorRepository;
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
    public void checkIfContractInteriorAlreadyExistedByIds(String contractId, String interiorId) {
        var contractInteriorId = new ContractInteriorId();
        contractInteriorId.setContractId(contractId);
        contractInteriorId.setInteriorId(interiorId);

        var isContractInteriorExisted = _contractInteriorRepository.existsById(contractInteriorId);

        if (!isContractInteriorExisted) {
            throw new EntityNotFoundException(
                    Translator.toLocale("exception.contractInterior.notFound"),
                    ErrorCode.ENTITY_NOT_FOUND,
                    HttpStatus.NOT_FOUND
            );
        }
    }

}
