package com.easyhostel.backend.application.service.implementations.manager;

import com.easyhostel.backend.application.dto.manager.ManagerDto;
import com.easyhostel.backend.application.mapping.interfaces.IManagerMapper;
import com.easyhostel.backend.application.service.implementations.base.BaseReadonlyService;
import com.easyhostel.backend.application.service.interfaces.manager.IManagerReadonlyService;
import com.easyhostel.backend.domain.entity.Manager;
import com.easyhostel.backend.domain.repository.interfaces.manager.IManagerReadonlyRepository;
import com.easyhostel.backend.domain.service.interfaces.manager.IManagerBusinessValidator;
import org.springframework.security.task.DelegatingSecurityContextAsyncTaskExecutor;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

/**
 * Manager readonly service for implementing abstract methods and define dedicated methods
 *
 * @author Nyx
 */
@Service
public class ManagerReadonlyService extends BaseReadonlyService<Manager, ManagerDto, String> implements IManagerReadonlyService {

    private final IManagerBusinessValidator _managerBusinessValidator;
    private final IManagerMapper _managerMapper;

    private final DelegatingSecurityContextAsyncTaskExecutor _taskExecutor;


    public ManagerReadonlyService(IManagerReadonlyRepository managerReadonlyRepository,
                                  IManagerBusinessValidator managerBusinessValidator,
                                  IManagerMapper managerMapper,
                                  DelegatingSecurityContextAsyncTaskExecutor taskExecutor) {
        super(managerReadonlyRepository, taskExecutor);
        _managerBusinessValidator = managerBusinessValidator;
        _managerMapper = managerMapper;

        _taskExecutor = taskExecutor;
    }

    @Override
    public CompletableFuture<Void> validateGettingBusinessAsync(String managerId) {
        return CompletableFuture.runAsync(() -> {
            if (!_managerBusinessValidator.checkIsAuthenticatedUserSysadmin()) {
                _managerBusinessValidator.checkIfManagerManagedByAuthUser(managerId);
            }
        }, _taskExecutor);
    }

    @Override
    public CompletableFuture<Void> validateGettingManyBusinessAsync() {
        return CompletableFuture.runAsync(() -> {
            if (!_managerBusinessValidator.checkIsAuthenticatedUserSysadmin()) {
                _managerBusinessValidator.checkIfAuthenticatedUserNotAdminThrowException();
            }
        }, _taskExecutor);
    }

    @Override
    public ManagerDto mapEntityToDto(Manager manager) {
        return _managerMapper.mapManagerToManagerDto(manager);
    }

}
