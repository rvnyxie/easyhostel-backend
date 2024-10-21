package com.easyhostel.backend.application.service.implementations.managerhouse;

import com.easyhostel.backend.application.dto.managerhouse.ManagerHouseDto;
import com.easyhostel.backend.application.mapping.interfaces.IManagerHouseMapper;
import com.easyhostel.backend.application.service.implementations.base.BaseReadonlyService;
import com.easyhostel.backend.application.service.interfaces.managerhouse.IManagerHouseReadonlyService;
import com.easyhostel.backend.domain.entity.ManagerHouse;
import com.easyhostel.backend.domain.entity.embedded.ManagerHouseId;
import com.easyhostel.backend.domain.repository.interfaces.managerhouse.IManagerHouseReadonlyRepository;
import org.springframework.security.task.DelegatingSecurityContextAsyncTaskExecutor;
import org.springframework.stereotype.Service;

/**
 * ManagerHouse readonly service for implementing abstract methods and define dedicated methods
 *
 * @author Nyx
 */
@Service
public class ManagerHouseReadonlyService extends BaseReadonlyService<ManagerHouse, ManagerHouseDto, ManagerHouseId> implements IManagerHouseReadonlyService {

    private final IManagerHouseMapper _managerHouseMapper;

    public ManagerHouseReadonlyService(IManagerHouseReadonlyRepository managerHouseReadonlyRepository,
                                       IManagerHouseMapper managerHouseMapper,
                                       DelegatingSecurityContextAsyncTaskExecutor taskExecutor) {
        super(managerHouseReadonlyRepository, taskExecutor);
        _managerHouseMapper = managerHouseMapper;
    }

    @Override
    public ManagerHouseDto mapEntityToDto(ManagerHouse managerHouse) {
        return _managerHouseMapper.MAPPER.mapManagerHouseToMangerHouseDto(managerHouse);
    }
}
