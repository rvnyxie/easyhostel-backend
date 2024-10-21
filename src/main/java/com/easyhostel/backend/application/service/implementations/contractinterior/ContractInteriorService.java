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
                    deleteContractInteriorByIdsAsync(
                            contractInteriorUpdateDto.getOldContractId(),
                            contractInteriorUpdateDto.getOldInteriorId());

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
    public CompletableFuture<Void> deleteContractInteriorByIdsAsync(String contractId, String interiorId) {
        return validateDeletionBusinessAsync(contractId, interiorId)
                .thenComposeAsync(v -> CompletableFuture.runAsync(() -> {
                    var contractInteriorId = new ContractInteriorId();
                    contractInteriorId.setContractId(contractId);
                    contractInteriorId.setInteriorId(interiorId);

                    _contractInteriorRepository.deleteById(contractInteriorId);
                }));
    }

    @Override
    public ContractInterior mapCreationDtoToEntity(ContractInteriorCreationDto contractInteriorCreationDto) {
        return _contractInteriorMapper.MAPPER.mapContractInteriorCreationDtoToContractInterior(contractInteriorCreationDto);
    }

    @Override
    public ContractInterior mapUpdateDtoToEntity(ContractInteriorUpdateDto contractInteriorUpdateDto) {
        return _contractInteriorMapper.MAPPER.mapContractInteriorUpdateDtoToContractInterior(contractInteriorUpdateDto);
    }

    @Override
    public ContractInteriorDto mapEntityToDto(ContractInterior contractInterior) {
        return _contractInteriorMapper.MAPPER.mapContractInteriorToContractInteriorDto(contractInterior);
    }

    @Override
    @Async
    public CompletableFuture<Void> validateCreationBusiness(ContractInteriorCreationDto contractInteriorCreationDto) {
        var contractId = contractInteriorCreationDto.getContractId();
        var interiorId = contractInteriorCreationDto.getInteriorId();

        return CompletableFuture
                .runAsync(() -> _contractInteriorBusinessValidator.checkIfContractAndInteriorExistedByIds(contractId, interiorId));
    }

    @Override
    @Async
    public CompletableFuture<Void> validateUpdateBusiness(ContractInteriorUpdateDto contractInteriorUpdateDto) {
        var oldContractId = contractInteriorUpdateDto.getOldContractId();
        var oldInteriorId = contractInteriorUpdateDto.getOldInteriorId();
        var contractId = contractInteriorUpdateDto.getContractId();
        var interiorId = contractInteriorUpdateDto.getInteriorId();

        return CompletableFuture
                .runAsync(() -> _contractInteriorBusinessValidator.checkIfContractAndInteriorExistedByIds(contractId, interiorId))
                .thenRunAsync(() -> _contractInteriorBusinessValidator.checkIfContractInteriorAlreadyExistedByIds(oldContractId, oldInteriorId));
    }

    @Override
    @Async
    public CompletableFuture<Void> validateDeletionBusinessAsync(String contractId, String interiorId) {
        return CompletableFuture
                .runAsync(() -> _contractInteriorBusinessValidator.checkIfContractInteriorAlreadyExistedByIds(contractId, interiorId));
    }

}
