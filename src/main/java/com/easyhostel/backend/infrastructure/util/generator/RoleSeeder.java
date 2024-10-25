package com.easyhostel.backend.infrastructure.util.generator;

import com.easyhostel.backend.domain.entity.Role;
import com.easyhostel.backend.domain.enums.RoleType;
import com.easyhostel.backend.domain.repository.interfaces.role.IRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;

/**
 * Automatically check and create required Roles if not present
 *
 * @author Nyx
 */
@Component
@RequiredArgsConstructor
public class RoleSeeder {

    private final IRoleRepository _roleRepository;

    /**
     * Initialize method to seed Roles
     *
     * @author Nyx
     */
    public void init() {
        loadRoles();
    }

    /**
     * Create default Roles
     *
     * @author Nyx
     */
    private void loadRoles() {
        RoleType[] roleTypes = new RoleType[] { RoleType.SYSADMIN , RoleType.ADMIN, RoleType.USER };
        var roleDescriptionMap = Map.of(
                RoleType.SYSADMIN, "System Admin role",
                RoleType.ADMIN, "Admin role",
                RoleType.USER, "Default user role"
        );

        Arrays.stream(roleTypes).forEach(roleType -> {
            Optional<Role> optionalRole = _roleRepository.findById(roleType.getRoleId());

            optionalRole.ifPresentOrElse(System.out::println, () -> {
                var roleToCreate = Role.builder()
                        .roleName(roleType.name())
                        .description(roleDescriptionMap.get(roleType))
                        .createdBy("auto-gen")
                        .modifiedBy("auto-gen")
                        .build();

                _roleRepository.save(roleToCreate);
            });
        });
    }

}
