package com.easyhostel.backend.application.dto.permission;

import com.easyhostel.backend.application.dto.base.BaseDtoEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * DTO for update Permission entity
 *
 * @author Nyx
 */
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
public class PermissionUpdateDto extends BaseDtoEntity {

    @NotNull(message = "{validation.permissionId.notNull")
    private Integer permissionId;

    @NotBlank(message = "{validation.permissionName.notBlank}")
    private String permissionName;

    @NotBlank(message = "{validation.permissionDescription.notBlank}")
    private String permissionDescription;

}
