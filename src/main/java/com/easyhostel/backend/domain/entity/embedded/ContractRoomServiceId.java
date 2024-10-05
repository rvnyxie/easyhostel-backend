package com.easyhostel.backend.domain.entity.embedded;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

/**
 * Embedded Contract-Room service ID entity
 *
 * @author Nyx
 */
@Embeddable
public class ContractRoomServiceId implements Serializable {

    private String contractId;

    private String roomServiceId;

}
