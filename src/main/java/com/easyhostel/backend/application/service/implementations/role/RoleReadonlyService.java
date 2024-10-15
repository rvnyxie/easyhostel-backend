package com.easyhostel.backend.application.service.implementations.role;

import com.easyhostel.backend.application.dto.role.RoleDto;
import com.easyhostel.backend.application.mapping.interfaces.IRoleMapper;
import com.easyhostel.backend.application.service.implementations.base.BaseReadonlyService;
import com.easyhostel.backend.application.service.interfaces.role.IRoleReadonlyService;
import com.easyhostel.backend.domain.entity.Role;
import com.easyhostel.backend.domain.repository.interfaces.role.IRoleReadonlyRepository;
import org.springframework.stereotype.Service;

/**
 * Role readonly service for implementing abstract methods and define dedicated methods
 *
 * @author Nyx
 */
@Service
public class RoleReadonlyService extends BaseReadonlyService<Role, RoleDto, Integer> implements IRoleReadonlyService {

    private final IRoleMapper _roleMapper;

    public RoleReadonlyService(IRoleReadonlyRepository roleReadonlyRepository, IRoleMapper roleMapper) {
        super(roleReadonlyRepository);
        _roleMapper = roleMapper;
    }

    @Override
    public RoleDto mapEntityToDto(Role role) {
        return _roleMapper.MAPPER.mapRoleToRoleDto(role);
    }

}
