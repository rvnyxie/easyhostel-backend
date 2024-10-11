package com.easyhostel.backend.domain.entity;

import com.easyhostel.backend.domain.entity.base.BaseEntity;
import com.easyhostel.backend.domain.entity.embedded.ContractInteriorId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * Contract-Interior entity
 *
 * @author Nyx
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Entity
@Table(name = "contract_has_interior",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"contract_id", "interior_id"})

        })
public class ContractInterior extends BaseEntity {

    @EmbeddedId
    private ContractInteriorId contractInteriorId = new ContractInteriorId();

    @ManyToOne
    @JoinColumn(name = "contract_id")
    @MapsId("contractId")
    // This one annotation to avoid circular dependency with toString() and hashcode()
    @EqualsAndHashCode.Exclude
    private Contract contract;

    @ManyToOne
    @JoinColumn(name = "interior_id")
    @MapsId("interiorId")
    // This one annotation to avoid circular dependency with toString() and hashcode()
    @EqualsAndHashCode.Exclude
    private Interior interior;

}
