package com.easyhostel.backend.domain.entity.embedded;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.UUID;

@Embeddable
public class ContractInteriorId implements Serializable {

    private UUID contractId;

    private UUID interiorId;

}
