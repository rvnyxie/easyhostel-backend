package com.easyhostel.backend.application.dto.managerhouse;

import com.easyhostel.backend.application.dto.base.BaseDtoEntity;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * DTO for update ManagerHouse entity
 *
 * @author Nyx
 */
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
public class ManagerHouseUpdateDto extends BaseDtoEntity {

    @NotBlank(message = "{validation.managerId.notBlank}")
    private String managerId;

    @NotBlank(message = "{validation.houseId.notBlank}")
    private String houseId;

    @NotBlank(message = "{validation.oldManagerId.notBlank}")
    private String oldManagerId;

    @NotBlank(message = "{validation.oldHouseId.notBlank}")
    private String oldHouseId;

}
