package com.easyhostel.backend.application.dto.vehicle;

import com.easyhostel.backend.application.dto.base.BaseDtoEntity;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * DTO for updating Vehicle entity
 *
 * @author Nyx
 */
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
public class VehicleUpdateDto extends BaseDtoEntity {

    @NotBlank(message = "{validation.vehicleId.notBlank}")
    private String vehicleId;

    @NotBlank(message = "{validation.vehicleType.notBlank}")
    private String vehicleType;

    private String vehicleModel;

    private String vehicleColor;

    private String vehicleLicensePlate;

}
