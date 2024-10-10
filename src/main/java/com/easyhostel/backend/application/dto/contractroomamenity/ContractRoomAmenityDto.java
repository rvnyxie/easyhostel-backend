package com.easyhostel.backend.application.dto.contractroomamenity;

import com.easyhostel.backend.application.dto.base.BaseDtoEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * DTO for ContractRoomAmenity entity
 *
 * @author Nyx
 */
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
public class ContractRoomAmenityDto extends BaseDtoEntity {

    private String contractId;

    private String roomAmenityId;

    private float roomAmenityPrice;

}
