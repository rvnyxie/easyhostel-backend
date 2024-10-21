package com.easyhostel.backend.application.service.implementations.house;

import com.easyhostel.backend.application.dto.house.HouseCreationDto;
import com.easyhostel.backend.application.dto.house.HouseDto;
import com.easyhostel.backend.application.dto.house.HouseUpdateDto;
import com.easyhostel.backend.application.mapping.interfaces.IHouseMapper;
import com.easyhostel.backend.application.service.implementations.base.BaseService;
import com.easyhostel.backend.application.service.interfaces.house.IHouseService;
import com.easyhostel.backend.domain.entity.House;
import com.easyhostel.backend.domain.repository.interfaces.house.IHouseRepository;
import com.easyhostel.backend.domain.repository.interfaces.room.IRoomRepository;
import com.easyhostel.backend.domain.service.interfaces.house.IHouseBusinessValidator;
import jakarta.transaction.Transactional;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.task.DelegatingSecurityContextAsyncTaskExecutor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * House service for implementing abstract methods and define dedicated methods
 *
 * @author Nyx
 */
@Service
@Primary
public class HouseService extends BaseService<House, HouseDto, HouseCreationDto, HouseUpdateDto, String> implements IHouseService {

    private final IHouseRepository _houseRepository;
    private final IHouseBusinessValidator _houseBusinessValidator;
    private final IHouseMapper _houseMapper;
    private final IRoomRepository _roomRepository;
    private final DelegatingSecurityContextAsyncTaskExecutor _taskExecutor;

    public HouseService(
            IHouseRepository houseRepository,
            IHouseBusinessValidator businessValidator,
            IHouseMapper houseMapper,
            IRoomRepository roomRepository,
            DelegatingSecurityContextAsyncTaskExecutor taskExecutor) {
        super(houseRepository, taskExecutor);
        _houseRepository = houseRepository;
        _houseBusinessValidator = businessValidator;
        _houseMapper = houseMapper;
        _roomRepository = roomRepository;
        _taskExecutor = taskExecutor;
    }

    @Override
    public CompletableFuture<HouseDto> insertAsync(HouseCreationDto houseCreationDto) {
        return validateCreationBusiness(houseCreationDto)
            .thenCompose(v -> CompletableFuture.supplyAsync(() -> {
                var house = mapCreationDtoToEntity(houseCreationDto);

                var savedHouse = _houseRepository.save(house);

                return mapEntityToDto(savedHouse);
            }));
    }

    @Override
    @Transactional
    @Async
    public CompletableFuture<Void> deleteRoomFromHouseByIdAsync(String houseId, String roomId) {
        _houseBusinessValidator.checkIsRoomBelongedToHouse(houseId, roomId);

        return validateDeletionBusinessAsync(houseId)
                .thenCompose(v -> CompletableFuture.runAsync(() -> {
                    var room = _roomRepository.findById(roomId).orElseThrow();

                    // To delete room, we don't need to save house
                    room.setHouse(null);
                    // Save room first, because we changed house reference to null
                    _roomRepository.save(room);
                    _roomRepository.delete(room);
                }));
    }

    @Override
    public House mapCreationDtoToEntity(HouseCreationDto houseCreationDto) {
        return _houseMapper.mapHouseCreationDtoToHouse(houseCreationDto);
    }

    @Override
    public House mapUpdateDtoToEntity(HouseUpdateDto houseUpdateDto) {
        return _houseMapper.mapHouseUpdateDtoToHouse(houseUpdateDto);
    }

    @Override
    public HouseDto mapEntityToDto(House house) {
        return _houseMapper.mapHouseToHouseDto(house);
    }

    @Override
    public CompletableFuture<Void> validateGettingBusinessAsync(String houseId) {
        return CompletableFuture.runAsync(() -> {
            if (!_houseBusinessValidator.checkIsAuthenticatedUserSysadmin()) {
                _houseBusinessValidator.checkIfHouseSupervisedByAuthUser(houseId);
            }
        }, _taskExecutor);
    }

    @Override
    public CompletableFuture<Void> validateCreationBusiness(HouseCreationDto houseCreationDto) {
        return CompletableFuture.runAsync(_houseBusinessValidator::checkIfAuthenticatedUserNotSysadminThrowException, _taskExecutor);
    }

    @Override
    public CompletableFuture<Void> validateUpdateBusiness(HouseUpdateDto houseUpdateDto) {
        return CompletableFuture.runAsync(() -> {
            _houseBusinessValidator.checkIfAuthenticatedUserNotSysadminThrowException();
            _houseBusinessValidator.checkIfHouseExistedFromId(houseUpdateDto.getHouseId());
        }, _taskExecutor);
    }

    @Override
    public CompletableFuture<Void> validateDeletionBusinessAsync(String houseId) {
        return CompletableFuture.runAsync(() -> {
            _houseBusinessValidator.checkIfAuthenticatedUserNotSysadminThrowException();
            _houseBusinessValidator.checkIfHouseExistedFromId(houseId);
        }, _taskExecutor);
    }

    @Override
    public CompletableFuture<Void> validateDeletionManyBusinessAsync(List<String> houseIds) {
        // TODO: if there's House's ID not existed, it should delete what present and return the ids not deletable
        return CompletableFuture.runAsync(() -> {
            _houseBusinessValidator.checkIfAuthenticatedUserNotSysadminThrowException();
            houseIds.forEach(_houseBusinessValidator::checkIfHouseExistedFromId);
        }, _taskExecutor);
    }
}
