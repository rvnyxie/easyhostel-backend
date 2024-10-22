package com.easyhostel.backend.application.service.implementations.vehicle;

import com.easyhostel.backend.application.dto.vehicle.VehicleDto;
import com.easyhostel.backend.application.mapping.interfaces.IVehicleMapper;
import com.easyhostel.backend.application.service.implementations.base.BaseReadonlyService;
import com.easyhostel.backend.application.service.interfaces.vehicle.IVehicleReadonlyService;
import com.easyhostel.backend.domain.entity.Vehicle;
import com.easyhostel.backend.domain.repository.interfaces.vehicle.IVehicleReadonlyRepository;
import org.springframework.security.task.DelegatingSecurityContextAsyncTaskExecutor;
import org.springframework.stereotype.Service;

/**
 * Vehicle readonly service for implementing abstract methods and define dedicated methods
 *
 * @author Nyx
 */
@Service
public class VehicleReadonlyService extends BaseReadonlyService<Vehicle, VehicleDto, String> implements IVehicleReadonlyService {

    private final IVehicleMapper _vehicleMapper;

    public VehicleReadonlyService(IVehicleReadonlyRepository vehicleReadonlyRepository,
                                  IVehicleMapper vehicleMapper,
                                  DelegatingSecurityContextAsyncTaskExecutor taskExecutor) {
        super(vehicleReadonlyRepository, taskExecutor);
        _vehicleMapper = vehicleMapper;
    }

    @Override
    public VehicleDto mapEntityToDto(Vehicle vehicle) {
        return _vehicleMapper.mapVehicleToVehicleDto(vehicle);
    }
}
