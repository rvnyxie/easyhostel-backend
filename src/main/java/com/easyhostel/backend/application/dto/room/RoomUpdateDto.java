package com.easyhostel.backend.application.dto.room;

import com.easyhostel.backend.application.dto.base.BaseDtoEntity;
import com.easyhostel.backend.application.dto.contract.ContractUpdateDto;
import com.easyhostel.backend.application.dto.house.HouseUpdateDto;
import com.easyhostel.backend.domain.entity.Contract;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;

/**
 * DTO for updating Room entity
 *
 * @author Nyx
 */
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
public class RoomUpdateDto extends BaseDtoEntity {

    @NotNull(message = "{validation.roomId.notNull}")
    private String roomId;

    @NotBlank(message = "{validation.roomNumber.notBlank}")
    private String roomNumber;

    @NotBlank(message = "{validation.rentPaymentStatus.notBlank}")
    private String rentPaymentStatus;

    private HouseUpdateDto house;

    private Set<ContractUpdateDto> contracts = new HashSet<>();
}
