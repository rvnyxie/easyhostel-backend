package com.easyhostel.backend.application.service.implementations.contractroomamenity;

import com.easyhostel.backend.application.dto.contractroomamenity.ContractRoomAmenityDto;
import com.easyhostel.backend.application.mapping.interfaces.IContractRoomAmenityMapper;
import com.easyhostel.backend.application.service.implementations.base.BaseReadonlyService;
import com.easyhostel.backend.application.service.interfaces.contractroomamenity.IContractRoomAmenityReadonlyService;
import com.easyhostel.backend.domain.entity.ContractRoomAmenity;
import com.easyhostel.backend.domain.entity.embedded.ContractRoomAmenityId;
import com.easyhostel.backend.domain.repository.interfaces.contractroomamenity.IContractRoomAmenityRepository;
import com.easyhostel.backend.domain.service.interfaces.contractroomamenity.IContractRoomAmenityBusinessValidator;
import org.springframework.security.task.DelegatingSecurityContextAsyncTaskExecutor;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

/**
 * ContractRoomAmenity readonly service for implementing abstract methods and define dedicated methods
 *
 * @author Nyx
 */
@Service
public class ContractRoomAmenityReadonlyService extends BaseReadonlyService<ContractRoomAmenity, ContractRoomAmenityDto, ContractRoomAmenityId> implements IContractRoomAmenityReadonlyService {

    private final IContractRoomAmenityBusinessValidator _contractRoomAmenityBusinessValidator;
    private final IContractRoomAmenityMapper _contractRoomAmenityMapper;

    private final DelegatingSecurityContextAsyncTaskExecutor _taskExecutor;

    public ContractRoomAmenityReadonlyService(IContractRoomAmenityRepository contractRoomAmenityRepository,
                                              IContractRoomAmenityBusinessValidator contractRoomAmenityBusinessValidator,
                                              IContractRoomAmenityMapper contractRoomAmenityMapper,
                                              DelegatingSecurityContextAsyncTaskExecutor taskExecutor) {
        super(contractRoomAmenityRepository, taskExecutor);
        _contractRoomAmenityBusinessValidator = contractRoomAmenityBusinessValidator;
        _contractRoomAmenityMapper = contractRoomAmenityMapper;
        _taskExecutor = taskExecutor;
    }

    @Override
    public CompletableFuture<Void> validateGettingBusinessAsync(ContractRoomAmenityId contractRoomAmenityId) {
        return CompletableFuture.runAsync(() -> {
            _contractRoomAmenityBusinessValidator.checkIfContractRoomAmenityExistedById(contractRoomAmenityId);

            if (!_contractRoomAmenityBusinessValidator.checkIsAuthenticatedUserSysadmin()) {
                _contractRoomAmenityBusinessValidator.checkIfContractRoomAmenityAccessibleByAuthUser(contractRoomAmenityId);
            }
        }, _taskExecutor);
    }

    @Override
    public ContractRoomAmenityDto mapEntityToDto(ContractRoomAmenity contractRoomAmenity) {
        return _contractRoomAmenityMapper.mapContractRoomAmenityToContractRoomAmenityDto(contractRoomAmenity);
    }

}
