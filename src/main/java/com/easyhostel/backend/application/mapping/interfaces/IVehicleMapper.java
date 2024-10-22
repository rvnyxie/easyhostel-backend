package com.easyhostel.backend.application.mapping.interfaces;

import com.easyhostel.backend.application.dto.vehicle.VehicleCreationDto;
import com.easyhostel.backend.application.dto.vehicle.VehicleDto;
import com.easyhostel.backend.application.dto.vehicle.VehicleUpdateDto;
import com.easyhostel.backend.domain.entity.Vehicle;
import org.mapstruct.Mapper;

/**
 * Mapper for Vehicle entity
 *
 * @author Nyx
 */
@Mapper(componentModel = "spring")
public interface IVehicleMapper {

    //region General

    VehicleDto mapVehicleToVehicleDto(Vehicle vehicle);

    //endregion

    //region Map creation

    Vehicle mapVehicleCreationDtoToVehicle(VehicleCreationDto vehicleCreationDto);

    //endregion

    //region Map update

    Vehicle mapVehicleUpdateDtoToVehicle(VehicleUpdateDto vehicleUpdateDto);

    //endregion

}
