package com.education.common.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.IOException;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JacksonUtils {

    private static final ObjectMapper defaultMapper = new ObjectMapper();

    public static String toJsonString(Object object) {
        try {
            return defaultMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Cannot convert to JSON String: " + object, e);
        }
    }

    public static <T> T toObject(String value, Class<T> clazz) {
        try {
            return defaultMapper.readValue(value, clazz);
        } catch (IOException e) {
            throw new IllegalArgumentException("Cannot convert to Object: " + value, e);
        }
    }

    public static <T> T toObject(String value, TypeReference<?> typeReference) {
        try {
            return defaultMapper.readValue(value, typeReference);
        } catch (IOException e) {
            throw new IllegalArgumentException("Cannot convert to Object: " + value, e);
        }
    }
}
