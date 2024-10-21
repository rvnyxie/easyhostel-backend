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

    @Override
    public Permission mapCreationDtoToEntity(PermissionCreationDto permissionCreationDto) {
        return _permissionMapper.MAPPER.mapPermissionCreationDtoToPermission(permissionCreationDto);
    }

    @Override
    public Permission mapUpdateDtoToEntity(PermissionUpdateDto permissionUpdateDto) {
        return _permissionMapper.MAPPER.mapPermissionUpdateDtoToPermission(permissionUpdateDto);
    }

    @Override
    public PermissionDto mapEntityToDto(Permission permission) {
        return _permissionMapper.MAPPER.mapPermissionToPermissionDto(permission);
    }

    // TODO: check duplicated permission name
    @Override
    public CompletableFuture<Void> validateCreationBusiness(PermissionCreationDto permissionCreationDto) {
        return super.validateCreationBusiness(permissionCreationDto);
    }

    @Override
    public CompletableFuture<Void> validateUpdateBusiness(PermissionUpdateDto permissionUpdateDto) {
        return super.validateUpdateBusiness(permissionUpdateDto);
    }

}
