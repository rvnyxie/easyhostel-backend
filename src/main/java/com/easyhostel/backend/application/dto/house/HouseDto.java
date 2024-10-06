package com.easyhostel.backend.application.dto.house;

import com.easyhostel.backend.application.dto.base.BaseDtoEntity;
import com.easyhostel.backend.application.dto.room.RoomDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;

/**
 * DTO for House entity
 *
 * @author Nyx
 */
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
public class HouseDto extends BaseDtoEntity {

    private String houseId;

    private String houseName;

    private int roomQuantity;

    private float occupancy;

    private Set<RoomDto> rooms = new HashSet<>();

}
