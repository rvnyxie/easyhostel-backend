package com.easyhostel.backend.application.service.implementations.role;

import com.easyhostel.backend.application.dto.role.RoleCreationDto;
import com.easyhostel.backend.application.dto.role.RoleDto;
import com.easyhostel.backend.application.dto.role.RoleUpdateDto;
import com.easyhostel.backend.application.mapping.interfaces.IRoleMapper;
import com.easyhostel.backend.application.service.implementations.base.BaseService;
import com.easyhostel.backend.application.service.interfaces.role.IRoleService;
import com.easyhostel.backend.domain.entity.Role;
import com.easyhostel.backend.domain.repository.interfaces.role.IRoleRepository;
import com.easyhostel.backend.domain.service.interfaces.role.IRoleBusinessValidator;
import org.springframework.security.task.DelegatingSecurityContextAsyncTaskExecutor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Role modification service for implementing abstract methods and define dedicated methods
 *
 * @author Nyx
 */
@Service
public class RoleService extends BaseService<Role, RoleDto, RoleCreationDto, RoleUpdateDto, Integer> implements IRoleService {

    private final IRoleBusinessValidator _roleBusinessValidator;
    private final IRoleMapper _roleMapper;

    private final DelegatingSecurityContextAsyncTaskExecutor _taskExecutor;

    public RoleService(IRoleRepository roleRepository,
                       IRoleBusinessValidator roleBusinessValidator,
                       IRoleMapper roleMapper,
                       DelegatingSecurityContextAsyncTaskExecutor taskExecutor) {
        super(roleRepository, taskExecutor);
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
    public CompletableFuture<Void> validateCreationBusiness(RoleCreationDto roleCreationDto) {
        return CompletableFuture.runAsync(() -> {
            _roleBusinessValidator.checkIfAuthenticatedUserNotSysadminThrowException();
            _roleBusinessValidator.checkIfRoleNameExistedThenThrowException(roleCreationDto.getRoleName());
        }, _taskExecutor);
    }

    @Override
    public CompletableFuture<Void> validateUpdateBusiness(RoleUpdateDto roleUpdateDto) {
        return CompletableFuture.runAsync(() -> {
            _roleBusinessValidator.checkIfAuthenticatedUserNotSysadminThrowException();
            _roleBusinessValidator.checkIfRoleExistedById(roleUpdateDto.getRoleId());
            _roleBusinessValidator.checkIfRoleNameUnchangedOrChangedToNonExistedValue(
                    roleUpdateDto.getRoleId(),
                    roleUpdateDto.getRoleName());
        }, _taskExecutor);
    }

    @Override
    public CompletableFuture<Void> validateDeletionBusinessAsync(Integer integer) {
        return CompletableFuture.runAsync(_roleBusinessValidator::checkIfAuthenticatedUserNotSysadminThrowException, _taskExecutor);
    }

    @Override
    public CompletableFuture<Void> validateDeletionManyBusinessAsync(List<Integer> roleIds) {
        return CompletableFuture.runAsync(() -> {
            _roleBusinessValidator.checkIfAuthenticatedUserNotSysadminThrowException();
            roleIds.forEach(_roleBusinessValidator::checkIfRoleExistedById);
        }, _taskExecutor);
    }

    @Override
    public Role mapCreationDtoToEntity(RoleCreationDto roleCreationDto) {
        return _roleMapper.mapRoleCreationDtoToRole(roleCreationDto);
    }

    @Override
    public Role mapUpdateDtoToEntity(RoleUpdateDto roleUpdateDto) {
        return _roleMapper.mapRoleUpdateDtoToRole(roleUpdateDto);
    }

    @Override
    public RoleDto mapEntityToDto(Role role) {
        return _roleMapper.mapRoleToRoleDto(role);
    }

}
