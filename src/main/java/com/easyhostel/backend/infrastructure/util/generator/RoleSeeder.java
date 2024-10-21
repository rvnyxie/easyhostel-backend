package com.easyhostel.backend.infrastructure.util.generator;

import com.easyhostel.backend.domain.entity.Role;
import com.easyhostel.backend.domain.enums.RoleType;
import com.easyhostel.backend.domain.repository.interfaces.role.IRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
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
public class RoleSeeder implements ApplicationListener<ContextRefreshedEvent> {

    private final IRoleRepository _roleRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        loadRoles();
    }

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
                        .build();

                _roleRepository.save(roleToCreate);
            });
        });
    }
}
