package com.easyhostel.backend.domain.service.implementation.contractvehicle;

import com.easyhostel.backend.domain.entity.embedded.ContractVehicleId;
import com.easyhostel.backend.domain.enums.ErrorCode;
import com.easyhostel.backend.domain.exception.EntityNotFoundException;
import com.easyhostel.backend.domain.repository.interfaces.contract.IContractRepository;
import com.easyhostel.backend.domain.repository.interfaces.contractvehicle.IContractVehicleRepository;
import com.easyhostel.backend.domain.repository.interfaces.vehicle.IVehicleRepository;
import com.easyhostel.backend.domain.service.interfaces.contractvehicle.IContractVehicleBusinessValidator;
import com.easyhostel.backend.infrastructure.configuration.Translator;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

/**
 * Implementation for ContractVehicle's business validator
 *
 * @author Nyx
 */
@Service
public class ContractVehicleBusinessValidator implements IContractVehicleBusinessValidator {

    private final IContractVehicleRepository _contractVehicleRepository;
    private final IContractRepository _contractRepository;
    private final IVehicleRepository _vehicleRepository;

    public ContractVehicleBusinessValidator(IContractVehicleRepository contractVehicleRepository,
                                            IContractRepository contractRepository,
                                            IVehicleRepository vehicleRepository) {
        _contractVehicleRepository = contractVehicleRepository;
        _contractRepository = contractRepository;
        _vehicleRepository = vehicleRepository;
    }

    @Override
    public void checkIfContractAndVehicleExisted(String contractId, String vehicleId) {
        var isContractExisted = _contractRepository.existsById(contractId);

        if (!isContractExisted) {
            throw new EntityNotFoundException(
                    Translator.toLocale("exception.contract.notFound"),
                    ErrorCode.ENTITY_NOT_FOUND,
                    HttpStatus.NOT_FOUND
            );
        }

        var isVehicleExisted = _vehicleRepository.existsById(vehicleId);

        if (!isVehicleExisted) {
            throw new EntityNotFoundException(
                    Translator.toLocale("exception.vehicle.notFound"),
                    ErrorCode.ENTITY_NOT_FOUND,
                    HttpStatus.NOT_FOUND
            );
        }
    }

    @Override
    public void checkIfContractVehicleExisted(String contractId, String vehicleId) {
        var contractVehicleId = new ContractVehicleId();
        contractVehicleId.setContractId(contractId);
        contractVehicleId.setVehicleId(vehicleId);

        var isContractVehicleExisted = _contractVehicleRepository.existsById(contractVehicleId);

        if (!isContractVehicleExisted) {
            throw new EntityNotFoundException(
                    Translator.toLocale("exception.contractVehicle.notFound"),
                    ErrorCode.ENTITY_NOT_FOUND,
                    HttpStatus.NOT_FOUND
            );
        }
    }

}
