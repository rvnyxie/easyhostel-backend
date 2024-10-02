package com.easyhostel.backend.application.dto.base;

import jakarta.persistence.Column;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * Abstract class base DTO entity
 *
 * @author Nyx
 */
@Data
@SuperBuilder
@NoArgsConstructor
public class BaseDtoEntity {

    @Column(updatable = false)
    private String createdBy;

    private String modifiedBy;

}
