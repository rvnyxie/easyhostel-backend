package com.easyhostel.backend.api.controller.room;

import com.easyhostel.backend.api.controller.base.BaseController;
import com.easyhostel.backend.application.dto.room.RoomCreationDto;
import com.easyhostel.backend.application.dto.room.RoomDto;
import com.easyhostel.backend.application.dto.room.RoomUpdateDto;
import com.easyhostel.backend.application.service.interfaces.room.IRoomService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for Room
 *
 * @author Nyx
 */
@RestController
@RequestMapping("${api.base-path}/room")
@Tag(name = "Room Controller")
public class RoomController extends BaseController<RoomDto, RoomCreationDto, RoomUpdateDto, String> {

    private final IRoomService _roomService;

    public RoomController(@Qualifier("roomService") IRoomService roomService) {
        super(roomService);
        _roomService = roomService;
    }

}
