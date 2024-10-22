package com.easyhostel.backend.application.service.implementations.contract;

import com.easyhostel.backend.application.dto.contract.ContractCreationDto;
import com.easyhostel.backend.application.dto.contract.ContractDto;
import com.easyhostel.backend.application.dto.contract.ContractUpdateDto;
import com.easyhostel.backend.application.mapping.interfaces.IContractMapper;
import com.easyhostel.backend.application.service.implementations.base.BaseService;
import com.easyhostel.backend.application.service.interfaces.contract.IContractService;
import com.easyhostel.backend.domain.entity.Contract;
import com.easyhostel.backend.domain.repository.interfaces.base.IBaseRepository;
import com.easyhostel.backend.domain.repository.interfaces.contract.IContractRepository;
import com.easyhostel.backend.domain.repository.interfaces.room.IRoomReadonlyRepository;
import com.easyhostel.backend.domain.service.interfaces.contract.IContractBusinessValidator;
import com.easyhostel.backend.domain.service.interfaces.room.IRoomBusinessValidator;
import org.springframework.security.task.DelegatingSecurityContextAsyncTaskExecutor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Contract modification service for implementing abstract methods and define dedicated methods
 *
 * @author Nyx
 */
@Service
public class ContractService extends BaseService<Contract, ContractDto, ContractCreationDto, ContractUpdateDto, String> implements IContractService {

    private final IContractRepository _contractRepository;
    private final IContractBusinessValidator _contractBusinessValidator;
    private final IContractMapper _contractMapper;

    private final IRoomReadonlyRepository _roomReadonlyRepository;
    private final IRoomBusinessValidator _roomBusinessValidator;

    private final DelegatingSecurityContextAsyncTaskExecutor _taskExecutor;

    public ContractService(IBaseRepository<Contract, String> baseRepository,
                           IContractRepository contractRepository,
                           IContractBusinessValidator contractBusinessValidator,
                           IContractMapper contractMapper,
                           IRoomReadonlyRepository roomReadonlyRepository,
                           IRoomBusinessValidator roomBusinessValidator,
                           DelegatingSecurityContextAsyncTaskExecutor taskExecutor) {
        super(baseRepository, taskExecutor);
        _contractRepository = contractRepository;
        _contractBusinessValidator = contractBusinessValidator;
        _contractMapper = contractMapper;

        _roomReadonlyRepository = roomReadonlyRepository;
        _roomBusinessValidator = roomBusinessValidator;

        _taskExecutor = taskExecutor;
    }

    @Override
    public CompletableFuture<ContractDto> insertAsync(ContractCreationDto contractCreationDto) {
        return validateCreationBusiness(contractCreationDto)
                .thenCompose(v -> CompletableFuture.supplyAsync(() -> {
                    var contract = mapCreationDtoToEntity(contractCreationDto);

                    var room = _roomReadonlyRepository.findById(contractCreationDto.getRoom().getRoomId()).orElseThrow();

                    contract.setRoom(room);

                    return mapEntityToDto(_contractRepository.save(contract));
                }, _taskExecutor));
    }

    @Override
    public CompletableFuture<Void> deleteByIdAsync(String contractId) {
        return validateDeletionBusinessAsync(contractId)
                .thenCompose(v -> CompletableFuture.runAsync(() -> {
                    var contract = _contractRepository.findById(contractId).orElseThrow();

                    contract.setRoom(null);

                    _contractRepository.save(contract);
                    _contractRepository.delete(contract);
                }));
    }

    @Override
    public CompletableFuture<Void> deleteManyByIdsAsync(List<String> contractIds) {
        return validateDeletionManyBusinessAsync(contractIds)
                .thenCompose(v -> CompletableFuture.runAsync(() -> {
                    contractIds.forEach(contractId -> {
                        var contract = _contractRepository.findById(contractId).orElseThrow();

                        contract.setRoom(null);

                        _contractRepository.save(contract);
                        _contractRepository.delete(contract);
                    });
                }));
    }

    @Override
    public CompletableFuture<Void> validateGettingBusinessAsync(String contractId) {
        return CompletableFuture.runAsync(() -> {
            if (!_contractBusinessValidator.checkIsAuthenticatedUserSysadmin()) {
                _contractBusinessValidator.checkIfContractManageableByAuthUser(contractId);
            }
        }, _taskExecutor);
    }

    @Override
    public CompletableFuture<Void> validateCreationBusiness(ContractCreationDto contractCreationDto) {
        return CompletableFuture.runAsync(() -> {
            _contractBusinessValidator.checkIfRoomIdProvidedInContractCreationDto(contractCreationDto);
            _roomBusinessValidator.checkIfRoomExistedById(contractCreationDto.getRoom().getRoomId());

            if (!_contractBusinessValidator.checkIsAuthenticatedUserSysadmin()) {
                _roomBusinessValidator.checkIfRoomSupervisedByAuthUser(contractCreationDto.getRoom().getRoomId());
            }
        }, _taskExecutor);
    }

    @Override
    public CompletableFuture<Void> validateUpdateBusiness(ContractUpdateDto contractUpdateDto) {
        return CompletableFuture.runAsync(() -> {
            if (!_contractBusinessValidator.checkIsAuthenticatedUserSysadmin()) {
                _contractBusinessValidator.checkIfContractManageableByAuthUser(contractUpdateDto.getContractId());
            }
        }, _taskExecutor);
    }

    @Override
    public CompletableFuture<Void> validateDeletionBusinessAsync(String contractId) {
        return CompletableFuture.runAsync(() -> {
            if (!_contractBusinessValidator.checkIsAuthenticatedUserSysadmin()) {
                _contractBusinessValidator.checkIfContractManageableByAuthUser(contractId);
            }
        }, _taskExecutor);
    }

    @Override
    public CompletableFuture<Void> validateDeletionManyBusinessAsync(List<String> contractIds) {
        return CompletableFuture.runAsync(() -> {
            if (!_contractBusinessValidator.checkIsAuthenticatedUserSysadmin()) {
                contractIds.forEach(_contractBusinessValidator::checkIfContractManageableByAuthUser);
            }
        }, _taskExecutor);
    }

    @Override
    public Contract mapCreationDtoToEntity(ContractCreationDto contractCreationDto) {
        return _contractMapper.mapContractCreationDtoToContract(contractCreationDto);
    }

    @Override
    public Contract mapUpdateDtoToEntity(ContractUpdateDto contractUpdateDto) {
        return _contractMapper.mapContractUpdateDtoToContract(contractUpdateDto);
    }

    @Override
    public ContractDto mapEntityToDto(Contract contract) {
        return _contractMapper.mapContractToContractDto(contract);
    }

}
