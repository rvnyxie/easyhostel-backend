package com.easyhostel.backend.application.service.implementations.roomamenity;

import com.easyhostel.backend.application.dto.roomservice.RoomAmenityCreationDto;
import com.easyhostel.backend.application.dto.roomservice.RoomAmenityDto;
import com.easyhostel.backend.application.dto.roomservice.RoomAmenityUpdateDto;
import com.easyhostel.backend.application.mapping.interfaces.IRoomAmenityMapper;
import com.easyhostel.backend.application.service.implementations.base.BaseService;
import com.easyhostel.backend.application.service.interfaces.roomamenity.IRoomAmenityService;
import com.easyhostel.backend.domain.entity.RoomAmenity;
import com.easyhostel.backend.domain.repository.interfaces.roomamenity.IRoomAmenityRepository;
import com.easyhostel.backend.domain.service.interfaces.roomamenity.IRoomAmenityBusinessValidator;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

/**
 * RoomAmenity modification service for implementing abstract methods and define dedicated methods
 *
 * @author Nyx
 */
@Service
public class RoomAmenityService extends BaseService<RoomAmenity, RoomAmenityDto, RoomAmenityCreationDto, RoomAmenityUpdateDto, String> implements IRoomAmenityService {

    private final IRoomAmenityRepository _roomAmenityRepository;
    private final IRoomAmenityBusinessValidator _roomAmenityBusinessValidator;
    private final IRoomAmenityMapper _roomAmenityMapper;

    public RoomAmenityService(IRoomAmenityRepository roomAmenityRepository, IRoomAmenityBusinessValidator roomAmenityBusinessValidator, IRoomAmenityMapper roomAmenityMapper) {
        super(roomAmenityRepository);
        _roomAmenityRepository = roomAmenityRepository;
        _roomAmenityBusinessValidator = roomAmenityBusinessValidator;
        _roomAmenityMapper = roomAmenityMapper;
    }

    @Override
    public RoomAmenity mapCreationDtoToEntity(RoomAmenityCreationDto roomAmenityCreationDto) {
        var roomAmenity = _roomAmenityMapper.MAPPER.mapRoomAmenityCreationDtoToRoomAmenity(roomAmenityCreationDto);

        return roomAmenity;
    }

    @Override
    public RoomAmenity mapUpdateDtoToEntity(RoomAmenityUpdateDto roomAmenityUpdateDto) {
        var roomAmenity = _roomAmenityMapper.MAPPER.mapRoomAmenityUpdateDtoToRoomAmenity(roomAmenityUpdateDto);

        return roomAmenity;
    }

    @Override
    public RoomAmenityDto mapEntityToDto(RoomAmenity roomAmenity) {
        var roomAmenityDto = _roomAmenityMapper.MAPPER.mapRoomAmenityToRoomAmenityDto(roomAmenity);

        return roomAmenityDto;
    }

    // TODO: Add business creation validation for RoomAmenity
    @Override
    public CompletableFuture<Void> validateCreationBusiness(RoomAmenityCreationDto roomAmenityCreationDto) {
        return super.validateCreationBusiness(roomAmenityCreationDto);
    }

    // TODO: Add business update validation for RoomAmenity
    @Override
    public CompletableFuture<Void> validateUpdateBusiness(RoomAmenityUpdateDto roomAmenityUpdateDto) {
        return super.validateUpdateBusiness(roomAmenityUpdateDto);
    }
}
