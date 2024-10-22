package com.easyhostel.backend.domain.service.implementation.contractvehicle;

import com.easyhostel.backend.application.service.interfaces.authentication.IAuthenticationService;
import com.easyhostel.backend.domain.entity.Manager;
import com.easyhostel.backend.domain.entity.embedded.ContractVehicleId;
import com.easyhostel.backend.domain.enums.ErrorCode;
import com.easyhostel.backend.domain.exception.DuplicatedDistinctRequiredValueException;
import com.easyhostel.backend.domain.exception.EntityNotFoundException;
import com.easyhostel.backend.domain.exception.UnauthorizedAccessException;
import com.easyhostel.backend.domain.repository.interfaces.contract.IContractRepository;
import com.easyhostel.backend.domain.repository.interfaces.contractvehicle.IContractVehicleRepository;
import com.easyhostel.backend.domain.repository.interfaces.vehicle.IVehicleRepository;
import com.easyhostel.backend.domain.service.implementation.base.BaseBusinessValidator;
import com.easyhostel.backend.domain.service.interfaces.contract.IContractBusinessValidator;
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
public class ContractVehicleBusinessValidator extends BaseBusinessValidator implements IContractVehicleBusinessValidator {

    private final IAuthenticationService _authenticationService;
    private final IContractVehicleRepository _contractVehicleRepository;
    private final IContractRepository _contractRepository;
    private final IVehicleRepository _vehicleRepository;
    private final IContractBusinessValidator _contractBusinessValidator;

    public ContractVehicleBusinessValidator(IAuthenticationService authenticationService,
                                            IContractVehicleRepository contractVehicleRepository,
                                            IContractRepository contractRepository,
                                            IVehicleRepository vehicleRepository,
                                            IContractBusinessValidator contractBusinessValidator) {
        super(authenticationService);
        _authenticationService = authenticationService;
        _contractVehicleRepository = contractVehicleRepository;
        _contractRepository = contractRepository;
        _vehicleRepository = vehicleRepository;
        _contractBusinessValidator = contractBusinessValidator;
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
    public void checkIfContractVehicleExisted(ContractVehicleId contractVehicleId) {
        var isContractVehicleExisted = _contractVehicleRepository.existsById(contractVehicleId);

        if (!isContractVehicleExisted) {
            throw new EntityNotFoundException(
                    Translator.toLocale("exception.contractVehicle.notFound"),
                    ErrorCode.ENTITY_NOT_FOUND,
                    HttpStatus.NOT_FOUND
            );
        }
    }

    @Override
    public void checkIfContractVehicleAccessibleByAuthUser(ContractVehicleId contractVehicleId) {
        var currentAuthUser = (Manager) _authenticationService.getAuthentication().getPrincipal();

        var contract = _contractRepository.findById(contractVehicleId.getContractId()).orElseThrow();

        var isContractVehicleAccessibleByAuthUser = currentAuthUser.getManagerHouses()
                .stream()
                .anyMatch(managerHouse -> managerHouse.getHouse().getHouseId().equals(
                        contract.getRoom().getHouse().getHouseId()));

        if (!isContractVehicleAccessibleByAuthUser) {
            throw new UnauthorizedAccessException(
                    Translator.toLocale("exception.contractVehicleNotAccessibleByAuthUser"),
                    ErrorCode.FORBIDDEN_ACCESS,
                    HttpStatus.FORBIDDEN
            );
        }
    }

    @Override
    public void checkIfContractVehicleExistedThrowException(ContractVehicleId contractVehicleId) {
        var isContractVehicleExisted = _contractVehicleRepository.existsById(contractVehicleId);

        if (isContractVehicleExisted) {
            throw new DuplicatedDistinctRequiredValueException(
                    Translator.toLocale("exception.contractVehicle.existed"),
                    ErrorCode.DUPLICATED_VALUE,
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    @Override
    public void checkIfContractAndVehicleAccessibleByAuthUser(String contractId, String vehicleId) {
        // Vehicle is accessible for any authenticated user
        _contractBusinessValidator.checkIfContractManageableByAuthUser(contractId);
    }

}
