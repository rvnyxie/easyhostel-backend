package com.easyhostel.backend.application.service.implementations.contractinterior;

import com.easyhostel.backend.application.dto.contractinterior.ContractInteriorDto;
import com.easyhostel.backend.application.mapping.interfaces.IContractInteriorMapper;
import com.easyhostel.backend.application.service.implementations.base.BaseReadonlyService;
import com.easyhostel.backend.application.service.interfaces.contractinterior.IContractInteriorReadonlyService;
import com.easyhostel.backend.domain.entity.ContractInterior;
import com.easyhostel.backend.domain.entity.embedded.ContractInteriorId;
import com.easyhostel.backend.domain.repository.interfaces.contractinterior.IContractInteriorReadonlyRepository;
import com.easyhostel.backend.domain.service.interfaces.contractinterior.IContractInteriorBusinessValidator;
import org.springframework.security.task.DelegatingSecurityContextAsyncTaskExecutor;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

/**
 * ContractInterior readonly service for implementing abstract methods and define dedicated methods
 *
 * @author Nyx
 */
@Service
public class ContractInteriorReadonlyService extends BaseReadonlyService<ContractInterior, ContractInteriorDto, ContractInteriorId> implements IContractInteriorReadonlyService {

    private final IContractInteriorBusinessValidator _contractInteriorBusinessValidator;
    private final IContractInteriorMapper _contractInteriorMapper;

    private final DelegatingSecurityContextAsyncTaskExecutor _taskExecutor;

    public ContractInteriorReadonlyService(IContractInteriorReadonlyRepository contractInteriorReadonlyRepository,
                                           IContractInteriorBusinessValidator contractInteriorBusinessValidator,
                                           IContractInteriorMapper contractInteriorMapper,
                                           DelegatingSecurityContextAsyncTaskExecutor taskExecutor) {
        super(contractInteriorReadonlyRepository, taskExecutor);
        _contractInteriorBusinessValidator = contractInteriorBusinessValidator;
        _contractInteriorMapper = contractInteriorMapper;
        _taskExecutor = taskExecutor;
    }

    @Override
    public CompletableFuture<Void> validateGettingBusinessAsync(ContractInteriorId contractInteriorId) {
        return CompletableFuture.runAsync(() -> {
            _contractInteriorBusinessValidator.checkIfContractInteriorExistedByIds(contractInteriorId);

            if (!_contractInteriorBusinessValidator.checkIsAuthenticatedUserSysadmin()) {
                _contractInteriorBusinessValidator.checkIfContractInteriorAccessibleByAuthUser(contractInteriorId);
            }
        }, _taskExecutor);
    }

    @Override
    public ContractInteriorDto mapEntityToDto(ContractInterior contractInterior) {
        return _contractInteriorMapper.mapContractInteriorToContractInteriorDto(contractInterior);
    }

}
