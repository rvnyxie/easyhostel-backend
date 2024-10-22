package com.easyhostel.backend.application.service.implementations.contractinterior;

import com.easyhostel.backend.application.dto.contractinterior.ContractInteriorCreationDto;
import com.easyhostel.backend.application.dto.contractinterior.ContractInteriorDto;
import com.easyhostel.backend.application.dto.contractinterior.ContractInteriorUpdateDto;
import com.easyhostel.backend.application.mapping.interfaces.IContractInteriorMapper;
import com.easyhostel.backend.application.service.implementations.base.BaseService;
import com.easyhostel.backend.application.service.interfaces.contractinterior.IContractInteriorService;
import com.easyhostel.backend.domain.entity.ContractInterior;
import com.easyhostel.backend.domain.entity.embedded.ContractInteriorId;
import com.easyhostel.backend.domain.repository.interfaces.contract.IContractRepository;
import com.easyhostel.backend.domain.repository.interfaces.contractinterior.IContractInteriorRepository;
import com.easyhostel.backend.domain.repository.interfaces.interior.IInteriorRepository;
import com.easyhostel.backend.domain.service.interfaces.contractinterior.IContractInteriorBusinessValidator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.task.DelegatingSecurityContextAsyncTaskExecutor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * ContractInterior modification service for implementing abstract methods and define dedicated methods
 *
 * @author Nyx
 */
@Service
public class ContractInteriorService extends BaseService<ContractInterior, ContractInteriorDto, ContractInteriorCreationDto, ContractInteriorUpdateDto, ContractInteriorId> implements IContractInteriorService {

    private final IContractInteriorRepository _contractInteriorRepository;
    private final IContractInteriorBusinessValidator _contractInteriorBusinessValidator;
    private final IContractInteriorMapper _contractInteriorMapper;

    private final IContractRepository _contractRepository;
    private final IInteriorRepository _interiorRepository;

    private final DelegatingSecurityContextAsyncTaskExecutor _taskExecutor;

    public ContractInteriorService(IContractInteriorRepository contractInteriorRepository,
                                   IContractInteriorBusinessValidator contractInteriorBusinessValidator,
                                   IContractInteriorMapper contractInteriorMapper,
                                   IContractRepository contractRepository,
                                   IInteriorRepository interiorRepository,
                                   DelegatingSecurityContextAsyncTaskExecutor taskExecutor) {
        super(contractInteriorRepository, taskExecutor);
        _contractInteriorRepository = contractInteriorRepository;
        _contractInteriorBusinessValidator = contractInteriorBusinessValidator;
        _contractInteriorMapper = contractInteriorMapper;
        _contractRepository = contractRepository;
        _interiorRepository = interiorRepository;
        _taskExecutor = taskExecutor;
    }

    @Override
    @Async
    public CompletableFuture<ContractInteriorDto> insertAsync(ContractInteriorCreationDto contractInteriorCreationDto) {
        return validateCreationBusiness(contractInteriorCreationDto)
                .thenComposeAsync(v -> CompletableFuture.supplyAsync(() -> {
                    var contractInterior = mapCreationDtoToEntity(contractInteriorCreationDto);

                    // Get Contract and Interior
                    var contract = _contractRepository.findById(contractInteriorCreationDto.getContractId()).orElseThrow();
                    var interior = _interiorRepository.findById(contractInteriorCreationDto.getInteriorId()).orElseThrow();

                    // Create ContractInteriorId before inserting
                    var contractInteriorId = new ContractInteriorId();
                    contractInteriorId.setContractId(contract.getContractId());
                    contractInteriorId.setInteriorId(interior.getInteriorId());

                    // Set ID and references for ContractInterior
                    contractInterior.setContractInteriorId(contractInteriorId);
                    contractInterior.setContract(contract);
                    contractInterior.setInterior(interior);

                    // Insert
                    var savedContractInterior = _contractInteriorRepository.save(contractInterior);

                    return mapEntityToDto(savedContractInterior);
                }));
    }

    @Override
    @Async
    public CompletableFuture<ContractInteriorDto> updateAsync(ContractInteriorUpdateDto contractInteriorUpdateDto) {
        return validateUpdateBusiness(contractInteriorUpdateDto)
                .thenComposeAsync(v -> CompletableFuture.supplyAsync(() -> {
                    // When updating ContractInterior, it means we change either ContractId or InteriorId
                    // In our case, it means we save new ContractInterior and delete the old one

                    // Delete old ContractInterior
                    var oldContractInteriorIdToDelete = ContractInteriorId.builder()
                            .contractId(contractInteriorUpdateDto.getOldContractId())
                            .interiorId(contractInteriorUpdateDto.getOldInteriorId())
                            .build();
                    deleteContractInteriorByIdsAsync(oldContractInteriorIdToDelete);

                    // Create new ContractInterior
                    var newContractInterior = mapUpdateDtoToEntity(contractInteriorUpdateDto);

                    var newContractId = contractInteriorUpdateDto.getContractId();
                    var newInteriorId = contractInteriorUpdateDto.getInteriorId();

                    // Set up new ID for ContractInterior
                    var newContractInteriorId = new ContractInteriorId();
                    newContractInteriorId.setContractId(newContractId);
                    newContractInteriorId.setInteriorId(newInteriorId);

                    // Search Contract and Interior
                    var contract = _contractRepository.findById(newContractId).orElseThrow();
                    var interior = _interiorRepository.findById(newInteriorId).orElseThrow();

                    // Assign new fields value and references
                    newContractInterior.setContractInteriorId(newContractInteriorId);
                    newContractInterior.setContract(contract);
                    newContractInterior.setInterior(interior);

                    // Insert
                    var savedContractInterior = _contractInteriorRepository.save(newContractInterior);

                    return mapEntityToDto(savedContractInterior);
                }));
    }

