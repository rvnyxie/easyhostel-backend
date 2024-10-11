package com.easyhostel.backend.application.dto.contractinterior;

import com.easyhostel.backend.application.dto.base.BaseDtoEntity;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * DTO for updating ContractInterior entity
 *
 * @author Nyx
 */
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
public class ContractInteriorUpdateDto extends BaseDtoEntity {

    @NotBlank(message = "{validation.contractId.notBlank}")
    private String contractId;

    @NotBlank(message = "{validation.interiorId.notBlank}")
    private String interiorId;

    @NotBlank(message = "{validation.oldContractID.notBlank}")
    private String oldContractId;

    @NotBlank(message = "{validation.oldInteriorID.notBlank}")
    private String oldInteriorId;

}
