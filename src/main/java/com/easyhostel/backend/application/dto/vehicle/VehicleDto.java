package com.easyhostel.backend.application.dto.vehicle;

import com.easyhostel.backend.application.dto.base.BaseDtoEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * DTO for Vehicle entity
 *
 * @author Nyx
 */
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
public class VehicleDto extends BaseDtoEntity {

    private String vehicleId;

    private String vehicleType;

    private String vehicleModel;

    private String vehicleColor;

    private String vehicleLicensePlate;

}
