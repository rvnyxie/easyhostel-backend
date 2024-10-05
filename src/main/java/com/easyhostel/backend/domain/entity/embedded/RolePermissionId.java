package com.easyhostel.backend.domain.entity.embedded;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Embedded Role-Permission ID entity
 *
 * @author Nyx
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Embeddable
public class RolePermissionId implements Serializable {

    private String roleId;

    private String permissionId;

}
