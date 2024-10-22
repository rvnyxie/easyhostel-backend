package com.easyhostel.backend.application.service.implementations.vehicle;

import com.easyhostel.backend.application.dto.vehicle.VehicleCreationDto;
import com.easyhostel.backend.application.dto.vehicle.VehicleDto;
import com.easyhostel.backend.application.dto.vehicle.VehicleUpdateDto;
import com.easyhostel.backend.application.mapping.interfaces.IVehicleMapper;
import com.easyhostel.backend.application.service.implementations.base.BaseService;
import com.easyhostel.backend.application.service.interfaces.vehicle.IVehicleService;
import com.easyhostel.backend.domain.entity.Vehicle;
import com.easyhostel.backend.domain.repository.interfaces.vehicle.IVehicleRepository;
import com.easyhostel.backend.domain.service.interfaces.vehicle.IVehicleBusinessValidator;
import org.springframework.security.task.DelegatingSecurityContextAsyncTaskExecutor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Vehicle modification service for implementing abstract methods and define dedicated methods
 *
 * @author Nyx
 */
@Service
public class VehicleService extends BaseService<Vehicle, VehicleDto, VehicleCreationDto, VehicleUpdateDto, String> implements IVehicleService {

    private final IVehicleBusinessValidator _vehicleBusinessValidator;
    private final IVehicleMapper _vehicleMapper;

    private final DelegatingSecurityContextAsyncTaskExecutor _taskExecutor;

    public VehicleService(IVehicleRepository vehicleRepository,
                          IVehicleBusinessValidator vehicleBusinessValidator,
                          IVehicleMapper vehicleMapper,
                          DelegatingSecurityContextAsyncTaskExecutor taskExecutor) {
        super(vehicleRepository, taskExecutor);
        _vehicleBusinessValidator = vehicleBusinessValidator;
        _vehicleMapper = vehicleMapper;
        _taskExecutor = taskExecutor;
    }

    @Override
    public CompletableFuture<Void> validateCreationBusiness(VehicleCreationDto vehicleCreationDto) {
        return CompletableFuture.runAsync(_vehicleBusinessValidator::checkIfAuthenticatedUserNotSysadminThrowException, _taskExecutor);
    }

    @Override
    public CompletableFuture<Void> validateUpdateBusiness(VehicleUpdateDto vehicleUpdateDto) {
        return CompletableFuture.runAsync(_vehicleBusinessValidator::checkIfAuthenticatedUserNotSysadminThrowException, _taskExecutor);
    }

    @Override
    public CompletableFuture<Void> validateDeletionBusinessAsync(String vehicleId) {
        return CompletableFuture.runAsync(() -> {
            _vehicleBusinessValidator.checkIfAuthenticatedUserNotSysadminThrowException();
            _vehicleBusinessValidator.checkIfVehicleExistedById(vehicleId);
        }, _taskExecutor);
    }

    @Override
    public CompletableFuture<Void> validateDeletionManyBusinessAsync(List<String> vehicleIds) {
        return CompletableFuture.runAsync(() -> {
            _vehicleBusinessValidator.checkIfAuthenticatedUserNotSysadminThrowException();
            vehicleIds.forEach(_vehicleBusinessValidator::checkIfVehicleExistedById);
        }, _taskExecutor);
    }

    @Override
    public Vehicle mapCreationDtoToEntity(VehicleCreationDto vehicleCreationDto) {
        return _vehicleMapper.mapVehicleCreationDtoToVehicle(vehicleCreationDto);
    }

    @Override
    public Vehicle mapUpdateDtoToEntity(VehicleUpdateDto vehicleUpdateDto) {
        return _vehicleMapper.mapVehicleUpdateDtoToVehicle(vehicleUpdateDto);
    }

    @Override
    public VehicleDto mapEntityToDto(Vehicle vehicle) {
        return _vehicleMapper.mapVehicleToVehicleDto(vehicle);
    }

}
