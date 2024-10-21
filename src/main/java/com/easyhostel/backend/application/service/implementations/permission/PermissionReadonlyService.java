package com.easyhostel.backend.application.service.implementations.permission;

import com.easyhostel.backend.application.dto.permission.PermissionDto;
import com.easyhostel.backend.application.mapping.interfaces.IPermissionMapper;
import com.easyhostel.backend.application.service.implementations.base.BaseReadonlyService;
import com.easyhostel.backend.application.service.interfaces.permission.IPermissionReadonlyService;
import com.easyhostel.backend.domain.entity.Permission;
import com.easyhostel.backend.domain.repository.interfaces.permission.IPermissionReadonlyRepository;
import org.springframework.security.task.DelegatingSecurityContextAsyncTaskExecutor;
import org.springframework.stereotype.Service;

/**
 * Permission readonly service for implementing abstract methods and define dedicated methods
 *
 * @author Nyx
 */
@Service
public class PermissionReadonlyService extends BaseReadonlyService<Permission, PermissionDto, Integer> implements IPermissionReadonlyService {

    private final IPermissionMapper _permissionMapper;

    public PermissionReadonlyService(IPermissionReadonlyRepository permissionReadonlyRepository,
                                     IPermissionMapper permissionMapper,
                                     DelegatingSecurityContextAsyncTaskExecutor taskExecutor) {
        super(permissionReadonlyRepository, taskExecutor);
        _permissionMapper = permissionMapper;
    }

    @Override
    public PermissionDto mapEntityToDto(Permission permission) {
        return _permissionMapper.MAPPER.mapPermissionToPermissionDto(permission);
    }
}
