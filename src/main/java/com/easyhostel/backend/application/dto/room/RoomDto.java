package com.easyhostel.backend.application.dto.room;

import com.easyhostel.backend.application.dto.base.BaseDtoEntity;
import com.easyhostel.backend.application.dto.house.HouseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * DTO for Room entity
 *
 * @author Nyx
 */
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
public class RoomDto extends BaseDtoEntity {

    private String roomId;

    private String roomNumber;

    private String rentPaymentStatus;

    private HouseDto house;

}