    @Override
    @Async
    public CompletableFuture<Void> deleteContractInteriorByIdsAsync(ContractInteriorId contractInteriorId) {
        return validateDeletionBusinessAsync(contractInteriorId)
                .thenComposeAsync(v -> CompletableFuture.runAsync(() -> {
                    _contractInteriorRepository.deleteById(contractInteriorId);
                }));
    }

    @Override
    public CompletableFuture<Void> validateGettingBusinessAsync(ContractInteriorId contractInteriorId) {
        return CompletableFuture.runAsync(() -> {
            _contractInteriorBusinessValidator.checkIfContractInteriorExistedByIds(contractInteriorId);

            if (!_contractInteriorBusinessValidator.checkIsAuthenticatedUserSysadmin()) {
                _contractInteriorBusinessValidator.checkIfContractInteriorAccessibleByAuthUser(contractInteriorId);
            }
        }, _taskExecutor);
    }

    @Override
    @Async
    public CompletableFuture<Void> validateCreationBusiness(ContractInteriorCreationDto contractInteriorCreationDto) {
        var contractId = contractInteriorCreationDto.getContractId();
        var interiorId = contractInteriorCreationDto.getInteriorId();

        var contractInteriorId = ContractInteriorId.builder().contractId(contractId).interiorId(interiorId).build();

        return CompletableFuture.runAsync(() -> {
            _contractInteriorBusinessValidator.checkIfContractAndInteriorExistedByIds(contractId, interiorId);
            _contractInteriorBusinessValidator.checkIfContractInteriorExistedThrowException(contractInteriorId);

            if (!_contractInteriorBusinessValidator.checkIsAuthenticatedUserSysadmin()) {
                _contractInteriorBusinessValidator.checkIfContractInteriorAccessibleByAuthUser(contractInteriorId);
            }
        }, _taskExecutor);
    }

    @Override
    @Async
    public CompletableFuture<Void> validateUpdateBusiness(ContractInteriorUpdateDto contractInteriorUpdateDto) {
        var oldContractInteriorId = ContractInteriorId.builder()
                .contractId(contractInteriorUpdateDto.getOldContractId())
                .interiorId(contractInteriorUpdateDto.getOldInteriorId())
                .build();

        var newContractId = contractInteriorUpdateDto.getContractId();
        var newInteriorId = contractInteriorUpdateDto.getInteriorId();

        return CompletableFuture.runAsync(() -> {
            _contractInteriorBusinessValidator.checkIfContractInteriorExistedByIds(oldContractInteriorId);
            _contractInteriorBusinessValidator.checkIfContractAndInteriorExistedByIds(newContractId, newInteriorId);

            if (!_contractInteriorBusinessValidator.checkIsAuthenticatedUserSysadmin()) {
                _contractInteriorBusinessValidator.checkIfContractInteriorAccessibleByAuthUser(oldContractInteriorId);
                _contractInteriorBusinessValidator.checkIfContractAndInteriorAccessibleByAuthUser(newContractId, newInteriorId);
            }
        }, _taskExecutor);
    }

    @Override
    @Async
    public CompletableFuture<Void> validateDeletionBusinessAsync(ContractInteriorId contractInteriorId) {
        return CompletableFuture.runAsync(() -> {
            if (!_contractInteriorBusinessValidator.checkIsAuthenticatedUserSysadmin()) {
                _contractInteriorBusinessValidator.checkIfContractInteriorAccessibleByAuthUser(contractInteriorId);
            }

            _contractInteriorBusinessValidator.checkIfContractInteriorExistedByIds(contractInteriorId);
        }, _taskExecutor);
    }

    @Override
    public CompletableFuture<Void> validateDeletionManyBusinessAsync(List<ContractInteriorId> contractInteriorIds) {
        return CompletableFuture.runAsync(() -> {
            contractInteriorIds.forEach(_contractInteriorBusinessValidator::checkIfContractInteriorExistedByIds);

            if (!_contractInteriorBusinessValidator.checkIsAuthenticatedUserSysadmin()) {
                contractInteriorIds.forEach(_contractInteriorBusinessValidator::checkIfContractInteriorAccessibleByAuthUser);
            }
        }, _taskExecutor);
    }

    @Override
    public ContractInterior mapCreationDtoToEntity(ContractInteriorCreationDto contractInteriorCreationDto) {
        return _contractInteriorMapper.mapContractInteriorCreationDtoToContractInterior(contractInteriorCreationDto);
    }

    @Override
    public ContractInterior mapUpdateDtoToEntity(ContractInteriorUpdateDto contractInteriorUpdateDto) {
        return _contractInteriorMapper.mapContractInteriorUpdateDtoToContractInterior(contractInteriorUpdateDto);
    }

    @Override
    public ContractInteriorDto mapEntityToDto(ContractInterior contractInterior) {
        return _contractInteriorMapper.mapContractInteriorToContractInteriorDto(contractInterior);
    }

}
