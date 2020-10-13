package com.global.education.IT;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import org.springframework.security.test.context.support.WithSecurityContext;


@Retention(RetentionPolicy.RUNTIME)
@WithSecurityContext(factory = EducationApplicationIT.WithMockOAuth2SecurityContextFactory.class)
public @interface WithMockOAuth2User {

	String username() default "default";

	String name() default "default";
}