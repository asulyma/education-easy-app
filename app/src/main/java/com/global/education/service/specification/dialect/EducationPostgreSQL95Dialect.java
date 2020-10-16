package com.global.education.service.specification.dialect;

import org.hibernate.dialect.PostgreSQL95Dialect;


/**
 * Custom Dialect, which extend PostgreSQL95Dialect and initialize new custom functions.
 */
public class EducationPostgreSQL95Dialect extends PostgreSQL95Dialect {

	public static final String JSONB_CONTAINS = "jsonb_contains";

	public EducationPostgreSQL95Dialect() {
		super();
		registerFunction(JSONB_CONTAINS, new JsonbContainsSQLFunction());
	}
}
