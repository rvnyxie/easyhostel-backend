package com.easyhostel.backend.domain.entity;

import com.easyhostel.backend.domain.entity.base.BaseEntity;
import com.easyhostel.backend.domain.entity.embedded.ContractInteriorId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contract_id")
    @MapsId("contractId")
    private Contract contract;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "interior_id")
    @MapsId("interiorId")
    private Interior interior;

}
