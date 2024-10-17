package com.easyhostel.backend.application.dto.manager;

import com.easyhostel.backend.application.dto.base.BaseDtoEntity;
import com.easyhostel.backend.domain.entity.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * DTO for update Manager entity
 *
 * @author Nyx
 */
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
public class ManagerUpdateDto extends BaseDtoEntity {

    @NotBlank(message = "{validation.managerId.notBlank}")
    private String managerId;

    @NotBlank(message = "{validation.email.notBlank}")
    @Pattern(message = "{validation.email.invalidFormat}", regexp = "^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$")
    private String email;

    @NotBlank(message = "{validation.password.notBlank}")
    private String password;

    private String avatar;

}
