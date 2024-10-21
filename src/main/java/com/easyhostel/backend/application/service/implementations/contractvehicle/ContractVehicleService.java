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
                .thenComposeAsync(v -> CompletableFuture.supplyAsync(() -> {
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
                .thenComposeAsync(v -> CompletableFuture.supplyAsync(() -> {
                    // Delete old ContractVehicle
                    deleteContractVehicleByIdsAsync(contractVehicleUpdateDto.getContractId(), contractVehicleUpdateDto.getOldVehicleId());

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
    public CompletableFuture<Void> deleteContractVehicleByIdsAsync(String contractId, String vehicleId) {
        return validateDeletionBusinessAsync(contractId, vehicleId)
                .thenComposeAsync(v -> CompletableFuture.runAsync(() -> {
                    var contractVehicleId = new ContractVehicleId();
                    contractVehicleId.setContractId(contractId);
                    contractVehicleId.setVehicleId(vehicleId);

                    _contractVehicleRepository.deleteById(contractVehicleId);
                }));
    }

    @Override
    public ContractVehicle mapCreationDtoToEntity(ContractVehicleCreationDto contractVehicleCreationDto) {
        return _contractVehicleMapper.MAPPER.mapContractVehicleCreationDtoToContractVehicle(contractVehicleCreationDto);
    }

    @Override
    public ContractVehicle mapUpdateDtoToEntity(ContractVehicleUpdateDto contractVehicleUpdateDto) {
        return _contractVehicleMapper.MAPPER.mapContractVehicleUpdateDtoToContractVehicle(contractVehicleUpdateDto);
    }

    @Override
    public ContractVehicleDto mapEntityToDto(ContractVehicle contractVehicle) {
        return _contractVehicleMapper.MAPPER.mapContractVehicleToContractVehicleDto(contractVehicle);
    }

    @Override
    public CompletableFuture<Void> validateCreationBusiness(ContractVehicleCreationDto contractVehicleCreationDto) {
        var contractId = contractVehicleCreationDto.getContractId();
        var vehicleId = contractVehicleCreationDto.getVehicleId();

        return CompletableFuture
                .runAsync(() -> _contractVehicleBusinessValidator.checkIfContractAndVehicleExisted(contractId, vehicleId));
    }

    @Override
    public CompletableFuture<Void> validateUpdateBusiness(ContractVehicleUpdateDto contractVehicleUpdateDto) {
        var oldContractId = contractVehicleUpdateDto.getOldContractId();
        var oldVehicleId = contractVehicleUpdateDto.getOldVehicleId();

        var contractId = contractVehicleUpdateDto.getContractId();
        var vehicleId = contractVehicleUpdateDto.getVehicleId();

        return CompletableFuture
                .runAsync(() -> _contractVehicleBusinessValidator.checkIfContractVehicleExisted(oldContractId, oldVehicleId))
                .thenRunAsync(() -> _contractVehicleBusinessValidator.checkIfContractAndVehicleExisted(contractId, vehicleId));
    }

    @Override
    public CompletableFuture<Void> validateDeletionBusinessAsync(String contractId, String vehicleId) {
        return CompletableFuture
                .runAsync(() -> _contractVehicleBusinessValidator.checkIfContractVehicleExisted(contractId, vehicleId));
    }

}
