package com.easyhostel.backend.application.dto.house;

import com.easyhostel.backend.application.dto.base.BaseDtoEntity;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * DTO for updating House entity
 *
 * @author Nyx
 */
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
public class HouseUpdateDto extends BaseDtoEntity {

    @NotBlank(message = "{validation.houseId.notBlank")
    private String houseId;

    @NotBlank(message = "{validation.houseName.notBlank}")
    private String houseName;

}
