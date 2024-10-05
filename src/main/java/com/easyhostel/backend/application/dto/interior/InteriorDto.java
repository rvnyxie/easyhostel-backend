package com.easyhostel.backend.application.dto.interior;

import com.easyhostel.backend.application.dto.base.BaseDtoEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * DTO for Interior entity
 *
 * @author Nyx
 */
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
public class InteriorDto extends BaseDtoEntity {

    private String interiorId;

    private String interiorName;

}
