package com.easyhostel.backend.application.dto.room;

import com.easyhostel.backend.application.dto.base.BaseDtoEntity;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * DTO for creating Room entity
 *
 * @author Nyx
 */
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
public class RoomCreationDto extends BaseDtoEntity {

    @NotBlank(message = "{validation.roomNumber.notBlank}")
    private String roomNumber;

    @NotBlank(message = "{validation.rentPaymentStatus.notBlank}")
    private String rentPaymentStatus;

}
