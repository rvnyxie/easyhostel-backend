package com.easyhostel.backend.application.dto.role;

import com.easyhostel.backend.application.dto.base.BaseDtoEntity;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    @NotNull(message = "{validation.roleId.notNull}")
    @Min(value = 1, message = "{validation.roleId.notValidValue}")
    @Max(value = 10, message = "{validation.roleId.notValidValue}")
    private Integer roleId;

    @NotBlank(message = "{validation.roleName.notBlank}")
    private String roleName;

    @NotBlank(message = "{validation.roleDescription.notBlank}")
    private String description;

}
