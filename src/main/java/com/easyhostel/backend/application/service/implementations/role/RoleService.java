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
        return CompletableFuture.runAsync(() -> {
            _roleBusinessValidator.checkIfRoleNameExistedThenThrowException(roleCreationDto.getRoleName());
        });
    }

    @Override
    public CompletableFuture<Void> validateUpdateBusiness(RoleUpdateDto roleUpdateDto) {
        return CompletableFuture.runAsync(() -> {
            _roleBusinessValidator.checkIfRoleExistedById(roleUpdateDto.getRoleId());
            _roleBusinessValidator.checkIfRoleNameUnchangedOrChangedToNonExistedValue(
                    roleUpdateDto.getRoleId(),
                    roleUpdateDto.getRoleName());
        });
    }
}
