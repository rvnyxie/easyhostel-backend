package com.easyhostel.backend.api.controller.roomamenity;

import com.easyhostel.backend.api.controller.base.BaseController;
import com.easyhostel.backend.application.dto.roomamenity.RoomAmenityCreationDto;
import com.easyhostel.backend.application.dto.roomamenity.RoomAmenityDto;
import com.easyhostel.backend.application.dto.roomamenity.RoomAmenityUpdateDto;
import com.easyhostel.backend.application.service.interfaces.roomamenity.IRoomAmenityService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for RoomAmenity
 *
 * @author Nyx
 */
@RestController
@RequestMapping("${api.base-path}/room-amenities")
@Tag(name = "RoomAmenity Controller")
public class RoomAmenityController extends BaseController<RoomAmenityDto, RoomAmenityCreationDto, RoomAmenityUpdateDto, String> {

    private final IRoomAmenityService _roomAmenityService;

    public RoomAmenityController(@Qualifier("roomAmenityService") IRoomAmenityService roomAmenityService) {
        super(roomAmenityService);
        _roomAmenityService = roomAmenityService;
    }

}
