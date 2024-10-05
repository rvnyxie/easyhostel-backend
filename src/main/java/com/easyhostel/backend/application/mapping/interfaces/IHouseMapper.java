package com.easyhostel.backend.application.mapping.interfaces;

import com.easyhostel.backend.application.dto.house.HouseCreationDto;
import com.easyhostel.backend.application.dto.house.HouseDto;
import com.easyhostel.backend.application.dto.house.HouseUpdateDto;
import com.easyhostel.backend.domain.entity.House;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Mapper for House entity
 *
 * @author Nyx
 */
@Mapper(componentModel = "spring")
public interface IHouseMapper {

    IHouseMapper MAPPER = Mappers.getMapper(IHouseMapper.class);

    HouseDto mapHouseToHouseDto(House house);

    House mapHouseCreationDtoToHouse(HouseCreationDto houseCreationDto);

    House mapHouseUpdateDtoToHouse(HouseUpdateDto houseUpdateDto);

}
