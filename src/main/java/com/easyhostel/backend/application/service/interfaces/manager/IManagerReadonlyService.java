package com.easyhostel.backend.application.service.interfaces.manager;

import com.easyhostel.backend.application.dto.manager.ManagerDto;
import com.easyhostel.backend.application.service.interfaces.base.IBaseReadonlyService;

/**
 * Interface for Manager service, extends base GET methods
 *
 * @author Nyx
 */
public interface IManagerReadonlyService extends IBaseReadonlyService<ManagerDto, String> {
}
