package com.easyhostel.backend.application.service.implementations.contract;

import com.easyhostel.backend.application.dto.contract.ContractDto;
import com.easyhostel.backend.application.mapping.interfaces.IContractMapper;
import com.easyhostel.backend.application.service.implementations.base.BaseReadonlyService;
import com.easyhostel.backend.application.service.interfaces.contract.IContractReadonlyService;
import com.easyhostel.backend.domain.entity.Contract;
import com.easyhostel.backend.domain.repository.interfaces.contract.IContractReadonlyRepository;
import org.springframework.core.task.TaskExecutor;
import org.springframework.security.task.DelegatingSecurityContextAsyncTaskExecutor;
import org.springframework.stereotype.Service;

/**
 * Contract readonly service for implementing abstract methods and define dedicated methods
 *
 * @author Nyx
 */
@Service
public class ContractReadonlyService extends BaseReadonlyService<Contract, ContractDto, String> implements IContractReadonlyService {

    private final IContractMapper _contractMapper;

    public ContractReadonlyService(IContractReadonlyRepository contractReadonlyRepository,
                                   IContractMapper contractMapper,
                                   DelegatingSecurityContextAsyncTaskExecutor taskExecutor) {
        super(contractReadonlyRepository, taskExecutor);
        _contractMapper = contractMapper;
    }

    @Override
    public ContractDto mapEntityToDto(Contract contract) {
        var contractDto = _contractMapper.mapContractToContractDto(contract);

        return contractDto;
    }

}
