package com.easyhostel.backend.application.service.implementations.rolepermission;

import com.easyhostel.backend.application.dto.rolepermission.RolePermissionDto;
import com.easyhostel.backend.application.mapping.interfaces.IRolePermissionMapper;
import com.easyhostel.backend.application.service.implementations.base.BaseReadonlyService;
import com.easyhostel.backend.application.service.interfaces.rolepermission.IRolePermissionReadonlyService;
import com.easyhostel.backend.domain.entity.RolePermission;
import com.easyhostel.backend.domain.entity.embedded.RolePermissionId;
import com.easyhostel.backend.domain.repository.interfaces.rolepermission.IRolePermissionReadonlyRepository;
import org.springframework.stereotype.Service;

/**
 * RolePermission readonly service for implementing abstract methods and define dedicated methods
 *
 * @author Nyx
 */
@Service
public class RolePermissionReadonlyService extends BaseReadonlyService<RolePermission, RolePermissionDto, RolePermissionId> implements IRolePermissionReadonlyService {

    private final IRolePermissionMapper _rolePermissionMapper;

    public RolePermissionReadonlyService(IRolePermissionReadonlyRepository rolePermissionReadonlyRepository,
                                         IRolePermissionMapper rolePermissionMapper) {
        super(rolePermissionReadonlyRepository);
        _rolePermissionMapper = rolePermissionMapper;
    }

    @Override
    public RolePermissionDto mapEntityToDto(RolePermission rolePermission) {
        return _rolePermissionMapper.MAPPER.mapRolePermissionToRolePermissionDto(rolePermission);
    }
}
