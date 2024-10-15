package com.easyhostel.backend.domain.entity.embedded;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
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
@Builder
@Embeddable
public class RolePermissionId implements Serializable {

    private Integer roleId;

    private Integer permissionId;

}
