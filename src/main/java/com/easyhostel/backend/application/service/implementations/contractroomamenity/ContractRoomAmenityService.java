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
import org.springframework.stereotype.Service;

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

    public ContractRoomAmenityService(IContractRoomAmenityRepository contractRoomAmenityRepository,
                                      IContractRoomAmenityBusinessValidator contractRoomAmenityBusinessValidator,
                                      IContractRoomAmenityMapper contractRoomAmenityMapper,
                                      IContractRepository contractRepository,
                                      IRoomAmenityRepository roomAmenityRepository) {
        super(contractRoomAmenityRepository);
        _contractRoomAmenityRepository = contractRoomAmenityRepository;
        _contractRoomAmenityBusinessValidator = contractRoomAmenityBusinessValidator;
        _contractRoomAmenityMapper = contractRoomAmenityMapper;
        _contractRepository = contractRepository;
        _roomAmenityRepository = roomAmenityRepository;
    }

    @Override
    @Async
    public CompletableFuture<ContractRoomAmenityDto> insertAsync(ContractRoomAmenityCreationDto contractRoomAmenityCreationDto) {
        return validateCreationBusiness(contractRoomAmenityCreationDto)
                .thenComposeAsync(v -> CompletableFuture.supplyAsync(() -> {
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
    public CompletableFuture<ContractRoomAmenityDto> updateAsync(ContractRoomAmenityUpdateDto contractRoomAmenityUpdateDto) {
        return validateUpdateBusiness(contractRoomAmenityUpdateDto)
                .thenComposeAsync(v -> CompletableFuture.supplyAsync(() -> {
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
    public CompletableFuture<Void> deleteContractRoomAmenityByIdsAsync(String contractId, String roomAmenityId) {
        return validateDeleteBusiness(contractId, roomAmenityId)
                .thenComposeAsync(v -> CompletableFuture.runAsync(() -> {
                    // Create ContractRoomAmenityId object to search
                    var contractRoomAmenityId = new ContractRoomAmenityId();
                    contractRoomAmenityId.setContractId(contractId);
                    contractRoomAmenityId.setRoomAmenityId(roomAmenityId);

                    // Search
                    var contractRoomAmenity = _contractRoomAmenityRepository.findById(contractRoomAmenityId).orElseThrow();

                    // Saved changes
                    var savedContractRoomAmenity = _contractRoomAmenityRepository.save(contractRoomAmenity);

                    // Delete ContractRoomAmenity
                    _contractRoomAmenityRepository.delete(savedContractRoomAmenity);
                }));
    }

    @Override
    public ContractRoomAmenity mapCreationDtoToEntity(ContractRoomAmenityCreationDto contractRoomAmenityCreationDto) {
        return _contractRoomAmenityMapper.MAPPER.mapContractRoomAmenityCreationDtoToContractRoomAmenity(contractRoomAmenityCreationDto);
    }

    @Override
    public ContractRoomAmenity mapUpdateDtoToEntity(ContractRoomAmenityUpdateDto contractRoomAmenityUpdateDto) {
        return _contractRoomAmenityMapper.MAPPER.mapContractRoomAmenityUpdateDtoToContractRoomAmenity(contractRoomAmenityUpdateDto);
    }

    @Override
    public ContractRoomAmenityDto mapEntityToDto(ContractRoomAmenity contractRoomAmenity) {
        return _contractRoomAmenityMapper.MAPPER.mapContractRoomAmenityToContractRoomAmenityDto(contractRoomAmenity);
    }

    @Override
    @Async
    public CompletableFuture<Void> validateCreationBusiness(ContractRoomAmenityCreationDto contractRoomAmenityCreationDto) {
            var contractId = contractRoomAmenityCreationDto.getContractId();
            var roomAmenityId = contractRoomAmenityCreationDto.getRoomAmenityId();

            return _contractRoomAmenityBusinessValidator.checkIfContractAndRoomAmenityExistedByIdsAsync(contractId, roomAmenityId);
    }

    @Override
    @Async
    public CompletableFuture<Void> validateUpdateBusiness(ContractRoomAmenityUpdateDto contractRoomAmenityUpdateDto) {
            var contractId = contractRoomAmenityUpdateDto.getContractId();
            var roomAmenityId = contractRoomAmenityUpdateDto.getRoomAmenityId();

            return _contractRoomAmenityBusinessValidator.checkIfContractAndRoomAmenityExistedByIdsAsync(contractId, roomAmenityId);
    }

    @Async
    public CompletableFuture<Void> validateDeleteBusiness(String contractId, String roomAmenityId) {
        return _contractRoomAmenityBusinessValidator.checkIfContractAndRoomAmenityExistedByIdsAsync(contractId, roomAmenityId);
    }

}
