package com.easyhostel.backend.domain.service.interfaces.contract;

import com.easyhostel.backend.domain.entity.Contract;

import java.util.concurrent.CompletableFuture;

/**
 * Interface for defining Contract's business validator
 *
 * @author Nyx
 */
public interface IContractBusinessValidator {

    /**
     * Asynchronously check is Contract existed by ID
     *
     * @param contractId Contract's ID
     * @return CompletableFuture Contract object
     * @author Nyx
     */
    CompletableFuture<Contract> checkIfContractExistedByIdAsync(String contractId);

}
