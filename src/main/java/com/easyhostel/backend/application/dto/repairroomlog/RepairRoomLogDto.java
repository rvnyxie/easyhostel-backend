package com.easyhostel.backend.application.dto.repairroomlog;

import com.easyhostel.backend.application.dto.base.BaseDtoEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

/**
 * DTO for RepairRoomLog entity
 *
 * @author Nyx
 */
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
public class RepairRoomLogDto extends BaseDtoEntity {

    private String repairRoomLogId;

    private LocalDate repairDate;

    private String repairType;

    private String repairDescription;

    private float repairCost;

}
