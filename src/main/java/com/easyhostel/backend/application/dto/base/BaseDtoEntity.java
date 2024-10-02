package com.easyhostel.backend.application.dto.base;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

/**
 * Abstract class base DTO entity
 *
 * @author Nyx
 */
@Data
@SuperBuilder
@NoArgsConstructor
public class BaseDtoEntity {

    private String createdBy;

    private String modifiedBy;

    private LocalDateTime createdDate;

    private LocalDateTime modifiedDate;

}
