package com.easyhostel.backend.application.service.implementations.room;

import com.easyhostel.backend.application.dto.room.RoomCreationDto;
import com.easyhostel.backend.application.dto.room.RoomDto;
import com.easyhostel.backend.application.dto.room.RoomUpdateDto;
import com.easyhostel.backend.application.mapping.interfaces.IRoomMapper;
import com.easyhostel.backend.application.service.implementations.base.BaseService;
import com.easyhostel.backend.application.service.interfaces.room.IRoomService;
import com.easyhostel.backend.domain.entity.Room;
import com.easyhostel.backend.domain.repository.interfaces.contract.IContractRepository;
import com.easyhostel.backend.domain.repository.interfaces.room.IRoomRepository;
import com.easyhostel.backend.domain.service.interfaces.room.IRoomBusinessValidator;
import org.springframework.security.task.DelegatingSecurityContextAsyncTaskExecutor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Room modification service for implementing abstract methods and define dedicated methods
 *
 * @author Nyx
 */
@Service
public class RoomService extends BaseService<Room, RoomDto, RoomCreationDto, RoomUpdateDto, String> implements IRoomService {

    private final IRoomRepository _roomRepository;
    private final IRoomBusinessValidator _roomBusinessValidator;
    private final IRoomMapper _roomMapper;
    private final IContractRepository _contractRepository;

    private final DelegatingSecurityContextAsyncTaskExecutor _taskExecutor;

    public RoomService(IRoomRepository roomRepository,
                       IRoomBusinessValidator roomBusinessValidator,
                       IRoomMapper roomMapper,
                       IContractRepository contractRepository,
                       DelegatingSecurityContextAsyncTaskExecutor taskExecutor) {
        super(roomRepository, taskExecutor);
        _roomRepository = roomRepository;
        _roomBusinessValidator = roomBusinessValidator;
        _roomMapper = roomMapper;
        _contractRepository = contractRepository;
        _taskExecutor = taskExecutor;
    }

    @Override
    public CompletableFuture<Void> deleteByIdAsync(String roomId) {
        return validateDeletionBusinessAsync(roomId)
                .thenCompose(v -> CompletableFuture.runAsync(() -> {
                    var room = _roomRepository.findById(roomId).orElseThrow();

                    room.setHouse(null);
                    _roomRepository.save(room);
                    _roomRepository.deleteById(roomId);
                }));
    }

    @Override
    public CompletableFuture<Void> deleteContractFromRoomByIdAsync(String roomId, String contractId) {
        _roomBusinessValidator.checkIfContractBelongedToRoom(roomId, contractId);
        _roomBusinessValidator.checkIfRoomSupervisedByAuthUser(roomId);

        return CompletableFuture.runAsync(() -> {
           var contract = _contractRepository.findById(contractId).orElseThrow();

           // Delete contract
           contract.setRoom(null);
           _contractRepository.save(contract);
           _contractRepository.delete(contract);
        });
    }

    @Override
    public CompletableFuture<Void> validateGettingBusinessAsync(String roomId) {
        return CompletableFuture.runAsync(() -> {
            if (!_roomBusinessValidator.checkIsAuthenticatedUserSysadmin()) {
                _roomBusinessValidator.checkIfRoomSupervisedByAuthUser(roomId);
            }
        }, _taskExecutor);
    }

    @Override
    public CompletableFuture<Void> validateCreationBusiness(RoomCreationDto roomCreationDto) {
        return CompletableFuture.runAsync(_roomBusinessValidator::checkIfAuthenticatedUserNotSysadminThrowException, _taskExecutor);
    }

    @Override
    public CompletableFuture<Void> validateUpdateBusiness(RoomUpdateDto roomUpdateDto) {
        return CompletableFuture.runAsync(() -> {
            _roomBusinessValidator.checkIfAuthenticatedUserNotSysadminThrowException();
            _roomBusinessValidator.checkIfRoomExistedById(roomUpdateDto.getRoomId());
        }, _taskExecutor);
    }

    @Override
    public CompletableFuture<Void> validateDeletionBusinessAsync(String roomId) {
        return CompletableFuture.runAsync(() -> {
            _roomBusinessValidator.checkIfAuthenticatedUserNotSysadminThrowException();
            _roomBusinessValidator.checkIfRoomExistedById(roomId);
        }, _taskExecutor);
    }

    @Override
    public CompletableFuture<Void> validateDeletionManyBusinessAsync(List<String> roomIds) {
        return CompletableFuture.runAsync(() -> {
            _roomBusinessValidator.checkIfAuthenticatedUserNotSysadminThrowException();
            roomIds.forEach(_roomBusinessValidator::checkIfRoomExistedById);
        }, _taskExecutor);
    }

    @Override
    public Room mapCreationDtoToEntity(RoomCreationDto roomCreationDto) {
        return _roomMapper.mapRoomCreationDtoToRoom(roomCreationDto);
    }

    @Override
    public Room mapUpdateDtoToEntity(RoomUpdateDto roomUpdateDto) {
        return _roomMapper.mapRoomUpdateDtoToRoom(roomUpdateDto);
    }

    @Override
    public RoomDto mapEntityToDto(Room room) {
        return _roomMapper.mapRoomToRoomDTO(room);
    }

}
