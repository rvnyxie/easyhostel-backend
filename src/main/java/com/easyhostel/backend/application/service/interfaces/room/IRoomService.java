package com.easyhostel.backend.application.service.interfaces.room;

import com.easyhostel.backend.application.dto.room.RoomCreationDto;
import com.easyhostel.backend.application.dto.room.RoomDto;
import com.easyhostel.backend.application.dto.room.RoomUpdateDto;
import com.easyhostel.backend.application.service.interfaces.base.IBaseService;

/**
 * Interface for Manager service, extends base GET, INSERT, UPDATE, DELETE methods
 *
 * @author Nyx
 */
public interface IRoomService extends IBaseService<RoomDto, RoomCreationDto, RoomUpdateDto, String> {
}
