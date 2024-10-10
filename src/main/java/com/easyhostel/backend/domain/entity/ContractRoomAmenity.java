package com.easyhostel.backend.domain.entity;

import com.easyhostel.backend.domain.entity.base.BaseEntity;
import com.easyhostel.backend.domain.entity.embedded.ContractRoomAmenityId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * Contract-RoomAmenity entity
 *
 * @author Nyx
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Entity
@Table(name = "contract_has_room_amenity",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"contract_id", "room_amenity_id"})

        })
public class ContractRoomAmenity extends BaseEntity {

    @EmbeddedId
    private ContractRoomAmenityId contractRoomAmenityId = new ContractRoomAmenityId();

    private float roomAmenityPrice;

    @ManyToOne
    @JoinColumn(name = "contract_id", insertable = false, updatable = false)
    @MapsId("contractId")
    // This one annotation to avoid circular dependency with toString() and hashcode()
    @EqualsAndHashCode.Exclude
    private Contract contract;

    @ManyToOne
    @JoinColumn(name = "room_amenity_id", insertable = false, updatable = false)
    @MapsId("roomAmenityId")
    // This one annotation to avoid circular dependency with toString() and hashcode()
    @EqualsAndHashCode.Exclude
    private RoomAmenity roomAmenity;

}
