package com.easyhostel.backend.api.controller.role;

import com.easyhostel.backend.api.controller.base.BaseController;
import com.easyhostel.backend.application.dto.role.RoleCreationDto;
import com.easyhostel.backend.application.dto.role.RoleDto;
import com.easyhostel.backend.application.dto.role.RoleUpdateDto;
import com.easyhostel.backend.application.service.interfaces.role.IRoleService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for Role
 *
 * @author Nyx
 */
@RestController
@RequestMapping("${api.base-path}/roles")
@Tag(name = "Role Controller")
public class RoleController extends BaseController<RoleDto, RoleCreationDto, RoleUpdateDto, Integer> {

    public RoleController(IRoleService roleService) {
        super(roleService);
    }

}
