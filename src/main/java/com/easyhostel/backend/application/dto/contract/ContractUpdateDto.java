package com.easyhostel.backend.application.dto.contract;

import com.easyhostel.backend.application.dto.base.BaseDtoEntity;
import com.easyhostel.backend.domain.entity.ContractRoomAmenity;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * DTO for updating Contract entity
 *
 * @author Nyx
 */
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
public class ContractUpdateDto extends BaseDtoEntity {

    @NotBlank(message = "{validation.contractId.notBlank}")
    private String contractId;

    @NotNull(message = "{validation.peopleQuantity.notNull}")
    @Min(value = 1, message = "{validation.peopleQuantity.notSmallerThanOne}")
    private int peopleQuantity;

    @NotNull(message = "{validation.rentPrice.notNull}")
    @Min(value = 0, message = "{validation.rentPrice.notNegative}")
    private float rentPrice;

    // Allow to update to any date
    private LocalDate signingDate;

    // Allow to update to any date
    private LocalDate expirationDate;

//    private Set<ContractRoomAmenity> contractRoomAmenities = new HashSet<>();

}
