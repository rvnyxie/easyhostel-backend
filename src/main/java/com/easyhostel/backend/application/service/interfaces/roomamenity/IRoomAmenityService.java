package com.easyhostel.backend.application.service.interfaces.roomamenity;

import com.easyhostel.backend.application.dto.roomamenity.RoomAmenityCreationDto;
import com.easyhostel.backend.application.dto.roomamenity.RoomAmenityDto;
import com.easyhostel.backend.application.dto.roomamenity.RoomAmenityUpdateDto;
import com.easyhostel.backend.application.service.interfaces.base.IBaseService;

/**
 * Interface for RoomAmenity service, extends base GET, INSERT, UPDATE, DELETE methods
 *
 * @author Nyx
 */
public interface IRoomAmenityService extends IBaseService<RoomAmenityDto, RoomAmenityCreationDto, RoomAmenityUpdateDto, String> {
}
