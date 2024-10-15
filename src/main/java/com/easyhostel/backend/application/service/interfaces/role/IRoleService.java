package com.easyhostel.backend.application.service.interfaces.role;

import com.easyhostel.backend.application.dto.role.RoleCreationDto;
import com.easyhostel.backend.application.dto.role.RoleDto;
import com.easyhostel.backend.application.dto.role.RoleUpdateDto;
import com.easyhostel.backend.application.service.interfaces.base.IBaseService;

/**
 * Interface for Role service, extends base GET, INSERT, UPDATE, DELETE methods
 *
 * @author Nyx
 */
public interface IRoleService extends IBaseService<RoleDto, RoleCreationDto, RoleUpdateDto, Integer> {
}
