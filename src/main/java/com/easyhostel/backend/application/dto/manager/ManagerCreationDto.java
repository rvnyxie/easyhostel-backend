package com.easyhostel.backend.application.dto.manager;

import com.easyhostel.backend.application.dto.base.BaseDtoEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * DTO for creating Manager entity
 *
 * @author Nyx
 */
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
public class ManagerCreationDto extends BaseDtoEntity {

    @NotBlank(message = "{validation.username.notBlank}")
    private String username;

    @NotBlank(message = "{validation.email.notBlank}")
    @Pattern(message = "{validation.email.invalidFormat}", regexp = "^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$")
    private String email;

    @NotBlank(message = "{validation.password.notBlank}")
    private String password;

//    private Set<ManagerRole> managerRoles = new HashSet<>();

}
