package com.easyhostel.backend.application.service.implementations.managerhouse;

import com.easyhostel.backend.application.dto.managerhouse.ManagerHouseCreationDto;
import com.easyhostel.backend.application.dto.managerhouse.ManagerHouseDto;
import com.easyhostel.backend.application.dto.managerhouse.ManagerHouseUpdateDto;
import com.easyhostel.backend.application.mapping.interfaces.IManagerHouseMapper;
import com.easyhostel.backend.application.service.implementations.base.BaseService;
import com.easyhostel.backend.application.service.interfaces.managerhouse.IManagerHouseService;
import com.easyhostel.backend.domain.entity.ManagerHouse;
import com.easyhostel.backend.domain.entity.embedded.ManagerHouseId;
import com.easyhostel.backend.domain.repository.interfaces.house.IHouseReadonlyRepository;
import com.easyhostel.backend.domain.repository.interfaces.manager.IManagerReadonlyRepository;
import com.easyhostel.backend.domain.repository.interfaces.managerhouse.IManagerHouseRepository;
import com.easyhostel.backend.domain.service.interfaces.managerhouse.IManagerHouseBusinessValidator;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

/**
 * ManagerHouse modification service for implementing abstract methods and define dedicated methods
 *
 * @author Nyx
 */
@Service
public class ManagerHouseService extends BaseService<ManagerHouse, ManagerHouseDto, ManagerHouseCreationDto, ManagerHouseUpdateDto, ManagerHouseId> implements IManagerHouseService {

    private final IManagerHouseRepository _managerHouseRepository;
    private final IManagerHouseBusinessValidator _managerHouseBusinessValidator;
    private final IManagerHouseMapper _managerHouseMapper;

    private final IManagerReadonlyRepository _managerReadonlyRepository;
    private final IHouseReadonlyRepository _houseReadonlyRepository;

    public ManagerHouseService(IManagerHouseRepository managerHouseRepository,
                               IManagerHouseBusinessValidator managerHouseBusinessValidator,
                               IManagerHouseMapper managerHouseMapper,
                               IManagerReadonlyRepository managerReadonlyRepository,
                               IHouseReadonlyRepository houseReadonlyRepository) {
        super(managerHouseRepository);
        _managerHouseRepository = managerHouseRepository;
        _managerHouseBusinessValidator = managerHouseBusinessValidator;
        _managerHouseMapper = managerHouseMapper;

        _managerReadonlyRepository = managerReadonlyRepository;
        _houseReadonlyRepository = houseReadonlyRepository;
    }

    @Override
    public CompletableFuture<ManagerHouseDto> insertAsync(ManagerHouseCreationDto managerHouseCreationDto) {
        return validateCreationBusiness(managerHouseCreationDto)
                .thenCompose(v -> CompletableFuture.supplyAsync(() -> {
                    var managerHouse = mapCreationDtoToEntity(managerHouseCreationDto);

                    // Get Manager and House
                    var manager = _managerReadonlyRepository.findById(managerHouseCreationDto.getManagerId()).orElseThrow();
                    var house = _houseReadonlyRepository.findById(managerHouseCreationDto.getHouseId()).orElseThrow();

                    // Create ManagerHouseId for inserting
                    var managerHouseId = ManagerHouseId.builder()
                            .managerId(managerHouseCreationDto.getManagerId())
                            .houseId(managerHouseCreationDto.getHouseId())
                            .build();

                    // Set ID and references for ManagerHouse
                    managerHouse.setManagerHouseId(managerHouseId);
                    managerHouse.setManager(manager);
                    managerHouse.setHouse(house);

                    // Insert
                    var savedManagerHouse = _managerHouseRepository.save(managerHouse);

                    return mapEntityToDto(savedManagerHouse);
                }));
    }

