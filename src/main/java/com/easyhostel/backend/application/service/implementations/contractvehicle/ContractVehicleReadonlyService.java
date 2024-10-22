package com.easyhostel.backend.application.service.implementations.contractvehicle;

import com.easyhostel.backend.application.dto.contractvehicle.ContractVehicleDto;
import com.easyhostel.backend.application.mapping.interfaces.IContractVehicleMapper;
import com.easyhostel.backend.application.service.implementations.base.BaseReadonlyService;
import com.easyhostel.backend.application.service.interfaces.contractvehicle.IContractVehicleReadonlyService;
import com.easyhostel.backend.domain.entity.ContractVehicle;
import com.easyhostel.backend.domain.entity.embedded.ContractVehicleId;
import com.easyhostel.backend.domain.repository.interfaces.contractvehicle.IContractVehicleReadonlyRepository;
import com.easyhostel.backend.domain.service.interfaces.contractvehicle.IContractVehicleBusinessValidator;
import org.springframework.security.task.DelegatingSecurityContextAsyncTaskExecutor;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

/**
 * ContractVehicle readonly service for implementing abstract methods and define dedicated methods
 *
 * @author Nyx
 */
@Service
public class ContractVehicleReadonlyService extends BaseReadonlyService<ContractVehicle, ContractVehicleDto, ContractVehicleId> implements IContractVehicleReadonlyService {

    private final IContractVehicleBusinessValidator _contractVehicleBusinessValidator;
    private final IContractVehicleMapper _contractVehicleMapper;

    private final DelegatingSecurityContextAsyncTaskExecutor _taskExecutor;

    public ContractVehicleReadonlyService(IContractVehicleReadonlyRepository contractVehicleReadonlyRepository,
                                          IContractVehicleBusinessValidator contractVehicleBusinessValidator,
                                          IContractVehicleMapper contractVehicleMapper,
                                          DelegatingSecurityContextAsyncTaskExecutor taskExecutor) {
        super(contractVehicleReadonlyRepository, taskExecutor);
        _contractVehicleBusinessValidator = contractVehicleBusinessValidator;
        _contractVehicleMapper = contractVehicleMapper;
        _taskExecutor = taskExecutor;
    }

    @Override
    public CompletableFuture<Void> validateGettingBusinessAsync(ContractVehicleId contractVehicleId) {
        return CompletableFuture.runAsync(() -> {
            _contractVehicleBusinessValidator.checkIfContractVehicleExisted(contractVehicleId);

            if (!_contractVehicleBusinessValidator.checkIsAuthenticatedUserSysadmin()) {
                _contractVehicleBusinessValidator.checkIfContractVehicleAccessibleByAuthUser(contractVehicleId);
            }
        }, _taskExecutor);
    }

    @Override
    public ContractVehicleDto mapEntityToDto(ContractVehicle contractVehicle) {
        return _contractVehicleMapper.mapContractVehicleToContractVehicleDto(contractVehicle);
    }

}
