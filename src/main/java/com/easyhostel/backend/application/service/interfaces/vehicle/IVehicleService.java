package com.easyhostel.backend.application.service.interfaces.vehicle;

import com.easyhostel.backend.application.dto.vehicle.VehicleCreationDto;
import com.easyhostel.backend.application.dto.vehicle.VehicleDto;
import com.easyhostel.backend.application.dto.vehicle.VehicleUpdateDto;
import com.easyhostel.backend.application.service.interfaces.base.IBaseService;

/**
 * Interface for Vehicle service, extends base GET, INSERT, UPDATE, DELETE methods
 *
 * @author Nyx
 */
public interface IVehicleService extends IBaseService<VehicleDto, VehicleCreationDto, VehicleUpdateDto, String> {
}
