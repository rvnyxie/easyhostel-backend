package com.easyhostel.backend.application.service.interfaces.repairroomlog;

import com.easyhostel.backend.application.dto.repairroomlog.RepairRoomLogCreationDto;
import com.easyhostel.backend.application.dto.repairroomlog.RepairRoomLogDto;
import com.easyhostel.backend.application.dto.repairroomlog.RepairRoomLogUpdateDto;
import com.easyhostel.backend.application.service.interfaces.base.IBaseService;

/**
 * Interface for RepairRoomLog service, extends base GET, INSERT, UPDATE, DELETE methods
 *
 * @author Nyx
 */
public interface IRepairRoomLogService extends IBaseService<RepairRoomLogDto, RepairRoomLogCreationDto, RepairRoomLogUpdateDto, String> {
}
