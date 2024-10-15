package com.easyhostel.backend.application.dto.rolepermission;

import com.easyhostel.backend.application.dto.base.BaseDtoEntity;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * DTO for update RolePermission entity
 *
 * @author Nyx
 */
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
public class RolePermissionUpdateDto extends BaseDtoEntity {

    @NotNull(message = "{validation.roleId.notNull}")
    private Integer roleId;

    @NotNull(message = "{validation.permissionId.notNull}")
    private Integer permissionId;

    @NotNull(message = "{validation.oldRoleId.notNull}")
    private Integer oldRoleId;

    @NotNull(message = "{validation.oldPermissionId.notNull}")
    private Integer oldPermissionId;

}
