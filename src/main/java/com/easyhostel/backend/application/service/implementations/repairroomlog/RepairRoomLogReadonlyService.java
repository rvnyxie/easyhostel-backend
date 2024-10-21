package com.easyhostel.backend.application.service.implementations.repairroomlog;

import com.easyhostel.backend.application.dto.repairroomlog.RepairRoomLogDto;
import com.easyhostel.backend.application.mapping.interfaces.IRepairRoomLogMapper;
import com.easyhostel.backend.application.service.implementations.base.BaseReadonlyService;
import com.easyhostel.backend.application.service.interfaces.repairroomlog.IRepairRoomLogReadonlyService;
import com.easyhostel.backend.domain.entity.RepairRoomLog;
import com.easyhostel.backend.domain.repository.interfaces.repairroomlog.IRepairRoomLogReadonlyRepository;
import org.springframework.security.task.DelegatingSecurityContextAsyncTaskExecutor;
import org.springframework.stereotype.Service;

/**
 * RepairRoomLog readonly service for implementing abstract methods and define dedicated methods
 *
 * @author Nyx
 */
@Service
public class RepairRoomLogReadonlyService extends BaseReadonlyService<RepairRoomLog, RepairRoomLogDto, String> implements IRepairRoomLogReadonlyService {

    private final IRepairRoomLogMapper _repairRoomLogMapper;

    public RepairRoomLogReadonlyService(IRepairRoomLogReadonlyRepository repairRoomLogReadonlyRepository,
                                        IRepairRoomLogMapper repairRoomLogMapper,
                                        DelegatingSecurityContextAsyncTaskExecutor taskExecutor) {
        super(repairRoomLogReadonlyRepository, taskExecutor);
        _repairRoomLogMapper = repairRoomLogMapper;
    }

    @Override
    public RepairRoomLogDto mapEntityToDto(RepairRoomLog repairRoomLog) {
        var repairRoomLogDto = _repairRoomLogMapper.MAPPER.mapRepairRoomLogToRepairRoomLogDto(repairRoomLog);

        return repairRoomLogDto;
    }
}
