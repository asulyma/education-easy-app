package com.education.common.utils;

import static org.apache.commons.lang3.ObjectUtils.defaultIfNull;
import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.apache.commons.lang3.StringUtils.defaultString;

import java.util.function.Function;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class GetterUtils {

	/**
	 * @param holder - instance of a class.
	 * @param getter - lambda for extracting value.
	 * @param <V>    value bean type
	 * @param <R>    return type
	 * @return object extracted by lambda
	 */
	@Nullable
	public static <V, R> R getValue(@Nullable V holder, @NotNull Function<V, R> getter) {
		return holder != null ? getter.apply(holder) : null;
	}

	/**
	 * @param holder - instance of a class.
	 * @param getter - lambda for extracting string value.
	 * @param <V>    value bean type
	 * @return string extracted by lambda or empty string in case when holder is null or extracted value is null
	 */
	@NotNull
	public static <V> String getString(@Nullable V holder, @NotNull Function<V, String> getter) {
		return holder != null ? defaultString(getter.apply(holder)) : EMPTY;
	}

	@NotNull
	public static <V, R> R getValueOrDefault(@Nullable V holder, @NotNull Function<V, R> getter,
			@NotNull R defaultValue) {
		return holder != null ? defaultIfNull(getter.apply(holder), defaultValue) : defaultValue;
	}

}
