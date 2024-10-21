package com.easyhostel.backend.application.service.implementations.contractvehicle;

import com.easyhostel.backend.application.dto.contractvehicle.ContractVehicleDto;
import com.easyhostel.backend.application.mapping.interfaces.IContractVehicleMapper;
import com.easyhostel.backend.application.service.implementations.base.BaseReadonlyService;
import com.easyhostel.backend.application.service.interfaces.contractvehicle.IContractVehicleReadonlyService;
import com.easyhostel.backend.domain.entity.ContractVehicle;
import com.easyhostel.backend.domain.entity.embedded.ContractVehicleId;
import com.easyhostel.backend.domain.repository.interfaces.contractvehicle.IContractVehicleReadonlyRepository;
import org.springframework.security.task.DelegatingSecurityContextAsyncTaskExecutor;
import org.springframework.stereotype.Service;

/**
 * ContractVehicle readonly service for implementing abstract methods and define dedicated methods
 *
 * @author Nyx
 */
@Service
public class ContractVehicleReadonlyService extends BaseReadonlyService<ContractVehicle, ContractVehicleDto, ContractVehicleId> implements IContractVehicleReadonlyService {

    private final IContractVehicleMapper _contractVehicleMapper;

    public ContractVehicleReadonlyService(IContractVehicleReadonlyRepository contractVehicleReadonlyRepository,
                                          IContractVehicleMapper contractVehicleMapper,
                                          DelegatingSecurityContextAsyncTaskExecutor taskExecutor) {
        super(contractVehicleReadonlyRepository, taskExecutor);
        _contractVehicleMapper = contractVehicleMapper;
    }

    @Override
    public ContractVehicleDto mapEntityToDto(ContractVehicle contractVehicle) {
        return _contractVehicleMapper.MAPPER.mapContractVehicleToContractVehicleDto(contractVehicle);
    }

}
