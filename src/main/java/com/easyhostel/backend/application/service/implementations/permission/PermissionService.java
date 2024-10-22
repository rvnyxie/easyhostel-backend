package com.easyhostel.backend.application.service.implementations.permission;

import com.easyhostel.backend.application.dto.permission.PermissionCreationDto;
import com.easyhostel.backend.application.dto.permission.PermissionDto;
import com.easyhostel.backend.application.dto.permission.PermissionUpdateDto;
import com.easyhostel.backend.application.mapping.interfaces.IPermissionMapper;
import com.easyhostel.backend.application.service.implementations.base.BaseService;
import com.easyhostel.backend.application.service.interfaces.permission.IPermissionService;
import com.easyhostel.backend.domain.entity.Permission;
import com.easyhostel.backend.domain.repository.interfaces.permission.IPermissionRepository;
import com.easyhostel.backend.domain.service.interfaces.permission.IPermissionBusinessValidator;
import org.springframework.security.task.DelegatingSecurityContextAsyncTaskExecutor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Permission modification service for implementing abstract methods and define dedicated methods
 *
 * @author Nyx
 */
@Service
public class PermissionService extends BaseService<Permission, PermissionDto, PermissionCreationDto, PermissionUpdateDto, Integer> implements IPermissionService {

    private final IPermissionRepository _permissionRepository;
    private final IPermissionBusinessValidator _permissionBusinessValidator;
    private final IPermissionMapper _permissionMapper;

    private final DelegatingSecurityContextAsyncTaskExecutor _taskExecutor;

    public PermissionService(IPermissionRepository permissionRepository,
                             IPermissionBusinessValidator permissionBusinessValidator,
                             IPermissionMapper permissionMapper,
                             DelegatingSecurityContextAsyncTaskExecutor taskExecutor) {
        super(permissionRepository, taskExecutor);
        _permissionRepository = permissionRepository;
        _permissionBusinessValidator = permissionBusinessValidator;
        _permissionMapper = permissionMapper;
        _taskExecutor = taskExecutor;
    }

    // TODO: override get many permissions

    @Override
    public CompletableFuture<Void> validateGettingBusinessAsync(Integer permissionId) {
        return CompletableFuture.runAsync(() -> {
            if (!_permissionBusinessValidator.checkIsAuthenticatedUserSysadmin()) {
                _permissionBusinessValidator.checkIfPermissionAccessibleByAuthUser(permissionId);
            }
        }, _taskExecutor);
    }

    @Override
    public CompletableFuture<Void> validateCreationBusiness(PermissionCreationDto permissionCreationDto) {
        return CompletableFuture.runAsync(() -> {
            _permissionBusinessValidator.checkIfAuthenticatedUserNotSysadminThrowException();
            _permissionBusinessValidator.checkIfNewPermissionHasDuplicatedPermissionName(permissionCreationDto.getPermissionName());
        }, _taskExecutor);
    }

    @Override
    public CompletableFuture<Void> validateUpdateBusiness(PermissionUpdateDto permissionUpdateDto) {
        return CompletableFuture.runAsync(() -> {
            _permissionBusinessValidator.checkIfAuthenticatedUserNotSysadminThrowException();
            _permissionBusinessValidator.checkIfPermissionExistedById(permissionUpdateDto.getPermissionId());
            _permissionBusinessValidator.checkIfUpdatePermissionHasDuplicatedPermissionName(
                    permissionUpdateDto.getPermissionId(),
                    permissionUpdateDto.getPermissionName());
        }, _taskExecutor);
    }

    @Override
    public CompletableFuture<Void> validateDeletionBusinessAsync(Integer permissionId) {
        return CompletableFuture.runAsync(() -> {
            _permissionBusinessValidator.checkIfAuthenticatedUserNotSysadminThrowException();
            _permissionBusinessValidator.checkIfPermissionExistedById(permissionId);
        }, _taskExecutor);
    }

    @Override
    public CompletableFuture<Void> validateDeletionManyBusinessAsync(List<Integer> permissionIds) {
        return CompletableFuture.runAsync(() -> {
            _permissionBusinessValidator.checkIfAuthenticatedUserNotSysadminThrowException();
            permissionIds.forEach(_permissionBusinessValidator::checkIfPermissionExistedById);
        }, _taskExecutor);
    }

    @Override
    public Permission mapCreationDtoToEntity(PermissionCreationDto permissionCreationDto) {
        return _permissionMapper.mapPermissionCreationDtoToPermission(permissionCreationDto);
    }

    @Override
    public Permission mapUpdateDtoToEntity(PermissionUpdateDto permissionUpdateDto) {
        return _permissionMapper.mapPermissionUpdateDtoToPermission(permissionUpdateDto);
    }

    @Override
    public PermissionDto mapEntityToDto(Permission permission) {
        return _permissionMapper.mapPermissionToPermissionDto(permission);
    }

}
