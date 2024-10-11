package com.easyhostel.backend.application.dto.contractvehicle;

import com.easyhostel.backend.application.dto.base.BaseDtoEntity;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * DTO for updating ContractVehicle entity
 *
 * @author Nyx
 */
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
public class ContractVehicleUpdateDto extends BaseDtoEntity {

    @NotBlank(message = "{validation.contractId.notBlank}")
    private String contractId;

    @NotBlank(message = "{validation.vehicleId.notBlank}")
    private String vehicleId;

    @NotBlank(message = "{validation.oldContractId.notBlank}")
    private String oldContractId;

    @NotBlank(message = "{validation.oldVehicleId.notBlank}")
    private String oldVehicleId;

}
