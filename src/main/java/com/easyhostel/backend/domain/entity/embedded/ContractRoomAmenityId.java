package com.easyhostel.backend.domain.entity.embedded;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

/**
 * Embedded Contract-RoomAmenity ID entity
 *
 * @author Nyx
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Embeddable
public class ContractRoomAmenityId implements Serializable {

    private String contractId;

    private String roomAmenityId;

}
