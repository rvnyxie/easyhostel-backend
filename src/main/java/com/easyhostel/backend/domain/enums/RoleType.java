package com.easyhostel.backend.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Type of Role constant values
 *
 * @author Nyx
 */
@Getter
@AllArgsConstructor
public enum RoleType {

    USER(1),
    ADMIN(2),
    SYSADMIN(3);

    private final int roleId;

}
