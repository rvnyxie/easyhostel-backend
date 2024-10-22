package com.easyhostel.backend.application.service.implementations.rolepermission;

import com.easyhostel.backend.application.dto.rolepermission.RolePermissionCreationDto;
import com.easyhostel.backend.application.dto.rolepermission.RolePermissionDto;
import com.easyhostel.backend.application.dto.rolepermission.RolePermissionUpdateDto;
import com.easyhostel.backend.application.mapping.interfaces.IRolePermissionMapper;
import com.easyhostel.backend.application.service.implementations.base.BaseService;
import com.easyhostel.backend.application.service.interfaces.rolepermission.IRolePermissionService;
import com.easyhostel.backend.domain.entity.RolePermission;
import com.easyhostel.backend.domain.entity.embedded.RolePermissionId;
import com.easyhostel.backend.domain.repository.interfaces.permission.IPermissionReadonlyRepository;
import com.easyhostel.backend.domain.repository.interfaces.role.IRoleReadonlyRepository;
import com.easyhostel.backend.domain.repository.interfaces.rolepermission.IRolePermissionRepository;
import com.easyhostel.backend.domain.service.interfaces.rolepermission.IRolePermissionBusinessValidator;
import org.springframework.security.task.DelegatingSecurityContextAsyncTaskExecutor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * RolePermission modification service for implementing abstract methods and define dedicated methods
 *
 * @author Nyx
 */
@Service
public class RolePermissionService extends BaseService<RolePermission, RolePermissionDto, RolePermissionCreationDto, RolePermissionUpdateDto, RolePermissionId> implements IRolePermissionService {

    private final IRolePermissionRepository _rolePermissionRepository;
    private final IRolePermissionBusinessValidator _rolePermissionBusinessValidator;
    private final IRolePermissionMapper _rolePermissionMapper;
    private final IRoleReadonlyRepository _roleReadonlyRepository;
    private final IPermissionReadonlyRepository _permissionReadonlyRepository;

    private final DelegatingSecurityContextAsyncTaskExecutor _taskExecutor;

    public RolePermissionService(IRolePermissionRepository rolePermissionRepository,
                                 IRolePermissionBusinessValidator rolePermissionBusinessValidator,
                                 IRolePermissionMapper rolePermissionMapper,
                                 IRoleReadonlyRepository roleReadonlyRepository,
                                 IPermissionReadonlyRepository permissionReadonlyRepository,
                                 DelegatingSecurityContextAsyncTaskExecutor taskExecutor) {
        super(rolePermissionRepository, taskExecutor);
        _rolePermissionRepository = rolePermissionRepository;
        _rolePermissionBusinessValidator = rolePermissionBusinessValidator;
        _rolePermissionMapper = rolePermissionMapper;

        _roleReadonlyRepository = roleReadonlyRepository;
        _permissionReadonlyRepository = permissionReadonlyRepository;

        _taskExecutor = taskExecutor;
    }

    @Override
    public CompletableFuture<RolePermissionDto> getByIdAsync(RolePermissionId rolePermissionId) {
        return validateGettingBusinessAsync(rolePermissionId)
                .thenComposeAsync(v -> CompletableFuture.supplyAsync(() -> {
                    var rolePermission = _rolePermissionRepository.findById(rolePermissionId).orElseThrow();

                    return mapEntityToDto(rolePermission);
                }));
    }

    // TODO: override get many method

    @Override
    public CompletableFuture<RolePermissionDto> insertAsync(RolePermissionCreationDto rolePermissionCreationDto) {
        return validateCreationBusiness(rolePermissionCreationDto)
                .thenCompose(v -> CompletableFuture.supplyAsync(() -> {
                    var rolePermission = mapCreationDtoToEntity(rolePermissionCreationDto);

                    // Get Role and Permission
                    var role = _roleReadonlyRepository.findById(rolePermissionCreationDto.getRoleId()).orElseThrow();
                    var permission = _permissionReadonlyRepository.findById(rolePermissionCreationDto.getPermissionId()).orElseThrow();

                    // Create RolePermissionId for inserting
                    var rolePermissionId = RolePermissionId.builder()
                            .roleId(rolePermissionCreationDto.getRoleId())
                            .permissionId(rolePermissionCreationDto.getPermissionId())
                            .build();

                    // Set ID and references for RolePermission
                    rolePermission.setRolePermissionId(rolePermissionId);
                    rolePermission.setRole(role);
                    rolePermission.setPermission(permission);

                    // Insert
                    var savedRolePermission = _rolePermissionRepository.save(rolePermission);

                    return mapEntityToDto(savedRolePermission);
                }));
    }

