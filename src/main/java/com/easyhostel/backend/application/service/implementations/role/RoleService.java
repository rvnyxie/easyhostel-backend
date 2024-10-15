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
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

/**
 * Role modification service for implementing abstract methods and define dedicated methods
 *
 * @author Nyx
 */
@Service
public class RoleService extends BaseService<Role, RoleDto, RoleCreationDto, RoleUpdateDto, String> implements IRoleService {

    private final IRoleRepository _roleRepository;
    private final IRoleBusinessValidator _roleBusinessValidator;
    private final IRoleMapper _roleMapper;

    public RoleService(IRoleRepository roleRepository, IRoleBusinessValidator roleBusinessValidator, IRoleMapper roleMapper) {
        super(roleRepository);
        _roleRepository = roleRepository;
        _roleBusinessValidator = roleBusinessValidator;
        _roleMapper = roleMapper;
    }

    @Override
    public Role mapCreationDtoToEntity(RoleCreationDto roleCreationDto) {
        return _roleMapper.MAPPER.mapRoleCreationDtoToRole(roleCreationDto);
    }

    @Override
    public Role mapUpdateDtoToEntity(RoleUpdateDto roleUpdateDto) {
        return _roleMapper.MAPPER.mapRoleUpdateDtoToRole(roleUpdateDto);
    }

    @Override
    public RoleDto mapEntityToDto(Role role) {
        return _roleMapper.MAPPER.mapRoleToRoleDto(role);
    }

    @Override
    public CompletableFuture<Void> validateCreationBusiness(RoleCreationDto roleCreationDto) {
        return super.validateCreationBusiness(roleCreationDto);
    }

    @Override
    public CompletableFuture<Void> validateUpdateBusiness(RoleUpdateDto roleUpdateDto) {
        return super.validateUpdateBusiness(roleUpdateDto);
    }
}
