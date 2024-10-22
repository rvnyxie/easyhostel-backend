package com.easyhostel.backend.application.service.implementations.contractroomamenity;

import com.easyhostel.backend.application.dto.contractroomamenity.ContractRoomAmenityCreationDto;
import com.easyhostel.backend.application.dto.contractroomamenity.ContractRoomAmenityDto;
import com.easyhostel.backend.application.dto.contractroomamenity.ContractRoomAmenityUpdateDto;
import com.easyhostel.backend.application.mapping.interfaces.IContractRoomAmenityMapper;
import com.easyhostel.backend.application.service.implementations.base.BaseService;
import com.easyhostel.backend.application.service.interfaces.contractroomamenity.IContractRoomAmenityService;
import com.easyhostel.backend.domain.entity.ContractRoomAmenity;
import com.easyhostel.backend.domain.entity.embedded.ContractRoomAmenityId;
import com.easyhostel.backend.domain.repository.interfaces.contract.IContractRepository;
import com.easyhostel.backend.domain.repository.interfaces.contractroomamenity.IContractRoomAmenityRepository;
import com.easyhostel.backend.domain.repository.interfaces.roomamenity.IRoomAmenityRepository;
import com.easyhostel.backend.domain.service.interfaces.contractroomamenity.IContractRoomAmenityBusinessValidator;
import jakarta.transaction.Transactional;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.task.DelegatingSecurityContextAsyncTaskExecutor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * ContractRoomAmenity modification service for implementing abstract methods and define dedicated methods
 *
 * @author Nyx
 */
