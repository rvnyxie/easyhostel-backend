package com.easyhostel.backend.application.service.interfaces.permission;

import com.easyhostel.backend.application.dto.permission.PermissionCreationDto;
import com.easyhostel.backend.application.dto.permission.PermissionDto;
import com.easyhostel.backend.application.dto.permission.PermissionUpdateDto;
import com.easyhostel.backend.application.service.interfaces.base.IBaseService;

/**
 * Interface for Permission service, extends base GET, INSERT, UPDATE, DELETE methods
 *
 * @author Nyx
 */
public interface IPermissionService extends IBaseService<PermissionDto, PermissionCreationDto, PermissionUpdateDto, Integer> {
}
