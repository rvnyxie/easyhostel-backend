package com.easyhostel.backend.application.dto.contract;

import com.easyhostel.backend.application.dto.base.BaseDtoEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

/**
 * DTO for Contract entity
 *
 * @author Nyx
 */
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
public class ContractDto extends BaseDtoEntity {

    private String contractId;

    private int peopleQuantity;

    private float rentPrice;

    private LocalDate signingDate;

    private LocalDate expirationDate;

}
