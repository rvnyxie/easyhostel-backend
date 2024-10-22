package com.easyhostel.backend.application.service.implementations.roomamenity;

import com.easyhostel.backend.application.dto.roomamenity.RoomAmenityCreationDto;
import com.easyhostel.backend.application.dto.roomamenity.RoomAmenityDto;
import com.easyhostel.backend.application.dto.roomamenity.RoomAmenityUpdateDto;
import com.easyhostel.backend.application.mapping.interfaces.IRoomAmenityMapper;
import com.easyhostel.backend.application.service.implementations.base.BaseService;
import com.easyhostel.backend.application.service.interfaces.roomamenity.IRoomAmenityService;
import com.easyhostel.backend.domain.entity.RoomAmenity;
import com.easyhostel.backend.domain.repository.interfaces.roomamenity.IRoomAmenityRepository;
import com.easyhostel.backend.domain.service.interfaces.roomamenity.IRoomAmenityBusinessValidator;
import org.springframework.security.task.DelegatingSecurityContextAsyncTaskExecutor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * RoomAmenity modification service for implementing abstract methods and define dedicated methods
 *
 * @author Nyx
 */
@Service
public class RoomAmenityService extends BaseService<RoomAmenity, RoomAmenityDto, RoomAmenityCreationDto, RoomAmenityUpdateDto, String> implements IRoomAmenityService {

    private final IRoomAmenityBusinessValidator _roomAmenityBusinessValidator;
    private final IRoomAmenityMapper _roomAmenityMapper;

    private final DelegatingSecurityContextAsyncTaskExecutor _taskExecutor;

    public RoomAmenityService(IRoomAmenityRepository roomAmenityRepository,
                              IRoomAmenityBusinessValidator roomAmenityBusinessValidator,
                              IRoomAmenityMapper roomAmenityMapper,
                              DelegatingSecurityContextAsyncTaskExecutor taskExecutor) {
        super(roomAmenityRepository, taskExecutor);
        _roomAmenityBusinessValidator = roomAmenityBusinessValidator;
        _roomAmenityMapper = roomAmenityMapper;
        _taskExecutor = taskExecutor;
    }


    @Override
    public CompletableFuture<Void> validateCreationBusiness(RoomAmenityCreationDto roomAmenityCreationDto) {
        return CompletableFuture.runAsync(() -> {
            _roomAmenityBusinessValidator.checkIfAuthenticatedUserNotSysadminThrowException();
            _roomAmenityBusinessValidator.checkIfNewRoomAmenityHasDuplicatedName(roomAmenityCreationDto.getRoomAmenityName());
        }, _taskExecutor);
    }

    @Override
    public CompletableFuture<Void> validateUpdateBusiness(RoomAmenityUpdateDto roomAmenityUpdateDto) {
        return CompletableFuture.runAsync(() -> {
            _roomAmenityBusinessValidator.checkIfAuthenticatedUserNotSysadminThrowException();
            _roomAmenityBusinessValidator.checkIfRoomAmenityExistedById(roomAmenityUpdateDto.getRoomAmenityId());
            _roomAmenityBusinessValidator.checkIfUpdateRoomAmenityHasDuplicatedName(
                    roomAmenityUpdateDto.getRoomAmenityId(),
                    roomAmenityUpdateDto.getRoomAmenityName());
        }, _taskExecutor);
    }

    @Override
    public CompletableFuture<Void> validateDeletionBusinessAsync(String roomAmenityId) {
        return CompletableFuture.runAsync(() -> {
            _roomAmenityBusinessValidator.checkIfAuthenticatedUserNotSysadminThrowException();
            _roomAmenityBusinessValidator.checkIfRoomAmenityExistedById(roomAmenityId);
        }, _taskExecutor);
    }

    @Override
    public CompletableFuture<Void> validateDeletionManyBusinessAsync(List<String> roomAmenityIds) {
        return CompletableFuture.runAsync(() -> {
            _roomAmenityBusinessValidator.checkIfAuthenticatedUserNotSysadminThrowException();
            roomAmenityIds.forEach(_roomAmenityBusinessValidator::checkIfRoomAmenityExistedById);
        }, _taskExecutor);
    }

    @Override
    public RoomAmenity mapCreationDtoToEntity(RoomAmenityCreationDto roomAmenityCreationDto) {
        return _roomAmenityMapper.mapRoomAmenityCreationDtoToRoomAmenity(roomAmenityCreationDto);
    }

    @Override
    public RoomAmenity mapUpdateDtoToEntity(RoomAmenityUpdateDto roomAmenityUpdateDto) {
        return _roomAmenityMapper.mapRoomAmenityUpdateDtoToRoomAmenity(roomAmenityUpdateDto);
    }

    @Override
    public RoomAmenityDto mapEntityToDto(RoomAmenity roomAmenity) {
        return _roomAmenityMapper.mapRoomAmenityToRoomAmenityDto(roomAmenity);
    }

}
