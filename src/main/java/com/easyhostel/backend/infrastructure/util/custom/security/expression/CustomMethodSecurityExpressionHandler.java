package com.easyhostel.backend.infrastructure.util.custom.security.expression;

import com.easyhostel.backend.domain.repository.interfaces.contract.IContractReadonlyRepository;
import com.easyhostel.backend.domain.repository.interfaces.interior.IInteriorReadonlyRepository;
import lombok.RequiredArgsConstructor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.core.Authentication;

/**
 * Custom method security expression handler
 *
 * @author Nyx
 */
@RequiredArgsConstructor
public class CustomMethodSecurityExpressionHandler extends DefaultMethodSecurityExpressionHandler {

    private final IContractReadonlyRepository _contractReadonlyRepository;

    private final AuthenticationTrustResolver trustResolver = new AuthenticationTrustResolverImpl();

    @Override
    protected MethodSecurityExpressionOperations createSecurityExpressionRoot(Authentication authentication, MethodInvocation invocation) {
        var root = new CustomMethodSecurityExpressionRoot(authentication, _contractReadonlyRepository);
        root.setTrustResolver(trustResolver);
        root.setPermissionEvaluator(getPermissionEvaluator());
        root.setRoleHierarchy(getRoleHierarchy());

        return root;
    }
}
