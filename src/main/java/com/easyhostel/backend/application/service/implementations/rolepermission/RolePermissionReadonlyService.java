package com.easyhostel.backend.application.service.implementations.rolepermission;

import com.easyhostel.backend.application.dto.rolepermission.RolePermissionDto;
import com.easyhostel.backend.application.mapping.interfaces.IRolePermissionMapper;
import com.easyhostel.backend.application.service.implementations.base.BaseReadonlyService;
import com.easyhostel.backend.application.service.interfaces.rolepermission.IRolePermissionReadonlyService;
import com.easyhostel.backend.domain.entity.RolePermission;
import com.easyhostel.backend.domain.entity.embedded.RolePermissionId;
import com.easyhostel.backend.domain.repository.interfaces.rolepermission.IRolePermissionReadonlyRepository;
import com.easyhostel.backend.domain.service.interfaces.rolepermission.IRolePermissionBusinessValidator;
import org.springframework.security.task.DelegatingSecurityContextAsyncTaskExecutor;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

/**
 * RolePermission readonly service for implementing abstract methods and define dedicated methods
 *
 * @author Nyx
 */
@Service
public class RolePermissionReadonlyService extends BaseReadonlyService<RolePermission, RolePermissionDto, RolePermissionId> implements IRolePermissionReadonlyService {

    private final IRolePermissionBusinessValidator _rolePermissionBusinessValidator;
    private final IRolePermissionMapper _rolePermissionMapper;

    private final DelegatingSecurityContextAsyncTaskExecutor _taskExecutor;

    public RolePermissionReadonlyService(IRolePermissionReadonlyRepository rolePermissionReadonlyRepository,
                                         IRolePermissionBusinessValidator rolePermissionBusinessValidator,
                                         IRolePermissionMapper rolePermissionMapper,
                                         DelegatingSecurityContextAsyncTaskExecutor taskExecutor) {
        super(rolePermissionReadonlyRepository, taskExecutor);
        _rolePermissionBusinessValidator = rolePermissionBusinessValidator;
        _rolePermissionMapper = rolePermissionMapper;
        _taskExecutor = taskExecutor;
    }

    @Override
    public CompletableFuture<Void> validateGettingBusinessAsync(RolePermissionId rolePermissionId) {
        return CompletableFuture.runAsync(() -> {
            _rolePermissionBusinessValidator.checkIfRoleAndPermissionExistedByIds(
                    rolePermissionId.getRoleId(),
                    rolePermissionId.getPermissionId());
            _rolePermissionBusinessValidator.checkIfRolePermissionExistedById(rolePermissionId);

            if (!_rolePermissionBusinessValidator.checkIsAuthenticatedUserSysadmin()) {
                _rolePermissionBusinessValidator.checkIfRolePermissionAccessibleByAuthUser(rolePermissionId);
            }
        }, _taskExecutor);
    }

    @Override
    public RolePermissionDto mapEntityToDto(RolePermission rolePermission) {
        return _rolePermissionMapper.mapRolePermissionToRolePermissionDto(rolePermission);
    }
}
