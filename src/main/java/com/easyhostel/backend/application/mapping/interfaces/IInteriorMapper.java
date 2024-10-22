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

    //region General

    InteriorDto mapInteriorToInteriorDto(Interior interior);

    //endregion

    //region Map creation

    Interior mapInteriorCreationDtoToInterior(InteriorCreationDto interiorCreationDto);

    //endregion

    //region Map update

    Interior mapInteriorUpdateDtoToInterior(InteriorUpdateDto interiorUpdateDto);

    //endregion

}
