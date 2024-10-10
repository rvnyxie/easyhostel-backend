package com.easyhostel.backend.domain.entity.embedded;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * Embedded Contract-RoomAmenity ID entity
 *
 * @author Nyx
 */
@Getter
@Setter
@NoArgsConstructor
@Embeddable
public class ContractRoomAmenityId implements Serializable {

    private String contractId;

    private String roomAmenityId;

}
