package com.global.education.utils;

import static java.util.Optional.ofNullable;

import java.io.IOException;
import java.util.*;

import org.apache.commons.codec.binary.Base64;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.global.education.controller.handler.exception.NotAllowedClientOperationRuntimeException;

import lombok.*;


/**
 * Utility class for get User Info from JWT Token
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class UserUtils {

	public static final Long TOTAL_PROGRESS = 1000L;
	public static final String ID_REGEXP = "^[0-9]{1,9}";

	private static final String CLIENT_RESPONSE = "Operation blocked! UserUuid doesn't exist, potentially it's client";

	public static UUID currentUserUuid() {
		Object userUuid = getEncodedUserData().get("userUuid");

		return ofNullable(userUuid).map(String::valueOf)
				.map(UUID::fromString)
				.orElseThrow(() -> new NotAllowedClientOperationRuntimeException(CLIENT_RESPONSE));
	}

	public static String currentUserName() {
		Object userName = getEncodedUserData().get("user_name");

		return ofNullable(userName).map(String::valueOf)
				.orElseThrow(() -> new NotAllowedClientOperationRuntimeException(CLIENT_RESPONSE));
	}

	@SuppressWarnings("unchecked")
	public static List<String> currentUserRoles() {
		Map<String, Object> encodedUserData = getEncodedUserData();
		return (List<String>) encodedUserData.get("authorities");
	}

	private static Map<String, Object> getEncodedUserData() {
		String token = getToken();
		String base64EncodedBody = getBase64EncodedBody(token);
		return getEncodedUserData(base64EncodedBody);
	}

	private static String getToken() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		OAuth2Authentication oAuth2Authentication = (OAuth2Authentication) authentication;
		OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) oAuth2Authentication.getDetails();
		return String.valueOf(details.getTokenValue());
	}

	private static String getBase64EncodedBody(String token) {
		String[] split_string = token.split("\\.");
		String base64EncodedHeader = split_string[0];
		String base64EncodedBody = split_string[1];
		String base64EncodedSignature = split_string[2];
		return base64EncodedBody;
	}

	@SneakyThrows(IOException.class)
	private static Map<String, Object> getEncodedUserData(String base64EncodedBody) {
		String body = new String(new Base64(true).decode(base64EncodedBody));
		TypeReference<Map<String, Object>> typeReference = new TypeReference<Map<String, Object>>() {
		};
		return new ObjectMapper().readValue(body, typeReference);
	}

}
