package com.easyhostel.backend.api.controller.repairroomlog;

import com.easyhostel.backend.api.controller.base.BaseController;
import com.easyhostel.backend.application.dto.repairroomlog.RepairRoomLogCreationDto;
import com.easyhostel.backend.application.dto.repairroomlog.RepairRoomLogDto;
import com.easyhostel.backend.application.dto.repairroomlog.RepairRoomLogUpdateDto;
import com.easyhostel.backend.application.service.interfaces.repairroomlog.IRepairRoomLogService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for RepairRoomLog
 *
 * @author Nyx
 */
@RestController
@RequestMapping("${api.base-path}/repair-room-logs")
@Tag(name = "RepairRoomLog Controller")
public class RepairRoomLogController extends BaseController<RepairRoomLogDto, RepairRoomLogCreationDto, RepairRoomLogUpdateDto, String> {

    private final IRepairRoomLogService _repairRoomLogService;

    public RepairRoomLogController(IRepairRoomLogService repairRoomLogService) {
        super(repairRoomLogService);
        _repairRoomLogService = repairRoomLogService;
    }

}
