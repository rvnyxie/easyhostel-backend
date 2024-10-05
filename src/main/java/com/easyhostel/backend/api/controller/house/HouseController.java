package com.easyhostel.backend.api.controller.house;

import com.easyhostel.backend.api.controller.base.BaseController;
import com.easyhostel.backend.application.dto.house.HouseCreationDto;
import com.easyhostel.backend.application.dto.house.HouseDto;
import com.easyhostel.backend.application.dto.house.HouseUpdateDto;
import com.easyhostel.backend.application.service.interfaces.house.IHouseService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for House
 *
 * @author Nyx
 */
@RestController
@RequestMapping("${api.base-path}/houses")
@Tag(name = "House Controller")
public class HouseController extends BaseController<HouseDto, HouseCreationDto, HouseUpdateDto, String> {

    private final IHouseService _houseService;

    public HouseController(@Qualifier("houseService") IHouseService houseService) {
        super(houseService);
        _houseService = houseService;
    }

}
