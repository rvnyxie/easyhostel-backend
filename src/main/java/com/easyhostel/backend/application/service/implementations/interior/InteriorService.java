package com.easyhostel.backend.application.service.implementations.interior;

import com.easyhostel.backend.application.dto.interior.InteriorCreationDto;
import com.easyhostel.backend.application.dto.interior.InteriorDto;
import com.easyhostel.backend.application.dto.interior.InteriorUpdateDto;
import com.easyhostel.backend.application.mapping.interfaces.IInteriorMapper;
import com.easyhostel.backend.application.service.implementations.base.BaseService;
import com.easyhostel.backend.application.service.interfaces.interior.IInteriorService;
import com.easyhostel.backend.domain.entity.Interior;
import com.easyhostel.backend.domain.repository.interfaces.interior.IInteriorRepository;
import com.easyhostel.backend.domain.service.interfaces.interior.IInteriorBusinessValidator;
import org.springframework.security.task.DelegatingSecurityContextAsyncTaskExecutor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Interior modification service for implementing abstract methods and define dedicated methods
 *
 * @author Nyx
 */
@Service
public class InteriorService extends BaseService<Interior, InteriorDto, InteriorCreationDto, InteriorUpdateDto, String> implements IInteriorService {

    private final IInteriorRepository _interiorRepository;
    private final IInteriorBusinessValidator _interiorBusinessValidator;
    private final IInteriorMapper _interiorMapper;

    private final DelegatingSecurityContextAsyncTaskExecutor _taskExecutor;

    public InteriorService(IInteriorRepository interiorRepository,
                           IInteriorBusinessValidator interiorBusinessValidator,
                           IInteriorMapper interiorMapper,
                           DelegatingSecurityContextAsyncTaskExecutor taskExecutor) {
        super(interiorRepository, taskExecutor);
        _interiorRepository = interiorRepository;
        _interiorBusinessValidator = interiorBusinessValidator;
        _interiorMapper = interiorMapper;
        _taskExecutor = taskExecutor;
    }

    @Override
    public CompletableFuture<Void> validateCreationBusiness(InteriorCreationDto interiorCreationDto) {
        return CompletableFuture.runAsync(() -> {
            _interiorBusinessValidator.checkIfAuthenticatedUserNotSysadminThrowException();
            _interiorBusinessValidator.checkIfNewInteriorHasDuplicatedName(interiorCreationDto.getInteriorName());
        }, _taskExecutor);
    }

    @Override
    public CompletableFuture<Void> validateUpdateBusiness(InteriorUpdateDto interiorUpdateDto) {
        return CompletableFuture.runAsync(() -> {
            _interiorBusinessValidator.checkIfAuthenticatedUserNotSysadminThrowException();
            _interiorBusinessValidator.checkIfInteriorExistedById(interiorUpdateDto.getInteriorId());
            _interiorBusinessValidator.checkIfUpdateInteriorHasDuplicatedName(
                    interiorUpdateDto.getInteriorId(),
                    interiorUpdateDto.getInteriorName());
        }, _taskExecutor);
    }

    @Override
    public CompletableFuture<Void> validateDeletionBusinessAsync(String interiorId) {
        return CompletableFuture.runAsync(() -> {
            _interiorBusinessValidator.checkIfAuthenticatedUserNotSysadminThrowException();
            _interiorBusinessValidator.checkIfInteriorExistedById(interiorId);
        }, _taskExecutor);
    }

    @Override
    public CompletableFuture<Void> validateDeletionManyBusinessAsync(List<String> interiorIds) {
        return CompletableFuture.runAsync(() -> {
            _interiorBusinessValidator.checkIfAuthenticatedUserNotSysadminThrowException();
            interiorIds.forEach(_interiorBusinessValidator::checkIfInteriorExistedById);
        }, _taskExecutor);
    }

    @Override
    public Interior mapCreationDtoToEntity(InteriorCreationDto interiorCreationDto) {
        return _interiorMapper.mapInteriorCreationDtoToInterior(interiorCreationDto);
    }

    @Override
    public Interior mapUpdateDtoToEntity(InteriorUpdateDto interiorUpdateDto) {
        return _interiorMapper.mapInteriorUpdateDtoToInterior(interiorUpdateDto);
    }

    @Override
    public InteriorDto mapEntityToDto(Interior interior) {
        return _interiorMapper.mapInteriorToInteriorDto(interior);
    }

}
