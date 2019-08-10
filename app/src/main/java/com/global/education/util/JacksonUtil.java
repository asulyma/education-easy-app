package com.global.education.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JacksonUtil {

    private static final ObjectMapper defaultMapper = new ObjectMapper();

    public static String toJsonString(Object object) {
        return toJsonString(defaultMapper, object);
    }

    public static String toJsonString(ObjectMapper mapper, Object object) {
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Cannot convert to JSON String: " + object, e);
        }
    }

}