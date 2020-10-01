package com.global.education.controller;

import static com.global.education.utils.UserUtils.currentUserRoles;

import java.io.IOException;
import java.util.Set;
import java.util.UUID;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import com.global.education.utils.UserUtils;
import com.google.common.collect.ImmutableSet;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Order
@Component
public class RoleSecurityFilter extends GenericFilterBean {

	private static final Set<String> ALLOWED_ROLES = ImmutableSet.of("ROLE_CLIENT", "ROLE_USER");

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletResponse httpResponse = (HttpServletResponse) response;

		UUID currentUser = UserUtils.currentUserUuid();
		boolean allowedRole = ALLOWED_ROLES.stream().anyMatch(role -> currentUserRoles().contains(role));

		if (currentUser == null && !allowedRole) {
			logger.info("Unauthorized user, skipped!");
			httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			return;
		}
		filterChain.doFilter(request, response);
	}
}
