package com.easyhostel.backend.api.controller.permission;

import com.easyhostel.backend.api.controller.base.BaseController;
import com.easyhostel.backend.application.dto.permission.PermissionCreationDto;
import com.easyhostel.backend.application.dto.permission.PermissionDto;
import com.easyhostel.backend.application.dto.permission.PermissionUpdateDto;
import com.easyhostel.backend.application.service.interfaces.permission.IPermissionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for Permission
 *
 * @author Nyx
 */
@RestController
@RequestMapping("${api.base-path}/permissions")
@Tag(name = "Permission Controller")
public class PermissionController extends BaseController<PermissionDto, PermissionCreationDto, PermissionUpdateDto, Integer> {

    public PermissionController(IPermissionService permissionService) {
        super(permissionService);
    }

}
