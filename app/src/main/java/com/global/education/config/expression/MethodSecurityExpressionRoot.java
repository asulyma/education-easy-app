package com.global.education.config.expression;

import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.core.Authentication;

import com.education.common.model.Rank;
import com.global.education.model.UserDataEntity;
import com.global.education.service.UserDataService;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class MethodSecurityExpressionRoot extends SecurityExpressionRoot implements MethodSecurityExpressionOperations {

	private UserDataService userDataService;
	private Object filterObject;
	private Object returnObject;
	private Object target;

	public MethodSecurityExpressionRoot(Authentication authentication, UserDataService userDataService) {
		super(authentication);
		this.userDataService = userDataService;
	}

	public boolean hasClientRole(String test) {
		return false;
	}

	public boolean hasSeniorRank() {
		UserDataEntity currentUser = userDataService.findCurrentUser();
		return Rank.SENIOR.equals(currentUser.getRank());
	}

	@Override
	public Object getThis() {
		return target;
	}

	public void setThis(Object target) {
		this.target = target;
	}
}