    @Override
    public CompletableFuture<ManagerHouseDto> updateAsync(ManagerHouseUpdateDto managerHouseUpdateDto) {
        return validateUpdateBusiness(managerHouseUpdateDto)
                .thenCompose(v -> CompletableFuture.supplyAsync(() -> {
                    // Delete old ManagerHouse
                    var oldManagerHouseId = ManagerHouseId.builder()
                            .managerId(managerHouseUpdateDto.getOldManagerId())
                            .houseId(managerHouseUpdateDto.getOldHouseId())
                            .build();
                    _managerHouseRepository.deleteById(oldManagerHouseId);

                    // Create new ManagerHouse
                    var newManagerHouse = mapUpdateDtoToEntity(managerHouseUpdateDto);

                    // Get Manager and House
                    var manager = _managerReadonlyRepository.findById(managerHouseUpdateDto.getManagerId()).orElseThrow();
                    var house = _houseReadonlyRepository.findById(managerHouseUpdateDto.getHouseId()).orElseThrow();

                    // Create new ManagerHouseId
                    var newManagerHouseId = ManagerHouseId.builder()
                            .managerId(managerHouseUpdateDto.getManagerId())
                            .houseId(managerHouseUpdateDto.getHouseId())
                            .build();

                    // Set ID and references for ManagerHouse
                    newManagerHouse.setManagerHouseId(newManagerHouseId);
                    newManagerHouse.setManager(manager);
                    newManagerHouse.setHouse(house);

                    // Insert
                    var savedManagerHouse = _managerHouseRepository.save(newManagerHouse);

                    return mapEntityToDto(savedManagerHouse);
                }));
    }

    @Override
    public CompletableFuture<Void> deleteByIdAsync(ManagerHouseId managerHouseId) {
        return validateDeletionBusinessAsync(managerHouseId)
                .thenCompose(v -> CompletableFuture.runAsync(() -> {
                    _managerHouseRepository.deleteById(managerHouseId);
                }));
    }

    @Override
    public ManagerHouse mapCreationDtoToEntity(ManagerHouseCreationDto managerHouseCreationDto) {
        return _managerHouseMapper.MAPPER.mapManagerHouseCreationDtoToManagerHouse(managerHouseCreationDto);
    }

    @Override
    public ManagerHouse mapUpdateDtoToEntity(ManagerHouseUpdateDto managerHouseUpdateDto) {
        return _managerHouseMapper.MAPPER.mapManagerHouseUpdateDtoToManagerHouse(managerHouseUpdateDto);
    }

    @Override
    public ManagerHouseDto mapEntityToDto(ManagerHouse managerHouse) {
        return _managerHouseMapper.MAPPER.mapManagerHouseToMangerHouseDto(managerHouse);
    }

    @Override
    public CompletableFuture<Void> validateGettingBusinessAsync(ManagerHouseId managerHouseId) {
        return CompletableFuture.runAsync(() -> {
            _managerHouseBusinessValidator.checkIfManagerHouseExistedById(managerHouseId);
        });
    }

    @Override
    public CompletableFuture<Void> validateCreationBusiness(ManagerHouseCreationDto managerHouseCreationDto) {
        return CompletableFuture.runAsync(() -> {
            _managerHouseBusinessValidator.checkIfManagerAndHouseExistedByIds(
                    managerHouseCreationDto.getManagerId(),
                    managerHouseCreationDto.getHouseId()
            );

            // Check if ManagerHouse not existed before creating
            var managerHouseId = ManagerHouseId.builder()
                    .houseId(managerHouseCreationDto.getHouseId())
                    .managerId(managerHouseCreationDto.getManagerId())
                    .build();

            _managerHouseBusinessValidator.checkIfManagerHouseNotExistedById(managerHouseId);
        });
    }

    @Override
    public CompletableFuture<Void> validateUpdateBusiness(ManagerHouseUpdateDto managerHouseUpdateDto) {
        return CompletableFuture.runAsync(() -> {
            // Check if ManagerHouse already existed for updating
            var oldManagerHouseId = ManagerHouseId.builder()
                    .managerId(managerHouseUpdateDto.getOldManagerId())
                    .houseId(managerHouseUpdateDto.getOldHouseId())
                    .build();

            _managerHouseBusinessValidator.checkIfManagerHouseExistedById(oldManagerHouseId);

            // Check if new Manager and House already existed
            var newManagerId = managerHouseUpdateDto.getManagerId();
            var newHouseId = managerHouseUpdateDto.getHouseId();

            _managerHouseBusinessValidator.checkIfManagerAndHouseExistedByIds(newManagerId, newHouseId);
        });
    }

    @Override
    public CompletableFuture<Void> validateDeletionBusinessAsync(ManagerHouseId managerHouseId) {
        return CompletableFuture.runAsync(() -> {
            _managerHouseBusinessValidator.checkIfManagerHouseExistedById(managerHouseId);
        });
    }
}
