package com.easyhostel.backend.application.dto.rolepermission;

import com.easyhostel.backend.application.dto.base.BaseDtoEntity;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * DTO for creating RolePermission entity
 *
 * @author Nyx
 */
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
public class RolePermissionCreationDto extends BaseDtoEntity {

    @NotNull(message = "{validation.roleId.notNull}")
    private Integer roleId;

    @NotNull(message = "{validation.permissionId.notNull}")
    private Integer permissionId;

}
