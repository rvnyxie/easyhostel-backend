package com.easyhostel.backend.application.dto.role;

import com.easyhostel.backend.application.dto.base.BaseDtoEntity;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * DTO for update Role entity
 *
 * @author Nyx
 */
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
public class RoleUpdateDto extends BaseDtoEntity {

    @NotBlank(message = "{validation.roleId.notBlank}")
    private String roleId;

    @NotBlank(message = "{validation.roleName.notBlank}")
    private String roleName;

    @NotBlank(message = "{validation.roleDescription.notBlank}")
    private String description;

}
