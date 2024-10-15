package com.easyhostel.backend.domain.entity.embedded;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Embedded Manager-Role ID entity
 *
 * @author Nyx
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Embeddable
public class ManagerRoleId implements Serializable {

    private String managerId;

    private Integer roleId;

}
