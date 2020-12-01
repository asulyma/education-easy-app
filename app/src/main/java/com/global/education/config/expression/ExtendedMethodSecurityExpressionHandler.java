package com.global.education.config.expression;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.expression.OAuth2MethodSecurityExpressionHandler;

import com.global.education.service.UserDataService;


public class ExtendedMethodSecurityExpressionHandler extends OAuth2MethodSecurityExpressionHandler {

	private final AuthenticationTrustResolver resolver = new AuthenticationTrustResolverImpl();
	private UserDataService userDataService;

	@Override
	protected MethodSecurityExpressionOperations createSecurityExpressionRoot(Authentication auth, MethodInvocation invocation) {
		MethodSecurityExpressionRoot root = new MethodSecurityExpressionRoot(auth, userDataService);
		root.setThis(invocation.getThis());
		root.setPermissionEvaluator(getPermissionEvaluator());
		root.setTrustResolver(resolver);
		root.setRoleHierarchy(getRoleHierarchy());
		root.setDefaultRolePrefix(getDefaultRolePrefix());
		return root;
	}

	public void setUserDataService(UserDataService userDataService) {
		this.userDataService = userDataService;
	}
}
