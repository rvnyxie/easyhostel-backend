package com.easyhostel.backend.application.service.implementations.manager;

import com.easyhostel.backend.application.dto.manager.ManagerDto;
import com.easyhostel.backend.application.mapping.interfaces.IManagerMapper;
import com.easyhostel.backend.application.service.implementations.base.BaseReadonlyService;
import com.easyhostel.backend.application.service.interfaces.manager.IManagerReadonlyService;
import com.easyhostel.backend.domain.entity.Manager;
import com.easyhostel.backend.domain.repository.interfaces.manager.IManagerReadonlyRepository;
import org.springframework.security.task.DelegatingSecurityContextAsyncTaskExecutor;
import org.springframework.stereotype.Service;

/**
 * Manager readonly service for implementing abstract methods and define dedicated methods
 *
 * @author Nyx
 */
@Service
public class ManagerReadonlyService extends BaseReadonlyService<Manager, ManagerDto, String> implements IManagerReadonlyService {

    private final IManagerMapper _managerMapper;

    public ManagerReadonlyService(IManagerReadonlyRepository managerReadonlyRepository,
                                  IManagerMapper managerMapper,
                                  DelegatingSecurityContextAsyncTaskExecutor taskExecutor) {
        super(managerReadonlyRepository, taskExecutor);
        _managerMapper = managerMapper;
    }

    @Override
    public ManagerDto mapEntityToDto(Manager manager) {
        return _managerMapper.MAPPER.mapManagerToManagerDto(manager);
    }
}
