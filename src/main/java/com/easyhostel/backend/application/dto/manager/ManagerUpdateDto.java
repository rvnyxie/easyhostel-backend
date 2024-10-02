package com.easyhostel.backend.application.dto.manager;

import com.easyhostel.backend.application.dto.base.BaseDtoEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

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

    private UUID managerId;

    private String username;

    private String email;

    private String password;

    private String avatar;

//    private Set<ManagerRole> managerRoles = new HashSet<>();

}
