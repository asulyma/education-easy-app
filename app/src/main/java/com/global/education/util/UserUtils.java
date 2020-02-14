package com.global.education.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.apache.commons.codec.binary.Base64;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Utility class for get User Info from JWT Token
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class UserUtils {

    public static final Long TOTAL_PROGRESS = 1000L;

    public static UUID currentUserUuid() {
        String base64EncodedBody = getBase64EncodedBody(getToken());
        Map<String, Object> encodedUserData = getEncodedUserData(base64EncodedBody);
        Object userUuid = encodedUserData.get("userUuid");
        return userUuid != null
                ? UUID.fromString(String.valueOf(userUuid))
                : null;
    }

    public static String currentUserName() {
        String base64EncodedBody = getBase64EncodedBody(getToken());
        Map<String, Object> encodedUserData = getEncodedUserData(base64EncodedBody);
        return String.valueOf(encodedUserData.get("user_name"));
    }

    @SuppressWarnings("unchecked")
    public static List<String> currentUserRoles() {
        String base64EncodedBody = getBase64EncodedBody(getToken());
        Map<String, Object> encodedUserData = getEncodedUserData(base64EncodedBody);
        return (List<String>) encodedUserData.get("authorities");
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
