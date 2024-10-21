package com.easyhostel.backend.api.controller.authentication;

import com.easyhostel.backend.application.dto.manager.ManagerLogInDto;
import com.easyhostel.backend.application.mapping.interfaces.IManagerMapper;
import com.easyhostel.backend.application.service.interfaces.authentication.IAuthenticationService;
import com.easyhostel.backend.infrastructure.configuration.Translator;
import com.easyhostel.backend.infrastructure.service.interfaces.IJwtService;
import com.easyhostel.backend.infrastructure.util.custom.response.FormattedResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for Authentication
 *
 * @author Nyx
 */
@RestController
@RequestMapping("${api.base-path}/auth")
@Tag(name = "Authentication Controller")
@RequiredArgsConstructor
public class AuthenticationController {

    private final IAuthenticationService _authenticationService;
    private final IJwtService _jwtService;
    private final IManagerMapper _managerMapper;

    /**
     * Login with credentials
     *
     * @param managerLogInDto ManagerLogInDto object
     * @return Full formatted response with token
     * @author Nyx
     */
    @PostMapping("/login")
    public ResponseEntity<FormattedResponse<Object>> authenticate(@RequestBody @Valid ManagerLogInDto managerLogInDto) {
        var authenticatedManagerDto = _authenticationService.authenticate(managerLogInDto).join();

        String jwtToken = _jwtService.generateToken(
                _managerMapper.MAPPER.mapManagerDtoToManager(authenticatedManagerDto)
        );

        Object loginData = new Object() {
            public final String token = jwtToken;
            public final long expiresIn = _jwtService.getExpirationTime();
        };

        var response = new FormattedResponse<>(
                true,
                HttpStatus.OK.value(),
                null,
                Translator.toLocale("login.success"),
                loginData
        );

        return ResponseEntity.ok(response);
    }

}
