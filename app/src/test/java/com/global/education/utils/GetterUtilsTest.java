package com.global.education.utils;

import static com.education.common.utils.GetterUtils.*;
import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.junit.Assert.*;

import org.junit.Test;


public class GetterUtilsTest {

	private static final String TEST_VALUE = "0000000";

	@Test
	public void shouldGetValueIfObjectIsNull() {
		assertNull(getValue(null, Object::toString));
	}

	@Test
	public void shouldGetValueIfObjectIsNotNull() {
		assertNotNull(getValue(TEST_VALUE, Object::toString));
	}

	@Test
	public void shouldGetEmptyStringIfNull() {
		assertEquals(EMPTY, getString(null, Object::toString));
	}

	@Test
	public void shouldGetStringIfNotNull() {
		assertFalse(getString(TEST_VALUE, Object::toString).isEmpty());
	}

	@Test
	public void shouldGetDefaultValueIfNull() {
		assertEquals(TEST_VALUE, getValueOrDefault(null, Object::toString, TEST_VALUE));
	}

	@Test
	public void shouldNotGetDefaultValueIfNotNull() {
		assertNotEquals(TEST_VALUE, getValueOrDefault(EMPTY, Object::toString, TEST_VALUE));
	}

}
