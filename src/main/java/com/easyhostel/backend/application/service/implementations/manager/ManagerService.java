package com.easyhostel.backend.application.service.implementations.manager;

import com.easyhostel.backend.application.dto.manager.ManagerCreationDto;
import com.easyhostel.backend.application.dto.manager.ManagerDto;
import com.easyhostel.backend.application.dto.manager.ManagerUpdateDto;
import com.easyhostel.backend.application.service.implementations.base.BaseService;
import com.easyhostel.backend.application.service.interfaces.manager.IManagerService;
import com.easyhostel.backend.domain.entity.Manager;
import com.easyhostel.backend.domain.repository.interfaces.manager.IManagerRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Manager service for implementing abstract methods and define dedicated methods
 *
 * @author Nyx
 */
@Service
public class ManagerService extends BaseService<Manager, ManagerDto, ManagerCreationDto, ManagerUpdateDto, UUID> implements IManagerService {

    private final IManagerRepository _managerRepository;

    public ManagerService(IManagerRepository managerRepository) {
        super(managerRepository);
        _managerRepository = managerRepository;
    }

    @Override
    public Manager mapCreationDtoToEntity(ManagerCreationDto creationDtoEntity) {
        var manager = new Manager();
        manager.setUsername(creationDtoEntity.getUsername());
        manager.setEmail(creationDtoEntity.getEmail());
        manager.setPassword(creationDtoEntity.getPassword());
        manager.setAvatar(creationDtoEntity.getAvatar());
        manager.setCreatedBy(creationDtoEntity.getCreatedBy());
        manager.setModifiedBy(creationDtoEntity.getModifiedBy());

//        if (creationDtoEntity.getManagerRoles() != null) {
//            manager.setManagerRoles(creationDtoEntity.getManagerRoles());
//        }

        return manager;
    }

    @Override
    public Manager mapUpdateDtoToEntity(ManagerUpdateDto updateDtoEntity) {
        Manager manager = new Manager();
        manager.setManagerId(updateDtoEntity.getManagerId());
        manager.setUsername(updateDtoEntity.getUsername());
        manager.setEmail(updateDtoEntity.getEmail());
        manager.setPassword(updateDtoEntity.getPassword());
        manager.setAvatar(updateDtoEntity.getAvatar());
        manager.setModifiedBy(updateDtoEntity.getModifiedBy());

        // Update roles if present
//        if (updateDtoEntity.getManagerRoles() != null) {
//            manager.setManagerRoles(updateDtoEntity.getManagerRoles());
//        }

        return manager;
    }

    @Override
    public ManagerDto mapEntityToDto(Manager manager) {
        return ManagerDto.builder()
                .managerId(manager.getManagerId())
                .username(manager.getUsername())
                .email(manager.getEmail())
                .avatar(manager.getAvatar())
                .createdBy(manager.getCreatedBy())
                .modifiedBy(manager.getModifiedBy())
//                .managerRoles(manager.getManagerRoles())
                .build();
    }
}
