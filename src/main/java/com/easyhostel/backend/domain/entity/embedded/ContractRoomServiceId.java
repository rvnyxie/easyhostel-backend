package com.easyhostel.backend.domain.entity.embedded;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.UUID;

@Embeddable
public class ContractRoomServiceId implements Serializable {

    private UUID contractId;

    private UUID roomServiceId;

}
