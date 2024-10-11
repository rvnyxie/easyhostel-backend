package com.easyhostel.backend.application.dto.contractinterior;

import com.easyhostel.backend.application.dto.base.BaseDtoEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * DTO for ContractInterior entity
 *
 * @author Nyx
 */
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
public class ContractInteriorDto extends BaseDtoEntity {

    private String contractId;

    private String interiorId;

}
