package com.easyhostel.backend.application.service.implementations.manager;

import com.easyhostel.backend.application.dto.manager.ManagerCreationDto;
import com.easyhostel.backend.application.dto.manager.ManagerDto;
import com.easyhostel.backend.application.dto.manager.ManagerUpdateDto;
import com.easyhostel.backend.application.mapping.interfaces.IManagerMapper;
import com.easyhostel.backend.application.service.implementations.base.BaseService;
import com.easyhostel.backend.application.service.interfaces.manager.IManagerService;
import com.easyhostel.backend.domain.entity.Manager;
import com.easyhostel.backend.domain.enums.RoleType;
import com.easyhostel.backend.domain.repository.interfaces.manager.IManagerRepository;
import com.easyhostel.backend.domain.repository.interfaces.role.IRoleReadonlyRepository;
import com.easyhostel.backend.domain.service.interfaces.manager.IManagerBusinessValidator;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;

/**
 * Manager service for implementing abstract methods and define dedicated methods
 *
 * @author Nyx
 */
@Service
public class ManagerService extends BaseService<Manager, ManagerDto, ManagerCreationDto, ManagerUpdateDto, String> implements IManagerService {

    private final IManagerRepository _managerRepository;
    private final IManagerBusinessValidator _managerBusinessValidator;
    private final IManagerMapper _managerMapper;

    private final IRoleReadonlyRepository _roleReadonlyRepository;

    public ManagerService(IManagerRepository managerRepository,
                          IManagerBusinessValidator managerBusinessValidator,
                          IManagerMapper managerMapper,
                          IRoleReadonlyRepository roleReadonlyRepository) {
        super(managerRepository);
        _managerRepository = managerRepository;
        _managerBusinessValidator = managerBusinessValidator;
        _managerMapper = managerMapper;
        _roleReadonlyRepository = roleReadonlyRepository;
    }

    @Override
    public CompletableFuture<ManagerDto> insertAsync(ManagerCreationDto managerCreationDto) {
        return validateCreationBusiness(managerCreationDto)
                .thenCompose(v -> CompletableFuture.supplyAsync(() -> {
                    var manager = mapCreationDtoToEntity(managerCreationDto);

                    // Need to check the Role when creating new Manager, because we can't update Role after creating
                    var roleId = manager.getRole().getRoleId();
                    var role = _roleReadonlyRepository.findById(
                            Objects.requireNonNullElse(roleId, RoleType.USER.getRoleId())).orElseThrow();

                    // Set reference to Role
                    manager.setRole(role);

                    // Insert
                    var savedManager = _managerRepository.save(manager);

                    return mapEntityToDto(savedManager);
                }));
    }

    @Override
    public Manager mapCreationDtoToEntity(ManagerCreationDto creationDtoEntity) {
        return _managerMapper.MAPPER.mapManagerCreationDtoToManager(creationDtoEntity);
    }

    @Override
    public Manager mapUpdateDtoToEntity(ManagerUpdateDto updateDtoEntity) {
        return _managerMapper.MAPPER.mapManagerUpdateDtoToManager(updateDtoEntity);
    }

    @Override
    public ManagerDto mapEntityToDto(Manager manager) {
        return _managerMapper.MAPPER.mapManagerToManagerDto(manager);
    }

    @Override
    public CompletableFuture<Void> validateCreationBusiness(ManagerCreationDto managerCreationDto) {
        return CompletableFuture.runAsync(() -> {
            _managerBusinessValidator.checkIfUsernameExistedThenThrowException(managerCreationDto.getUsername());
            _managerBusinessValidator.checkIfEmailExistedThenThrowException(managerCreationDto.getEmail());
        });
    }

    @Override
    public CompletableFuture<Void> validateUpdateBusiness(ManagerUpdateDto managerUpdateDto) {
        return CompletableFuture.runAsync(() -> {
            _managerBusinessValidator.checkIfManagerExistedById(managerUpdateDto.getManagerId());
            _managerBusinessValidator.checkIfEmailNotTakenByManagerThenThrowException(
                    managerUpdateDto.getManagerId(),
                    managerUpdateDto.getEmail());
        });
    }

    @Override
    public CompletableFuture<Void> validateDeletionBusinessAsync(String managerId) {
        return CompletableFuture.runAsync(() -> {
            _managerBusinessValidator.checkIfManagerExistedById(managerId);
        });
    }

}
