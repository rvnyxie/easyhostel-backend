package com.easyhostel.backend.application.service.interfaces.rolepermission;

import com.easyhostel.backend.application.dto.rolepermission.RolePermissionDto;
import com.easyhostel.backend.application.service.interfaces.base.IBaseReadonlyService;
import com.easyhostel.backend.domain.entity.embedded.RolePermissionId;

/**
 * Interface for RolePermission service, extends base GET methods
 *
 * @author Nyx
 */
public interface IRolePermissionReadonlyService extends IBaseReadonlyService<RolePermissionDto, RolePermissionId> {
}
