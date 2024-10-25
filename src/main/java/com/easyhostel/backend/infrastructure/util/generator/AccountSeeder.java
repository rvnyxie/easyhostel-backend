package com.easyhostel.backend.infrastructure.util.generator;

import com.easyhostel.backend.application.dto.manager.ManagerCreationDto;
import com.easyhostel.backend.application.mapping.interfaces.IManagerMapper;
import com.easyhostel.backend.application.mapping.interfaces.IRoleMapper;
import com.easyhostel.backend.domain.entity.Manager;
import com.easyhostel.backend.domain.entity.Role;
import com.easyhostel.backend.domain.repository.interfaces.manager.IManagerRepository;
import com.easyhostel.backend.domain.repository.interfaces.role.IRoleReadonlyRepository;
import com.easyhostel.backend.infrastructure.service.interfaces.IPasswordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Automatically check and create default accounts if not present
 *
 * @author Nyx
 */
@Component
@RequiredArgsConstructor
public class AccountSeeder {

    private final IManagerRepository _managerRepository;
    private final IRoleReadonlyRepository _roleReadonlyRepository;
    private final IManagerMapper _managerMapper;
    private final IRoleMapper _roleMapper;
    private final IPasswordService _passwordService;

    /**
     * Initialize method to seed accounts
     *
     * @author Nyx
     */
    public void init() {
        createAccounts();
    }

    /**
     * Create accounts
     *
     * @author Nyx
     */
    private void createAccounts() {
        createSystemAdministrator();
        createAdministrator();
        createDefaultUser();
    }

    /**
     * Create SYSADMIN account
     *
     * @author Nyx
     */
    private void createSystemAdministrator() {
        Optional<Manager> optionalManager = _managerRepository.findManagerByUsername("sysadmin");
        Optional<Role> optionalRole = _roleReadonlyRepository.findRoleByRoleName("SYSADMIN");

        if (optionalManager.isPresent() || optionalRole.isEmpty()) {
            return;
        }

        var sysadminCreationDto = ManagerCreationDto.builder()
                .username("sysadmin")
                .email("sysadmin@easyhostel.com")
                .password(_passwordService.encodePassword("sysadmin"))
                .role(_roleMapper.mapRoleToRoleDto(optionalRole.get()))
                .createdBy("auto-gen")
                .modifiedBy("auto-gen")
                .build();

        _managerRepository.save(_managerMapper.mapManagerCreationDtoToManager(sysadminCreationDto));
    }

    /**
     * Create ADMIN account
     *
     * @author Nyx
     */
    private void createAdministrator() {
        Optional<Manager> optionalManager = _managerRepository.findManagerByUsername("admin");
        Optional<Role> optionalRole = _roleReadonlyRepository.findRoleByRoleName("ADMIN");

        if (optionalManager.isPresent() || optionalRole.isEmpty()) {
            return;
        }

        var adminCreationDto = ManagerCreationDto.builder()
                .username("admin")
                .email("admin@easyhostel.com")
                .password(_passwordService.encodePassword("admin"))
                .role(_roleMapper.mapRoleToRoleDto(optionalRole.get()))
                .createdBy("auto-gen")
                .modifiedBy("auto-gen")
                .build();

        _managerRepository.save(_managerMapper.mapManagerCreationDtoToManager(adminCreationDto));
    }

    /**
     * Create USER account
     *
     * @author Nyx
     */
    private void createDefaultUser() {
        Optional<Manager> optionalManager = _managerRepository.findManagerByUsername("user");
        Optional<Role> optionalRole = _roleReadonlyRepository.findRoleByRoleName("USER");

        if (optionalManager.isPresent() || optionalRole.isEmpty()) {
            return;
        }

        var defaultUserCreationDto = ManagerCreationDto.builder()
                .username("user")
                .email("user@easyhostel.com")
                .password(_passwordService.encodePassword("user"))
                .role(_roleMapper.mapRoleToRoleDto(optionalRole.get()))
                .createdBy("auto-gen")
                .modifiedBy("auto-gen")
                .build();

        _managerRepository.save(_managerMapper.mapManagerCreationDtoToManager(defaultUserCreationDto));
    }

}
