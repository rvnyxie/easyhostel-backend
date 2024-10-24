package com.easyhostel.backend.application.service.implementations.roomamenity;

import com.easyhostel.backend.application.dto.roomamenity.RoomAmenityDto;
import com.easyhostel.backend.application.mapping.interfaces.IRoomAmenityMapper;
import com.easyhostel.backend.application.service.implementations.base.BaseReadonlyService;
import com.easyhostel.backend.application.service.interfaces.roomamenity.IRoomAmenityReadonlyService;
import com.easyhostel.backend.domain.entity.RoomAmenity;
import com.easyhostel.backend.domain.repository.interfaces.roomamenity.IRoomAmenityReadonlyRepository;
import org.springframework.security.task.DelegatingSecurityContextAsyncTaskExecutor;
import org.springframework.stereotype.Service;

/**
 * RoomAmenity readonly service for implementing abstract methods and define dedicated methods
 *
 * @author Nyx
 */
@Service
public class RoomAmenityReadonlyService extends BaseReadonlyService<RoomAmenity, RoomAmenityDto, String> implements IRoomAmenityReadonlyService {

    private final IRoomAmenityMapper _roomAmenityMapper;

    public RoomAmenityReadonlyService(IRoomAmenityReadonlyRepository roomAmenityReadonlyRepository,
                                      IRoomAmenityMapper roomAmenityMapper,
                                      DelegatingSecurityContextAsyncTaskExecutor taskExecutor) {
        super(roomAmenityReadonlyRepository, taskExecutor);
        _roomAmenityMapper = roomAmenityMapper;
    }

    @Override
    public RoomAmenityDto mapEntityToDto(RoomAmenity roomAmenity) {
        return _roomAmenityMapper.mapRoomAmenityToRoomAmenityDto(roomAmenity);
    }
}
