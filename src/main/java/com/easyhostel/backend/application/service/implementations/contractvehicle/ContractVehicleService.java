package com.easyhostel.backend.application.service.implementations.contractvehicle;

import com.easyhostel.backend.application.dto.contractvehicle.ContractVehicleCreationDto;
import com.easyhostel.backend.application.dto.contractvehicle.ContractVehicleDto;
import com.easyhostel.backend.application.dto.contractvehicle.ContractVehicleUpdateDto;
import com.easyhostel.backend.application.mapping.interfaces.IContractVehicleMapper;
import com.easyhostel.backend.application.service.implementations.base.BaseService;
import com.easyhostel.backend.application.service.interfaces.contractvehicle.IContractVehicleService;
import com.easyhostel.backend.domain.entity.ContractVehicle;
import com.easyhostel.backend.domain.entity.embedded.ContractVehicleId;
import com.easyhostel.backend.domain.repository.interfaces.contract.IContractRepository;
import com.easyhostel.backend.domain.repository.interfaces.contractvehicle.IContractVehicleRepository;
import com.easyhostel.backend.domain.repository.interfaces.vehicle.IVehicleRepository;
import com.easyhostel.backend.domain.service.interfaces.contractvehicle.IContractVehicleBusinessValidator;
import jakarta.transaction.Transactional;
import org.springframework.security.task.DelegatingSecurityContextAsyncTaskExecutor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * ContractVehicle modification service for implementing abstract methods and define dedicated methods
 *
 * @author Nyx
 */
@Service
public class ContractVehicleService extends BaseService<ContractVehicle, ContractVehicleDto, ContractVehicleCreationDto, ContractVehicleUpdateDto, ContractVehicleId> implements IContractVehicleService {

    private final IContractVehicleRepository _contractVehicleRepository;
    private final IContractVehicleBusinessValidator _contractVehicleBusinessValidator;
    private final IContractVehicleMapper _contractVehicleMapper;

    private final IContractRepository _contractRepository;
    private final IVehicleRepository _vehicleRepository;

    private final DelegatingSecurityContextAsyncTaskExecutor _taskExecutor;

    public ContractVehicleService(IContractVehicleRepository contractVehicleRepository,
                                  IContractVehicleBusinessValidator contractVehicleBusinessValidator,
                                  IContractVehicleMapper contractVehicleMapper,
                                  IContractRepository contractRepository,
                                  IVehicleRepository vehicleRepository,
                                  DelegatingSecurityContextAsyncTaskExecutor taskExecutor) {
        super(contractVehicleRepository, taskExecutor);
        _contractVehicleRepository = contractVehicleRepository;
        _contractVehicleBusinessValidator = contractVehicleBusinessValidator;
        _contractVehicleMapper = contractVehicleMapper;
        _contractRepository = contractRepository;
        _vehicleRepository = vehicleRepository;
        _taskExecutor = taskExecutor;
    }

    @Override
    public CompletableFuture<ContractVehicleDto> insertAsync(ContractVehicleCreationDto contractVehicleCreationDto) {
        return validateCreationBusiness(contractVehicleCreationDto)
                .thenCompose(v -> CompletableFuture.supplyAsync(() -> {
                    // Get ContractId and VehicleId
                    var contractId = contractVehicleCreationDto.getContractId();
                    var vehicleId = contractVehicleCreationDto.getVehicleId();

                    // Create ContractVehicleId
                    var contractVehicleId = new ContractVehicleId();
                    contractVehicleId.setContractId(contractId);
                    contractVehicleId.setVehicleId(vehicleId);

                    // Get Contract and Vehicle
                    var contract = _contractRepository.findById(contractId).orElseThrow();
                    var vehicle = _vehicleRepository.findById(vehicleId).orElseThrow();

                    var contractVehicle = mapCreationDtoToEntity(contractVehicleCreationDto);

                    // Set value and references
                    contractVehicle.setContractVehicleId(contractVehicleId);
                    contractVehicle.setContract(contract);
                    contractVehicle.setVehicle(vehicle);

                    // Insert
                    var savedContractVehicle = _contractVehicleRepository.save(contractVehicle);

                    return mapEntityToDto(savedContractVehicle);
                }));
    }

    @Override
    @Transactional
    public CompletableFuture<ContractVehicleDto> updateAsync(ContractVehicleUpdateDto contractVehicleUpdateDto) {
        return validateUpdateBusiness(contractVehicleUpdateDto)
                .thenCompose(v -> CompletableFuture.supplyAsync(() -> {
                    // Delete old ContractVehicle
                    var contractVehicleIdToDelete = ContractVehicleId.builder()
                            .contractId(contractVehicleUpdateDto.getOldContractId())
                            .vehicleId(contractVehicleUpdateDto.getOldVehicleId())
                            .build();
                    deleteContractVehicleByIdsAsync(contractVehicleIdToDelete);

                    // Insert new ContractVehicle
                    // Get new ContractId and VehicleId
                    var newContractId = contractVehicleUpdateDto.getContractId();
                    var newVehicleId = contractVehicleUpdateDto.getVehicleId();

                    var newContractVehicleId = ContractVehicleId.builder()
                            .contractId(newContractId)
                            .vehicleId(newVehicleId)
                            .build();

                    var contract = _contractRepository.findById(newContractId).orElseThrow();
                    var vehicle = _vehicleRepository.findById(newVehicleId).orElseThrow();

                    var newContractVehicle = mapUpdateDtoToEntity(contractVehicleUpdateDto);

                    // Set value and references
                    newContractVehicle.setContractVehicleId(newContractVehicleId);
                    newContractVehicle.setContract(contract);
                    newContractVehicle.setVehicle(vehicle);

                    // Insert
                    var savedContractVehicle = _contractVehicleRepository.save(newContractVehicle);

                    return mapEntityToDto(savedContractVehicle);
                }));
    }

