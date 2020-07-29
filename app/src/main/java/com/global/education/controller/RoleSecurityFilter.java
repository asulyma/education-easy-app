package com.global.education.controller;

import static com.global.education.utils.UserUtils.currentUserRoles;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import com.global.education.utils.UserUtils;


@Order
@Component
public class RoleSecurityFilter extends GenericFilterBean {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletResponse httpResponse = (HttpServletResponse) response;

		UUID currentUser = UserUtils.currentUserUuid();
		if (currentUser == null && !currentUserRoles().contains("ROLE_CLIENT")) {
			httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			return;
		}
		filterChain.doFilter(request, response);
	}
}
