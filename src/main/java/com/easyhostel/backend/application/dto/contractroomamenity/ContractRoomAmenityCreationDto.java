package com.easyhostel.backend.application.dto.contractroomamenity;

import com.easyhostel.backend.application.dto.base.BaseDtoEntity;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * DTO for creating ContractRoomAmenity entity
 *
 * @author Nyx
 */
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
public class ContractRoomAmenityCreationDto extends BaseDtoEntity {

    @NotBlank(message = "{validation.contractId.notBlank}")
    private String contractId;

    @NotBlank(message = "{validation.roomAmenityId.notBlank}")
    private String roomAmenityId;

    @DecimalMin(value = "0.0", message = "{validation.roomAmenityPrice.notNegative}")
    @NotNull(message = "{validation.roomAmenityPrice.notNull}")
    private Float roomAmenityPrice;

}
