package com.easyhostel.backend.infrastructure.configuration;

import com.easyhostel.backend.domain.repository.interfaces.contract.IContractReadonlyRepository;
import com.easyhostel.backend.infrastructure.util.custom.security.expression.CustomMethodSecurityExpressionHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import org.springframework.security.core.Authentication;

import java.io.Serializable;

/**
 * Configuration for MethodSecurity
 *
 * @author Nyx
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class MethodSecurityConfig extends GlobalMethodSecurityConfiguration {

    private final IContractReadonlyRepository _contractReadonlyRepository;

    @Override
    protected MethodSecurityExpressionHandler createExpressionHandler() {
        var expressionHandler = new CustomMethodSecurityExpressionHandler(_contractReadonlyRepository);
        expressionHandler.setPermissionEvaluator(new PermissionEvaluator() {
            @Override
            public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
                return false;
            }

            @Override
            public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
                return false;
            }
        });

        return expressionHandler;
    }
}
