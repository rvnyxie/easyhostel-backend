package com.easyhostel.backend.application.mapping.interfaces;

import com.easyhostel.backend.application.dto.managerhouse.ManagerHouseCreationDto;
import com.easyhostel.backend.application.dto.managerhouse.ManagerHouseDto;
import com.easyhostel.backend.application.dto.managerhouse.ManagerHouseUpdateDto;
import com.easyhostel.backend.domain.entity.ManagerHouse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * Mapper for ManagerHouse entity
 *
 * @author Nyx
 */
@Mapper(componentModel = "spring")
public interface IManagerHouseMapper {

    //region Instance

    IManagerHouseMapper MAPPER = Mappers.getMapper(IManagerHouseMapper.class);

    //endregion

    //region General

    @Mapping(source = "managerHouse.managerHouseId.managerId", target = "managerId")
    @Mapping(source = "managerHouse.managerHouseId.houseId", target = "houseId")
    ManagerHouseDto mapManagerHouseToMangerHouseDto(ManagerHouse managerHouse);

    //endregion

    //region Map creation

    ManagerHouse mapManagerHouseCreationDtoToManagerHouse(ManagerHouseCreationDto managerHouseCreationDto);

    //endregion

    //region Map update

    ManagerHouse mapManagerHouseUpdateDtoToManagerHouse(ManagerHouseUpdateDto managerHouseUpdateDto);

    //endregion

}
