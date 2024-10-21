package com.easyhostel.backend.application.service.implementations.authentication;

import com.easyhostel.backend.application.dto.manager.ManagerDto;
import com.easyhostel.backend.application.dto.manager.ManagerLogInDto;
import com.easyhostel.backend.application.mapping.interfaces.IManagerMapper;
import com.easyhostel.backend.application.service.interfaces.authentication.IAuthenticationService;
import com.easyhostel.backend.domain.repository.interfaces.manager.IManagerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

/**
 * Authentication service
 *
 * @author Nyx
 */
@Service
@RequiredArgsConstructor
public class AuthenticationService implements IAuthenticationService {

    private final AuthenticationManager _authenticationManager;
    private final IManagerRepository _managerRepository;
    private final IManagerMapper _managerMapper;

    @Override
    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    @Override
    public CompletableFuture<ManagerDto> authenticate(ManagerLogInDto managerLogInDto) {
        return CompletableFuture.supplyAsync(() -> {
            _authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            managerLogInDto.getUsername(),
                            managerLogInDto.getPassword()
                    )
            );

            return _managerMapper.MAPPER.mapManagerToManagerDto(
                        _managerRepository.findManagerByUsername(managerLogInDto.getUsername()).orElseThrow());
        });
    }

}
