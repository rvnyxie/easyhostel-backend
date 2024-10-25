package com.easyhostel.backend.infrastructure.util.generator;

import com.easyhostel.backend.domain.entity.Permission;
import com.easyhostel.backend.domain.enums.PermissionEnum;
import com.easyhostel.backend.domain.repository.interfaces.permission.IPermissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Automatically check and create all Permission required
 *
 * @author Nyx
 */
@Component
@RequiredArgsConstructor
public class PermissionSeeder {

    private final IPermissionRepository _permissionRepository;

    /**
     * Initialize method to seed Permission
     *
     * @author Nyx
     */
    public void init() {
        createPermissions();
    }

    /**
     * Create all Permission defined in PermissionEnum
     *
     * @author Nyx
     */
    private void createPermissions() {
        // Seed Permissions
        for (PermissionEnum permissionEnum: PermissionEnum.values()) {
            String permissionName = permissionEnum.getName();

            if (!_permissionRepository.existsByPermissionName(permissionName)) {
                var newPermission = Permission.builder()
                        .permissionName(permissionName)
                        .permissionDescription(permissionEnum.getDescription())
                        .createdBy("auto-gen")
                        .modifiedBy("auto-gen")
                        .build();
                _permissionRepository.save(newPermission);
            }
        }
    }

}
