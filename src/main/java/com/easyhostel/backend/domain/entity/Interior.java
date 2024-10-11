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
 * Interior entity
 *
 * @author Nyx
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Entity
@Table(name = "interior")
public class Interior extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String interiorId;

    private String interiorName;

    @OneToMany(mappedBy = "interior", fetch = FetchType.EAGER)
    // This one annotation to avoid circular dependency with toString() and hashcode()
    @EqualsAndHashCode.Exclude
    private Set<ContractInterior> contractInteriors = new HashSet<>();

}
