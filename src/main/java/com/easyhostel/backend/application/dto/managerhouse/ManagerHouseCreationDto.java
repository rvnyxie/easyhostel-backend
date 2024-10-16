package com.easyhostel.backend.application.dto.managerhouse;

import com.easyhostel.backend.application.dto.base.BaseDtoEntity;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * DTO for creating ManagerHouse entity
 *
 * @author Nyx
 */
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
public class ManagerHouseCreationDto extends BaseDtoEntity {

    @NotBlank(message = "{validation.managerId.notBlank}")
    private String managerId;

    @NotBlank(message = "{validation.houseId.notBlank}")
    private String houseId;

}
