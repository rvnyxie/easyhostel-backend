package com.easyhostel.backend.application.mapping.interfaces;

import com.easyhostel.backend.application.dto.repairroomlog.RepairRoomLogCreationDto;
import com.easyhostel.backend.application.dto.repairroomlog.RepairRoomLogDto;
import com.easyhostel.backend.application.dto.repairroomlog.RepairRoomLogUpdateDto;
import com.easyhostel.backend.domain.entity.RepairRoomLog;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Mapper for RepairRoomLog entity
 *
 * @author Nyx
 */
@Mapper(componentModel = "spring")
public interface IRepairRoomLogMapper {

    IRepairRoomLogMapper MAPPER = Mappers.getMapper(IRepairRoomLogMapper.class);

    RepairRoomLogDto mapRepairRoomLogToRepairRoomLogDto(RepairRoomLog repairRoomLog);

    RepairRoomLog mapRepairRoomLogCreationDtoToRepairRoomLog(RepairRoomLogCreationDto repairRoomLogCreationDto);

    RepairRoomLog mapRepairRoomLogUpdateDtoToRepairRoomLog(RepairRoomLogUpdateDto repairRoomLogUpdateDto);

}
