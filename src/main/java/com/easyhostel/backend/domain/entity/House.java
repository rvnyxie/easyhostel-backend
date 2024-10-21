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
 * House entity
 *
 * @author Nyx
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Entity
@Table(name = "house")
public class House extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String houseId;

    private String houseName;

    private int roomQuantity;

    private float occupancy;

    @OneToMany(mappedBy = "house", fetch = FetchType.EAGER)
    private Set<ManagerHouse> managerHouses = new HashSet<>();

    @OneToMany(mappedBy = "house", cascade = { CascadeType.PERSIST, CascadeType.REMOVE }, fetch = FetchType.EAGER)
    // This one annotation to avoid circular dependency with toString() and hashcode()
    @EqualsAndHashCode.Exclude
    private Set<Room> rooms = new HashSet<>();

}
