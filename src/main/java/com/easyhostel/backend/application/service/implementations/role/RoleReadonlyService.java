package com.easyhostel.backend.application.service.implementations.role;

import com.easyhostel.backend.application.dto.role.RoleDto;
import com.easyhostel.backend.application.mapping.interfaces.IRoleMapper;
import com.easyhostel.backend.application.service.implementations.base.BaseReadonlyService;
import com.easyhostel.backend.application.service.interfaces.role.IRoleReadonlyService;
import com.easyhostel.backend.domain.entity.Role;
import com.easyhostel.backend.domain.repository.interfaces.role.IRoleReadonlyRepository;
import com.easyhostel.backend.domain.service.interfaces.role.IRoleBusinessValidator;
import org.springframework.security.task.DelegatingSecurityContextAsyncTaskExecutor;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

/**
 * Role readonly service for implementing abstract methods and define dedicated methods
 *
 * @author Nyx
 */
@Service
public class RoleReadonlyService extends BaseReadonlyService<Role, RoleDto, Integer> implements IRoleReadonlyService {

    private final IRoleBusinessValidator _roleBusinessValidator;
    private final IRoleMapper _roleMapper;

    private final DelegatingSecurityContextAsyncTaskExecutor _taskExecutor;

    public RoleReadonlyService(IRoleReadonlyRepository roleReadonlyRepository,
                               IRoleBusinessValidator roleBusinessValidator,
                               IRoleMapper roleMapper,
                               DelegatingSecurityContextAsyncTaskExecutor taskExecutor) {
        super(roleReadonlyRepository, taskExecutor);
        _roleBusinessValidator = roleBusinessValidator;
        _roleMapper = roleMapper;
        _taskExecutor = taskExecutor;
    }

    @Override
    public CompletableFuture<Void> validateGettingBusinessAsync(Integer roleId) {
        return CompletableFuture.runAsync(() -> {
            if (!_roleBusinessValidator.checkIsAuthenticatedUserSysadmin()) {
                _roleBusinessValidator.checkIfRoleAccessibleByAuthUser(roleId);
            }
        }, _taskExecutor);
    }

    @Override
    public CompletableFuture<Void> validateGettingManyBusinessAsync() {
        return CompletableFuture.runAsync(_roleBusinessValidator::checkIfAuthenticatedUserNotSysadminThrowException, _taskExecutor);
    }

    @Override
    public RoleDto mapEntityToDto(Role role) {
        return _roleMapper.mapRoleToRoleDto(role);
    }

}
