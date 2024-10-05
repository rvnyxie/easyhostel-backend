package com.easyhostel.backend.api.controller.interior;

import com.easyhostel.backend.api.controller.base.BaseController;
import com.easyhostel.backend.application.dto.interior.InteriorCreationDto;
import com.easyhostel.backend.application.dto.interior.InteriorDto;
import com.easyhostel.backend.application.dto.interior.InteriorUpdateDto;
import com.easyhostel.backend.application.service.interfaces.interior.IInteriorService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for Interior
 *
 * @author Nyx
 */
@RestController
@RequestMapping("${api.base-path}/interiors")
@Tag(name = "Interior Controller")
public class InteriorController extends BaseController<InteriorDto, InteriorCreationDto, InteriorUpdateDto, String> {

    private final IInteriorService _interiorService;

    public InteriorController(IInteriorService interiorService) {
        super(interiorService);
        _interiorService = interiorService;
    }

}
