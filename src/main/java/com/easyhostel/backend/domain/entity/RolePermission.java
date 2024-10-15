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

    @ManyToOne
    @JoinColumn(name = "role_id")
    @MapsId("roleId")
    // This one annotation to avoid circular dependency with toString() and hashcode()
    @EqualsAndHashCode.Exclude
    private Role role;

    @ManyToOne
    @JoinColumn(name = "permission_id")
    @MapsId("permissionId")
    // This one annotation to avoid circular dependency with toString() and hashcode()
    @EqualsAndHashCode.Exclude
    private Permission permission;

}
