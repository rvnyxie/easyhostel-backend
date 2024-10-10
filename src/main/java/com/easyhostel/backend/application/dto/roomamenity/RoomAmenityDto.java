package com.easyhostel.backend.application.dto.roomamenity;

import com.easyhostel.backend.application.dto.base.BaseDtoEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * DTO for RoomAmenity entity
 *
 * @author Nyx
 */
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
public class RoomAmenityDto extends BaseDtoEntity {

    private String roomAmenityId;

    private String roomAmenityType;

    private String roomAmenityName;

}
