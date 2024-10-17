package com.easyhostel.backend.application.dto.role;

import com.easyhostel.backend.application.dto.base.BaseDtoEntity;
import com.easyhostel.backend.application.dto.manager.ManagerDto;
import com.easyhostel.backend.application.dto.rolepermission.RolePermissionDto;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;

/**
 * DTO for Role entity
 *
 * @author Nyx
 */
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
public class RoleDto extends BaseDtoEntity {

    @Min(value = 1, message = "{validation.roleId.notValidValue}")
    @Max(value = 10, message = "{validation.roleId.notValidValue}")
    private Integer roleId;

    private String roleName;

    private String description;

    private Set<RolePermissionDto> rolePermissions = new HashSet<>();

    private Set<ManagerDto> managers = new HashSet<>();

}
