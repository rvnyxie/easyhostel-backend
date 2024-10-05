package com.easyhostel.backend.domain.entity;

import com.easyhostel.backend.domain.entity.base.BaseEntity;
import com.easyhostel.backend.domain.entity.embedded.RolePermissionId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * Role-Permission entity
 *
 * @author Nyx
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Entity
@Table(name = "role_has_permission",
        uniqueConstraints = {
            @UniqueConstraint(columnNames = {"role_id", "permission_id"})

})
public class RolePermission extends BaseEntity {

    @EmbeddedId
    private RolePermissionId rolePermissionId = new RolePermissionId();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    @MapsId("roleId")
    private Role role;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "permission_id")
    @MapsId("permissionId")
    private Permission permission;

}
