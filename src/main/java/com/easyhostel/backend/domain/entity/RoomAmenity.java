package com.easyhostel.backend.domain.entity;

import com.easyhostel.backend.domain.entity.base.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;

/**
 * RoomAmenity entity
 *
 * @author Nyx
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Entity
@Table(name = "room_amenity")
public class RoomAmenity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String roomAmenityId;

    private String roomAmenityType;

    @Column(unique = true)
    private String roomAmenityName;

    @OneToMany(mappedBy = "roomAmenity", fetch = FetchType.EAGER)
    // This one annotation to avoid circular dependency with toString() and hashcode()
    @EqualsAndHashCode.Exclude
    private Set<ContractRoomAmenity> contractRoomAmenities = new HashSet<>();

}
