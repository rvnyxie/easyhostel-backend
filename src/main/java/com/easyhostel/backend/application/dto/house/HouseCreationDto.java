package com.easyhostel.backend.application.dto.house;

import com.easyhostel.backend.application.dto.base.BaseDtoEntity;
import com.easyhostel.backend.application.dto.room.RoomCreationDto;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;

/**
 * DTO for creating House entity
 *
 * @author Nyx
 */
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
public class HouseCreationDto extends BaseDtoEntity {

    @NotBlank(message = "{validation.houseName.notBlank}")
    private String houseName;

    private Set<RoomCreationDto> rooms = new HashSet<>();

}
