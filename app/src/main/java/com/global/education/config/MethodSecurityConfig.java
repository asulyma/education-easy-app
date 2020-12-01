package com.global.education.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

import com.global.education.config.expression.ExtendedMethodSecurityExpressionHandler;
import com.global.education.service.UserDataService;


@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class MethodSecurityConfig extends GlobalMethodSecurityConfiguration {

	@Autowired
	private UserDataService userDataService;

	@Override
	protected MethodSecurityExpressionHandler createExpressionHandler() {
		ExtendedMethodSecurityExpressionHandler handler = new ExtendedMethodSecurityExpressionHandler();
		handler.setUserDataService(userDataService);
		return handler;
	}
}
