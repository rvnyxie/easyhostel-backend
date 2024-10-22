package com.easyhostel.backend.domain.entity.embedded;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

/**
 * Embedded Contract-Interior ID entity
 *
 * @author Nyx
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Embeddable
public class ContractInteriorId implements Serializable {

    private String contractId;

    private String interiorId;

}
