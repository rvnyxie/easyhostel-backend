package com.easyhostel.backend.application.dto.contractvehicle;

import com.easyhostel.backend.application.dto.base.BaseDtoEntity;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * DTO for creating ContractVehicle entity
 *
 * @author Nyx
 */
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
public class ContractVehicleCreationDto extends BaseDtoEntity {

    @NotBlank(message = "{validation.contractId.notBlank}")
    private String contractId;

    @NotBlank(message = "{validation.vehicleId.notBlank}")
    private String vehicleId;

}
