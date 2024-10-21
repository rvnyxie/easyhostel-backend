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

import java.util.concurrent.CompletableFuture;

/**
 * Vehicle modification service for implementing abstract methods and define dedicated methods
 *
 * @author Nyx
 */
@Service
public class VehicleService extends BaseService<Vehicle, VehicleDto, VehicleCreationDto, VehicleUpdateDto, String> implements IVehicleService {

    private final IVehicleRepository _vehicleRepository;
    private final IVehicleBusinessValidator _vehicleBusinessValidator;
    private final IVehicleMapper _vehicleMapper;

    private final DelegatingSecurityContextAsyncTaskExecutor _taskExecutor;

    public VehicleService(IVehicleRepository vehicleRepository,
                          IVehicleBusinessValidator vehicleBusinessValidator,
                          IVehicleMapper vehicleMapper,
                          DelegatingSecurityContextAsyncTaskExecutor taskExecutor) {
        super(vehicleRepository, taskExecutor);
        _vehicleRepository = vehicleRepository;
        _vehicleBusinessValidator = vehicleBusinessValidator;
        _vehicleMapper = vehicleMapper;
        _taskExecutor = taskExecutor;
    }

    @Override
    public Vehicle mapCreationDtoToEntity(VehicleCreationDto vehicleCreationDto) {
        var vehicle = _vehicleMapper.MAPPER.mapVehicleCreationDtoToVehicle(vehicleCreationDto);

        return vehicle;
    }

    @Override
    public Vehicle mapUpdateDtoToEntity(VehicleUpdateDto vehicleUpdateDto) {
        var vehicle = _vehicleMapper.MAPPER.mapVehicleUpdateDtoToVehicle(vehicleUpdateDto);

        return vehicle;
    }

    @Override
    public VehicleDto mapEntityToDto(Vehicle vehicle) {
        var vehicleDto = _vehicleMapper.MAPPER.mapVehicleToVehicleDto(vehicle);

        return vehicleDto;
    }

    // TODO: Add business creation validation for Vehicle
    @Override
    public CompletableFuture<Void> validateCreationBusiness(VehicleCreationDto vehicleCreationDto) {
        return super.validateCreationBusiness(vehicleCreationDto);
    }

    // TODO: Add business creation validation for Vehicle
    @Override
    public CompletableFuture<Void> validateUpdateBusiness(VehicleUpdateDto vehicleUpdateDto) {
        return super.validateUpdateBusiness(vehicleUpdateDto);
    }
}
