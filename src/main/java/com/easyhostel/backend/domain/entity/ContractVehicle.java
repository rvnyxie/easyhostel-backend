package com.easyhostel.backend.domain.entity;

import com.easyhostel.backend.domain.entity.base.BaseEntity;
import com.easyhostel.backend.domain.entity.embedded.ContractVehicleId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * Contract-Vehicle entity
 *
 * @author Nyx
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Entity
@Table(name = "contract_has_vehicle",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"contract_id", "vehicle_id"})

        })
public class ContractVehicle extends BaseEntity {

    @EmbeddedId
    private ContractVehicleId contractVehicleId = new ContractVehicleId();

    @ManyToOne
    @JoinColumn(name = "contract_id")
    @MapsId("contractId")
    // This one annotation to avoid circular dependency with toString() and hashcode()
    @EqualsAndHashCode.Exclude
    private Contract contract;

    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    @MapsId("vehicleId")
    // This one annotation to avoid circular dependency with toString() and hashcode()
    @EqualsAndHashCode.Exclude
    private Vehicle vehicle;

}
