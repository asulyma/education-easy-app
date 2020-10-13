package com.education.common.utils;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class JacksonUtils {

	private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

	public static String toJsonString(Object object) {
		try {
			return OBJECT_MAPPER.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			throw new IllegalArgumentException("Cannot convert to JSON String: " + object, e);
		}
	}

	public static <T> T toObject(String value, Class<T> clazz) {
		try {
			return OBJECT_MAPPER.readValue(value, clazz);
		} catch (IOException e) {
			throw new IllegalArgumentException("Cannot convert to Object: " + value, e);
		}
	}

	public static <T> T toObject(Object value, TypeReference<T> typeReference) {
		try {
			String map = OBJECT_MAPPER.writeValueAsString(value);
			return OBJECT_MAPPER.readValue(map, typeReference);
		} catch (IOException e) {
			throw new IllegalArgumentException("Cannot convert to Object: " + value, e);
		}
	}
}