    @Override
    @Transactional
    public CompletableFuture<Void> deleteContractVehicleByIdsAsync(ContractVehicleId contractVehicleId) {
        return validateDeletionBusinessAsync(contractVehicleId)
                .thenCompose(v -> CompletableFuture.runAsync(() -> {
                    _contractVehicleRepository.deleteById(contractVehicleId);
                }));
    }

    @Override
    public CompletableFuture<Void> validateGettingBusinessAsync(ContractVehicleId contractVehicleId) {
        return CompletableFuture.runAsync(() -> {
            _contractVehicleBusinessValidator.checkIfContractVehicleExisted(contractVehicleId);

            if (!_contractVehicleBusinessValidator.checkIsAuthenticatedUserSysadmin()) {
                _contractVehicleBusinessValidator.checkIfContractVehicleAccessibleByAuthUser(contractVehicleId);
            }
        }, _taskExecutor);
    }

    @Override
    public CompletableFuture<Void> validateCreationBusiness(ContractVehicleCreationDto contractVehicleCreationDto) {
        var contractId = contractVehicleCreationDto.getContractId();
        var vehicleId = contractVehicleCreationDto.getVehicleId();
        var contractVehicleId = ContractVehicleId.builder()
                .contractId(contractId)
                .vehicleId(vehicleId)
                .build();

        return CompletableFuture.runAsync(() -> {
            _contractVehicleBusinessValidator.checkIfContractAndVehicleExisted(contractId, vehicleId);
            _contractVehicleBusinessValidator.checkIfContractVehicleExistedThrowException(contractVehicleId);

            if (!_contractVehicleBusinessValidator.checkIsAuthenticatedUserSysadmin()) {
                _contractVehicleBusinessValidator.checkIfContractAndVehicleAccessibleByAuthUser(contractId, vehicleId);
            }
        }, _taskExecutor);
    }

    @Override
    public CompletableFuture<Void> validateUpdateBusiness(ContractVehicleUpdateDto contractVehicleUpdateDto) {
        var oldContractVehicleId = ContractVehicleId.builder()
                .contractId(contractVehicleUpdateDto.getOldContractId())
                .vehicleId(contractVehicleUpdateDto.getOldVehicleId())
                .build();

        var newContractId = contractVehicleUpdateDto.getContractId();
        var newVehicleId = contractVehicleUpdateDto.getVehicleId();

        return CompletableFuture.runAsync(() -> {
            _contractVehicleBusinessValidator.checkIfContractVehicleExisted(oldContractVehicleId);
            _contractVehicleBusinessValidator.checkIfContractAndVehicleExisted(newContractId, newVehicleId);

            if (!_contractVehicleBusinessValidator.checkIsAuthenticatedUserSysadmin()) {
                _contractVehicleBusinessValidator.checkIfContractVehicleAccessibleByAuthUser(oldContractVehicleId);
                _contractVehicleBusinessValidator.checkIfContractAndVehicleAccessibleByAuthUser(newContractId, newVehicleId);
            }
        }, _taskExecutor);
    }

    @Override
    public CompletableFuture<Void> validateDeletionBusinessAsync(ContractVehicleId contractVehicleId) {
        return CompletableFuture.runAsync(() -> {
            _contractVehicleBusinessValidator.checkIfContractVehicleExisted(contractVehicleId);

            if (_contractVehicleBusinessValidator.checkIsAuthenticatedUserSysadmin()) {
                _contractVehicleBusinessValidator.checkIfContractVehicleAccessibleByAuthUser(contractVehicleId);
            }
        }, _taskExecutor);
    }

    @Override
    public CompletableFuture<Void> validateDeletionManyBusinessAsync(List<ContractVehicleId> contractVehicleIds) {
        return CompletableFuture.runAsync(() -> {
            contractVehicleIds.forEach(_contractVehicleBusinessValidator::checkIfContractVehicleExisted);

            if (_contractVehicleBusinessValidator.checkIsAuthenticatedUserSysadmin()) {
                contractVehicleIds.forEach(_contractVehicleBusinessValidator::checkIfContractVehicleAccessibleByAuthUser);
            }
        }, _taskExecutor);
    }

    @Override
    public ContractVehicle mapCreationDtoToEntity(ContractVehicleCreationDto contractVehicleCreationDto) {
        return _contractVehicleMapper.mapContractVehicleCreationDtoToContractVehicle(contractVehicleCreationDto);
    }

    @Override
    public ContractVehicle mapUpdateDtoToEntity(ContractVehicleUpdateDto contractVehicleUpdateDto) {
        return _contractVehicleMapper.mapContractVehicleUpdateDtoToContractVehicle(contractVehicleUpdateDto);
    }

    @Override
    public ContractVehicleDto mapEntityToDto(ContractVehicle contractVehicle) {
        return _contractVehicleMapper.mapContractVehicleToContractVehicleDto(contractVehicle);
    }

}
