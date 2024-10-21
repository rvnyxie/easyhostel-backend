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

    USER(3),
    ADMIN(2),
    SYSADMIN(1);

    private final int roleId;

}
