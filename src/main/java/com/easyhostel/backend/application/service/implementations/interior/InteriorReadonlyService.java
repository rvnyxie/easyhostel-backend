package com.easyhostel.backend.application.service.implementations.interior;

import com.easyhostel.backend.application.dto.interior.InteriorDto;
import com.easyhostel.backend.application.mapping.interfaces.IInteriorMapper;
import com.easyhostel.backend.application.service.implementations.base.BaseReadonlyService;
import com.easyhostel.backend.application.service.interfaces.interior.IInteriorReadonlyService;
import com.easyhostel.backend.domain.entity.Interior;
import com.easyhostel.backend.domain.repository.interfaces.interior.IInteriorRepository;
import org.springframework.security.task.DelegatingSecurityContextAsyncTaskExecutor;
import org.springframework.stereotype.Service;

/**
 * Interior readonly service for implementing abstract methods and define dedicated methods
 *
 * @author Nyx
 */
@Service
public class InteriorReadonlyService extends BaseReadonlyService<Interior, InteriorDto, String> implements IInteriorReadonlyService {

    private final IInteriorMapper _interiorMapper;

    public InteriorReadonlyService(IInteriorRepository interiorRepository,
                                   IInteriorMapper interiorMapper,
                                   DelegatingSecurityContextAsyncTaskExecutor taskExecutor) {
        super(interiorRepository, taskExecutor);
        _interiorMapper = interiorMapper;
    }

    @Override
    public InteriorDto mapEntityToDto(Interior interior) {
        return _interiorMapper.mapInteriorToInteriorDto(interior);
    }
}
