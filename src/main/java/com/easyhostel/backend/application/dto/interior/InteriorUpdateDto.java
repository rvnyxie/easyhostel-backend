package com.easyhostel.backend.application.dto.interior;

import com.easyhostel.backend.application.dto.base.BaseDtoEntity;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * DTO for updating Interior entity
 *
 * @author Nyx
 */
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
public class InteriorUpdateDto extends BaseDtoEntity {

    @NotBlank(message = "{validation.interiorId.notBlank}")
    private String interiorId;

    @NotBlank(message = "{validation.interiorName.notBlank}")
    private String interiorName;

}
