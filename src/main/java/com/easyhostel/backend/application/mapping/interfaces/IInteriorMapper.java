package com.easyhostel.backend.application.mapping.interfaces;

import com.easyhostel.backend.application.dto.interior.InteriorCreationDto;
import com.easyhostel.backend.application.dto.interior.InteriorDto;
import com.easyhostel.backend.application.dto.interior.InteriorUpdateDto;
import com.easyhostel.backend.domain.entity.Interior;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Mapper for Interior entity
 *
 * @author Nyx
 */
@Mapper(componentModel = "spring")
public interface IInteriorMapper {

    IInteriorMapper MAPPER = Mappers.getMapper(IInteriorMapper.class);

    InteriorDto mapInteriorToInteriorDto(Interior interior);

    Interior mapInteriorCreationDtoToInterior(InteriorCreationDto interiorCreationDto);

    Interior mapInteriorUpdateDtoToInterior(InteriorUpdateDto interiorUpdateDto);

}
