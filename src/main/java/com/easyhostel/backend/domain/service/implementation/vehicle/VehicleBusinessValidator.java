package com.easyhostel.backend.domain.service.implementation.vehicle;

import com.easyhostel.backend.application.service.interfaces.authentication.IAuthenticationService;
import com.easyhostel.backend.domain.enums.ErrorCode;
import com.easyhostel.backend.domain.exception.EntityNotFoundException;
import com.easyhostel.backend.domain.repository.interfaces.vehicle.IVehicleRepository;
import com.easyhostel.backend.domain.service.implementation.base.BaseBusinessValidator;
import com.easyhostel.backend.domain.service.interfaces.vehicle.IVehicleBusinessValidator;
import com.easyhostel.backend.infrastructure.configuration.Translator;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

/**
 * Implementation for Vehicle's business validator
 *
 * @author Nyx
 */
@Service
public class VehicleBusinessValidator extends BaseBusinessValidator implements IVehicleBusinessValidator {

    private final IVehicleRepository _vehicleRepository;

    public VehicleBusinessValidator(IAuthenticationService _authenticationService,
                                    IVehicleRepository vehicleRepository) {
        super(_authenticationService);
        _vehicleRepository = vehicleRepository;
    }

    @Override
    public void checkIfVehicleExistedById(String vehicleId) {
        var isVehicleExisted = _vehicleRepository.existsById(vehicleId);

        if (!isVehicleExisted) {
            throw new EntityNotFoundException(
                    Translator.toLocale("exception.vehicle.notFound"),
                    ErrorCode.ENTITY_NOT_FOUND,
                    HttpStatus.NOT_FOUND
            );
        }
    }
}
