package com.easyhostel.backend.application.dto.room;

import com.easyhostel.backend.application.dto.base.BaseDtoEntity;
import com.easyhostel.backend.application.dto.contract.ContractDto;
import com.easyhostel.backend.application.dto.house.HouseDto;
import com.easyhostel.backend.domain.entity.Contract;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;

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

    private Set<ContractDto> contracts = new HashSet<>();

}
