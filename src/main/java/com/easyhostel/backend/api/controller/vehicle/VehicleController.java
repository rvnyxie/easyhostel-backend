package com.easyhostel.backend.api.controller.vehicle;

import com.easyhostel.backend.api.controller.base.BaseController;
import com.easyhostel.backend.application.dto.vehicle.VehicleCreationDto;
import com.easyhostel.backend.application.dto.vehicle.VehicleDto;
import com.easyhostel.backend.application.dto.vehicle.VehicleUpdateDto;
import com.easyhostel.backend.application.service.interfaces.vehicle.IVehicleService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for Vehicle
 *
 * @author Nyx
 */
@RestController
@RequestMapping("${api.base-path}/vehicles")
@Tag(name = "Vehicle Controller")
public class VehicleController extends BaseController<VehicleDto, VehicleCreationDto, VehicleUpdateDto, String> {

    private final IVehicleService _vehicleService;

    public VehicleController(IVehicleService vehicleService) {
        super(vehicleService);
        _vehicleService = vehicleService;
    }

}
