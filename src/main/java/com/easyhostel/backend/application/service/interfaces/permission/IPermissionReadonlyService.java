package com.easyhostel.backend.application.service.interfaces.permission;

import com.easyhostel.backend.application.dto.permission.PermissionDto;
import com.easyhostel.backend.application.service.interfaces.base.IBaseReadonlyService;

/**
 * Interface for Permission service, extends base GET methods
 *
 * @author Nyx
 */
public interface IPermissionReadonlyService extends IBaseReadonlyService<PermissionDto, Integer> {
}
