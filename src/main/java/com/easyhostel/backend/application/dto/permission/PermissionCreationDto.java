package com.easyhostel.backend.application.dto.permission;

import com.easyhostel.backend.application.dto.base.BaseDtoEntity;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * DTO for creating Permission entity
 *
 * @author Nyx
 */
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
public class PermissionCreationDto extends BaseDtoEntity {

    @NotBlank(message = "{validation.permissionName.notBlank}")
    private String permissionName;

    @NotBlank(message = "{validation.permissionDescription.notBlank}")
    private String permissionDescription;

}
