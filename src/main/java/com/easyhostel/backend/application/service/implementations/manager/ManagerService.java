package com.easyhostel.backend.application.service.implementations.manager;

import com.easyhostel.backend.application.dto.manager.ManagerCreationDto;
import com.easyhostel.backend.application.dto.manager.ManagerDto;
import com.easyhostel.backend.application.dto.manager.ManagerUpdateDto;
import com.easyhostel.backend.application.mapping.interfaces.IManagerMapper;
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
    private final IManagerMapper _managerMapper;

    public ManagerService(IManagerRepository managerRepository, IManagerMapper managerMapper) {
        super(managerRepository);
        _managerRepository = managerRepository;
        _managerMapper = managerMapper;
    }

    @Override
    public Manager mapCreationDtoToEntity(ManagerCreationDto creationDtoEntity) {
        return _managerMapper.MAPPER.mapCreationDtoToManager(creationDtoEntity);
    }

    @Override
    public Manager mapUpdateDtoToEntity(ManagerUpdateDto updateDtoEntity) {
        return _managerMapper.MAPPER.mapUpdateDtoToManager(updateDtoEntity);
    }

    @Override
    public ManagerDto mapEntityToDto(Manager manager) {
        return _managerMapper.MAPPER.mapManagerToDto(manager);
    }
}