    @Override
    public CompletableFuture<RolePermissionDto> updateAsync(RolePermissionUpdateDto rolePermissionUpdateDto) {
        return validateUpdateBusiness(rolePermissionUpdateDto)
                .thenCompose(v -> CompletableFuture.supplyAsync(() -> {
                    // When updating RolePermission, it means we change either roleId or permissionId
                    // In our case, it means we save new RolePermission and delete the old one

                    // Delete old RolePermission
                    var oldRolePermissionId = RolePermissionId.builder()
                            .roleId(rolePermissionUpdateDto.getOldRoleId())
                            .permissionId(rolePermissionUpdateDto.getOldPermissionId())
                            .build();
                    deleteByIdAsync(oldRolePermissionId).join();

                    // Create new RolePermission
                    var newRolePermission = mapUpdateDtoToEntity(rolePermissionUpdateDto);

                    // Setup new ID for RolePermission
                    var newRolePermissionId = RolePermissionId.builder()
                            .roleId(rolePermissionUpdateDto.getRoleId())
                            .permissionId(rolePermissionUpdateDto.getPermissionId())
                            .build();

                    // Get Role and Permission
                    var role = _roleReadonlyRepository.findById(rolePermissionUpdateDto.getRoleId()).orElseThrow();
                    var permission = _permissionReadonlyRepository.findById(rolePermissionUpdateDto.getPermissionId()).orElseThrow();

                    // Assign new fields and references
                    newRolePermission.setRolePermissionId(newRolePermissionId);
                    newRolePermission.setRole(role);
                    newRolePermission.setPermission(permission);

                    // Insert
                    var savedNewRolePermission = _rolePermissionRepository.save(newRolePermission);

                    return mapEntityToDto(savedNewRolePermission);
                }));
    }

    @Override
    public CompletableFuture<Void> deleteByIdAsync(RolePermissionId rolePermissionId) {
        return validateDeletionBusinessAsync(rolePermissionId)
                .thenCompose(v -> CompletableFuture.runAsync(() -> {
                    _rolePermissionRepository.deleteById(rolePermissionId);
                }));
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
    public CompletableFuture<Void> validateCreationBusiness(RolePermissionCreationDto rolePermissionCreationDto) {
        return CompletableFuture.runAsync(() -> {
            _rolePermissionBusinessValidator.checkIfRoleAndPermissionExistedByIds(
                    rolePermissionCreationDto.getRoleId(),
                    rolePermissionCreationDto.getPermissionId());

            var rolePermissionCreationId = RolePermissionId.builder()
                    .roleId(rolePermissionCreationDto.getRoleId())
                    .permissionId(rolePermissionCreationDto.getPermissionId())
                    .build();
            _rolePermissionBusinessValidator.checkIfRolePermissionExistedThrowException(rolePermissionCreationId);

            _rolePermissionBusinessValidator.checkIfAuthenticatedUserNotSysadminThrowException();
        }, _taskExecutor);
    }

    @Override
    public CompletableFuture<Void> validateUpdateBusiness(RolePermissionUpdateDto rolePermissionUpdateDto) {
        return CompletableFuture.runAsync(() -> {
            // Check if the RolePermission existed (the one we will update)
            var oldRolePermissionId = RolePermissionId.builder()
                    .roleId(rolePermissionUpdateDto.getOldRoleId())
                    .permissionId(rolePermissionUpdateDto.getOldPermissionId())
                    .build();

            _rolePermissionBusinessValidator.checkIfRolePermissionExistedById(oldRolePermissionId);

            // Check if new Role and Permission existed (the one which will be referenced to)
            var newRoleId = rolePermissionUpdateDto.getRoleId();
            var newPermissionId = rolePermissionUpdateDto.getPermissionId();
            var newRolePermissionId = RolePermissionId.builder()
                    .roleId(newRoleId)
                    .permissionId(newPermissionId)
                    .build();

            _rolePermissionBusinessValidator.checkIfRoleAndPermissionExistedByIds(newRoleId, newPermissionId);
            _rolePermissionBusinessValidator.checkIfRolePermissionExistedThrowException(newRolePermissionId);

            _rolePermissionBusinessValidator.checkIfAuthenticatedUserNotSysadminThrowException();
        }, _taskExecutor);
    }

    @Override
    public CompletableFuture<Void> validateDeletionBusinessAsync(RolePermissionId rolePermissionId) {
        return CompletableFuture.runAsync(() -> {
            _rolePermissionBusinessValidator.checkIfAuthenticatedUserNotSysadminThrowException();
            _rolePermissionBusinessValidator.checkIfRolePermissionExistedById(rolePermissionId);
        }, _taskExecutor);
    }

    @Override
    public CompletableFuture<Void> validateDeletionManyBusinessAsync(List<RolePermissionId> rolePermissionIds) {
        return CompletableFuture.runAsync(() -> {
            _rolePermissionBusinessValidator.checkIfAuthenticatedUserNotSysadminThrowException();
            rolePermissionIds.forEach(_rolePermissionBusinessValidator::checkIfRolePermissionExistedById);
        }, _taskExecutor);
    }

    @Override
    public RolePermission mapCreationDtoToEntity(RolePermissionCreationDto rolePermissionCreationDto) {
        return _rolePermissionMapper.mapRolePermissionCreationDtoToRolePermission(rolePermissionCreationDto);
    }

    @Override
    public RolePermission mapUpdateDtoToEntity(RolePermissionUpdateDto rolePermissionUpdateDto) {
        return _rolePermissionMapper.mapRolePermissionUpdateDtoToRolePermission(rolePermissionUpdateDto);
    }

    @Override
    public RolePermissionDto mapEntityToDto(RolePermission rolePermission) {
        return _rolePermissionMapper.mapRolePermissionToRolePermissionDto(rolePermission);
    }

}
