package com.easyhostel.backend.domain.entity;

import com.easyhostel.backend.domain.entity.base.BaseEntity;
import com.easyhostel.backend.domain.entity.embedded.ManagerRoleId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * Manager-Role entity
 *
 * @author Nyx
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Entity
@Table(name = "manager_has_role",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"manager_id", "role_id"})

        })
public class ManagerRole extends BaseEntity {

    @EmbeddedId
    private ManagerRoleId managerRoleId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_id")
    @MapsId("managerId")
    private Manager manager;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    @MapsId("roleId")
    private Role role;

}
