package com.easyhostel.backend.domain.entity.embedded;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

/**
 * Embedded Contract-Vehicle ID entity
 *
 * @author Nyx
 */
@Embeddable
public class ContractVehicleId implements Serializable {

    private String contractId;

    private String vehicleId;

}
