package com.easyhostel.backend.infrastructure.util.generator;

import com.easyhostel.backend.application.dto.rolepermission.RolePermissionCreationDto;
import com.easyhostel.backend.application.mapping.interfaces.IRolePermissionMapper;
import com.easyhostel.backend.domain.entity.Role;
import com.easyhostel.backend.domain.entity.embedded.RolePermissionId;
import com.easyhostel.backend.domain.enums.PermissionEnum;
import com.easyhostel.backend.domain.repository.interfaces.permission.IPermissionRepository;
import com.easyhostel.backend.domain.repository.interfaces.role.IRoleRepository;
import com.easyhostel.backend.domain.repository.interfaces.rolepermission.IRolePermissionRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Automatically check and create all RolePermission required
 *
 * @author Nyx
 */
@Component
@RequiredArgsConstructor
public class RolePermissionSeeder {

    private static final Logger log = LoggerFactory.getLogger(RolePermissionSeeder.class);

    private final IPermissionRepository _permissionRepository;
    private final IRoleRepository _roleRepository;
    private final IRolePermissionRepository _rolePermissionRepository;
    private final IRolePermissionMapper _rolePermissionMapper;

    /**
     * Initialize method to seed RolePermission
     *
     * @author Nyx
     */
    public void init() {
        createRolePermissions();
    }

    /**
     * Create all RolePermission required
     *
     * @author Nyx
     */
    @PostConstruct
    private void createRolePermissions() {
        // Get all Role
        var sysadminRole = _roleRepository.findRoleByRoleName("SYSADMIN");
        var adminRole = _roleRepository.findRoleByRoleName("ADMIN");
        var userRole = _roleRepository.findRoleByRoleName("USER");

        if (sysadminRole.isEmpty() || adminRole.isEmpty() || userRole.isEmpty()) {
            return;
        }

        // Seed RolePermission for Sysadmin Role
        seedRolePermission(Arrays.asList(PermissionEnum.values()), sysadminRole);

        // Seed RolePermission for Admin Role
        var listPermissionOfAdmin = Arrays.asList(
                PermissionEnum.READ_HOUSE,
                PermissionEnum.READ_ROOM, PermissionEnum.UPDATE_ROOM,
                PermissionEnum.MANAGE_CONTRACT, PermissionEnum.MANAGE_CONTRACT_INTERIOR,
                PermissionEnum.MANAGE_CONTRACT_ROOM_AMENITY, PermissionEnum.MANAGE_CONTRACT_VEHICLE,
                PermissionEnum.MANAGE_INTERIOR, PermissionEnum.MANAGE_ROOM_AMENITY,
                PermissionEnum.MANAGE_VEHICLE, PermissionEnum.MANAGE_RENT_PAYMENT,
                PermissionEnum.MANAGE_REPAIR_ROOM_LOG
        );
        seedRolePermission(listPermissionOfAdmin, adminRole);

        // Seed RolePermission for User Role
        var listPermissionOfUser = Arrays.asList(
                PermissionEnum.READ_HOUSE,
                PermissionEnum.READ_ROOM, PermissionEnum.UPDATE_ROOM,
                PermissionEnum.MANAGE_CONTRACT, PermissionEnum.MANAGE_CONTRACT_INTERIOR,
                PermissionEnum.MANAGE_CONTRACT_ROOM_AMENITY, PermissionEnum.MANAGE_CONTRACT_VEHICLE,
                PermissionEnum.MANAGE_INTERIOR, PermissionEnum.MANAGE_ROOM_AMENITY,
                PermissionEnum.MANAGE_VEHICLE, PermissionEnum.MANAGE_RENT_PAYMENT,
                PermissionEnum.MANAGE_REPAIR_ROOM_LOG
        );
        seedRolePermission(listPermissionOfUser, userRole);
    }

    /**
     * Seed RolePermission with a list of PermissionEnum and attached Role
     *
     * @param permissionEnums List of PermissionEnum
     * @param role Role object
     * @author Nyx
     */
    private void seedRolePermission(List<PermissionEnum> permissionEnums, Optional<Role> role) {
        permissionEnums.forEach(permissionEnum -> {
            try {
                if (role.isPresent()) {
                    var roleId = role.get().getRoleId();
                    var permission = _permissionRepository.findByPermissionName(permissionEnum.getName()).orElseThrow();

                    // Create RolePermissionId for inserting
                    var rolePermissionId = RolePermissionId.builder()
                            .roleId(roleId)
                            .permissionId(permission.getPermissionId())
                            .build();

                    // Check if RolePermission already existed
                    var isRolePermissionExisted = _rolePermissionRepository.existsById(rolePermissionId);

                    // Continue if RolePermission already existed
                    if (isRolePermissionExisted) {
                        return;
                    }

                    var rolePermissionCreationDto = RolePermissionCreationDto.builder()
                            .roleId(roleId)
                            .permissionId(permission.getPermissionId())
                            .createdBy("auto-gen")
                            .modifiedBy("auto-gen")
                            .build();

                    var rolePermission = _rolePermissionMapper.mapRolePermissionCreationDtoToRolePermission(rolePermissionCreationDto);

                    // Set ID and references for RolePermission
                    rolePermission.setRolePermissionId(rolePermissionId);
                    rolePermission.setRole(role.get());
                    rolePermission.setPermission(permission);

                    // Insert
                    _rolePermissionRepository.save(rolePermission);
                }
            } catch (Exception ex) {
                log.info(ex.getLocalizedMessage());
            }
        });
    }

}
