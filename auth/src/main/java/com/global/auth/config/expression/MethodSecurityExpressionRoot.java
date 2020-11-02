package com.global.auth.config.expression;

import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.core.Authentication;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class MethodSecurityExpressionRoot extends SecurityExpressionRoot implements MethodSecurityExpressionOperations {

	private Object filterObject;
	private Object returnObject;
	private Object target;

	public MethodSecurityExpressionRoot(Authentication authentication) {
		super(authentication);
	}

	public boolean hasClientRole(String test) {
		return false;
	}

	public boolean hasAdminRole(String test) {
		return true;
	}

	@Override
	public Object getThis() {
		return target;
	}

	public void setThis(Object target) {
		this.target = target;
	}
}
