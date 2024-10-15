package com.easyhostel.backend.application.dto.role;

import com.easyhostel.backend.application.dto.base.BaseDtoEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

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

    private Integer roleId;

    private String roleName;

    private String description;

}
