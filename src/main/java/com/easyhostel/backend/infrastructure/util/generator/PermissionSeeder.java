package com.easyhostel.backend.infrastructure.util.generator;

import com.easyhostel.backend.domain.entity.Permission;
import com.easyhostel.backend.domain.enums.PermissionEnum;
import com.easyhostel.backend.domain.repository.interfaces.permission.IPermissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * Automatically check and create all Permission required
 *
 * @author Nyx
 */
@Component
@RequiredArgsConstructor
public class PermissionSeeder implements ApplicationListener<ContextRefreshedEvent> {

    private final IPermissionRepository _permissionRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
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
                        .build();
                _permissionRepository.save(newPermission);
            }
        }
    }

}
