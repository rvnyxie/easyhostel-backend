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
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Automatically check and create default accounts if not present
 *
 * @author Nyx
 */
@Component
@RequiredArgsConstructor
public class AccountSeeder implements ApplicationListener<ContextRefreshedEvent> {

    private final IManagerRepository _managerRepository;
    private final IRoleReadonlyRepository _roleReadonlyRepository;
    private final IManagerMapper _managerMapper;
    private final IRoleMapper _roleMapper;
    private final IPasswordService _passwordService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        createSystemAdministrator();
        createAdministrator();
        createDefaultUser();
    }

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
                .role(_roleMapper.MAPPER.mapRoleToRoleDto(optionalRole.get()))
                .build();

        _managerRepository.save(_managerMapper.mapManagerCreationDtoToManager(sysadminCreationDto));
    }

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
                .role(_roleMapper.MAPPER.mapRoleToRoleDto(optionalRole.get()))
                .build();

        _managerRepository.save(_managerMapper.mapManagerCreationDtoToManager(adminCreationDto));
    }

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
                .role(_roleMapper.MAPPER.mapRoleToRoleDto(optionalRole.get()))
                .build();

        _managerRepository.save(_managerMapper.mapManagerCreationDtoToManager(defaultUserCreationDto));
    }

}
