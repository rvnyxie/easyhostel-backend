package com.easyhostel.backend.domain.entity.embedded;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

/**
 * Embedded Contract-RoomAmenity ID entity
 *
 * @author Nyx
 */
@Embeddable
public class ContractRoomAmenityId implements Serializable {

    private String contractId;

    private String roomAmenityId;

}