@Service
public class ContractRoomAmenityService
        extends BaseService<ContractRoomAmenity, ContractRoomAmenityDto, ContractRoomAmenityCreationDto, ContractRoomAmenityUpdateDto, ContractRoomAmenityId>
        implements IContractRoomAmenityService {

    private final IContractRoomAmenityRepository _contractRoomAmenityRepository;
    private final IContractRoomAmenityBusinessValidator _contractRoomAmenityBusinessValidator;
    private final IContractRoomAmenityMapper _contractRoomAmenityMapper;

    private final IContractRepository _contractRepository;
    private final IRoomAmenityRepository _roomAmenityRepository;

    private final DelegatingSecurityContextAsyncTaskExecutor _taskExecutor;

    public ContractRoomAmenityService(IContractRoomAmenityRepository contractRoomAmenityRepository,
                                      IContractRoomAmenityBusinessValidator contractRoomAmenityBusinessValidator,
                                      IContractRoomAmenityMapper contractRoomAmenityMapper,
                                      IContractRepository contractRepository,
                                      IRoomAmenityRepository roomAmenityRepository,
                                      DelegatingSecurityContextAsyncTaskExecutor taskExecutor) {
        super(contractRoomAmenityRepository, taskExecutor);
        _contractRoomAmenityRepository = contractRoomAmenityRepository;
        _contractRoomAmenityBusinessValidator = contractRoomAmenityBusinessValidator;
        _contractRoomAmenityMapper = contractRoomAmenityMapper;
        _contractRepository = contractRepository;
        _roomAmenityRepository = roomAmenityRepository;
        _taskExecutor = taskExecutor;
    }

    @Override
    @Async
    public CompletableFuture<ContractRoomAmenityDto> insertAsync(ContractRoomAmenityCreationDto contractRoomAmenityCreationDto) {
        return validateCreationBusiness(contractRoomAmenityCreationDto)
                .thenCompose(v -> CompletableFuture.supplyAsync(() -> {
                    var contractRoomAmenity = mapCreationDtoToEntity(contractRoomAmenityCreationDto);

                    var contract = _contractRepository.findById(contractRoomAmenityCreationDto.getContractId())
                            .orElseThrow();
                    var roomAmenity = _roomAmenityRepository.findById(contractRoomAmenityCreationDto.getRoomAmenityId())
                            .orElseThrow();

                    var contractRoomAmenityId = new ContractRoomAmenityId();
                    contractRoomAmenityId.setContractId(contract.getContractId());
                    contractRoomAmenityId.setRoomAmenityId(roomAmenity.getRoomAmenityId());

                    contractRoomAmenity.setContract(contract);
                    contractRoomAmenity.setRoomAmenity(roomAmenity);
                    contractRoomAmenity.setContractRoomAmenityId(contractRoomAmenityId);

                    var savedContractRoomAmenity = _contractRoomAmenityRepository.save(contractRoomAmenity);

                    return mapEntityToDto(savedContractRoomAmenity);
                }));
    }

    @Override
    @Async
    public CompletableFuture<ContractRoomAmenityDto> updateAsync(ContractRoomAmenityUpdateDto contractRoomAmenityUpdateDto) {
        return validateUpdateBusiness(contractRoomAmenityUpdateDto)
                .thenCompose(v -> CompletableFuture.supplyAsync(() -> {
                    var contractRoomAmenity = mapUpdateDtoToEntity(contractRoomAmenityUpdateDto);

                    var contract = _contractRepository.findById(contractRoomAmenityUpdateDto.getContractId())
                            .orElseThrow();
                    var roomAmenity = _roomAmenityRepository.findById(contractRoomAmenityUpdateDto.getRoomAmenityId())
                            .orElseThrow();

                    var contractRoomAmenityId = new ContractRoomAmenityId();
                    contractRoomAmenityId.setContractId(contract.getContractId());
                    contractRoomAmenityId.setRoomAmenityId(roomAmenity.getRoomAmenityId());

                    contractRoomAmenity.setContractRoomAmenityId(contractRoomAmenityId);
                    contractRoomAmenity.setContract(contract);
                    contractRoomAmenity.setRoomAmenity(roomAmenity);

                    var savedContractRoomAmenity = _contractRoomAmenityRepository.save(contractRoomAmenity);

                    return mapEntityToDto(savedContractRoomAmenity);
                }));
    }

    @Override
    @Transactional
    public CompletableFuture<Void> deleteContractRoomAmenityByIdsAsync(ContractRoomAmenityId contractRoomAmenityIdToDelete) {
        return validateDeletionBusinessAsync(contractRoomAmenityIdToDelete)
                .thenCompose(v -> CompletableFuture.runAsync(() -> {
                    _contractRoomAmenityRepository.deleteById(contractRoomAmenityIdToDelete);
                }));
    }

    @Override
    public CompletableFuture<Void> validateGettingBusinessAsync(ContractRoomAmenityId contractRoomAmenityId) {
        return CompletableFuture.runAsync(() -> {
            _contractRoomAmenityBusinessValidator.checkIfContractRoomAmenityExistedById(contractRoomAmenityId);

            if (!_contractRoomAmenityBusinessValidator.checkIsAuthenticatedUserSysadmin()) {
                _contractRoomAmenityBusinessValidator.checkIfContractRoomAmenityAccessibleByAuthUser(contractRoomAmenityId);
            }
        }, _taskExecutor);
    }

    @Override
    @Async
    public CompletableFuture<Void> validateCreationBusiness(ContractRoomAmenityCreationDto contractRoomAmenityCreationDto) {
            var contractId = contractRoomAmenityCreationDto.getContractId();
            var roomAmenityId = contractRoomAmenityCreationDto.getRoomAmenityId();

            var contractRoomAmenityToCreate = ContractRoomAmenityId.builder()
                    .contractId(contractId)
                    .roomAmenityId(roomAmenityId)
                    .build();

            return CompletableFuture.runAsync(() -> {
                _contractRoomAmenityBusinessValidator.checkIfContractAndRoomAmenityExistedByIds(contractId, roomAmenityId);
                _contractRoomAmenityBusinessValidator.checkIfContractRoomAmenityExistedThrowException(contractRoomAmenityToCreate);

                if (!_contractRoomAmenityBusinessValidator.checkIsAuthenticatedUserSysadmin()) {
                    _contractRoomAmenityBusinessValidator.checkIfContractAndRoomAmenityAccessibleByAuthUser(contractId, roomAmenityId);
                }
            }, _taskExecutor);
    }

    @Override
    @Async
    public CompletableFuture<Void> validateUpdateBusiness(ContractRoomAmenityUpdateDto contractRoomAmenityUpdateDto) {
            var contractId = contractRoomAmenityUpdateDto.getContractId();
            var roomAmenityId = contractRoomAmenityUpdateDto.getRoomAmenityId();

            var contractRoomAmenityIdToUpdate = ContractRoomAmenityId.builder()
                    .contractId(contractId)
                    .roomAmenityId(roomAmenityId)
                    .build();

            return CompletableFuture.runAsync(() -> {
                _contractRoomAmenityBusinessValidator.checkIfContractRoomAmenityExistedById(contractRoomAmenityIdToUpdate);

                if (!_contractRoomAmenityBusinessValidator.checkIsAuthenticatedUserSysadmin()) {
                    _contractRoomAmenityBusinessValidator.checkIfContractRoomAmenityAccessibleByAuthUser(contractRoomAmenityIdToUpdate);
                }
            }, _taskExecutor);
    }

    @Override
    public CompletableFuture<Void> validateDeletionBusinessAsync(ContractRoomAmenityId contractRoomAmenityId) {
        return CompletableFuture.runAsync(() -> {
            _contractRoomAmenityBusinessValidator.checkIfContractRoomAmenityExistedById(contractRoomAmenityId);

            if (!_contractRoomAmenityBusinessValidator.checkIsAuthenticatedUserSysadmin()) {
                _contractRoomAmenityBusinessValidator.checkIfContractRoomAmenityAccessibleByAuthUser(contractRoomAmenityId);
            }
        }, _taskExecutor);
    }

    @Override
    public CompletableFuture<Void> validateDeletionManyBusinessAsync(List<ContractRoomAmenityId> contractRoomAmenityIds) {
        return CompletableFuture.runAsync(() -> {
            contractRoomAmenityIds.forEach(_contractRoomAmenityBusinessValidator::checkIfContractRoomAmenityExistedById);

            if (!_contractRoomAmenityBusinessValidator.checkIsAuthenticatedUserSysadmin()) {
                contractRoomAmenityIds.forEach(_contractRoomAmenityBusinessValidator::checkIfContractRoomAmenityAccessibleByAuthUser);
            }
        }, _taskExecutor);
    }

    @Override
    public ContractRoomAmenity mapCreationDtoToEntity(ContractRoomAmenityCreationDto contractRoomAmenityCreationDto) {
        return _contractRoomAmenityMapper.mapContractRoomAmenityCreationDtoToContractRoomAmenity(contractRoomAmenityCreationDto);
    }

    @Override
    public ContractRoomAmenity mapUpdateDtoToEntity(ContractRoomAmenityUpdateDto contractRoomAmenityUpdateDto) {
        return _contractRoomAmenityMapper.mapContractRoomAmenityUpdateDtoToContractRoomAmenity(contractRoomAmenityUpdateDto);
    }

    @Override
    public ContractRoomAmenityDto mapEntityToDto(ContractRoomAmenity contractRoomAmenity) {
        return _contractRoomAmenityMapper.mapContractRoomAmenityToContractRoomAmenityDto(contractRoomAmenity);
    }

}
