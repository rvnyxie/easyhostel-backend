package com.easyhostel.backend.application.service.interfaces.manager;

import com.easyhostel.backend.application.dto.manager.ManagerCreationDto;
import com.easyhostel.backend.application.dto.manager.ManagerDto;
import com.easyhostel.backend.application.dto.manager.ManagerUpdateDto;
import com.easyhostel.backend.application.service.interfaces.base.IBaseService;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Interface for Manager service, extends base GET, INSERT, UPDATE, DELETE methods
 *
 * @author Nyx
 */
public interface IManagerService extends IBaseService<ManagerDto, ManagerCreationDto, ManagerUpdateDto, UUID> {
}
