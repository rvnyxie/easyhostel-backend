package com.easyhostel.backend.application.service.implementations.contractinterior;

import com.easyhostel.backend.application.dto.contractinterior.ContractInteriorDto;
import com.easyhostel.backend.application.mapping.interfaces.IContractInteriorMapper;
import com.easyhostel.backend.application.service.implementations.base.BaseReadonlyService;
import com.easyhostel.backend.application.service.interfaces.contractinterior.IContractInteriorReadonlyService;
import com.easyhostel.backend.domain.entity.ContractInterior;
import com.easyhostel.backend.domain.entity.embedded.ContractInteriorId;
import com.easyhostel.backend.domain.repository.interfaces.contractinterior.IContractInteriorReadonlyRepository;
import org.springframework.security.task.DelegatingSecurityContextAsyncTaskExecutor;
import org.springframework.stereotype.Service;

/**
 * ContractInterior readonly service for implementing abstract methods and define dedicated methods
 *
 * @author Nyx
 */
@Service
public class ContractInteriorReadonlyService extends BaseReadonlyService<ContractInterior, ContractInteriorDto, ContractInteriorId> implements IContractInteriorReadonlyService {

    private final IContractInteriorMapper _contractInteriorMapper;

    public ContractInteriorReadonlyService(IContractInteriorReadonlyRepository contractInteriorReadonlyRepository,
                                           IContractInteriorMapper contractInteriorMapper,
                                           DelegatingSecurityContextAsyncTaskExecutor taskExecutor) {
        super(contractInteriorReadonlyRepository, taskExecutor);
        _contractInteriorMapper = contractInteriorMapper;
    }

    @Override
    public ContractInteriorDto mapEntityToDto(ContractInterior contractInterior) {
        return _contractInteriorMapper.MAPPER.mapContractInteriorToContractInteriorDto(contractInterior);
    }

}
