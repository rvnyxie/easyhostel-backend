package com.easyhostel.backend.domain.service.implementation.contract;

import com.easyhostel.backend.domain.entity.Contract;
import com.easyhostel.backend.domain.enums.ErrorCode;
import com.easyhostel.backend.domain.exception.EntityNotFoundException;
import com.easyhostel.backend.domain.repository.interfaces.contract.IContractRepository;
import com.easyhostel.backend.domain.service.interfaces.contract.IContractBusinessValidator;
import com.easyhostel.backend.infrastructure.configuration.Translator;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

/**
 * Implementation for Contract's business validator
 *
 * @author Nyx
 */
@Service
public class ContractBusinessValidator implements IContractBusinessValidator {

    private final IContractRepository _contractRepository;

    public ContractBusinessValidator(IContractRepository contractRepository) {
        _contractRepository = contractRepository;
    }

    @Override
    @Async
    public CompletableFuture<Contract> checkIfContractExistedByIdAsync(String contractId) {
        return CompletableFuture.supplyAsync(() -> {
            var contract = _contractRepository.findById(contractId).orElseThrow(() -> new EntityNotFoundException(
                    Translator.toLocale("exception.contract.notFound"),
                    ErrorCode.RESOURCE_NOT_FOUND,
                    HttpStatus.NOT_FOUND
            ));

            return contract;
        });
    }

}
