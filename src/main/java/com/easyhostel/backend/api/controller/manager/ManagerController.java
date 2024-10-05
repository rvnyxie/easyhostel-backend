package com.easyhostel.backend.api.controller.manager;

import com.easyhostel.backend.api.controller.base.BaseController;
import com.easyhostel.backend.application.dto.manager.ManagerCreationDto;
import com.easyhostel.backend.application.dto.manager.ManagerDto;
import com.easyhostel.backend.application.dto.manager.ManagerUpdateDto;
import com.easyhostel.backend.application.service.interfaces.manager.IManagerService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * Controller for Manager
 *
 * @author Nyx
 */
@RestController
@RequestMapping("${api.base-path}/manager")
@Tag(name = "Manager Controller")
public class ManagerController extends BaseController<ManagerDto, ManagerCreationDto, ManagerUpdateDto, UUID> {

    private final IManagerService _managerService;

    public ManagerController(@Qualifier("managerService") IManagerService managerService) {
        super(managerService);
        _managerService = managerService;
    }

}
