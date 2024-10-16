package com.easyhostel.backend.domain.entity;

import com.easyhostel.backend.domain.entity.base.BaseEntity;
import com.easyhostel.backend.domain.entity.embedded.ManagerHouseId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * Manager-House entity
 *
 * @author Nyx
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Entity
@Table(name = "manager_supervise_house",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"manager_id", "house_id"})

        })
public class ManagerHouse extends BaseEntity {

    @EmbeddedId
    private ManagerHouseId managerHouseId;

    @ManyToOne
    @JoinColumn(name = "manager_id")
    @MapsId("managerId")
    private Manager manager;

    @ManyToOne
    @JoinColumn(name = "house_id")
    @MapsId("houseId")
    private House house;

}
