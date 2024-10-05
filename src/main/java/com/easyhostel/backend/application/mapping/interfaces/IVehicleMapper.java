package com.easyhostel.backend.application.mapping.interfaces;

import com.easyhostel.backend.application.dto.vehicle.VehicleCreationDto;
import com.easyhostel.backend.application.dto.vehicle.VehicleDto;
import com.easyhostel.backend.application.dto.vehicle.VehicleUpdateDto;
import com.easyhostel.backend.domain.entity.Vehicle;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Mapper for Vehicle entity
 *
 * @author Nyx
 */
@Mapper(componentModel = "spring")
public interface IVehicleMapper {

    IVehicleMapper MAPPER = Mappers.getMapper(IVehicleMapper.class);

    VehicleDto mapVehicleToVehicleDto(Vehicle vehicle);

    Vehicle mapVehicleCreationDtoToVehicle(VehicleCreationDto vehicleCreationDto);

    Vehicle mapVehicleUpdateDtoToVehicle(VehicleUpdateDto vehicleUpdateDto);

}
