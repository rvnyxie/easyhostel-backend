package com.easyhostel.backend.application.service.implementations.permission;

import com.easyhostel.backend.application.dto.permission.PermissionDto;
import com.easyhostel.backend.application.mapping.interfaces.IPermissionMapper;
import com.easyhostel.backend.application.service.implementations.base.BaseReadonlyService;
import com.easyhostel.backend.application.service.interfaces.permission.IPermissionReadonlyService;
import com.easyhostel.backend.domain.entity.Permission;
import com.easyhostel.backend.domain.repository.interfaces.permission.IPermissionReadonlyRepository;
import com.easyhostel.backend.domain.service.interfaces.permission.IPermissionBusinessValidator;
import org.springframework.security.task.DelegatingSecurityContextAsyncTaskExecutor;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

/**
 * Permission readonly service for implementing abstract methods and define dedicated methods
 *
 * @author Nyx
 */
@Service
public class PermissionReadonlyService extends BaseReadonlyService<Permission, PermissionDto, Integer> implements IPermissionReadonlyService {

    private final IPermissionBusinessValidator _permissionBusinessValidator;
    private final IPermissionMapper _permissionMapper;

    private final DelegatingSecurityContextAsyncTaskExecutor _taskExecutor;

    public PermissionReadonlyService(IPermissionReadonlyRepository permissionReadonlyRepository,
                                     IPermissionBusinessValidator permissionBusinessValidator,
                                     IPermissionMapper permissionMapper,
                                     DelegatingSecurityContextAsyncTaskExecutor taskExecutor) {
        super(permissionReadonlyRepository, taskExecutor);
        _permissionBusinessValidator = permissionBusinessValidator;
        _permissionMapper = permissionMapper;

        _taskExecutor = taskExecutor;
    }

    @Override
    public CompletableFuture<Void> validateGettingBusinessAsync(Integer permissionId) {
        return CompletableFuture.runAsync(() -> {
            if (!_permissionBusinessValidator.checkIsAuthenticatedUserSysadmin()) {
                _permissionBusinessValidator.checkIfPermissionAccessibleByAuthUser(permissionId);
            }
        }, _taskExecutor);
    }

    @Override
    public PermissionDto mapEntityToDto(Permission permission) {
        return _permissionMapper.mapPermissionToPermissionDto(permission);
    }

}
