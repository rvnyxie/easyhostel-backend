package com.easyhostel.backend.application.dto.roomservice;

import com.easyhostel.backend.application.dto.base.BaseDtoEntity;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * DTO for updating RoomAmenity entity
 *
 * @author Nyx
 */
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
public class RoomAmenityUpdateDto extends BaseDtoEntity {

    @NotBlank(message = "{validation.roomAmenityId.notBlank}")
    private String roomAmenityId;

    @NotBlank(message = "{validation.roomAmenityType.notBlank}")
    private String roomAmenityType;

    @NotBlank(message = "{validation.roomAmenityName.notBlank}")
    private String roomAmenityName;

}
