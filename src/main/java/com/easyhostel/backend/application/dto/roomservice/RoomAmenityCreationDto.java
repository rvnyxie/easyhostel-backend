package com.easyhostel.backend.application.dto.roomservice;

import com.easyhostel.backend.application.dto.base.BaseDtoEntity;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * DTO for creating RoomAmenity entity
 *
 * @author Nyx
 */
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
public class RoomAmenityCreationDto extends BaseDtoEntity {

    @NotBlank(message = "{validation.roomAmenityType.notBlank}")
    private String roomAmenityType;

    @NotBlank(message = "{validation.roomAmenityName.notBlank}")
    private String roomAmenityName;

}
