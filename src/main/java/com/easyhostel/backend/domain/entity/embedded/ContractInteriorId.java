package com.easyhostel.backend.domain.entity.embedded;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

/**
 * Embedded Contract-Interior ID entity
 *
 * @author Nyx
 */
@Embeddable
public class ContractInteriorId implements Serializable {

    private String contractId;

    private String interiorId;

}
