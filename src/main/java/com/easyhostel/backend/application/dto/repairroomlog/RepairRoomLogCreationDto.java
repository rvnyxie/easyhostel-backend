package com.easyhostel.backend.application.dto.repairroomlog;

import com.easyhostel.backend.application.dto.base.BaseDtoEntity;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

/**
 * DTO for creating RepairRoomLog entity
 *
 * @author Nyx
 */
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
public class RepairRoomLogCreationDto extends BaseDtoEntity {

    private LocalDate repairDate;

    @NotBlank(message = "{validation.repairType.notBlank}")
    private String repairType;

    private String repairDescription;

    @Min(value = 0, message = "{validation.repairCost.notNegative}")
    private float repairCost;

}
