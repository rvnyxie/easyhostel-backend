package com.easyhostel.backend.application.dto.permission;

import com.easyhostel.backend.application.dto.base.BaseDtoEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * DTO for Permission entity
 *
 * @author Nyx
 */
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
public class PermissionDto extends BaseDtoEntity {

    private Integer permissionId;

    private String permissionName;

    private String permissionDescription;

}
