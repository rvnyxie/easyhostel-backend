package com.easyhostel.backend.application.service.interfaces.manager;

import com.easyhostel.backend.application.dto.manager.ManagerCreationDto;
import com.easyhostel.backend.application.dto.manager.ManagerDto;
import com.easyhostel.backend.application.dto.manager.ManagerUpdateDto;
import com.easyhostel.backend.application.service.interfaces.base.IBaseService;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Interface for Manager service, extends base GET, INSERT, UPDATE, DELETE methods
 *
 * @author Nyx
 */
public interface IManagerService extends IBaseService<ManagerDto, ManagerCreationDto, ManagerUpdateDto, String> {

    /**
     * Provides a UserDetailsService for handling manager-specific user details
     * in the application. This service is typically used for authentication and retrieving
     * user-related data such as username, password, and authorities.
     *
     * @return UserDetailsService instance that can be used for manager authentication
     * @author Nyx
     */
    UserDetailsService userDetailsService();

}
