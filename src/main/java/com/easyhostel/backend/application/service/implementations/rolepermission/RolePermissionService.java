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
import org.springframework.stereotype.Service;

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

    public RolePermissionService(IRolePermissionRepository rolePermissionRepository,
                                 IRolePermissionBusinessValidator rolePermissionBusinessValidator,
                                 IRolePermissionMapper rolePermissionMapper,
                                 IRoleReadonlyRepository roleReadonlyRepository,
                                 IPermissionReadonlyRepository permissionReadonlyRepository) {
        super(rolePermissionRepository);
        _rolePermissionRepository = rolePermissionRepository;
        _rolePermissionBusinessValidator = rolePermissionBusinessValidator;
        _rolePermissionMapper = rolePermissionMapper;

        _roleReadonlyRepository = roleReadonlyRepository;
        _permissionReadonlyRepository = permissionReadonlyRepository;
    }

    @Override
    public CompletableFuture<RolePermissionDto> getByIdAsync(RolePermissionId rolePermissionId) {
        return validateGettingBusinessAsync(rolePermissionId)
                .thenComposeAsync(v -> CompletableFuture.supplyAsync(() -> {
                    var rolePermission = _rolePermissionRepository.findById(rolePermissionId).orElseThrow();

                    return mapEntityToDto(rolePermission);
                }));
    }

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

                    return _rolePermissionMapper.MAPPER.mapRolePermissionToRolePermissionDto(savedRolePermission);
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
    public RolePermission mapCreationDtoToEntity(RolePermissionCreationDto rolePermissionCreationDto) {
        return _rolePermissionMapper.MAPPER.mapRolePermissionCreationDtoToRolePermission(rolePermissionCreationDto);
    }

    @Override
    public RolePermission mapUpdateDtoToEntity(RolePermissionUpdateDto rolePermissionUpdateDto) {
        return _rolePermissionMapper.MAPPER.mapRolePermissionUpdateDtoToRolePermission(rolePermissionUpdateDto);
    }

    @Override
    public RolePermissionDto mapEntityToDto(RolePermission rolePermission) {
        return _rolePermissionMapper.MAPPER.mapRolePermissionToRolePermissionDto(rolePermission);
    }

    @Override
    public CompletableFuture<Void> validateGettingBusinessAsync(RolePermissionId rolePermissionId) {
        return CompletableFuture.runAsync(() -> {
            _rolePermissionBusinessValidator.checkIfRoleAndPermissionExistedByIds(
                    rolePermissionId.getRoleId(),
                    rolePermissionId.getPermissionId());
        });
    }

    @Override
    public CompletableFuture<Void> validateCreationBusiness(RolePermissionCreationDto rolePermissionCreationDto) {
        return CompletableFuture.runAsync(() -> {
            _rolePermissionBusinessValidator.checkIfRoleAndPermissionExistedByIds(
                    rolePermissionCreationDto.getRoleId(),
                    rolePermissionCreationDto.getPermissionId());
        });
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

            _rolePermissionBusinessValidator.checkIfRoleAndPermissionExistedByIds(newRoleId, newPermissionId);
        });
    }

    @Override
    public CompletableFuture<Void> validateDeletionBusinessAsync(RolePermissionId rolePermissionId) {
        return CompletableFuture.runAsync(() -> {
            _rolePermissionBusinessValidator.checkIfRolePermissionExistedById(rolePermissionId);
        });
    }

}
