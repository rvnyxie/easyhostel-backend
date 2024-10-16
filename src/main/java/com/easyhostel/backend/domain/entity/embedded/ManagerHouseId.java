package com.easyhostel.backend.domain.entity.embedded;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

/**
 * Embedded Manager-House ID entity
 *
 * @author Nyx
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Embeddable
public class ManagerHouseId implements Serializable {

    private String managerId;

    private String houseId;

}
