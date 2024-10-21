package com.easyhostel.backend.application.service.implementations.repairroomlog;

import com.easyhostel.backend.application.dto.repairroomlog.RepairRoomLogCreationDto;
import com.easyhostel.backend.application.dto.repairroomlog.RepairRoomLogDto;
import com.easyhostel.backend.application.dto.repairroomlog.RepairRoomLogUpdateDto;
import com.easyhostel.backend.application.mapping.interfaces.IRepairRoomLogMapper;
import com.easyhostel.backend.application.service.implementations.base.BaseService;
import com.easyhostel.backend.application.service.interfaces.repairroomlog.IRepairRoomLogService;
import com.easyhostel.backend.domain.entity.RepairRoomLog;
import com.easyhostel.backend.domain.repository.interfaces.repairroomlog.IRepairRoomLogRepository;
import com.easyhostel.backend.domain.service.interfaces.repairroomlog.IRepairRoomLogBusinessValidator;
import org.springframework.security.task.DelegatingSecurityContextAsyncTaskExecutor;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

/**
 * RepairRoomLog modification service for implementing abstract methods and define dedicated methods
 *
 * @author Nyx
 */
@Service
public class RepairRoomLogService extends BaseService<RepairRoomLog, RepairRoomLogDto, RepairRoomLogCreationDto, RepairRoomLogUpdateDto, String> implements IRepairRoomLogService {

    private final IRepairRoomLogRepository _repairRoomLogRepository;
    private final IRepairRoomLogBusinessValidator _repairRoomLogBusinessValidator;
    private final IRepairRoomLogMapper _repairRoomLogMapper;

    private final DelegatingSecurityContextAsyncTaskExecutor _taskExecutor;

    public RepairRoomLogService(IRepairRoomLogRepository repairRoomLogRepository,
                                IRepairRoomLogBusinessValidator repairRoomLogBusinessValidator,
                                IRepairRoomLogMapper repairRoomLogMapper,
                                DelegatingSecurityContextAsyncTaskExecutor taskExecutor) {
        super(repairRoomLogRepository, taskExecutor);
        _repairRoomLogRepository = repairRoomLogRepository;
        _repairRoomLogBusinessValidator = repairRoomLogBusinessValidator;
        _repairRoomLogMapper = repairRoomLogMapper;
        _taskExecutor = taskExecutor;
    }

    @Override
    public RepairRoomLog mapCreationDtoToEntity(RepairRoomLogCreationDto repairRoomLogCreationDto) {
        var repairRoomLog = _repairRoomLogMapper.MAPPER.mapRepairRoomLogCreationDtoToRepairRoomLog(repairRoomLogCreationDto);

        return repairRoomLog;
    }

    @Override
    public RepairRoomLog mapUpdateDtoToEntity(RepairRoomLogUpdateDto repairRoomLogUpdateDto) {
        var repairRoomLog = _repairRoomLogMapper.MAPPER.mapRepairRoomLogUpdateDtoToRepairRoomLog(repairRoomLogUpdateDto);

        return repairRoomLog;
    }

    @Override
    public RepairRoomLogDto mapEntityToDto(RepairRoomLog repairRoomLog) {
        var repairRoomLogDto = _repairRoomLogMapper.MAPPER.mapRepairRoomLogToRepairRoomLogDto(repairRoomLog);

        return repairRoomLogDto;
    }

    // TODO: Add business creation validation for RepairRoomLog
    @Override
    public CompletableFuture<Void> validateCreationBusiness(RepairRoomLogCreationDto repairRoomLogCreationDto) {
        return super.validateCreationBusiness(repairRoomLogCreationDto);
    }

    // TODO: Add business update validation for RepairRoomLog
    @Override
    public CompletableFuture<Void> validateUpdateBusiness(RepairRoomLogUpdateDto repairRoomLogUpdateDto) {
        return super.validateUpdateBusiness(repairRoomLogUpdateDto);
    }

}
