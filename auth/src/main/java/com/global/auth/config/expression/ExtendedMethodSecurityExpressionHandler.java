package com.global.auth.config.expression;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.core.Authentication;


public class ExtendedMethodSecurityExpressionHandler extends DefaultMethodSecurityExpressionHandler {

	private final AuthenticationTrustResolver resolver = new AuthenticationTrustResolverImpl();

	@Override
	protected MethodSecurityExpressionOperations createSecurityExpressionRoot(Authentication auth, MethodInvocation invocation) {
		MethodSecurityExpressionRoot root = new MethodSecurityExpressionRoot(auth);
		root.setThis(invocation.getThis());
		root.setPermissionEvaluator(getPermissionEvaluator());
		root.setTrustResolver(resolver);
		root.setRoleHierarchy(getRoleHierarchy());
		root.setDefaultRolePrefix(getDefaultRolePrefix());
		return root;
	}
}
