package com.easyhostel.backend.application.dto.manager;

import com.easyhostel.backend.application.dto.base.BaseDtoEntity;
import com.easyhostel.backend.application.dto.role.RoleDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * DTO for Manager entity
 *
 * @author Nyx
 */
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
public class ManagerDto extends BaseDtoEntity {

    private String managerId;

    private String username;

    private String email;

    private String avatar;

    private RoleDto role;

}
