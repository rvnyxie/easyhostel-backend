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
import org.springframework.stereotype.Service;

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

    public InteriorService(IInteriorRepository interiorRepository, IInteriorBusinessValidator interiorBusinessValidator, IInteriorMapper interiorMapper) {
        super(interiorRepository);
        _interiorRepository = interiorRepository;
        _interiorBusinessValidator = interiorBusinessValidator;
        _interiorMapper = interiorMapper;
    }

    @Override
    public Interior mapCreationDtoToEntity(InteriorCreationDto interiorCreationDto) {
        var interior = _interiorMapper.MAPPER.mapInteriorCreationDtoToInterior(interiorCreationDto);

        return interior;
    }

    @Override
    public Interior mapUpdateDtoToEntity(InteriorUpdateDto interiorUpdateDto) {
        var interior = _interiorMapper.mapInteriorUpdateDtoToInterior(interiorUpdateDto);

        return interior;
    }

    @Override
    public InteriorDto mapEntityToDto(Interior interior) {
        var interiorDto = _interiorMapper.mapInteriorToInteriorDto(interior);

        return interiorDto;
    }

    // TODO: Add business creation validation for Interior
    @Override
    public CompletableFuture<Void> validateCreationBusiness(InteriorCreationDto interiorCreationDto) {
        return super.validateCreationBusiness(interiorCreationDto);
    }

    // TODO: Add business update validation for Interior
    @Override
    public CompletableFuture<Void> validateUpdateBusiness(InteriorUpdateDto interiorUpdateDto) {
        return super.validateUpdateBusiness(interiorUpdateDto);
    }
}
