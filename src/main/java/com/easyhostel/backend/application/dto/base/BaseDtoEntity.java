package com.easyhostel.backend.application.dto.base;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

/**
 * Abstract class base DTO entity
 *
 * @author Nyx
 */
@Getter
@ToString
@EqualsAndHashCode
@SuperBuilder
@NoArgsConstructor
public class BaseDtoEntity {

    @Setter
    private String createdBy;

    @Setter
    private String modifiedBy;

    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private LocalDateTime createdDate;

    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private LocalDateTime modifiedDate;

}
