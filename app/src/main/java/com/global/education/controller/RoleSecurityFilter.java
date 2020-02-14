package com.global.education.controller;

import com.global.education.util.UserUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

import static com.global.education.util.UserUtils.currentUserRoles;

@Slf4j
@Order
@Component
public class RoleSecurityFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // TODO Make a flexible solution for public endpoints
        try {
            UUID currentUser = UserUtils.currentUserUuid();
            if (currentUser == null && !currentUserRoles().contains("ROLE_CLIENT")) {
                logger.warn("Unauthorized user, skipped!");
                httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }
        } catch (Exception e) {

        }
        filterChain.doFilter(request, response);
    }
}
