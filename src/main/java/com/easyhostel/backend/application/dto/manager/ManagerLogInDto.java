package com.easyhostel.backend.application.dto.manager;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@EqualsAndHashCode
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ManagerLogInDto {

    @NotBlank(message = "{validation.username.notBlank}")
    private String username;

    @NotBlank(message = "{validation.password.notBlank}")
    private String password;

}
