package com.easyhostel.backend.application.service.interfaces.authentication;

import com.easyhostel.backend.application.dto.manager.ManagerDto;
import com.easyhostel.backend.application.dto.manager.ManagerLogInDto;

import java.util.concurrent.CompletableFuture;

/**
 * Interface for authentication service
 *
 * @author Nyx
 */
public interface IAuthenticationService {

    /**
     * Authenticate user with credentials provided
     *
     * @param managerLogInDto ManagerLoginDto object
     * @return ManagerDto object
     * @author Nyx
     */
    CompletableFuture<ManagerDto> authenticate(ManagerLogInDto managerLogInDto);

}
